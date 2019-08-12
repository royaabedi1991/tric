package com.a3rick.a3rick.activities;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.a3rick.a3rick.R;
import com.a3rick.a3rick.adapters.CustomPagerAdapter;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {
    private ViewPager pager;
    Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    int selectedItemId;
    String mobileNumner;
    TextView textView;
    TextView tvToolbar;

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
        mobileNumner = getSharedPreferences("MyPref", 0).getString("PHONENUMBER", "");


//ViewPager
        pager = findViewById(R.id.view_pager);
        CustomPagerAdapter adapter = new CustomPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setCurrentItem(3);
        pager.addOnPageChangeListener(this);

//BottomnNavigation
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomn_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.vitrin);
        selectedItemId = R.id.vitrin;
//       loadHomeFragments();


//Tolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

//ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);



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
                tvToolbar.setText("فیلم های من");
                return true;
            case R.id.more:
                selectedItemId = R.id.more;
                pager.setCurrentItem(0, true);
                tvToolbar.setText("بیشتر");
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


}
