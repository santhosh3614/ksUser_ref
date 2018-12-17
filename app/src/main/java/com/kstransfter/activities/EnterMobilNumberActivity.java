package com.kstransfter.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.kstransfter.R;
import com.kstransfter.models.app.SignUpModel;
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
        progressDialog = new SpotsDialog(this, R.style.Custom);
        txtNext = findViewById(R.id.txtNext);
        txtNext.setOnClickListener(v -> {
            Intent intent = new Intent(EnterMobilNumberActivity.this, OtpActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
         });
    }

    private void otpGet() {
        progressDialog.show();
        Map<String, String> map = new HashMap<>();
        map.put("iMobileExtension", "91");
        map.put("vMobileno", "9584288185");
        map.put("txDeviceToken", "sdfnsdfkjkdnf");
        map.put("eDeviceType", "Android");
        Call signUpWsCall = WsFactory.signUp(map);
        WsUtils.getReponse(signUpWsCall, StaticUtils.REQUEST_SIGN_UP, this);
    }

    @Override
    public void successResponse(Object response, int code) {
        progressDialog.cancel();
        switch (code) {
            case StaticUtils.REQUEST_SIGN_UP:
                SignUpModel signUpModel = (SignUpModel) response;
                if (signUpModel.getResponseCode() == 1) {
                    Intent intent = new Intent(EnterMobilNumberActivity.this, OtpActivity.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                } else {

                }
        }
    }

    @Override
    public void failureRespons(Throwable error, int code) {
        progressDialog.cancel();

    }

}

