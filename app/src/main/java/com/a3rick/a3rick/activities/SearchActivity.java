package com.a3rick.a3rick.activities;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.a3rick.a3rick.R;
import com.a3rick.a3rick.models.models.Trick.tags.GetAllTagsResult;
import com.a3rick.a3rick.models.models.Trick.tags.Result;
import com.a3rick.a3rick.webService.Trick.FileApi;
import com.a3rick.a3rick.webService.Trick.RetrofitClient;
import com.adroitandroid.chipcloud.ChipCloud;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    Toolbar toolbar;
    ChipCloud tvTag;
    List<Result> tags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        getAllTags();
        init();

    }

    private void init() {

        toolbar = findViewById(R.id.toolbar_search);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.back), PorterDuff.Mode.SRC_ATOP);
        tvTag = findViewById(R.id.chip_cloud_search);


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


    public String[] increaseArray(List<Result> tags) {
        if (tags != null) {
            int i = tags.size();
            int n = i++;
            String[] newArray = new String[n];
            for (int cnt = 0; cnt < tags.size(); cnt++) {
                newArray[cnt] = tags.get(cnt).getTitle();

            }
            return newArray;
        }

        return null;
    }


    public Integer[] increaseArray2(List<Result> tags) {
            int i = tags.size();
            int n = i++;
            Integer[] newArray2 = new Integer[n];
            for (int cnt = 0; cnt < tags.size(); cnt++) {
                newArray2[cnt] = tags.get(cnt).getTagId();

            return newArray2;
        }

        return null;
    }


    private void getAllTags() {

        FileApi fileApi = RetrofitClient.getRetroClient().create(FileApi.class);
        Call<GetAllTagsResult> call = fileApi.getAllTags();
        call.enqueue(new Callback<GetAllTagsResult>() {
            @Override
            public void onResponse(Call<GetAllTagsResult> call, Response<GetAllTagsResult> response) {
                GetAllTagsResult apiResponse = new GetAllTagsResult();
                apiResponse = response.body();
                tags = apiResponse.getResult();
                if (tags != null)
                    tvTag.addChips(increaseArray(tags));

            }

            @Override
            public void onFailure(Call<GetAllTagsResult> call, Throwable t) {
                Log.e("Tag", "error");


            }
        });


    }
}
