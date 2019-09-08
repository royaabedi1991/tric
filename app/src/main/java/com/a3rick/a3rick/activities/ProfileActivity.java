package com.a3rick.a3rick.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.a3rick.a3rick.R;

public class ProfileActivity extends AppCompatActivity {
    String mobileNumner;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mobileNumner = getSharedPreferences("MyPref", 0).getString("PHONENUMBER", "");
        textView = findViewById(R.id.TVnumber);
        textView.setText(mobileNumner);

    }
}
