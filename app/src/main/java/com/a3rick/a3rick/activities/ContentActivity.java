package com.a3rick.a3rick.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.a3rick.a3rick.R;
import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.VideoView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ContentActivity extends AppCompatActivity implements OnPreparedListener {
    TextView tvSubject;
    TextView tvBody;
    String videoFileAddress;
    String subject;
    String body;
    private VideoView videoView;

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_content);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        tvSubject = findViewById(R.id.tvSubject);
        tvBody = findViewById(R.id.tvBody);

        Intent intent = getIntent();

        videoFileAddress = intent.getStringExtra("VIDEOADRESS");
        body = intent.getStringExtra("BODY");
        subject = intent.getStringExtra("SUBJECT");

        setupVideoView();


    }


    private void setupVideoView() {

        videoView = (VideoView) findViewById(R.id.video_view);
        videoView.setOnPreparedListener(this);
        videoView.showControls();
        //For now we just picked an arbitrary item to play
        videoView.setVideoURI(Uri.parse(videoFileAddress));
        tvSubject.setText(subject);
        tvBody.setText(body);

    }


    @Override
    public void onPrepared() {
        videoView.start();
        videoView.showControls();
    }
}
