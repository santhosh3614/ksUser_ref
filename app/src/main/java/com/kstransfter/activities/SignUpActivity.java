package com.kstransfter.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.kstransfter.R;
import com.kstransfter.models.SignUpAndUpdate;
import com.kstransfter.utils.PoupUtils;
import com.kstransfter.utils.SessionManager;
import com.kstransfter.utils.StaticUtils;
import com.kstransfter.webservice.WsFactory;
import com.kstransfter.webservice.WsResponse;
import com.kstransfter.webservice.WsUtils;

import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;

public class SignUpActivity extends BaseActivity implements WsResponse {

    private TextView txtLogin;
    private EditText edtUserName, edtEmail;
    private AlertDialog progressDialog;
    private SessionManager sessionManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        try {
            initial();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initial() {
        edtUserName = findViewById(R.id.edtUserName);
        edtEmail = findViewById(R.id.edtEmail);
        txtLogin = findViewById(R.id.txtLogin);
        progressDialog = new SpotsDialog(this, R.style.Custom);
        sessionManager = new SessionManager(this);
        txtLogin.setOnClickListener(v -> {
            String userName = edtUserName.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();
            if (TextUtils.isEmpty(userName)) {
                PoupUtils.showAlertDailog(this, "Enter user name.");
            } else {
                wsCallingHere(userName, email);
            }
         });
    }

    private void wsCallingHere(String userName, String email) {
        progressDialog.show();
        Map<String, String> map = new HashMap<>();
        map.put("iUserId", sessionManager.getUserId());
        map.put("vUserName", userName);
        map.put("vEmail", email);
        Call signUpWsCall = WsFactory.signAndUpdate(map);
        WsUtils.getReponse(signUpWsCall, StaticUtils.REQUEST_SIGN_UP, this);
    }

    @Override
    public void successResponse(Object response, int code) {
        switch (code) {
            case StaticUtils.REQUEST_SIGN_UP:
                SignUpAndUpdate signUpModel = (SignUpAndUpdate) response;
                if (signUpModel != null) {
                    Intent intent = new Intent(this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                 } else {
                    PoupUtils.showAlertDailog(this, "Something went wrong,Please try again.");
                 }
                break;
            default:
                break;
        }
    }

    @Override
    public void failureRespons(Throwable error, int code) {

    }


}
