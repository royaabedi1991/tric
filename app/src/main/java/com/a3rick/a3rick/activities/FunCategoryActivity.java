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
import com.a3rick.a3rick.adapters.FunCategoryAdapter;
import com.a3rick.a3rick.models.ApiModels.Teepeto.Results.Result_;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class FunCategoryActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_fun_category);


        toolbar= findViewById(R.id.toolbar_fun_category);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        RecyclerView categoryFunRecyceler = findViewById(R.id.category_fun_recycler);
        LinearLayoutManager categoryFunManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        categoryFunRecyceler.setLayoutManager(categoryFunManager);
        FunCategoryAdapter funCategoryAdapter = new FunCategoryAdapter(getApplicationContext(), getResults());
        categoryFunRecyceler.setAdapter(funCategoryAdapter);


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