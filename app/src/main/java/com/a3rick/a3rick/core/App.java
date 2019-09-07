package com.a3rick.a3rick.core;

import android.app.Application;

import com.a3rick.a3rick.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class App extends Application {
//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
//   }

    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/IRANSans_UltraLight.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
