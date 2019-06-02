package com.a3rick.a3rick.activities;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    private String CURRENT_TAG = "current";
    private final String SHARE_TAG = "معرفی به دوستان";
    private final String ABAUT_TAG = "درباره ما";
    private final String HOME_TAG = "تریک";
    private int selectedIndex = 0;
    String mobileNumner;
    TextView textView;
    TextView tvToolbar;
    SharedPreferences.Editor editor;






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

////BannerRecycler
//        RecyclerView bannerRecycler = findViewById(R.id.recyceler_banner);
//        final BannerAdapter bannerAdapter = new BannerAdapter(this, getBanners());
//
//        bannerRecycler.setAdapter(bannerAdapter);
//        LinearLayoutManager bannerManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
//        bannerRecycler.setLayoutManager(bannerManager);
//
//        bannerRecycler.addOnItemTouchListener(new RecyclerItemClickListener(getBaseContext(), new RecyclerItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                startActivity(new Intent(MainActivity.this, BannerActivity.class));
//            }
//        }));
//
//
////FavoriteRecycler
//        RecyclerView favoriteRecyceler = findViewById(R.id.recyceler_Favorit);
//        FavoriteAdapter favoriteAdapter = new FavoriteAdapter(this, getFavorites());
//
//        favoriteRecyceler.setAdapter(favoriteAdapter);
//        LinearLayoutManager favoriteManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
//        favoriteRecyceler.setLayoutManager(favoriteManager);
//
//
//        favoriteRecyceler.addOnItemTouchListener(new RecyclerItemClickListener(getBaseContext(), new RecyclerItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                startActivity(new Intent(MainActivity.this, BannerActivity.class));
//            }
//        }));
//
////BeautyRecycler
//        RecyclerView beautyRecycler = findViewById(R.id.recyceler_beauty_category);
//        BeautyAdapter beautyAdapter = new BeautyAdapter(this, getBeauties());
//
//        beautyRecycler.setAdapter(beautyAdapter);
//        LinearLayoutManager beautyManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
//        beautyRecycler.setLayoutManager(beautyManager);
//
//        beautyRecycler.addOnItemTouchListener(new RecyclerItemClickListener(getBaseContext(), new RecyclerItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                startActivity(new Intent(MainActivity.this, BannerActivity.class));
//            }
//        }));
//
////HouseRecycler
//
//        RecyclerView houseRecycler = findViewById(R.id.recyceler_khanedari_category);
//        HouseAdapter houseAdapter = new HouseAdapter(this, getHouses());
//
//        houseRecycler.setAdapter(houseAdapter);
//        LinearLayoutManager houseManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
//        houseRecycler.setLayoutManager(houseManager);
//
//        houseRecycler.addOnItemTouchListener(new RecyclerItemClickListener(getBaseContext(), new RecyclerItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                startActivity(new Intent(MainActivity.this, BannerActivity.class));
//            }
//        }));
//
//
////CoockRecycler
//
//        RecyclerView coockRecycler = findViewById(R.id.recyceler_coock_category);
//        CoockAdapter coockAdapter = new CoockAdapter(this, getCoockes());
//
//
//        coockRecycler.setAdapter(coockAdapter);
//        LinearLayoutManager coockManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
//        coockRecycler.setLayoutManager(coockManager);
//
//        coockRecycler.addOnItemTouchListener(new RecyclerItemClickListener(getBaseContext(), new RecyclerItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                startActivity(new Intent(MainActivity.this, BannerActivity.class));
//            }
//        }));
//
//        //FunRecycler
//
//
//        RecyclerView funRecycler = findViewById(R.id.recyceler_fun_category);
//        FunAdapter funAdapter = new FunAdapter(this, getFunes());
//
//        funRecycler.setAdapter(funAdapter);
//        LinearLayoutManager funManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
//        funRecycler.setLayoutManager(funManager);
//
//        funRecycler.addOnItemTouchListener(new RecyclerItemClickListener(getBaseContext(), new RecyclerItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                startActivity(new Intent(MainActivity.this, BannerActivity.class));
//            }
//        }));

    }

//    private void loadCategoryFrfagments() {
//        FragmentTransaction categoriesTransaction = getSupportFragmentManager().beginTransaction();
//        categoriesTransaction.replace(R.id.home_container, getCategoriesFragments());
//        categoriesTransaction.commit();
//
//    }


//    private Fragment getCategoriesFragments() {
//        switch (Possition) {
//            case 3:
//
//
//                return new MyVideoFragment();
//
//            case 2:
//
//                return new MyVideoFragment();
//
//            case 1:
//
//                return new MyVideoFragment();
//
//            case 0:
//
//                return new MyVideoFragment();
//        }
//
//        return null;
//    }

