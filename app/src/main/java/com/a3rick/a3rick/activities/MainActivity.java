package com.a3rick.a3rick.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.a3rick.a3rick.R;
import com.a3rick.a3rick.adapters.CustomPagerAdapter;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {
    private ViewPager pager;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    private BottomNavigationView bottomNavigationView;
    int selectedItemId;
    private String[] navTitle;
    String mobileNumner;
    TextView textView;
    TextView tvToolbar;
    public static ProgressBar progressBar;


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


        navTitle = getResources().getStringArray(R.array.nav_menu_items);


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
        pager.setCurrentItem(2);
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
        progressBar = (ProgressBar) findViewById(R.id.toolbarProgress);
//ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

//DrowerLayout
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerToggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View headerView = navigationView.getHeaderView(0);
        textView = (TextView) headerView.findViewById(R.id.TVnumber);
        textView.setText(mobileNumner);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent = new Intent(MainActivity.this, Drawer_Item_FragmentsActivity.class);
                intent.putExtra("ITEMID", item.getItemId());
                startActivity(intent);

                return true;
            }
        });


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        tvToolbar = findViewById(R.id.tv_text_toolbar);

        switch (item.getItemId()) {
            case R.id.vitrin:
                selectedItemId = R.id.vitrin;
                pager.setCurrentItem(2, true);
                //         loadHomeFragments();
                tvToolbar.setText("ویترین");
                return true;


            case R.id.categori:
                selectedItemId = R.id.categori;
                pager.setCurrentItem(1, true);
                tvToolbar.setText("دسته بندی");
                //       loadHomeFragments();
                return true;


            case R.id.my_video:
                selectedItemId = R.id.my_video;
                pager.setCurrentItem(0, true);
                //      loadHomeFragments();
                tvToolbar.setText("فیلم های من");
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

            case 2:
                bottomNavigationView.setSelectedItemId(R.id.vitrin);
                break;

            case 1:
                bottomNavigationView.setSelectedItemId(R.id.categori);
                break;


            case 0:
                bottomNavigationView.setSelectedItemId(R.id.my_video);
                break;


        }


    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
