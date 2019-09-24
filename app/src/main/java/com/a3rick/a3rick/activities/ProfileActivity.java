package com.a3rick.a3rick.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
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

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_profile);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        toolbar_profile = findViewById(R.id.toolbar_profile);
        setSupportActionBar(toolbar_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar_profile.getNavigationIcon().setColorFilter(getResources().getColor(R.color.back), PorterDuff.Mode.SRC_ATOP);

//        mobileNumner = getSharedPreferences("MyPref", 0).getString("PHONENUMBER", "");
        textView = findViewById(R.id.show_number);
//        textView.setText(mobileNumner);
        card_darbare = findViewById(R.id.card_darbare);
        card_share = findViewById(R.id.card_davat);
        card_favorite = findViewById(R.id.card_favorite);
        editName= findViewById(R.id.edit_name_leanear);
//        editName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent editNamIntent = new Intent(ProfileActivity.this,EditProfileActivity.class);
//                startActivity(editNamIntent);
//            }
//        });
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

}
