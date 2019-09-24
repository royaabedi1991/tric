package com.a3rick.a3rick.activities;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.a3rick.a3rick.R;
import com.a3rick.a3rick.adapters.SaveAdapter;
import com.a3rick.a3rick.models.models.Trick.favorites.Result;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SaveActivity extends AppCompatActivity {
    LinearLayout linearLayout;
    private Toolbar favoriteToolbar;

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_save);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
        linearLayout = findViewById(R.id.loai_lenear);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                linearLayout.setVisibility(View.GONE);


            }
        }, 2000);



    }

    private void init() {


        RecyclerView saveRecyceler = findViewById(R.id.save_recycler);
        LinearLayoutManager saveManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        saveRecyceler.setLayoutManager(saveManager);
        SaveAdapter saveAdapter = new SaveAdapter(getApplicationContext(),getFavorite());
        saveRecyceler.setAdapter(saveAdapter);
    }

    private List<Result> getFavorite() {
        List<Result> results = new ArrayList<>();

        for (int i = 0; i < 3; i++) {

            Result current = new Result();

            results.add(current);
            favoriteToolbar = findViewById(R.id.toolbar_favorite_activity);
            setSupportActionBar(favoriteToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            favoriteToolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.back), PorterDuff.Mode.SRC_ATOP);


        }
        return results;


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
