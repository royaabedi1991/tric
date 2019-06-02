package com.a3rick.a3rick.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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
    private EditText etMobileNumber;
    private TextView massage;
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
        etMobileNumber = findViewById(R.id.etNumber);
        massage = findViewById(R.id.TVmsg);
        register = findViewById(R.id.btnRegister);
        ServiceId = 11;
        Channel = "AnyText";


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("PHONENUMBER", String.valueOf(etMobileNumber.getText()));
                editor.apply();
                registerUser(String.valueOf(etMobileNumber.getText()), ServiceId, Channel);

            }
        });


        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
  editor = pref.edit();


    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void registerUser(final String MobileNumber, int ServiceId, String Channel) {
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
                        Toast.makeText(getApplicationContext(), "لطفا بعد از چند دقیقه مجدد تلاش کنید", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResult> call, Throwable t) {
                Log.e("error", t.toString());

            }
        });
//        Call<ApiResult> call1 =   apiInterface.subscribeUser("11",1169921,3138);
//        call1.enqueue(new Callback<ApiResult>() {
//            @Override
//            public void onResponse(Call<ApiResult> call, Response<ApiResult> response) {
//                Log.e("fdfa","Fdfsa");
//            }
//
//            @Override
//            public void onFailure(Call<ApiResult> call, Throwable t) {
//                Log.e("fdfa","Fdfsa");
//            }
//        });
//        call.enqueue(new Callback<ApiResult>() {
//            @Override
//            public void onResponse(Call<ApiResult> call, Response<ApiResult> response) {
//
//                if (response.body().getIsSuccessful() == true & (response.body().getResult() > 0)) {
//                    Intent intent = new Intent(RegisterActivity.this, SubscribeActivity.class);
//                    intent.putExtra("RESULT", response.body().getResult());
//                    startActivity(intent);
//                    Toast.makeText(RegisterActivity.this, "درخواست عضویت با موفقیت انجام شد "
//                            , Toast.LENGTH_LONG).show();
//
//
//                } else if (response.body().getIsSuccessful() == false & (response.body().getResult() == 0)) {
//                    massage.setText("این سرویس برای مشترکین همراه اول میباشد");
//                } else if
//                        (response.body().getIsSuccessful() == true & (response.body().getResult() == -1)) {
//                    getConfirmviaCode();
//                }
//
//            }
//
//
//            @Override
//            public void onFailure(Call<ApiResult> call, Throwable t) {
//                Log.e("tag", t.toString());
//            }
//        })


//        ;
    }


}