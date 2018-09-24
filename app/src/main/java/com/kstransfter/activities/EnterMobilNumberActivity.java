package com.kstransfter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.kstransfter.R;

/**
 * Created by SONI on 9/23/2018.
 */

public class EnterMobilNumberActivity extends BaseActivity {

    private TextView txtNext;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_mobile_number);
        try {
            initial();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initial() {
        txtNext = findViewById(R.id.txtNext);
        txtNext.setOnClickListener(v -> {
            Intent intent = new Intent(EnterMobilNumberActivity.this, OtpActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

    }
}
