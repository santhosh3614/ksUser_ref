package com.kstransfter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kstransfter.R;

public class HomeActivity extends BaseActivity {

    //Change home..

    private LinearLayout llCar, llBookDriver;


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

        llCar.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        });

        llBookDriver.setOnClickListener(v -> {

            Toast.makeText(this, "not implemenented", Toast.LENGTH_SHORT).show();

        });

    }

}
