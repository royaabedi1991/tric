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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a3rick.a3rick.R;
import com.a3rick.a3rick.models.ApiModels.Trick.Results.AllTag;
import com.a3rick.a3rick.models.ApiModels.Trick.Results.GetLikeStateResult;
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

public class ContentActivity extends AppCompatActivity implements OnPreparedListener, View.OnClickListener {
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
    ImageButton back_press;
    Button like;
    Button dislike;

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
        getLikeState();

    }

    private void init() {

        tvSubject = findViewById(R.id.tvSubject);
        tvSubject1 = findViewById(R.id.tv_subject);
        tvBody = findViewById(R.id.tvBody);
        tvLikeCount = findViewById(R.id.tv_like);
        tvViewCount = findViewById(R.id.tv_view);
        tvTag = findViewById(R.id.chip_cloud);
        like = findViewById(R.id.like);
        dislike = findViewById(R.id.dislike);
        like.setOnClickListener(this);

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
        likeCount = intent.getIntExtra("LIKECOUNT", 1);
        tags = (List<AllTag>) intent.getSerializableExtra("TAGS");
        contentId = intent.getIntExtra("CONTENTID", 1);
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
        tvViewCount.setText(String.valueOf(viewCount));
        tvLikeCount.setText(String.valueOf(likeCount));
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
        int i = tags.size();
        int n = i++;
        String[] newArray = new String[n];
        for (int cnt = 0; cnt < tags.size(); cnt++) {
            newArray[cnt] = tags.get(cnt).getTitle();
        }
        return newArray;

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

    }

    private void getLikeState() {

        FileApi fileApi = RetrofitClient.getRetroClient().create(FileApi.class);
        Call<GetLikeStateResult> call = fileApi.getLikeState("{{Token}}",contentId);
        call.enqueue(new Callback<GetLikeStateResult>() {
            @Override
            public void onResponse(Call<GetLikeStateResult> call, Response<GetLikeStateResult> response) {
                GetLikeStateResult apiResponse = new GetLikeStateResult();
                apiResponse = response.body();


            }

            @Override
            public void onFailure(Call<GetLikeStateResult> call, Throwable t) {
                Log.e("Tag", "error");

            }
        });




    }


    @Override
    public void onClick(View v) {
        getLikeState();
    }


}
