package com.a3rick.a3rick.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.a3rick.a3rick.R;
import com.a3rick.a3rick.fragments.AboutFragment;
import com.a3rick.a3rick.fragments.ShareFragment;

public class Drawer_Item_FragmentsActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    private String[] navTitle;
    private String CURRENT_TAG = "current";
    private final String SHARE_TAG = "معرفی به دوستان";
    private final String ABAUT_TAG = "درباره ما";
    private final String HOME_TAG = "تریک";
    private int selectedIndex = 0;
    ImageView imageView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_fragments_nav_item);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        toolbar = (Toolbar) findViewById(R.id.toolbar_drawer);
        setSupportActionBar(toolbar);
        navTitle = getResources().getStringArray(R.array.nav_menu_items);

        imageView = findViewById(R.id.back_press);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Drawer_Item_FragmentsActivity.this, MainActivity.class));
            }
        });


        Intent intent = getIntent();
        int itemId = intent.getIntExtra("ITEMID", 1);


        switch (itemId) {
            case R.id.about:

                selectedIndex = 0;
                setHomeFragment(ABAUT_TAG);
                setActionBarTitle(0);


                break;
            case R.id.share:
                selectedIndex = 1;
                setHomeFragment(SHARE_TAG);
                setActionBarTitle(1);


                break;
        }


    }

    private void setHomeFragment(String tag) {

        CURRENT_TAG = tag;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container_drawer, getFragment());

        transaction.commit();
    }

    private Fragment getFragment() {
        switch (selectedIndex) {
            case 0:
                AboutFragment aboutFragment = new AboutFragment();
                return aboutFragment;

            case 1:
                ShareFragment shareFragment = new ShareFragment();
                return shareFragment;


        }


        return null;
    }

    private void setActionBarTitle(int index) {
        getSupportActionBar().setTitle(navTitle[index]);
    }
}
