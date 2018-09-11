package com.kstransfter.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.kstransfter.R;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        try {
            initial();
          } catch (Exception e) {
         }
    }

    @Override
    public void initial() {
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },100);


    }
}
