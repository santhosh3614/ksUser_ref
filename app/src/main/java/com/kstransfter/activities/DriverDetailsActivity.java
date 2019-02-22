package com.kstransfter.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

public class DriverDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            initial();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initial() {

    }
}
