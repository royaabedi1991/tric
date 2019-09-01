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
import com.a3rick.a3rick.adapters.CoockCategoryAdapter;
import com.a3rick.a3rick.models.models.Trick.content_with_categoriId.Result_;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class CoockCategoryActivity extends AppCompatActivity {
    Toolbar toolbar;
//    String videoFileAddress;
//    String body;
//    String subject;
//    String headerImage;


    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_coock_category);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        toolbar = findViewById(R.id.toolbar_coock_category);
        RecyclerView categoryCoockRecyceler = findViewById(R.id.category_coock_recycler);
        LinearLayoutManager categoryCoockManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        categoryCoockRecyceler.setLayoutManager(categoryCoockManager);
        CoockCategoryAdapter coockCategoryAdapter = new CoockCategoryAdapter(getApplicationContext(), getResults());
        categoryCoockRecyceler.setAdapter(coockCategoryAdapter);


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
