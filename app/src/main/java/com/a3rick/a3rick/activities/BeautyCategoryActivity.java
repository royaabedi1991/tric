package com.a3rick.a3rick.activities;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.a3rick.a3rick.R;
import com.a3rick.a3rick.adapters.BeautyCategoryAdapter;
import com.a3rick.a3rick.models.models.Trick.content_with_categoriId.Result;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BeautyCategoryActivity extends AppCompatActivity {
    Toolbar toolbar_beauty_category;
    int like_count;
    int view_count;
    TextView tvLikeCount;
    TextView tvViewCount;
    int contentId;


    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_beauty_category);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        RecyclerView categoryBeautyRecyceler = findViewById(R.id.category_beauty_recycler);
        LinearLayoutManager categoryBeautyManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        categoryBeautyRecyceler.setLayoutManager(categoryBeautyManager);
        BeautyCategoryAdapter beautyCategoryAdapter = new BeautyCategoryAdapter(getApplicationContext(), getResults());
        categoryBeautyRecyceler.setAdapter(beautyCategoryAdapter);

        init();


//        Intent intent = getIntent();
//
//        like_count = intent.getIntExtra("LIKECOUNT", 10);
//        view_count = intent.getIntExtra("TOTALVIEW", 1);
//        tvLikeCount.setText(like_count);
//        tvViewCount.setText(view_count);
    }

    private void init() {

        tvLikeCount = findViewById(R.id.like_count_categories);
        tvViewCount = findViewById(R.id.view_count_categories);

        toolbar_beauty_category = findViewById(R.id.toolbar_beauty_category);
        setSupportActionBar(toolbar_beauty_category);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar_beauty_category.getNavigationIcon().setColorFilter(getResources().getColor(R.color.back), PorterDuff.Mode.SRC_ATOP);

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


    private List<Result> getResults() {
        List<Result> result_s = new ArrayList<>();

        for (int i = 0; i < 3; i++) {

            Result current = new Result();
            result_s.add(current);


        }
        return result_s;


    }

    @Override
    protected void onResume() {
        init();

        super.onResume();
    }
}
