package com.a3rick.a3rick.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.a3rick.a3rick.ChangeCategoryItem;
import com.a3rick.a3rick.R;
import com.a3rick.a3rick.models.models.Trick.content_with_categoriId.AllTag;
import com.a3rick.a3rick.models.models.Trick.content_with_contentId.GetContentWithIdResult;
import com.a3rick.a3rick.models.models.Trick.favorites.AddFavoriteContentResult;
import com.a3rick.a3rick.models.models.Trick.favorites.DeleteFavoriteContentResult;
import com.a3rick.a3rick.models.models.Trick.like_view.GetLikeDisLikeResult;
import com.a3rick.a3rick.models.models.Trick.like_view.GetViewCountResult;
import com.a3rick.a3rick.webService.Trick.FileApi;
import com.a3rick.a3rick.webService.Trick.RetrofitClient;
import com.adroitandroid.chipcloud.ChipCloud;
import com.devbrackets.android.exomedia.listener.OnCompletionListener;
import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.VideoView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ContentActivity extends AppCompatActivity implements OnPreparedListener {
    TextView tvSubject;
    TextView tvSubject1;
    TextView tvBody;
    TextView tvLikeCount;
    TextView tvViewCount;
    ChipCloud tvTag;
    int contentId;
    String videoFileAddress;
    String subject;
    Boolean isLiked;
    Boolean isBookmarked;
    Integer viewCount;
    Integer likeCount;
    List<AllTag> tags;
    String body;
    private VideoView videoView;
    private Toolbar toolbar;
    LinearLayout likeLinearLayout;
    LinearLayout bodyLinearLayout;
    private LinearLayout holder;
    boolean firstPlay = true;
    private int orientation;
    Button like;
    Button dislike;
    Button addFavorite;
    Button deleteFavorite;
    Button share;
    ChangeCategoryItem ChangeCategory;

    public ContentActivity() {
    }

    private int totalLike;
    private int totalView;
    private int favoriteId;


    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_content);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        init();
        getContentWithId();
        getViewCount();


    }


    private void init() {
        share = findViewById(R.id.share);
        tvSubject = findViewById(R.id.tvSubject);
        tvSubject1 = findViewById(R.id.tv_subject);
        tvBody = findViewById(R.id.tvBody);
        tvLikeCount = findViewById(R.id.tv_like);
        tvViewCount = findViewById(R.id.tv_view);
        tvTag = findViewById(R.id.chip_cloud);
        addFavorite = findViewById(R.id.add_favorite);
        addFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContentToFavorite();
            }
        });
        deleteFavorite = findViewById(R.id.delete_favorite);
        deleteFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteContentToFavorite();
            }
        });


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, videoFileAddress);
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);

            }
        });
        like = findViewById(R.id.like);
        dislike = findViewById(R.id.dislike);
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getLikeDislike();


            }
        });
        dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLikeDislike();

            }
        });

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.back), PorterDuff.Mode.SRC_ATOP);


        likeLinearLayout = findViewById(R.id.like_linear);
        bodyLinearLayout = findViewById(R.id.linear_body);
        holder = findViewById(R.id.holder);


        Intent intent = getIntent();


        videoFileAddress = intent.getStringExtra("VIDEOADRESS");
        body = intent.getStringExtra("BODY");
        subject = intent.getStringExtra("SUBJECT");
        isBookmarked = intent.getBooleanExtra("ISBOOKMARKED", true);
        isLiked = intent.getBooleanExtra("ISLIKED", true);
        viewCount = intent.getIntExtra("VIECOUNT", 1);
        likeCount = intent.getIntExtra("LIKECOUNT", 20);
        tags = (List<AllTag>) intent.getSerializableExtra("TAGS");
        contentId = intent.getIntExtra("CONTENTID", 1);
        ChangeCategory = (ChangeCategoryItem) intent.getSerializableExtra("ChangeCategory");
        setupVideoView();
        orientation = getResources().getConfiguration().orientation;


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
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            showJustVideo();
            toolbar.setVisibility(View.GONE);

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            showVideoAndRelated();
        }
    }

    private void showVideoAndRelated() {
        likeLinearLayout.setVisibility(View.VISIBLE);
        bodyLinearLayout.setVisibility(View.VISIBLE);
        toolbar.setVisibility(View.VISIBLE);
        setMatchParent(holder);
        resetHeight(videoView);
    }

    private void showJustVideo() {
        likeLinearLayout.setVisibility(View.GONE);
        bodyLinearLayout.setVisibility(View.GONE);
        setMatchParent1(holder);
        setMatchParent1(videoView);


    }

    private void setupVideoView() {

        videoView = findViewById(R.id.video_view);
        videoView.setOnPreparedListener(this);
        videoView.showControls();
        videoView.setVideoURI(Uri.parse(videoFileAddress));
        tvSubject.setText(subject);
        tvSubject1.setText(subject);
        tvBody.setText(body);
        if (tags != null)
            tvTag.addChips(increaseArray(tags));
        videoView.setOnPreparedListener(this);
        videoView.getVideoControls().hide();
        videoView.getPreviewImageView().setVisibility(View.INVISIBLE);
        videoView.setOnCompletionListener(new OnCompletionListener() {
            @Override
            public void onCompletion() {
                videoView.restart();
                firstPlay = false;
            }
        });
    }

    public String[] increaseArray(List<AllTag> tags) {
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

    private void setMatchParent(LinearLayout layout) {
        ViewGroup.LayoutParams params = layout.getLayoutParams();
        params.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        params.height = RelativeLayout.LayoutParams.MATCH_PARENT;
        layout.setLayoutParams(params);
    }

    private void setMatchParent1(View layout) {
        ViewGroup.LayoutParams params = layout.getLayoutParams();
        params.width = getScreenWidth();
        params.height = getScreenHeight();
        layout.setLayoutParams(params);
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    private void resetHeight(RelativeLayout layout) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) layout.getLayoutParams();
        params.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, getResources().getDisplayMetrics());
        params.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        layout.setLayoutParams(params);
    }


    @Override
    public void onPause() {
        super.onPause();
        videoView.pause();
    }


    @Override
    public void onPrepared() {
        if (firstPlay) {
            videoView.start();
        } else {
            videoView.pause();
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

//       Intent intent = new Intent();
//       intent.putExtra("CONTENTID_APDATE",contentId);
//       startActivity(intent);
//        Intent intent0 = new Intent(ContentActivity.this, CoockCategoryActivity.class);
//        startActivity(intent0);
//
//        Intent intent1 = new Intent(ContentActivity.this, HouseCategoryActivity.class);
//        startActivity(intent1);
//
//        Intent intent2 = new Intent(ContentActivity.this, CategoryActivity.class);
//        startActivity(intent2);
//
//        Intent intent3 = new Intent(ContentActivity.this, FunCategoryActivity.class);
//        startActivity(intent3);

        finish();

    }

    private void getLikeDislike() {

        FileApi fileApi = RetrofitClient.getRetroClient().create(FileApi.class);
        Call<GetLikeDisLikeResult> call = fileApi.getLikeDisLike(contentId);
        call.enqueue(new Callback<GetLikeDisLikeResult>() {
            @Override
            public void onResponse(Call<GetLikeDisLikeResult> call, Response<GetLikeDisLikeResult> response) {
                GetLikeDisLikeResult apiResponse = new GetLikeDisLikeResult();
                apiResponse = response.body();
                totalLike = apiResponse.getResult().getTotalLike();

                if (apiResponse.getResult().getIsLiked() == true) {
                    tvLikeCount.setText(String.valueOf(totalLike));
                    like.setVisibility(View.GONE);
                    dislike.setVisibility(View.VISIBLE);
//                    ChangeCategory.changeDate(viewCount, likeCount, contentId);
                } else if (apiResponse.getResult().getIsLiked() == false & like.isCursorVisible()) {
                    tvLikeCount.setText(String.valueOf(totalLike));
                    like.setVisibility(View.VISIBLE);
                    dislike.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<GetLikeDisLikeResult> call, Throwable t) {
                Log.e("Tag", "error");
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast,
                        (ViewGroup) findViewById(R.id.custom_toast_container));

                TextView text = (TextView) layout.findViewById(R.id.text);
                text.setText("درخواست با خطا مواجه شد");

                Toast toast = new Toast(getApplicationContext());
//                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();

            }
        });


    }


//    private void getLikeState() {
//
//        FileApi fileApi = RetrofitClient.getRetroClient().create(FileApi.class);
//        Call<GetLikeStateResult> call = fileApi.getLikeState(contentId);
//        call.enqueue(new Callback<GetLikeStateResult>() {
//            @Override
//            public void onResponse(Call<GetLikeStateResult> call, Response<GetLikeStateResult> response) {
//                GetLikeStateResult apiResponse = new GetLikeStateResult();
//                apiResponse = response.body();
//                if (apiResponse.getResult() == true) {
//
//
//                } else if (apiResponse.getResult() == false) {
//                    like.setVisibility(View.VISIBLE);
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<GetLikeStateResult> call, Throwable t) {
//                Log.e("Tag", "error");
//
//            }
//        });
//
//
//    }


    private void getViewCount() {

        FileApi fileApi = RetrofitClient.getRetroClient().create(FileApi.class);
        Call<GetViewCountResult> call = fileApi.getViewCount(contentId);
        call.enqueue(new Callback<GetViewCountResult>() {
            @Override
            public void onResponse(Call<GetViewCountResult> call, Response<GetViewCountResult> response) {

                GetViewCountResult apiResponse = new GetViewCountResult();
                apiResponse = response.body();
                totalView = apiResponse.getResult();

                tvViewCount.setText(String.valueOf(totalView));

            }

            @Override
            public void onFailure(Call<GetViewCountResult> call, Throwable t) {
                Log.e("Tag", "error");

            }
        });


    }


    private void getContentWithId() {

        FileApi fileApi = RetrofitClient.getRetroClient().create(FileApi.class);
        Call<GetContentWithIdResult> call = fileApi.getContentWithId(contentId);
        call.enqueue(new Callback<GetContentWithIdResult>() {
            @Override
            public void onResponse(Call<GetContentWithIdResult> call, Response<GetContentWithIdResult> response) {
//                Intent intent1 = new Intent(ContentActivity.this,CategoryActivity.class );
//                Intent intent2 = new Intent(ContentActivity.this,ContentActivity.class );
//                Intent intent3 = new Intent(ContentActivity.this,FunCategoryActivity.class );
//                Intent intent4 = new Intent(ContentActivity.this,HouseCategoryActivity.class );
                GetContentWithIdResult apiResponse = new GetContentWithIdResult();
                apiResponse = response.body();
                favoriteId = apiResponse.getResult().getFavoriteId();

                int likeCount = apiResponse.getResult().getLikeCount();
//                intent1.putExtra("LIKECOUNT",likeCount);
//                intent1.putExtra("TOTALVIEW",totalView);
//                intent2.putExtra("LIKECOUNT",likeCount);
//                intent2.putExtra("TOTALVIEW",totalView);
//                intent3.putExtra("LIKECOUNT",likeCount);
//                intent3.putExtra("TOTALVIEW",totalView);
//                intent4.putExtra("LIKECOUNT",likeCount);
//                intent4.putExtra("TOTALVIEW",totalView);


                tvLikeCount.setText(String.valueOf(likeCount));
                if (apiResponse.getResult().getIsLiked() == true) {
                    like.setVisibility(View.GONE);
                    dislike.setVisibility(View.VISIBLE);
                } else if (apiResponse.getResult().getIsLiked() == true) {
                    like.setVisibility(View.VISIBLE);
                    dislike.setVisibility(View.GONE);
                }
                if (apiResponse.getResult().getIsBookmarked() == true) {
                    addFavorite.setVisibility(View.GONE);
                    deleteFavorite.setVisibility(View.VISIBLE);
                } else if (apiResponse.getResult().getIsBookmarked() == false) {
                    addFavorite.setVisibility(View.VISIBLE);
                    deleteFavorite.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<GetContentWithIdResult> call, Throwable t) {
                Log.e("Tag", "error");
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast,
                        (ViewGroup) findViewById(R.id.custom_toast_container));

                TextView text = (TextView) layout.findViewById(R.id.text);
                text.setText("درخواست با خطا مواجه شد");

                Toast toast = new Toast(getApplicationContext());
//                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();

            }
        });


    }

    private void addContentToFavorite() {

        FileApi fileApi = RetrofitClient.getRetroClient().create(FileApi.class);
        Call<AddFavoriteContentResult> call = fileApi.addFavorite(contentId);
        call.enqueue(new Callback<AddFavoriteContentResult>() {
            @Override
            public void onResponse(Call<AddFavoriteContentResult> call, Response<AddFavoriteContentResult> response) {
                AddFavoriteContentResult apiResponse = new AddFavoriteContentResult();
                apiResponse = response.body();


                addFavorite.setVisibility(View.GONE);
                deleteFavorite.setVisibility(View.VISIBLE);

                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast,
                        (ViewGroup) findViewById(R.id.custom_toast_container));

                TextView text = (TextView) layout.findViewById(R.id.text);
                text.setText("به لیست علاقه مندی ها افزوده شد");

                Toast toast = new Toast(getApplicationContext());
//                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

            }

            @Override
            public void onFailure(Call<AddFavoriteContentResult> call, Throwable t) {
                Log.e("Tag", "error");
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast,
                        (ViewGroup) findViewById(R.id.custom_toast_container));

                TextView text = (TextView) layout.findViewById(R.id.text);
                text.setText("درخواست با خطا مواجه شد");

                Toast toast = new Toast(getApplicationContext());
//                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);
                toast.show();
            }
        });


    }


    private void deleteContentToFavorite() {

        FileApi fileApi = RetrofitClient.getRetroClient().create(FileApi.class);
        Call<DeleteFavoriteContentResult> call = fileApi.deleteFavorite(favoriteId);
        call.enqueue(new Callback<DeleteFavoriteContentResult>() {
            @Override
            public void onResponse(Call<DeleteFavoriteContentResult> call, Response<DeleteFavoriteContentResult> response) {
                DeleteFavoriteContentResult apiResponse = new DeleteFavoriteContentResult();
                apiResponse = response.body();

                addFavorite.setVisibility(View.VISIBLE);
                deleteFavorite.setVisibility(View.GONE);

                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast,
                        (ViewGroup) findViewById(R.id.custom_toast_container));

                TextView text = (TextView) layout.findViewById(R.id.text);
                text.setText("از لیست علاقه مندی ها حذف شد");

                Toast toast = new Toast(getApplicationContext());
//                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.setView(layout);
                toast.show();

            }

            @Override
            public void onFailure(Call<DeleteFavoriteContentResult> call, Throwable t) {
                Log.e("Tag", "error");
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast,
                        (ViewGroup) findViewById(R.id.custom_toast_container));

                TextView text = (TextView) layout.findViewById(R.id.text);
                text.setText("درخواست با خطا مواجه شد");

                Toast toast = new Toast(getApplicationContext());
//                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();

            }
        });


    }


}
