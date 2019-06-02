package com.a3rick.a3rick.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.a3rick.a3rick.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class CategoryActivity extends AppCompatActivity {
    
//    private Toolbar categoryToolbar;
//    private CardView categoryCard;
//    ImageView categoryImage;
//    TextView tvTitleCategory;

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_category);
       
        
    }

//    private void init() {
//        categoryToolbar= findViewById(R.id.toolbar_category);
//        categoryCard= findViewById(R.id.card_category);
//        categoryImage= findViewById(R.id.img_category);
//        tvTitleCategory= findViewById(R.id.tv_category);
//
//    }
}
