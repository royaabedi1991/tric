package com.a3rick.a3rick.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.a3rick.a3rick.R;
import com.a3rick.a3rick.models.GetSubResult;
import com.a3rick.a3rick.webService.APIClient;
import com.a3rick.a3rick.webService.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    private int ServiceId = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final SharedPreferences prefs;
        prefs = getApplicationContext().getSharedPreferences("MyPref", 0);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String MobileNumber = prefs.getString("PHONENUMBER", "");
                if (MobileNumber != null && !MobileNumber.equals("")) {
                    getSubRequest(MobileNumber, ServiceId);

                } else {
                    startActivity(new Intent(SplashActivity.this, RegisterActivity.class));
                    finish();
                }


                finish();

            }
        }, 3000);


    }

    private void getSubRequest(String MobileNumber, int ServiceId) {

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<GetSubResult> call = apiInterface.getSubRequest(MobileNumber, ServiceId);
        call.enqueue(new Callback<GetSubResult>() {
            @Override
            public void onResponse(Call<GetSubResult> call, Response<GetSubResult> response) {

                GetSubResult apiResponse = response.body();
                if (apiResponse.getIsSuccessful() == true && apiResponse.getResult() == true)  {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();

                } else if (apiResponse.getIsSuccessful() == true && apiResponse.getResult() == false) {

                    startActivity(new Intent(SplashActivity.this, RegisterActivity.class));
                    finish();

                }


            }

            @Override
            public void onFailure(Call<GetSubResult> call, Throwable t) {

            }
        });


    }


}
