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
import com.a3rick.a3rick.adapters.BeautyCategoryAdapter;
import com.a3rick.a3rick.models.models.Trick.content_with_categoriId.Result_;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BeautyCategoryActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_beauty_category);


        toolbar= findViewById(R.id.toolbar_beauty_category);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        RecyclerView categoryBeautyRecyceler = findViewById(R.id.category_beauty_recycler);
        LinearLayoutManager categoryBeautyManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        categoryBeautyRecyceler.setLayoutManager(categoryBeautyManager);
        BeautyCategoryAdapter beautyCategoryAdapter = new BeautyCategoryAdapter(getApplicationContext(), getResults());
        categoryBeautyRecyceler.setAdapter(beautyCategoryAdapter);


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
