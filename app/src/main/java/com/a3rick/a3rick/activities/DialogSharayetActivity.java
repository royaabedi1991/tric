package com.a3rick.a3rick.activities;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;

import com.a3rick.a3rick.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class DialogSharayetActivity extends Activity implements View.OnClickListener {


    ImageButton btnCloseDialogSharayet;
    WebView www1;
    Button btnDialogSharayet;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_sharayet);
        btnCloseDialogSharayet = findViewById(R.id.btnClose_dialog_sharayet);
        btnDialogSharayet= findViewById(R.id.btn_dialog_sharayet);
        btnDialogSharayet.setOnClickListener(this);
        btnCloseDialogSharayet.setOnClickListener(this);


        this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

//        WebView webView = (WebView) findViewById(R.id.www1);
//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);

//        webView.loadUrl("file:///android_asset/Sample.html");


    }


    @Override
    public void onClick(View v) {
        finish();
    }
}
