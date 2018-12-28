package com.kstransfter.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.kstransfter.R;
import com.kstransfter.models.app.SignUpModel;
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

/**
 * Created by SONI on 9/23/2018.
 */

public class EnterMobilNumberActivity extends BaseActivity implements WsResponse {

    private TextView txtNext;
    private AlertDialog progressDialog;
    private EditText edtPhone;
    private SessionManager sessionManager;

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
        sessionManager = new SessionManager(this);
        progressDialog = new SpotsDialog(this, R.style.Custom);
        txtNext = findViewById(R.id.txtNext);
        txtNext.setOnClickListener(v -> {
            otpGet();
        });
    }

    private void otpGet() {
        edtPhone = findViewById(R.id.edtPhone);
        String phone = edtPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            PoupUtils.showAlertDailog(this, "Enter your phone no.");
        } else {
            progressDialog.show();
            Map<String, String> map = new HashMap<>();
            map.put("iMobileExtension", "91");
            map.put("vMobileno", phone);
            map.put("txDeviceToken", "sdfnsdfkjkdnf");
            map.put("eDeviceType", "Android");
            Call signUpWsCall = WsFactory.signUp(map);
            WsUtils.getReponse(signUpWsCall, StaticUtils.REQUEST_SIGN_UP, this);
        }
    }

    @Override
    public void successResponse(Object response, int code) {
        progressDialog.cancel();
        switch (code) {
            case StaticUtils.REQUEST_SIGN_UP:
                SignUpModel signUpModel = (SignUpModel) response;
                if (signUpModel != null) {
                    sessionManager.setUserId(signUpModel.getResponseData().getIUserId());
                    Intent intent = new Intent(EnterMobilNumberActivity.this, OtpActivity.class);
                    intent.putExtra("signUpResponse", signUpModel.getResponseData());
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                } else {
                    PoupUtils.showAlertDailog(this, "Somthing went wrong,please try again");
                }
        }
    }

    @Override
    public void failureRespons(Throwable error, int code) {
        progressDialog.cancel();
        PoupUtils.showAlertDailog(this, "Somthing went wrong, please try again.");
    }
}

