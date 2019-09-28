package com.a3rick.a3rick.activities;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.a3rick.a3rick.R;
import com.a3rick.a3rick.models.models.Trick.profile.GetProfileInfoResult;
import com.a3rick.a3rick.models.models.Trick.profile.UpdateProfileNameResult;
import com.a3rick.a3rick.webService.Trick.FileApi;
import com.a3rick.a3rick.webService.Trick.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class EditProfileActivity extends Activity implements View.OnClickListener {
    public EditText enterFirstName;
    public EditText enterLastName;
    Button taeed;
    ImageButton close;
    private String firstName;
    private String lastName;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);
        enterFirstName = findViewById(R.id.firstName);
        enterLastName = findViewById(R.id.FamilyName);


        taeed = findViewById(R.id.btn_taeed);
        close = findViewById(R.id.close_popup);


        taeed.setOnClickListener(this);
        close.setOnClickListener(this);

//        btnDialogSharayet= findViewById(R.id.btn_dialog_sharayet);
//        btnDialogSharayet.setOnClickListener(this);


        this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

//        WebView webView = (WebView) findViewById(R.id.www1);
//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        webView.loadUrl("file:///android_asset/Sample.html");


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_taeed:
                firstName = String.valueOf(enterFirstName.getText());
                lastName = String.valueOf(enterLastName.getText());
                if (!firstName.equals("")&!lastName.equals("")) { uploadProfileName(); }
                else { finish(); }



                break;
            case R.id.close_popup:
                finish();
                break;
            default:
                break;
        }
    }


    private void uploadProfileName() {


        FileApi fileApi = RetrofitClient.getRetroClient().create(FileApi.class);
        Call<UpdateProfileNameResult> call = fileApi.updateName(firstName, lastName);
        call.enqueue(new Callback<UpdateProfileNameResult>() {
            @Override
            public void onResponse(Call<UpdateProfileNameResult> call, Response<UpdateProfileNameResult> response) {
                UpdateProfileNameResult apiResponse = new UpdateProfileNameResult();
                apiResponse = response.body();


                getProfileInfo();

            }

            @Override
            public void onFailure(Call<UpdateProfileNameResult> call, Throwable t) {
                Log.e("Tag", "error");


            }
        });


    }


    private void getProfileInfo() {

        FileApi fileApi = RetrofitClient.getRetroClient().create(FileApi.class);
        Call<GetProfileInfoResult> call = fileApi.getProfileInfo();
        call.enqueue(new Callback<GetProfileInfoResult>() {
            @Override
            public void onResponse(Call<GetProfileInfoResult> call, Response<GetProfileInfoResult> response) {
                GetProfileInfoResult apiResponse = new GetProfileInfoResult();
                apiResponse = response.body();
                String firstNameResult = apiResponse.getResult().getFirstName();
                String lastNameResult = apiResponse.getResult().getLastName();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("FIRSTNAME", firstNameResult);
                returnIntent.putExtra("LASTNAME", lastNameResult);

                SharedPreferences preferences = getSharedPreferences("MyPref", 0);
                preferences = getSharedPreferences("MyPref", 0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("FIRSTNAME", firstNameResult);
                editor.putString("LASTNAME", lastNameResult);
                editor.commit();

                setResult(Activity.RESULT_OK, returnIntent);
                finish();


            }

            @Override
            public void onFailure(Call<GetProfileInfoResult> call, Throwable t) {
                Log.e("Tag", "error");


            }
        });


    }
}