//    public boolean onPositionItemSelected(int possition) {
//
//
//        switch (possition) {
//            case 0:
//              //  loadCategoryFrfagments();
//                tvToolbar.setText("سرگرمی");
//                return true;
//
//
//            case 1:
//                loadCategoryFrfagments();
//                tvToolbar.setText("آشپزی");
//                return true;
//
//
//            case 2:
//                loadCategoryFrfagments();
//                tvToolbar.setText("زیبایی");
//                return true;
//
//            case 3:
//                loadCategoryFrfagments();
//                tvToolbar.setText("خانه داری");
//                return true;
//
//
//        }
//
//
//        return false;
//    }


//    private void loadHomeFragments() {
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.home_container, getFragment());
//        transaction.commit();
//
//    }


//    private Fragment getFragment() {
//
//        switch (selectedItemId) {
//            case R.id.vitrin:
//
//
//                return new VitrinFragment();
//
//
//            case R.id.categori:
//
//                return new CategoryFragment();
//
//
//            case R.id.my_video:
//                return new MyVideoFragment();
//
//            case R.id.img_category:
//                return new MyVideoFragment();
//
//        }
//
//        return null;
//    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        tvToolbar = findViewById(R.id.tv_text_toolbar);

        switch (item.getItemId()) {
            case R.id.vitrin:
                selectedItemId = R.id.vitrin;
                pager.setCurrentItem(2,true);
       //         loadHomeFragments();
                tvToolbar.setText("ویترین");
                return true;


            case R.id.categori:
                selectedItemId = R.id.categori;
                pager.setCurrentItem(1,true);
                tvToolbar.setText("دسته بندی");
         //       loadHomeFragments();
                return true;


            case R.id.my_video:
                selectedItemId = R.id.my_video;
                pager.setCurrentItem(0,true);
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

        switch (position){

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


//    private List<Fun> getFunes() {
//
//        List<Fun> funs = new ArrayList<>();
//        int[] drawables = new int[]{
//
//                R.drawable.pic_21,
//                R.drawable.pic_22,
//                R.drawable.pic_23,
//                R.drawable.pic_24,
//                R.drawable.pic_25,
//
//        };
//        for (int i = 0; i < 5; i++) {
//            Fun current = new Fun();
//            current.setResource(drawables[i]);
//            funs.add(current);
//        }
//        return funs;
//    }
//
//    private List<Coock> getCoockes() {
//        List<Coock> coocks = new ArrayList<>();
//        int[] drawables = new int[]{
//                R.drawable.pic_16,
//                R.drawable.pic_17,
//                R.drawable.pic_18,
//                R.drawable.pic_19,
//                R.drawable.pic_20,
//        };
//        for (int i = 0; i < 5; i++) {
//            Coock current = new Coock();
//            current.setResorce(drawables[i]);
//
//            coocks.add(current);
//
//
//        }
//        return coocks;
//    }
//
//    private List<House> getHouses() {
//        List<House> houses = new ArrayList<>();
//        int[] drawables = new int[]{
//
//                R.drawable.pic_10,
//                R.drawable.pic_11,
//                R.drawable.pic_12,
//                R.drawable.pic_13,
//                R.drawable.pic_14,
//
//
//        };
//        for (int i = 0; i < 5; i++) {
//
//            House current = new House();
//            current.setResource(drawables[i]);
//            houses.add(current);
//
//        }
//        return houses;
//    }
//
//
//    private List<Beauty> getBeauties() {
//
//        List<Beauty> beauties = new ArrayList<>();
//        int[] drawables = new int[]{
//                R.drawable.pic_5,
//                R.drawable.pic_4,
//                R.drawable.pic_6,
//                R.drawable.pic_7,
//                R.drawable.pic_8,
//                R.drawable.pic_9,
//                R.drawable.pic_5,
//
//
//        };
//
//        for (int i = 0; i < 7; i++) {
//
//            Beauty current = new Beauty();
//            current.setResource(drawables[i]);
//            beauties.add(current);
//
//        }
//        return beauties;
//
//
//    }
//
//
//    private List<Favorite> getFavorites() {
//        List<Favorite> favorites = new ArrayList<>();
//        int[] drawables = {
//                R.drawable.pic_1,
//                R.drawable.pic_2,
//                R.drawable.pic_3,
//                R.drawable.pic_1,
//                R.drawable.pic_2,
//
//
//        };
//
//        for (int i = 0; i < 5; i++) {
//
//            Favorite current = new Favorite();
//            current.setRecource(drawables[i]);
//            favorites.add(current);
//
//        }
//        return favorites;
//
//    }
//
//
//    private List<Banner> getBanners() {
//
//        List<Banner> banners = new ArrayList<>();
//
//        for (int i = 0; i < 5; i++) {
//
//            Banner current = new Banner();
//            current.setRecource(R.drawable.banner);
//            current.setTitle("طرز تهیه سبزی پلو");
//            banners.add(current);
//
//
//        }
//        return banners;
//    }


}
