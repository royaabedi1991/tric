package com.a3rick.a3rick.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.a3rick.a3rick.R;
import com.a3rick.a3rick.models.ApiModels.Teepeto.Results.GetContentResult;
import com.a3rick.a3rick.models.ApiModels.Teepeto.Results.Result_;
import com.a3rick.a3rick.webService.Teepeto.FileApi;
import com.a3rick.a3rick.webService.Teepeto.RetrofitClient;
import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.VideoView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ContentActivity extends AppCompatActivity implements OnPreparedListener {
    int Possition;
    private String URL0;
    private String URL1;
    private String URL2;
    private String URL3;

    public List<Result_> resultS;
    private String headerImageFileAddress;
    private String body;
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




        getContents();







    }



    private void getContents() {
        FileApi fileApi = RetrofitClient.getRetroClient().create(FileApi.class);
        Call<GetContentResult> call = fileApi.getContent(/*"{{Token}}",*/11, 1, 5, "LastItem");
        call.enqueue(new Callback<GetContentResult>() {
            @Override
            public void onResponse(Call<GetContentResult> call, Response<GetContentResult> response) {
                GetContentResult apiResponse = new GetContentResult();
                apiResponse = response.body();
                resultS = apiResponse.getResult();
                URL0 = resultS.get(0).getVideoFileAddress().toString();
                URL1 = resultS.get(1).getVideoFileAddress().toString();
                URL2 = resultS.get(2).getVideoFileAddress().toString();
                URL3 = resultS.get(3).getVideoFileAddress().toString();


                Intent intent = getIntent();
                Possition = intent.getIntExtra("BaNNERITEMPOSITION", 1);
                setupVideoView();

            }

            @Override
            public void onFailure(Call<GetContentResult> call, Throwable t) {
                Log.e("Tag", "error");

            }
        });


    }
    private void setupVideoView() {

        // Make sure to use the correct VideoView import
        videoView = (VideoView)findViewById(R.id.video_view);
        videoView.setOnPreparedListener(this);
        videoView.showControls();
        //For now we just picked an arbitrary item to play



        switch (Possition) {

            case 0:
                videoView.setVideoURI(Uri.parse(URL0));
                break;

            case 1:
                videoView.setVideoURI(Uri.parse(URL1));
                break;

            case 2:
                videoView.setVideoURI(Uri.parse(URL2));
                break;

            case 3:
                videoView.setVideoURI(Uri.parse(URL3));
                break;
        }

    }


    @Override
    public void onPrepared() {
        videoView.start();
        videoView.showControls();
    }
}
