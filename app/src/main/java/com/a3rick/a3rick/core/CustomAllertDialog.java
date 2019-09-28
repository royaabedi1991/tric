package com.a3rick.a3rick.core;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.a3rick.a3rick.R;
import com.a3rick.a3rick.models.models.Trick.profile.UpdateProfileNameResult;
import com.a3rick.a3rick.webService.Trick.FileApi;
import com.a3rick.a3rick.webService.Trick.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CustomAllertDialog extends Dialog implements
        android.view.View.OnClickListener {
    public EditText enterFirstName;
    public EditText enterLastName;
    String firstName;
    String lastName;

    public Activity c;
    public Dialog d;
    public Button yes;
    public ImageButton no;

    public CustomAllertDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.edit_profile);
        enterFirstName= findViewById(R.id.firstName);
        enterLastName= findViewById(R.id.FamilyName);
        firstName=String.valueOf(enterFirstName.getText());
        lastName =String.valueOf(enterLastName.getText());
        yes = (Button) findViewById(R.id.btn_taeed);
        no = (ImageButton) findViewById(R.id.close_popup);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_taeed:
                  uploadProfileName();
                  dismiss();

                break;
            case R.id.close_popup:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }

    private void uploadProfileName() {

        FileApi fileApi = RetrofitClient.getRetroClient().create(FileApi.class);
        Call<UpdateProfileNameResult> call = fileApi.updateName(firstName, lastName);
        call.enqueue(new Callback<UpdateProfileNameResult>() {
            @Override
            public void onResponse(Call<UpdateProfileNameResult> call, Response<UpdateProfileNameResult> response) {
                UpdateProfileNameResult apiResponse = new UpdateProfileNameResult();
                apiResponse = response.body();


            }

            @Override
            public void onFailure(Call<UpdateProfileNameResult> call, Throwable t) {
                Log.e("Tag", "error");


            }
        });


    }



}
