//package com.example.hillavas.tipnoo;
//
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Color;
//import android.os.Parcelable;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.widget.AppCompatButton;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//
//import com.adroitandroid.chipcloud.ChipCloud;
//import com.adroitandroid.chipcloud.ChipListener;
//import com.adroitandroid.chipcloud.FlowLayout;
//import com.example.hillavas.tipnoo.Models.TagList;
//import com.example.hillavas.tipnoo.Models.TagResults;
//import com.example.hillavas.tipnoo.Retrofit.FileApi;
//import com.example.hillavas.tipnoo.Retrofit.RetroClient;
//import com.google.android.flexbox.FlexboxLayout;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
//
//import static android.view.View.*;
//
//public class SearchActivity extends AppCompatActivity implements View.OnClickListener{
//    RecyclerView searchrecyclerView;
//    Context cx;
//    ChipCloud chipCloud;
//    Button button;
//    List selectedChips;
//    List<TagList> contentLists;
//    // FlexboxLayout flexboxLayout;
//    String[] demoArray;
//    ArrayList<String> arrayList ;
//    ImageView searchBack;
//
//    RelativeLayout relativeLayoutFail,relativeLayoutLoading;
//    TextView lblFailMessage;
//    Button btnFailTryAgain;
//    List<Integer> selectedindex;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search);
//        searchBack=findViewById(R.id.search_back);
//        searchBack.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
//
//        relativeLayoutFail = (RelativeLayout) findViewById(R.id.layout_fail);
//        relativeLayoutLoading=(RelativeLayout)findViewById(R.id.layout_loading);
//        lblFailMessage=findViewById(R.id.lbl_fail);
//        btnFailTryAgain=findViewById(R.id.btn_fail_try_again);
//        btnFailTryAgain.setOnClickListener(this);
//        chipCloud=findViewById(R.id.chip_cloud_search);
//        getAllTags();
//        chipCloud.isSelected(1);
////        new ChipCloud.Configure()
////                .chipCloud(chipCloud)
////                .selectedColor(Color.parseColor("#ff00cc"))
////                .selectedFontColor(Color.parseColor("#ffffff"))
////                .deselectedColor(Color.parseColor("#e1e1e1"))
////                .deselectedFontColor(Color.parseColor("#333333"))
////                .selectTransitionMS(500)
////                .deselectTransitionMS(250)
////
////                .mode(ChipCloud.Mode.MULTI)
////                .allCaps(false)
////                .gravity(ChipCloud.Gravity.CENTER)
////                .textSize(getResources().getDimensionPixelSize(R.dimen.default_textsize))
////                .verticalSpacing(getResources().getDimensionPixelSize(R.dimen.vertical_spacing))
////                .minHorizontalSpacing(getResources().getDimensionPixelSize(R.dimen.min_horizontal_spacing))
////
////                .chipListener(new ChipListener() {
////                    @Override
////                    public void chipSelected(int index) {
////                        Toast.makeText(SearchActivity.this,"nl:"+index,Toast.LENGTH_LONG).show();
////                    }
////                    @Override
////                    public void chipDeselected(int index) {
////                        //...
////                    }
////                });
//        button=findViewById(R.id.button);
//        // flexboxLayout = (FlexboxLayout) findViewById(R.id.flexbox_layout);
//        selectedindex = new ArrayList<>();
//        chipCloud.setChipListener(new ChipListener() {
//            @Override
//            public void chipSelected(int index) {
//                //Toast.makeText(SearchActivity.this,"vvvvvvv"+index,Toast.LENGTH_LONG).show();
//                selectedindex.add(index);
//            }
//
//            @Override
//            public void chipDeselected(int index) {
//
//            }
//        });
//
//
//
//
//        //   chipCloud = new TagCloudView(SearchActivity.this, flexboxLayout, config);
//
//
//        button.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                selectedChips = selectedindex;
//
//                //getSearchResultdContent();
//                Intent intent = new Intent(SearchActivity.this,SearchResultActivity.class);
//                Bundle bundle=new Bundle();
//                intent.putParcelableArrayListExtra("selectedChips", (ArrayList<? extends Parcelable>) selectedChips);
//                startActivity(intent);
//
//            }
//
//
//        });
//
//    }
//    private void getAllTags() {
//        loadingOrFail(true, true);
//        FileApi fileApi = RetroClient.getApiService();
//        final Call<TagResults> contentResultCall=fileApi.getSearchTags();
//        contentResultCall.enqueue(new Callback<TagResults>() {
//            @Override
//            public void onResponse(Call<TagResults> call, Response<TagResults> response) {
//
//
//
//                if(response.isSuccessful()){
//                    if(response.body().getIsSuccessful()) {
//                        arrayList = new ArrayList<String>();
//                        contentLists = response.body().getResult();
//                        for(int i=0;i<response.body().getResult().size();i++){
//                            chipCloud.addChips(new String[]{contentLists.get(i).getText()});
//                        }
//                        // chipCloud.addChips(contentLists);
//                        loadingOrFail(true, false);
//                    }else
//                    {
//                        loadingOrFail(false, true);//fail layout visible
//                        lblFailMessage.setText(String.valueOf(response.body().getMessage()));
//                    }
//                }else
//                {
//                    loadingOrFail(false, true);//fail layout visible
//                    lblFailMessage.setText(getString(R.string.serverError));
//                }
//
//            }
//            @Override
//            public void onFailure(Call<TagResults> call, Throwable t) {
//                loadingOrFail(false, true);//fail layout visible
//                lblFailMessage.setText(getString(R.string.noConnection));
//
//            }
//        });
//
//    }
//
//
//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//
//            case R.id.btn_fail_try_again:
//
//                getAllTags();
//                break;
//
//        }
//    }
//
//
//    void loadingOrFail(boolean hasLoading, boolean showing) {
//        if (hasLoading)//if is loading layout
//        {
//            relativeLayoutFail.setVisibility(View.INVISIBLE);
//            //layout fail hidden gone
//            if (showing) {
//                relativeLayoutLoading.setVisibility(View.VISIBLE);
//                //layout laoding visible
//
//            } else {
//                relativeLayoutLoading.setVisibility(View.INVISIBLE);
//                //layout loading hidden
//            }
//        } else {// if is fail layout
//
//            relativeLayoutLoading.setVisibility(View.INVISIBLE);
//            //layout loadign hidden
//            if (showing) {
//                relativeLayoutFail.setVisibility(View.VISIBLE);
//                //layout fail visible
//            } else {
//                relativeLayoutFail.setVisibility(View.INVISIBLE);
//
//                //layout fail hidden
//            }
//        }
//    }
//    public class TagCloudView extends ChipCloud {
//
//        private ViewGroup layout;
//        private List<TagList> tagListArray;
//        public TagCloudView(Context context, ViewGroup layout, ChipCloud.Configure config) {
//            super(context);
//            // super(context, layout, config);
//            this.layout = layout;
//        }
//
////        @Override
////        public <T> void addChips(T[] objects) {
////            super.addChips(objects);
////        }
//
//
//        public void  addTagsChips(List<TagList> objects) {
//            tagListArray=objects;
//            Iterator var2 = objects.iterator();
//
//            while(var2.hasNext()) {
//                TagList object = (TagList) var2.next();
//                this.addChip(object.getText());
//            }
//
//        }
//
//        // here
//        public List<Integer> getSelectedTagByIndex() {
//            List<Integer> index = new ArrayList<>();
//            for (int i = 0; i < layout.getChildCount(); i++) {
//                // ToggleChip chip = (ToggleChip) this.layout.getChildAt(i);
//                // if (chip.isChecked()) {
//                index.add(tagListArray.get(i).getValue());
//                // }
//            }
//            return index;
//        }
//
//        private int getIndexByView(View view) {
//            for (int i = 0; i < layout.getChildCount(); i++) {
//                View child = this.layout.getChildAt(i);
//                if (child == view) {
//                    return i;
//                }
//            }
//            return -1;
//        }
//
//
//    }
//}
