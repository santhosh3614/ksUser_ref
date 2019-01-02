package com.kstransfter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.kstransfter.R;
import com.kstransfter.utils.SessionManager;

public class SplashActivity extends BaseActivity {

    private SessionManager sessionManager;
    private LinearLayout llResendOtp;

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
        sessionManager = new SessionManager(this);
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sessionManager.getUserId().equalsIgnoreCase("-1")) {
                    Intent intent = new Intent(SplashActivity.this, TutorialActivity.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                } else {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            }
        }, 200);


    }
}
