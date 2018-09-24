package com.kstransfter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.kstransfter.R;

public class TutorialActivity extends BaseActivity {

    private TextView txtNext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
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
            Intent intent = new Intent(TutorialActivity.this, EnterMobilNumberActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });


    }
}
