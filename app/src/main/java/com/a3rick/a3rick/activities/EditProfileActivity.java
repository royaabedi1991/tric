package com.a3rick.a3rick.activities;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.a3rick.a3rick.R;
import com.a3rick.a3rick.models.models.Trick.profile.GetProfileInfoResult;
import com.a3rick.a3rick.models.models.Trick.profile.UpdateProfileNameResult;
import com.a3rick.a3rick.models.models.Trick.profile.UploadProfileImageResult;
import com.a3rick.a3rick.webService.Trick.FileApi;
import com.a3rick.a3rick.webService.Trick.RetrofitClient;
import com.squareup.picasso.Picasso;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static android.support.constraint.ConstraintSet.VISIBLE;


public class EditProfileActivity extends Activity implements View.OnClickListener {
    public EditText enterFirstName;
    public EditText enterLastName;
    Button taeed;
    ImageButton close;
    private String firstName;
    private String lastName;
    CircleImageView editImageProfile;
    TextView changeImage;
    ProgressBar progressBar;
    String firstNamePrefrence;
    String lastNamePrefrence;
    String profileImage;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);
        progressBar = findViewById(R.id.pr_profile);
        enterFirstName = findViewById(R.id.firstName);
        enterLastName = findViewById(R.id.FamilyName);
        editImageProfile = findViewById(R.id.editProfile_image);
        firstNamePrefrence = getSharedPreferences("MyPref", 0).getString("FIRSTNAME", "");
        lastNamePrefrence = getSharedPreferences("MyPref", 0).getString("LASTNAME", "");
        enterFirstName.setText(firstNamePrefrence);
        enterLastName.setText(lastNamePrefrence);
        String imageProfilePrefrence = getSharedPreferences("MyPref", 0).getString("PROFILEIMAGE", "");
        if (!imageProfilePrefrence.equals("")&& imageProfilePrefrence!=null) {
            Picasso.with(getApplicationContext()).load(imageProfilePrefrence).fit().centerCrop().into(editImageProfile);
        }

        taeed = findViewById(R.id.btn_taeed);
        close = findViewById(R.id.close_popup);
        changeImage = findViewById(R.id.changeImage);

        changeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if (ActivityCompat.checkSelfPermission(EditProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(EditProfileActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
                   }


                     else {
                        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(galleryIntent, 100);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


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

//    private void checkStatus() {
//
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            //the image URI
            Uri selectedImage = data.getData();
            uploadFile(selectedImage, "My Image");

        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_taeed:
                firstName = String.valueOf(enterFirstName.getText());
                lastName = String.valueOf(enterLastName.getText());
                if (!firstName.equals("") && !lastName.equals("")) {
                    uploadProfileName();
                }


                else {
                    finish();
                }

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

    private void uploadFile(Uri fileUri, String desc) {
        progressBar.setVisibility(VISIBLE);
        File file = new File(getRealPathFromURI(fileUri));

        //creating request body for file
        RequestBody requestFile = RequestBody.create(MediaType.parse(getContentResolver().getType(fileUri)), file);
        RequestBody descBody = RequestBody.create(MediaType.parse("text/plain"), desc);


        FileApi fileApi = RetrofitClient.getRetroClient().create(FileApi.class);
        Call<UploadProfileImageResult> call = fileApi.uploadImage(requestFile, descBody);
        call.enqueue(new Callback<UploadProfileImageResult>() {
            @Override
            public void onResponse(Call<UploadProfileImageResult> call, Response<UploadProfileImageResult> response) {
                UploadProfileImageResult apiResponse = new UploadProfileImageResult();
                apiResponse = response.body();

                getProfileInfoForImage();

            }

            @Override
            public void onFailure(Call<UploadProfileImageResult> call, Throwable t) {
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
                returnIntent.putExtra("PROFILEIMAGE", profileImage);
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

    private void getProfileInfoForImage() {

        progressBar.setVisibility(VISIBLE);
        FileApi fileApi = RetrofitClient.getRetroClient().create(FileApi.class);
        Call<GetProfileInfoResult> call = fileApi.getProfileInfo();
        call.enqueue(new Callback<GetProfileInfoResult>() {
            @Override
            public void onResponse(Call<GetProfileInfoResult> call, Response<GetProfileInfoResult> response) {
                GetProfileInfoResult apiResponse = new GetProfileInfoResult();
                apiResponse = response.body();
                progressBar.setVisibility(View.GONE);

                profileImage = apiResponse.getResult().getProfileImageUrl();
//                Intent returnIntent = new Intent();

//                returnIntent.putExtra("PROFILEIMAGE", profileImage);

                Picasso.with(getApplicationContext()).load(profileImage).fit().centerCrop().into(editImageProfile);
                SharedPreferences preferences = getSharedPreferences("MyPref", 0);
                preferences = getSharedPreferences("MyPref", 0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("PROFILEIMAGE", profileImage);
                editor.commit();

//                setResult(Activity.RESULT_OK, returnIntent);
//                finish();


            }

            @Override
            public void onFailure(Call<GetProfileInfoResult> call, Throwable t) {
                Log.e("Tag", "error");


            }
        });


    }


    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(this, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case 100:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                }else{

                }
                break;
            default:
                break;
        }
    }
}
