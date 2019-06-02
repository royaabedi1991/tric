package com.a3rick.a3rick.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.a3rick.a3rick.R;
import com.a3rick.a3rick.models.ApiModels.OTP.ApiResult;
import com.a3rick.a3rick.webService.OTP.APIClient;
import com.a3rick.a3rick.webService.OTP.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubscribeActivity extends AppCompatActivity/* implements View.OnClickListener*/ {


    private EditText etPin;
    private TextView Emassage;
    private Button subscribe;
    private Button btnSubscribe;
    private String ServiceId;
    private String TransactionId;
    private Long  mobileNumber;
    private String result;
    private String message;
    private String fromWhere;
    private ImageView logo;
    private Long ressult;
    private Boolean isSucssesful;

    public SubscribeActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_subscribe);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Intent intent = getIntent();
        TransactionId = intent.getStringExtra("TRANSACTIONID");
        mobileNumber = Long.valueOf(intent.getStringExtra("NUMBER"));
//        message = intent.getStringExtra("MASSAGE");
//        result = (intent.getStringExtra("RESULT"));
        fromWhere = (intent.getStringExtra("FROMWHER"));

        setContentView(R.layout.activity_subscribe);
        etPin = findViewById(R.id.etPin);
        Emassage = findViewById(R.id.TVmasege);
        subscribe = findViewById(R.id.sub);
        ServiceId = "11";
        subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fromWhere.equals("RESULT")) {
                    subscribeUser(ServiceId, TransactionId, String.valueOf(etPin.getText()));
                } else if (fromWhere.equals("MESSAGE")) {
                    subscribeUserViaCode(ServiceId, TransactionId, String.valueOf(etPin.getText()));
                }

            }
        });


    }

    private void subscribeUser(String ServiceId, String TransactionId, String Pin) {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ApiResult> call = apiInterface.subscribeUser(ServiceId, TransactionId, etPin.getText().toString());
        call.enqueue(new Callback<ApiResult>() {
            @Override
            public void onResponse(Call<ApiResult> call, Response<ApiResult> response) {


                ApiResult apiResponse = new ApiResult();
                apiResponse = response.body();
                if (apiResponse.getIsSuccessful() == true && apiResponse.getResult().equals(mobileNumber)) {

                    Intent intent = new Intent(SubscribeActivity.this, MainActivity.class);
                    intent.putExtra("PHONE", String.valueOf(mobileNumber));
                    startActivity(intent);
                    finish();

                    finish();
                    Toast.makeText(SubscribeActivity.this,
                            "عضویت با موفقیت انجام شد "

                            , Toast.LENGTH_LONG).show();
                } else if (apiResponse.getIsSuccessful() == false && apiResponse.getResult() == 0) {

                    Emassage.setText("خطا، لطفا کد 4 رقمی ای که توسط پیامک دریافت کرده اید را مجددا وارد کرده و بعد از چند لحظه مجددا تلاش کنید");

                } else {
                    Emassage.setText("خطا");
                }


            }


            @Override
            public void onFailure(Call<ApiResult> call, Throwable t) {
                Emassage.setText(t.toString());
                Emassage.setText(t.toString());
            }
        });

    }

    private void subscribeUserViaCode(String ServiceId, String TransactionId, String Pin) {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ApiResult> call = apiInterface.subscribeUserViaCode(ServiceId, TransactionId, Pin);
        call.enqueue(new Callback<ApiResult>() {
            @Override
            public void onResponse(Call<ApiResult> call, Response<ApiResult> response) {

                ApiResult apiResponse = response.body();

                if (apiResponse.getIsSuccessful() == true && apiResponse.getResult().equals(mobileNumber)) {
                    Intent intent = new Intent(SubscribeActivity.this, MainActivity.class);
                    intent.putExtra("PHONE", String.valueOf(mobileNumber));

                    startActivity(intent);
                    finish();
                    Toast.makeText(SubscribeActivity.this,
                            "عضویت با موفقیت انجام شد "

                            , Toast.LENGTH_LONG).show();
                } else if (apiResponse.getIsSuccessful() == false && apiResponse.getResult() == -2) {

                    Emassage.setText("خطای سیستمی، لطفا مجدد تلاش کنید");
                } else if (apiResponse.getIsSuccessful() == false && apiResponse.getResult() == -4) {
                    Emassage.setText("پین کد وارد شده اشتباه است، لطفا دوباره تلاش کنید");
                } else {
                    Emassage.setText("مشترک گرامی لطفا بعد از چند دقیقه دوباره تلاش کنید");
                }
            }

            @Override
            public void onFailure(Call<ApiResult> call, Throwable t) {
                Emassage.setText(t.toString());
            }
        });

    }


}
