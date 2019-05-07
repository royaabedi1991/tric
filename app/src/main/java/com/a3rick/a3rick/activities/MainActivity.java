package com.a3rick.a3rick.activities;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.a3rick.a3rick.R;
import com.a3rick.a3rick.adapters.BannerAdapter;
import com.a3rick.a3rick.adapters.BeautyAdapter;
import com.a3rick.a3rick.adapters.CoockAdapter;
import com.a3rick.a3rick.adapters.FavoriteAdapter;
import com.a3rick.a3rick.adapters.FunAdapter;
import com.a3rick.a3rick.adapters.HouseAdapter;
import com.a3rick.a3rick.core.RecyclerItemClickListener;
import com.a3rick.a3rick.fragments.CategoryFragment;
import com.a3rick.a3rick.fragments.MyVideoFragment;
import com.a3rick.a3rick.models.Banner;
import com.a3rick.a3rick.models.Beauty;
import com.a3rick.a3rick.models.Coock;
import com.a3rick.a3rick.models.Favorite;
import com.a3rick.a3rick.models.Fun;
import com.a3rick.a3rick.models.House;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    private BottomNavigationView bottomNavigationView;
    int selectedItemId;
    private String[] navTitle;
    private String CURRENT_TAG = "current";
    private final String SHARE_TAG = "معرفی به دوستان";
    private final String ABAUT_TAG = "درباره ما";
    private final String HOME_TAG = "تریک";
    private int selectedIndex = 0;
    String mobileNumner;
    TextView textView;
    SharedPreferences.Editor editor;

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


    @Override
    public void onBackPressed() {

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }


    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    private void init() {


        mobileNumner = getSharedPreferences("MyPref", 0).getString("PHONENUMBER", "");

//BottomnVvigation
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomn_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.vitrin);
        selectedItemId = R.id.vitrin;
        loadFragment();


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


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


//BannerRecycler
        RecyclerView bannerRecycler = findViewById(R.id.recyceler_banner);
        final BannerAdapter bannerAdapter = new BannerAdapter(this, getBanners());

        bannerRecycler.setAdapter(bannerAdapter);
        LinearLayoutManager bannerManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        bannerRecycler.setLayoutManager(bannerManager);

        bannerRecycler.addOnItemTouchListener(new RecyclerItemClickListener(getBaseContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(MainActivity.this, BannerActivity.class));
            }
        }));


//FavoriteRecycler
        RecyclerView favoriteRecyceler = findViewById(R.id.recyceler_Favorit);
        FavoriteAdapter favoriteAdapter = new FavoriteAdapter(this, getFavorites());

        favoriteRecyceler.setAdapter(favoriteAdapter);
        LinearLayoutManager favoriteManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        favoriteRecyceler.setLayoutManager(favoriteManager);


        favoriteRecyceler.addOnItemTouchListener(new RecyclerItemClickListener(getBaseContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(MainActivity.this, BannerActivity.class));
            }
        }));

//BeautyRecycler
        RecyclerView beautyRecycler = findViewById(R.id.recyceler_beauty_category);
        BeautyAdapter beautyAdapter = new BeautyAdapter(this, getBeauties());

        beautyRecycler.setAdapter(beautyAdapter);
        LinearLayoutManager beautyManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        beautyRecycler.setLayoutManager(beautyManager);

        beautyRecycler.addOnItemTouchListener(new RecyclerItemClickListener(getBaseContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(MainActivity.this, BannerActivity.class));
            }
        }));

//HouseRecycler

        RecyclerView houseRecycler = findViewById(R.id.recyceler_khanedari_category);
        HouseAdapter houseAdapter = new HouseAdapter(this, getHouses());

        houseRecycler.setAdapter(houseAdapter);
        LinearLayoutManager houseManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        houseRecycler.setLayoutManager(houseManager);

        houseRecycler.addOnItemTouchListener(new RecyclerItemClickListener(getBaseContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(MainActivity.this, BannerActivity.class));
            }
        }));


