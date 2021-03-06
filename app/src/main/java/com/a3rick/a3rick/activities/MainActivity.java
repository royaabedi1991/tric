package com.a3rick.a3rick.activities;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a3rick.a3rick.R;
import com.a3rick.a3rick.adapters.CustomPagerAdapter;
import com.a3rick.a3rick.core.BottomNavigationViewHelper;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {
    private ViewPager pager;
    Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    int selectedItemId;
    String mobileNumner;
    TextView textView;
    TextView tvToolbar;
    LinearLayout searchBox;
    LinearLayout profile;
    ImageButton profileBotton;


    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        init();






    }


    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    private void init() {
//GetMobileNumber from SharedPreferences

        profileBotton= findViewById(R.id.profile);

        profile = findViewById(R.id.profile_linear);
//
        profileBotton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
                profileBotton.setClickable(false);
            }
        });


        searchBox = findViewById(R.id.search_box);
        searchBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                searchBox.setClickable(false);

            }
        });


//ViewPager
        pager = findViewById(R.id.view_pager);
        CustomPagerAdapter adapter = new CustomPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setCurrentItem(3);
        pager.addOnPageChangeListener(this);

//BottomnNavigation
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomn_nav);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.vitrin);
        selectedItemId = R.id.vitrin;
//       loadHomeFragments();


//Tolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

//ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
// to delete toolbar back buttom
        actionBar.setDisplayHomeAsUpEnabled(false);


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        tvToolbar = findViewById(R.id.tv_text_toolbar);

        switch (item.getItemId()) {
            case R.id.vitrin:
                selectedItemId = R.id.vitrin;
                pager.setCurrentItem(3, true);
                tvToolbar.setText("ویترین");
                return true;


            case R.id.categori:
                selectedItemId = R.id.categori;
                pager.setCurrentItem(2, true);
                tvToolbar.setText("دسته بندی");
                return true;


            case R.id.my_video:
                selectedItemId = R.id.my_video;
                pager.setCurrentItem(1, true);
                tvToolbar.setText("ذخیره شده");
                return true;
            case R.id.more:
                selectedItemId = R.id.more;
                pager.setCurrentItem(0, true);
                tvToolbar.setText("درباره ما");
                return true;

        }

        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        switch (position) {

            case 3:
                bottomNavigationView.setSelectedItemId(R.id.vitrin);
                break;

            case 2:
                bottomNavigationView.setSelectedItemId(R.id.categori);
                break;


            case 1:
                bottomNavigationView.setSelectedItemId(R.id.my_video);
                break;

            case 0:
                bottomNavigationView.setSelectedItemId(R.id.more);
                break;
        }


    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onBackPressed() {

//        super.onBackPressed();

        backButtonHandler();
        return;
    }

    public void backButtonHandler() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                MainActivity.this);
        // Setting Dialog Title
        alertDialog.setTitle("تریک");
        // Setting Dialog Message
        alertDialog.setMessage("آیا می خواهید از برنامه خارج شوید؟");
        // Setting Icon to Dialog
//        alertDialog.setIcon(R.drawable.ic_launcher);
        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("بله",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("خیر",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event
                        dialog.cancel();
                    }
                });
        // Showing Alert Message
        alertDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        profileBotton.setClickable(true);
        searchBox.setClickable(true);
    }
}
