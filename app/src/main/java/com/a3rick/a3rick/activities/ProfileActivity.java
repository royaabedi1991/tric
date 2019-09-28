package com.a3rick.a3rick.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a3rick.a3rick.R;
import com.a3rick.a3rick.models.models.Trick.profile.UploadProfileImageResult;
import com.a3rick.a3rick.webService.Trick.FileApi;
import com.a3rick.a3rick.webService.Trick.RetrofitClient;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ProfileActivity extends AppCompatActivity {
    String mobileNumner;
    TextView textView;
    CardView card_share;
    CardView card_darbare;
    CardView card_porBazdid;
    CardView card_favorite;
    Toolbar toolbar_profile;
    LinearLayout editName;
    String firstName;
    String lastName;
    TextView interFirstName;
    TextView interLastName;
    SharedPreferences.Editor editor;
    String firstNamePrefrence;
    String lastNamePrefrence;
    CircleImageView profileImage;


    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_profile);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:" + getPackageName()));
            finish();
            startActivity(intent);
            return;
        }


        init();
        firstNamePrefrence = getSharedPreferences("MyPref", 0).getString("FIRSTNAME", "نام و نام خانوادگی");
        lastNamePrefrence = getSharedPreferences("MyPref", 0).getString("LASTNAME", "");
        interFirstName.setText(firstNamePrefrence);
        interLastName.setText(lastNamePrefrence);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            //the image URI
            Uri selectedImage = data.getData();
            uploadFile(selectedImage, "My Image");
        }

            if (requestCode == 1) {
                if (resultCode == Activity.RESULT_OK) {
                    firstName = data.getStringExtra("FIRSTNAME");
                    lastName = data.getStringExtra("LASTNAME");
                    interFirstName.setText(firstName);
                    interLastName.setText(lastName);

                }
                if (resultCode == Activity.RESULT_CANCELED) {
                    //Write your code if there's no result
                }
            }
        }



    private void init () {
            profileImage = findViewById(R.id.profile_image);
            profileImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, 100);
                }
            });
            toolbar_profile = findViewById(R.id.toolbar_profile);
            setSupportActionBar(toolbar_profile);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            toolbar_profile.getNavigationIcon().setColorFilter(getResources().getColor(R.color.back), PorterDuff.Mode.SRC_ATOP);


//        mobileNumner = getSharedPreferences("MyPref", 0).getString("PHONENUMBER", "");
            textView = findViewById(R.id.show_number);
            interFirstName = findViewById(R.id.name);
            interLastName = findViewById(R.id.lastName);

//        textView.setText(mobileNumner);
            card_darbare = findViewById(R.id.card_darbare);
            card_share = findViewById(R.id.card_davat);
            card_favorite = findViewById(R.id.card_favorite);
            editName = findViewById(R.id.edit_name_leanear);
            editName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                    startActivityForResult(intent, 1);

                }
            });
            card_favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent favoriteIntent = new Intent(ProfileActivity.this, SaveActivity.class);
                    startActivity(favoriteIntent);


                }
            });
            card_porBazdid = findViewById(R.id.card_porBazdid);

            card_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "http://hubland.ir/apps/Trick.apk");
                    sendIntent.setType("text/plain");
                    Intent shareIntent = Intent.createChooser(sendIntent, null);
                    startActivity(shareIntent);
                }
            });
            card_darbare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ProfileActivity.this, AboutActivity.class);
                    startActivity(intent);
                }
            });

        }



    private void uploadFile(Uri fileUri, String desc) {

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




                }

                @Override
                public void onFailure(Call<UploadProfileImageResult> call, Throwable t) {
                    Log.e("Tag", "error");


                }
            });






    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
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


}