//CoockRecycler

        RecyclerView coockRecycler = findViewById(R.id.recyceler_coock_category);
        CoockAdapter coockAdapter = new CoockAdapter(this, getCoockes());


        coockRecycler.setAdapter(coockAdapter);
        LinearLayoutManager coockManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        coockRecycler.setLayoutManager(coockManager);

        coockRecycler.addOnItemTouchListener(new RecyclerItemClickListener(getBaseContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(MainActivity.this, BannerActivity.class));
            }
        }));

        //FunRecycler


        RecyclerView funRecycler = findViewById(R.id.recyceler_fun_category);
        FunAdapter funAdapter = new FunAdapter(this, getFunes());

        funRecycler.setAdapter(funAdapter);
        LinearLayoutManager funManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        funRecycler.setLayoutManager(funManager);

        funRecycler.addOnItemTouchListener(new RecyclerItemClickListener(getBaseContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(MainActivity.this, BannerActivity.class));
            }
        }));

    }


    private void loadFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, getFragment());
        transaction.commit();

    }


    private Fragment getFragment() {

        switch (selectedItemId) {
            case R.id.vitrin:
               break;


            case R.id.categori:

                return new CategoryFragment();


            case R.id.my_video:
                return new MyVideoFragment();

        }

        return null;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.vitrin:
                selectedItemId = R.id.vitrin;


                return true;


            case R.id.categori:
                selectedItemId = R.id.categori;

                loadFragment();
                return true;


            case R.id.my_video:
                selectedItemId = R.id.my_video;
                loadFragment();

                return true;


        }

        return false;
    }


    private List<Fun> getFunes() {

        List<Fun> funs = new ArrayList<>();
        int[] drawables = new int[]{

                R.drawable.pic_21,
                R.drawable.pic_22,
                R.drawable.pic_23,
                R.drawable.pic_24,
                R.drawable.pic_25,

        };
        for (int i = 0; i < 5; i++) {
            Fun current = new Fun();
            current.setResource(drawables[i]);
            funs.add(current);
        }
        return funs;
    }

    private List<Coock> getCoockes() {
        List<Coock> coocks = new ArrayList<>();
        int[] drawables = new int[]{
                R.drawable.pic_16,
                R.drawable.pic_17,
                R.drawable.pic_18,
                R.drawable.pic_19,
                R.drawable.pic_20,
        };
        for (int i = 0; i < 5; i++) {
            Coock current = new Coock();
            current.setResorce(drawables[i]);

            coocks.add(current);


        }
        return coocks;
    }

    private List<House> getHouses() {
        List<House> houses = new ArrayList<>();
        int[] drawables = new int[]{

                R.drawable.pic_10,
                R.drawable.pic_11,
                R.drawable.pic_12,
                R.drawable.pic_13,
                R.drawable.pic_14,


        };
        for (int i = 0; i < 5; i++) {

            House current = new House();
            current.setResource(drawables[i]);
            houses.add(current);

        }
        return houses;
    }


    private List<Beauty> getBeauties() {

        List<Beauty> beauties = new ArrayList<>();
        int[] drawables = new int[]{
                R.drawable.pic_5,
                R.drawable.pic_4,
                R.drawable.pic_6,
                R.drawable.pic_7,
                R.drawable.pic_8,
                R.drawable.pic_9,
                R.drawable.pic_5,


        };

        for (int i = 0; i < 7; i++) {

            Beauty current = new Beauty();
            current.setResource(drawables[i]);
            beauties.add(current);

        }
        return beauties;


    }


    private List<Favorite> getFavorites() {
        List<Favorite> favorites = new ArrayList<>();
        int[] drawables = {
                R.drawable.pic_1,
                R.drawable.pic_2,
                R.drawable.pic_3,
                R.drawable.pic_1,
                R.drawable.pic_2,


        };

        for (int i = 0; i < 5; i++) {

            Favorite current = new Favorite();
            current.setRecource(drawables[i]);
            favorites.add(current);

        }
        return favorites;

    }


    private List<Banner> getBanners() {

        List<Banner> banners = new ArrayList<>();

        for (int i = 0; i < 5; i++) {

            Banner current = new Banner();
            current.setRecource(R.drawable.banner);
            current.setTitle("طرز تهیه سبزی پلو");
            banners.add(current);


        }
        return banners;
    }


}
