package com.kstransfter.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kstransfter.R;

public class HomeActivity extends BaseActivity {

    //Change home..

    private LinearLayout llCenterButton;
    private ImageView imgNotification, imgMenu, imgBack;
    private TextView txtCenterTitle;

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
        llCenterButton = findViewById(R.id.llCenterButton);
        imgNotification = findViewById(R.id.imgNotification);
        imgBack = findViewById(R.id.imgBack);
        txtCenterTitle = findViewById(R.id.txtCenterTitle);
        imgMenu = findViewById(R.id.imgMenu);
        llCenterButton.setVisibility(View.GONE);
        imgNotification.setVisibility(View.GONE);
        imgMenu.setVisibility(View.VISIBLE);
        txtCenterTitle.setVisibility(View.VISIBLE);
        imgBack.setVisibility(View.VISIBLE);

    }

}
