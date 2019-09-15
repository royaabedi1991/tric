package com.a3rick.a3rick.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.a3rick.a3rick.R;
import com.a3rick.a3rick.models.models.OTP.ApiResult;
import com.a3rick.a3rick.webService.OTP.APIClient;
import com.a3rick.a3rick.webService.OTP.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SubscribeActivity extends AppCompatActivity/* implements View.OnClickListener*/ {


    private EditText etPin;
    private TextView Emassage;
    private Button subscribe;
    private Button btnSubscribe;
    private String ServiceId;
    private String TransactionId;
    private Long mobileNumber;
    private String result;
    private String message;
    private String fromWhere;
    private ImageView logo;
    private Long ressult;
    private Boolean isSucssesful;
    private ProgressBar progressBar;
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    public SubscribeActivity() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_subscribe);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_subscribe);

        init();
        subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator();
            }
        });

    }

    void init() {
        Intent intent = getIntent();
        TransactionId = intent.getStringExtra("TRANSACTIONID");
        mobileNumber = Long.valueOf(intent.getStringExtra("NUMBER"));
//        message = intent.getStringExtra("MASSAGE");
//        result = (intent.getStringExtra("RESULT"));
        fromWhere = (intent.getStringExtra("FROMWHER"));


        etPin = findViewById(R.id.etPin);
        Emassage = findViewById(R.id.TVmasege);
        Emassage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubscribeActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
        subscribe = findViewById(R.id.sub);
        ServiceId = "11";
        progressBar = findViewById(R.id.pr_subscribe);


    }

    public void validator() {

        String errorString = "کد صحیح را وارد کنید";  // Your custom error message.
        int errorColor = getResources().getColor(R.color.dot_dark_screen1);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(errorColor);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(errorString);
        spannableStringBuilder.setSpan(foregroundColorSpan, 0, errorString.length(), 0);
        if (!etPin.getText().toString().equals("") & (etPin.getText().length() == 4)) {


            if (fromWhere.equals("RESULT")) {
                subscribeUser(ServiceId, TransactionId, String.valueOf(etPin.getText()));
            } else if (fromWhere.equals("MESSAGE")) {
                subscribeUserViaCode(ServiceId, TransactionId, String.valueOf(etPin.getText()));
            }

        } else if (etPin.getText().length() < 4 && !etPin.getText().toString().equals("")) {
            etPin.setError(errorString);
            etPin.setBackgroundResource(R.drawable.et_red_back);
            progressBar.setVisibility(View.GONE);
        } else if (etPin.getText().toString().equals("")) {

            etPin.setError(errorString);
            etPin.setBackgroundResource(R.drawable.et_red_back);
        }
    }

    private void subscribeUser(String ServiceId, String TransactionId, String Pin) {
        progressBar.setVisibility(View.VISIBLE);
        subscribe.setVisibility(View.GONE);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ApiResult> call = apiInterface.subscribeUser(ServiceId, TransactionId, etPin.getText().toString());
        call.enqueue(new Callback<ApiResult>() {
            @Override
            public void onResponse(Call<ApiResult> call, Response<ApiResult> response) {
                CoordinatorLayout coordinatorLayout = findViewById(R.id.crd_layout);


                ApiResult apiResponse = new ApiResult();
                apiResponse = response.body();
                if (apiResponse.getIsSuccessful() == true && apiResponse.getResult().equals(mobileNumber)) {

                    Intent intent = new Intent(SubscribeActivity.this, MainActivity.class);
                    intent.putExtra("PHONE", String.valueOf(mobileNumber));
                    startActivity(intent);
                    finish();

                    finish();
                    Toast.makeText(SubscribeActivity.this,
                            "عضویت با موفقیت انجام شد "

                            , Toast.LENGTH_LONG).show();
                } else if (apiResponse.getIsSuccessful() == false && apiResponse.getResult() == 0) {
                    progressBar.setVisibility(View.GONE);
                    subscribe.setVisibility(View.VISIBLE);
                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "کد وارد شده اشتباه است، دوباره تلاش کنید", Snackbar.LENGTH_LONG);
                    TextView mainTextView = (TextView) (snackbar.getView()).findViewById(android.support.design.R.id.snackbar_text);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                        mainTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    else
                        mainTextView.setGravity(Gravity.CENTER_HORIZONTAL);
                    mainTextView.setGravity(Gravity.CENTER_HORIZONTAL);
                    snackbar.show();

                } else {
                    progressBar.setVisibility(View.GONE);
                    subscribe.setVisibility(View.VISIBLE);
                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "خطای سیستمی", Snackbar.LENGTH_LONG);
                    TextView mainTextView = (TextView) (snackbar.getView()).findViewById(android.support.design.R.id.snackbar_text);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                        mainTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    else
                        mainTextView.setGravity(Gravity.CENTER_HORIZONTAL);
                    mainTextView.setGravity(Gravity.CENTER_HORIZONTAL);
                    snackbar.show();
                }


            }


            @Override
            public void onFailure(Call<ApiResult> call, Throwable t) {
                Emassage.setText(t.toString());
                Emassage.setText(t.toString());
            }
        });

    }

    private void subscribeUserViaCode(String ServiceId, String TransactionId, String Pin) {
        progressBar.setVisibility(View.VISIBLE);
        subscribe.setVisibility(View.GONE);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ApiResult> call = apiInterface.subscribeUserViaCode(ServiceId, TransactionId, Pin);
        call.enqueue(new Callback<ApiResult>() {
            @Override
            public void onResponse(Call<ApiResult> call, Response<ApiResult> response) {
                CoordinatorLayout coordinatorLayout = findViewById(R.id.crd_layout);
                ApiResult apiResponse = response.body();

                if (apiResponse.getIsSuccessful() == true && apiResponse.getResult().equals(mobileNumber)) {
                    Intent intent = new Intent(SubscribeActivity.this, MainActivity.class);
                    intent.putExtra("PHONE", String.valueOf(mobileNumber));

                    startActivity(intent);
                    finish();
                    Toast.makeText(SubscribeActivity.this,
                            "عضویت با موفقیت انجام شد "

                            , Toast.LENGTH_LONG).show();
                } else if (apiResponse.getIsSuccessful() == false && apiResponse.getResult() == -2) {
                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "خطای سیستمی، لطفا مجدد تلاش کنید", Snackbar.LENGTH_LONG);
                    TextView mainTextView = (TextView) (snackbar.getView()).findViewById(android.support.design.R.id.snackbar_text);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                        mainTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    else
                        mainTextView.setGravity(Gravity.CENTER_HORIZONTAL);
                    mainTextView.setGravity(Gravity.CENTER_HORIZONTAL);
                    snackbar.show();
                    progressBar.setVisibility(View.GONE);
                    subscribe.setVisibility(View.VISIBLE);
                } else if (apiResponse.getIsSuccessful() == false && apiResponse.getResult() == -4) {
                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "پین کد وارد شده اشتباه است، لطفا دوباره تلاش کنید", Snackbar.LENGTH_LONG).setActionTextColor(2);
                    TextView mainTextView = (TextView) (snackbar.getView()).findViewById(android.support.design.R.id.snackbar_text);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                        mainTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    else
                        mainTextView.setGravity(Gravity.CENTER_HORIZONTAL);
                    mainTextView.setGravity(Gravity.CENTER_HORIZONTAL);
                    snackbar.show();
                    progressBar.setVisibility(View.GONE);
                    subscribe.setVisibility(View.VISIBLE);
                } else {

                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "مشترک گرامی لطفا بعد از چند دقیقه دوباره تلاش کنید", Snackbar.LENGTH_LONG);
                    TextView mainTextView = (TextView) (snackbar.getView()).findViewById(android.support.design.R.id.snackbar_text);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                        mainTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    else
                        mainTextView.setGravity(Gravity.CENTER_HORIZONTAL);
                    mainTextView.setGravity(Gravity.CENTER_HORIZONTAL);
                    snackbar.show();
                }
            }

            @Override
            public void onFailure(Call<ApiResult> call, Throwable t) {
                Emassage.setText(t.toString());
            }
        });

    }


}
