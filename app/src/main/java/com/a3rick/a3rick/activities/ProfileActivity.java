package com.a3rick.a3rick.activities;

import android.app.Activity;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a3rick.a3rick.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
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
    String imageProfilePrefrence;
    String imageProfile;
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




        init();
        firstNamePrefrence = getSharedPreferences("MyPref", 0).getString("FIRSTNAME", "نام و نام خانوادگی");
        lastNamePrefrence = getSharedPreferences("MyPref", 0).getString("LASTNAME", "");
        imageProfilePrefrence = getSharedPreferences("MyPref", 0).getString("PROFILEIMAGE","");
        interFirstName.setText(firstNamePrefrence);
        interLastName.setText(lastNamePrefrence);
        if (imageProfilePrefrence!=null && !imageProfilePrefrence.equals(""))
        Picasso.with(getApplicationContext()).load(imageProfilePrefrence).fit().centerCrop().into(profileImage);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//            super.onActivityResult(requestCode, resultCode, data);
//            if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
//                //the image URI
//                Uri selectedImage = data.getData();
//                uploadFile(selectedImage, "My Image");
//            }

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                imageProfile = data.getStringExtra("PROFILEIMAGE");
                firstName = data.getStringExtra("FIRSTNAME");
                lastName = data.getStringExtra("LASTNAME");

                if (firstName != null && !firstName.equals("") && lastName != null && !lastName.equals("")) {
                    interFirstName.setText(firstName);
                    interLastName.setText(lastName);
                }
                if (imageProfile != null && !imageProfile.equals(""))
                    Picasso.with(getApplicationContext()).load(imageProfile).fit().centerCrop().into(profileImage);

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }


    private void init() {
        profileImage = findViewById(R.id.profile_image);
//        profileImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    if (ActivityCompat.checkSelfPermission(ProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                        ActivityCompat.requestPermissions(ProfileActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
//
//                    } else {
//                        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                        startActivityForResult(galleryIntent, 100);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
        toolbar_profile = findViewById(R.id.toolbar_profile);
        setSupportActionBar(toolbar_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar_profile.getNavigationIcon().setColorFilter(getResources().getColor(R.color.back), PorterDuff.Mode.SRC_ATOP);


        mobileNumner = getSharedPreferences("MyPref", 0).getString("PHONENUMBER", "");
        textView = findViewById(R.id.show_number);
        interFirstName = findViewById(R.id.name);
        interLastName = findViewById(R.id.lastName);
if(!mobileNumner.equals(""))
    textView.setText(mobileNumner);
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
