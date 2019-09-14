package com.a3rick.a3rick.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.a3rick.a3rick.ChangeCategoryItem;
import com.a3rick.a3rick.R;
import com.a3rick.a3rick.RecyclerViewClicked;
import com.a3rick.a3rick.adapters.SearchResultAdapter;
import com.a3rick.a3rick.models.models.Trick.content_with_categoriId.Result;
import com.a3rick.a3rick.models.models.Trick.content_with_tagId.GetContentsWithTagIdsResult;
import com.a3rick.a3rick.tt;
import com.a3rick.a3rick.webService.Trick.FileApi;
import com.a3rick.a3rick.webService.Trick.RetrofitClient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SearchResultActivity extends AppCompatActivity {
    Toolbar toolbar_search_result;
    SearchResultAdapter searchResultAdapter;
    List<Result> result_s = new ArrayList<>();
    ArrayList selectedTags;
    LinearLayout linearLayout;


    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search_result);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Bundle bundle = getIntent().getExtras();
        selectedTags = bundle.getParcelableArrayList("selectedChips");
        getContentWithTags();
        init();


    }


    private void init() {

        RecyclerView searchResultRecyceler = findViewById(R.id.search_result_recycler);
        LinearLayoutManager searchResultManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        searchResultRecyceler.setLayoutManager(searchResultManager);
        searchResultAdapter = new SearchResultAdapter(getApplicationContext(), result_s, new RecyclerViewClicked() {
            @Override
            public void onIteMRecycler(View view, String id, Result current, ChangeCategoryItem changeCategoryItem) {
                Intent intent = new Intent(view.getContext(), ContentActivity.class);

                intent.putExtra("VIDEOADRESS", current.getVideoFileAddress());
                intent.putExtra("SUBJECT", current.getSubject());
                intent.putExtra("BODY", current.getBody());
                intent.putExtra("ImageHEADER", current.getHeaderImageFileAddress());
                intent.putExtra("LIKECOUNT", current.getLikeCount());
                intent.putExtra("ISLIKED", current.getIsLiked());
                intent.putExtra("ISBOOKMARKED", current.getIsBookmarked());
                intent.putExtra("TAGS", (Serializable) current.getAllTags());
                intent.putExtra("VIECOUNT", current.getViewCount());
                intent.putExtra("CONTENTID", current.getContentId());
                intent.putExtra("ChangeCategory", changeCategoryItem);
                view.getContext().startActivity(intent);
            }


        }, new tt(14/*, beautyCategoryAdapter, result_s*/));
        searchResultRecyceler.setAdapter(searchResultAdapter);

//        tvLikeCount = findViewById(R.id.like_count_categories);
//        tvViewCount = findViewById(R.id.view_count_categories);
//        tvCategory = findViewById(R.id.tv_category_title);

//        tvCategory.setText(title);
        linearLayout = findViewById(R.id.loai_lenear);
        toolbar_search_result = findViewById(R.id.toolbar_search_result);
        setSupportActionBar(toolbar_search_result);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar_search_result.getNavigationIcon().setColorFilter(getResources().getColor(R.color.back), PorterDuff.Mode.SRC_ATOP);

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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SearchResultActivity.this, SearchActivity.class);
        startActivity(intent);
        super.onBackPressed();
        finish();
    }

    private void getContentWithTags() {

        FileApi fileApi = RetrofitClient.getRetroClient().create(FileApi.class);
        Call<GetContentsWithTagIdsResult> call = fileApi.getContentWithTags(selectedTags, 1, 10, "LastItem");
        call.enqueue(new Callback<GetContentsWithTagIdsResult>() {
            @Override
            public void onResponse(Call<GetContentsWithTagIdsResult> call, Response<GetContentsWithTagIdsResult> response) {
                GetContentsWithTagIdsResult apiResponse = new GetContentsWithTagIdsResult();
                apiResponse = response.body();
                if (result_s != null) {
                    result_s = apiResponse.getResult();
                    init();
                    linearLayout.setVisibility(View.GONE);


                }


            }

            @Override
            public void onFailure(Call<GetContentsWithTagIdsResult> call, Throwable t) {
                Log.e("Tag", "error");


            }
        });


    }

}
