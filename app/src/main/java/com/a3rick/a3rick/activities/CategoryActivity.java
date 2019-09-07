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
import android.widget.TextView;

import com.a3rick.a3rick.ChangeCategoryItem;
import com.a3rick.a3rick.R;
import com.a3rick.a3rick.RecyclerViewClicked;
import com.a3rick.a3rick.adapters.CategoriesAdapter;
import com.a3rick.a3rick.models.models.Trick.content_with_categoriId.GetContentResult;
import com.a3rick.a3rick.models.models.Trick.content_with_categoriId.Result;
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

public class CategoryActivity extends AppCompatActivity {
    Toolbar toolbar_beauty_category;
    TextView tvLikeCount;
    TextView tvViewCount;
    TextView tvCategory;
    String title = "";
    int position;
    CategoriesAdapter beautyCategoryAdapter;
    List<Result> result_s = new ArrayList<>();


    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_beauty_category);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Intent intent = getIntent();
        position = intent.getIntExtra("POSOTION", 1);
        title = intent.getStringExtra("TITLE");


        switch (position) {
            case 0:
                getCategoryContents(1);


                break;

            case 1:

                getCategoryContents(2);

            case 2:
                getCategoryContents(3);
                break;

            case 3:
                getCategoryContents(4);
                break;


        }


    }

    static View customView;

    private void init() {
        RecyclerView categoryBeautyRecyceler = findViewById(R.id.category_beauty_recycler);
        LinearLayoutManager categoryBeautyManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        categoryBeautyRecyceler.setLayoutManager(categoryBeautyManager);
        beautyCategoryAdapter = new CategoriesAdapter(getApplicationContext(), result_s, new RecyclerViewClicked() {
            @Override
            public void onIteMRecycler(View view, String id, Result current, ChangeCategoryItem changeCategoryItem) {
                Intent intent = new Intent(view.getContext(), ContentActivity.class);

                intent.putExtra("VIDEOADRESS", current.getVideoFileAddress());
                intent.putExtra("SUBJECT", current.getSubject());
                intent.putExtra("BODY", current.getBody());
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
        categoryBeautyRecyceler.setAdapter(beautyCategoryAdapter);

        tvLikeCount = findViewById(R.id.like_count_categories);
        tvViewCount = findViewById(R.id.view_count_categories);
        tvCategory = findViewById(R.id.tv_category_title);

        tvCategory.setText(title);

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


        super.onResume();
//        getBeautyCategoryContents(position);
    }

    private void getCategoryContents(int c) {
        int catId = c;

        FileApi fileApi = RetrofitClient.getRetroClient().create(FileApi.class);
        Call<GetContentResult> call = fileApi.getContent(/*"{{Token}}",*/catId, 1, 50, "LastItem");
        call.enqueue(new Callback<GetContentResult>() {
            @Override
            public void onResponse(Call<GetContentResult> call, Response<GetContentResult> response) {
                GetContentResult apiResponse = new GetContentResult();
                apiResponse = response.body();
                if (result_s != null) {
                    result_s = apiResponse.getResult();
                    init();


                }

            }

            @Override
            public void onFailure(Call<GetContentResult> call, Throwable t) {
                Log.e("Tag", "error");
            }
        });


    }





    //    private void getCoockCategoryContents() {
//        FileApi fileApi = RetrofitClient.getRetroClient().create(FileApi.class);
//        Call<GetContentResult> call = fileApi.getContent(/*"{{Token}}",*/1, 1, 50, "LastItem");
//        call.enqueue(new Callback<GetContentResult>() {
//            @Override
//            public void onResponse(Call<GetContentResult> call, Response<GetContentResult> response) {
//                GetContentResult apiResponse = new GetContentResult();
//                apiResponse = response.body();
//                if (result_s != null) {
//                    result_s = apiResponse.getResult();
//                    init();
//                    tvCategory.setText("آشپزی");
//
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<GetContentResult> call, Throwable t) {
//                Log.e("Tag", "error");
//
//            }
//        });
//
//
//    }
//
//    private void getHouseCategoryContents() {
//        FileApi fileApi = RetrofitClient.getRetroClient().create(FileApi.class);
//        Call<GetContentResult> call = fileApi.getContent(/*"{{Token}}",*/2, 1, 50, "LastItem");
//        call.enqueue(new Callback<GetContentResult>() {
//            @Override
//            public void onResponse(Call<GetContentResult> call, Response<GetContentResult> response) {
//                GetContentResult apiResponse = new GetContentResult();
//                apiResponse = response.body();
//                if (result_s != null) {
//                    result_s = apiResponse.getResult();
//                    init();
//                    tvCategory.setText("خانه داری");
//
//
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<GetContentResult> call, Throwable t) {
//                Log.e("Tag", "error");
//
//            }
//        });
//
//
//    }
//
//    private void getFunCategoryContents() {
//        FileApi fileApi = RetrofitClient.getRetroClient().create(FileApi.class);
//        Call<GetContentResult> call = fileApi.getContent(/*"{{Token}}",*/4, 1, 50, "LastItem");
//        call.enqueue(new Callback<GetContentResult>() {
//            @Override
//            public void onResponse(Call<GetContentResult> call, Response<GetContentResult> response) {
//                GetContentResult apiResponse = new GetContentResult();
//                apiResponse = response.body();
//                if (result_s != null) {
//                    result_s = apiResponse.getResult();
//                    init();
//                    tvCategory.setText("سرگرمی");
//
//
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<GetContentResult> call, Throwable t) {
//                Log.e("Tag", "error");
//
//            }
//        });
//
//
//    }
//    private static class ChangeCategoryImpl implements ChangeCategoryItem, Serializable {
//
//
//        @Override
//        public void changeDate(int countView, int likeCount, int id) {
//            Log.e("d", "D");
////            result_s.get(id).setLikeCount(likeCount);
////            result_s.get(id).setViewCount(countView);
////            beautyCategoryAdapter.notifyDataSetChanged();
////            ((TextView) customView.findViewById(R.id.like_count_categories)).setText(countView);
//
//        }
//    }
}
