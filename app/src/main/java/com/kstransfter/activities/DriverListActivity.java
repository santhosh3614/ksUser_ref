package com.kstransfter.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.kstransfter.R;
import com.kstransfter.adapters.DriverListAdapter;
import com.kstransfter.fragments.DriverDetailsFragment;
import com.kstransfter.models.app.Driver;

import java.util.ArrayList;

public class DriverListActivity extends BaseActivity {

    private ImageView imgMenu, imgBack;
    private RecyclerView rvDriverList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_list);
        try {
            initial();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initial() {
        imgMenu = findViewById(R.id.imgMenu);
        imgBack = findViewById(R.id.imgBack);
        imgMenu.setVisibility(View.GONE);
        imgBack.setVisibility(View.VISIBLE);
        rvDriverList = findViewById(R.id.rvDriverList);
        rvDriverList.setLayoutManager(new LinearLayoutManager(this));
     /*   for (int i = 0; i < 5; i++) {
            Driver driver = new Driver();
            driver.setName("rahul Kumar");
            driver.setName("exp:  6 years");
            drivers.add(driver);
        }
        DriverListAdapter driverListAdapter = new DriverListAdapter(this, drivers, (view, pos) -> {
            replaceFragmenr(DriverDetailsFragment.getInstance(), DriverDetailsFragment.TAG, false);
        });*/
//        rvDriverList.setAdapter(driverListAdapter);
        imgBack.setOnClickListener(v -> {
            super.onBackPressed();
        });
    }

}
