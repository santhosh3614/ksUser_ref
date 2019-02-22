package com.kstransfter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kstransfter.R;

public class LoginActivity extends BaseActivity {

    private TextView txtLogin, txtSignUp;
    private EditText edtUserName, edtPassword;
    private Button btnFacebook, btnGooglePlus;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        try {
            initial();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initial() {
        txtLogin = findViewById(R.id.txtLogin);
        txtSignUp = findViewById(R.id.txtSignUp);
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        btnFacebook = findViewById(R.id.btnFacebook);
        btnGooglePlus = findViewById(R.id.btnGooglePlus);

        btnFacebook.setOnClickListener(v -> {
            Toast.makeText(this, "click on login facebook", Toast.LENGTH_SHORT).show();
        });

        btnGooglePlus.setOnClickListener(v -> {
            Toast.makeText(this, "click on login google plus", Toast.LENGTH_SHORT).show();

        });

        txtLogin.setOnClickListener(v -> {
            clickLogin();
        });
        txtSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
    }

    private void clickLogin() {
        String userName = edtUserName.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(LoginActivity.this, "enter user name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(LoginActivity.this, "enter password", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }

}
