package com.a3rick.a3rick.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.a3rick.a3rick.R;
import com.a3rick.a3rick.models.ApiModels.OTP.ApiResult;
import com.a3rick.a3rick.webService.OTP.APIClient;
import com.a3rick.a3rick.webService.OTP.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RegisterActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private EditText etMobileNumber;
    private TextView textView;
    private Button register;
    private int ServiceId;
    private String Channel;
    Boolean isSucessful;
    Long result;
    SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        progressBar=findViewById(R.id.pr_register);

        etMobileNumber = findViewById(R.id.etNumber);
        textView = findViewById(R.id.tv_terms);
        register = findViewById(R.id.btnRegister);
//        btnTerms=findViewById(R.id.btn_terms);
        ServiceId = 11;
        Channel = "AnyText";

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent intent = new Intent (RegisterActivity.this, DialogSharayetActivity.class);
               startActivity(intent);

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator();

            }
        });


        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();


    }
    public void validator(){
        if(!etMobileNumber.getText().toString().equals("")){

            editor.putString("PHONENUMBER", String.valueOf(etMobileNumber.getText()));
            editor.apply();
            registerUser(String.valueOf(etMobileNumber.getText()), ServiceId, Channel);

        }else {
            Toast.makeText(this, "شماره صحیح وارد نشده است", Toast.LENGTH_SHORT).show();
           // etMobileNumber.setError("شماره صحیح وارد نشده است");
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void registerUser(final String MobileNumber, int ServiceId, String Channel) {
        progressBar.setVisibility(View.VISIBLE);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ApiResult> call = apiInterface.registerUser(MobileNumber, ServiceId, Channel);
        call.enqueue(new Callback<ApiResult>() {
            @Override
            public void onResponse(Call<ApiResult> call, Response<ApiResult> response) {
                if (response.isSuccessful()) {
                    isSucessful = response.body().getIsSuccessful();
                    result = response.body().getResult();


                    if (isSucessful == true &  result> 0) {
                        Intent intent = new Intent(RegisterActivity.this, SubscribeActivity.class);
                        finish();
                        intent.putExtra("NUMBER", etMobileNumber.getText().toString());
                        intent.putExtra("TRANSACTIONID", response.body().getResult().toString());
                        intent.putExtra("FROMWHER", "RESULT");

                        startActivity(intent);
                        Toast.makeText(RegisterActivity.this, "درخواست عضویت با موفقیت انجام شد "
                                , Toast.LENGTH_LONG).show();

                    } else if (isSucessful == true & result == -1) {
                        Intent intent = new Intent(RegisterActivity.this, SubscribeActivity.class);
                        finish();
                        intent.putExtra("NUMBER", etMobileNumber.getText().toString());
                        intent.putExtra("TRANSACTIONID", response.body().getMessage().toString());
                        intent.putExtra("FROMWHER", "MESSAGE");

                        startActivity(intent);
                    } else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "لطفا بعد از چند دقیقه مجدد تلاش کنید", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResult> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "لطفا بعد از چند دقیقه مجدد تلاش کنید", Toast.LENGTH_LONG).show();

            }
        });





    }

    private PopupWindow pw;


//    private void initiatePopupWindow() {
//        RelativeLayout relativeLayout = findViewById(R.id.bac_dim_layout);
//        try {relativeLayout.setVisibility(View.VISIBLE);
//            //We need to get the instance of the LayoutInflater, use the context of this activity
//            LayoutInflater inflater = (LayoutInflater) RegisterActivity.this
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            //Inflate the view from a predefined XML layout
//            View layout = inflater.inflate(R.layout.popup_terms,
//                    (ViewGroup) findViewById(R.id.popup_element));
//            // create a 300px width and 470px height PopupWindow
//            pw = new PopupWindow(layout,500, 200, false);
//            // display the popup in the center
//            pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
//            pw.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//            TextView cancelButton = layout.findViewById(R.id.end_data_send_button);
//            cancelButton.setOnClickListener(cancel_button_click_listener);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//
//    private View.OnClickListener cancel_button_click_listener = new View.OnClickListener() {
//        public void onClick(View v) {
//            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.bac_dim_layout);
//            relativeLayout.setVisibility(View.GONE);
//            pw.dismiss();
//        }
//    };
}