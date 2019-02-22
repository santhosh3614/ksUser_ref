package com.kstransfter.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kstransfter.R;
import com.kstransfter.models.app.ResendPasswordModel;
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

public class OtpActivity extends BaseActivity implements WsResponse, TextWatcher {

    private TextView txtDone;
    private LinearLayout llResendOtp;
    private AlertDialog progressDialog;
    private EditText edtFirstDigit, edtSecondDigit, edtThirdDigit, edtFourthDigit;
    private SessionManager sessionManager;
    private View view;
    private String vCode = "", userId = "";

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
        sessionManager = new SessionManager(this);
        progressDialog = new SpotsDialog(this, R.style.Custom);
        txtDone = findViewById(R.id.txtDone);
        llResendOtp = findViewById(R.id.llResendOtp);
        edtFirstDigit = findViewById(R.id.edtFirstDigit);
        edtSecondDigit = findViewById(R.id.edtSecondDigit);
        edtThirdDigit = findViewById(R.id.edtThirdDigit);
        edtFourthDigit = findViewById(R.id.edtFourthDigit);
        edtFirstDigit.addTextChangedListener(this);
        edtSecondDigit.addTextChangedListener(this);
        edtThirdDigit.addTextChangedListener(this);
        getDataFromPrev();
        llResendOtp.setOnClickListener(v -> {
            progressDialog.show();
            Map<String, String> map = new HashMap<>();
            map.put("iUserId", userId);
            Call signUpWsCall = WsFactory.resendPassword(map);
            WsUtils.getReponse(signUpWsCall, StaticUtils.REQUEST_OTP_SEND_PASSWORD, this);
        });
        txtDone.setOnClickListener(v -> {
            String enteredOtp = edtFirstDigit.getText().toString().trim()
                    + edtSecondDigit.getText().toString().trim()
                    + edtThirdDigit.getText().toString().trim()
                    + edtFourthDigit.getText().toString().trim();
            if (vCode.equalsIgnoreCase(enteredOtp)) {
                sessionManager.setUserId(userId);
                Intent intent = new Intent(OtpActivity.this, SignUpActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            } else {
                PoupUtils.showAlertDailog(this, "OTP not match");
            }
        });
    }

    private void getDataFromPrev() {
        vCode = getIntent().getExtras().getString("vCode");
        userId = getIntent().getExtras().getString("userId");
    }

    @Override
    public void successResponse(Object response, int code) {
        progressDialog.cancel();
        switch (code) {
            case StaticUtils.REQUEST_OTP_SEND_PASSWORD:
                ResendPasswordModel resendPasswordModel = (ResendPasswordModel) response;
                if (resendPasswordModel != null) {
                    if (resendPasswordModel.getResponseCode() == 1) {

                    } else {
                        PoupUtils.showAlertDailog(this, resendPasswordModel.getResponseMessage());
                    }
                } else {
                    PoupUtils.showAlertDailog(this, "Something went wrong, Please try again.");
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void failureRespons(Throwable error, int code) {
        progressDialog.cancel();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (edtFirstDigit.getText().toString().trim().length() == 1) {
            edtSecondDigit.requestFocus();
        }
        if (edtSecondDigit.getText().toString().trim().length() == 1) {
            edtThirdDigit.requestFocus();
        }
        if (edtThirdDigit.getText().toString().trim().length() == 1) {
            edtFourthDigit.requestFocus();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

}
