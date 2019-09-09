package com.a3rick.a3rick.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.a3rick.a3rick.R;
import com.a3rick.a3rick.models.models.Trick.tags.GetAllTagsResult;
import com.a3rick.a3rick.models.models.Trick.tags.Result;
import com.a3rick.a3rick.webService.Trick.FileApi;
import com.a3rick.a3rick.webService.Trick.RetrofitClient;
import com.adroitandroid.chipcloud.ChipCloud;
import com.adroitandroid.chipcloud.ChipListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SearchActivity extends AppCompatActivity {

    Toolbar toolbar;
    ChipCloud chipCloud;
    Button search;
    List selectedChips;
    List<Result> contentLists;
    ArrayList<String> arrayList;
    List<Integer> selectedindex;
    ProgressBar progressBar;
    ProgressBar progressBar2;

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        // getAllTags();
        init();

    }

    private void init() {
        progressBar = findViewById(R.id.pr_search);
        progressBar2 = findViewById(R.id.pr_search2);
        toolbar = findViewById(R.id.toolbar_search);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.back), PorterDuff.Mode.SRC_ATOP);
        chipCloud = findViewById(R.id.chip_cloud_search);
        getAllTags();
        chipCloud.isSelected(1);
        selectedindex = new ArrayList<>();
        chipCloud.setChipListener(new ChipListener() {
            @Override
            public void chipSelected(int index) {



                selectedindex.add(contentLists.get(index).getTagId());
            }

            @Override
            public void chipDeselected(int index) {

            }
        });

        search = findViewById(R.id.search_bottum);
        selectedChips = selectedindex;

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(selectedChips.size()>0){

                    progressBar.setVisibility(View.VISIBLE);

                    Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                    Bundle bundle = new Bundle();
                    intent.putParcelableArrayListExtra("selectedChips", (ArrayList<? extends Parcelable>) selectedChips);
                    startActivity(intent);
                    finish();
                }else {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast,
                            (ViewGroup) findViewById(R.id.custom_toast_container));

                    TextView text = (TextView) layout.findViewById(R.id.text);
                    text.setText("برچسبی انتخاب نکرده اید");

                    Toast toast = new Toast(getApplicationContext());
//                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();


                }



            }
        });

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


//    public String[] increaseArray(List<Result> tags) {
//        if (tags != null) {
//            int i = tags.size();
//            int n = i++;
//            String[] newArray = new String[n];
//            for (int cnt = 0; cnt < tags.size(); cnt++) {
//                newArray[cnt] = tags.get(cnt).getTitle();
//
//            }
//            return newArray;
//        }
//
//        return null;
//    }


//    public Integer[] increaseArray2(List<Result> tags) {
//        int i = tags.size();
//        int n = i++;
//        Integer[] newArray2 = new Integer[n];
//        for (int cnt = 0; cnt < tags.size(); cnt++) {
//            newArray2[cnt] = tags.get(cnt).getTagId();
//
//            return newArray2;
//        }
//
//        return null;
//    }


    private void getAllTags() {

        FileApi fileApi = RetrofitClient.getRetroClient().create(FileApi.class);
        Call<GetAllTagsResult> call = fileApi.getAllTags();
        call.enqueue(new Callback<GetAllTagsResult>() {
            @Override
            public void onResponse(Call<GetAllTagsResult> call, Response<GetAllTagsResult> response) {
                if (response.isSuccessful()) {
                    if (response.body().getIsSuccessful()) {
//                        arrayList = new ArrayList<String>();
                        contentLists = response.body().getResult();
                        for (int i = 0; i < response.body().getResult().size(); i++) {
                            progressBar2.setVisibility(View.GONE);
                            chipCloud.setVisibility(View.VISIBLE);
                            chipCloud.addChips(new String[]{contentLists.get(i).getTitle()});
                        }

                    }
                }

            }

            @Override
            public void onFailure(Call<GetAllTagsResult> call, Throwable t) {
                Log.e("Tag", "error");


            }
        });


    }


}
