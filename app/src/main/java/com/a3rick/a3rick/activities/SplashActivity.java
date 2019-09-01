package com.a3rick.a3rick.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.a3rick.a3rick.R;
import com.a3rick.a3rick.models.models.OTP.GetSubResult;
import com.a3rick.a3rick.webService.OTP.APIClient;
import com.a3rick.a3rick.webService.OTP.APIInterface;

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






        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkConnection();



//                finish();

            }
        }, 4000);




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

                else
                    Toast.makeText(SplashActivity.this, "خطا", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<GetSubResult> call, Throwable t) {

            }
        });


    }


  protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }


    public void checkConnection(){

        if(isOnline()){
            final SharedPreferences prefs;
            prefs = getApplicationContext().getSharedPreferences("MyPref", 0);
            String MobileNumber = prefs.getString("PHONENUMBER", "");
            if (MobileNumber != null && !MobileNumber.equals("")) {
                getSubRequest(MobileNumber, ServiceId);

            } else {
                startActivity(new Intent(SplashActivity.this, RegisterActivity.class));
                finish();
            }
        }else{
            Toast.makeText(SplashActivity.this, "لطفا اتصال اینترنت خود را بررسی کرده و دوباره تلاش کنید", Toast.LENGTH_LONG).show();
        }
    }


}
