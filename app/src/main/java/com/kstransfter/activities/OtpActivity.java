package com.kstransfter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.kstransfter.R;

public class OtpActivity extends BaseActivity {

    private TextView txtDone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        try {
            initial();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initial() {
        txtDone = findViewById(R.id.txtDone);
        txtDone.setOnClickListener(v -> {
            Intent intent = new Intent(OtpActivity.this, SignUpActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
    }

}
