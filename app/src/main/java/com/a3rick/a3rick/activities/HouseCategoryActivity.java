package com.a3rick.a3rick.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

import com.a3rick.a3rick.R;
import com.a3rick.a3rick.adapters.HouseCategoryAdapter;
import com.a3rick.a3rick.models.ApiModels.Trick.Results.Result_;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class HouseCategoryActivity extends AppCompatActivity {
    Toolbar toolbar;
    String videoFileAddress;
    String body;
    String subject;
    String headerImage;


    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_house_category);


        toolbar= findViewById(R.id.toolbar_house_category);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        RecyclerView categoryHouseRecyceler = findViewById(R.id.category_house_recycler);
        LinearLayoutManager categoryHouseManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        categoryHouseRecyceler.setLayoutManager(categoryHouseManager);
        HouseCategoryAdapter houseCategoryAdapter = new HouseCategoryAdapter(getApplicationContext(), getResults());
        categoryHouseRecyceler.setAdapter(houseCategoryAdapter);


//        Intent intent = getIntent();
//        videoFileAddress = intent.getStringExtra("VIDEOADRESS");
//        body = intent.getStringExtra("BODY");
//        subject = intent.getStringExtra("SUBJECT");
//        headerImage = intent.getStringExtra("HEADERIMAGE");
    }


    private List<Result_> getResults() {
        List<Result_> result_s = new ArrayList<>();

        for (int i = 0; i < 3; i++) {

            Result_ current = new Result_();

            result_s.add(current);


        }
        return result_s;


    }

}
