package com.kstransfter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.kstransfter.R;

public class HomeActivity extends BaseActivity {
    //Change home..
    private LinearLayout llCar, llBookDriver;
    private ImageView imgMenu, imgBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        try {
            initial();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initial() {
        llCar = findViewById(R.id.llCar);
        llBookDriver = findViewById(R.id.llBookDriver);
        imgMenu = findViewById(R.id.imgMenu);
        imgBack = findViewById(R.id.imgBack);
        imgMenu.setVisibility(View.GONE);
        imgBack.setVisibility(View.VISIBLE);

        llCar.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        llBookDriver.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, DriverListActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

    }

}
