package com.kstransfter.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.LinearLayout;
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

public class OtpActivity extends BaseActivity implements WsResponse {

    private TextView txtDone;
    private LinearLayout llResendOtp;
    private AlertDialog progressDialog;
    private SignUpModel.ResponseData responseData;
    private EditText edtFirstDigit, edtSecondDigit, edtThirdDigit, edtFourthDigit;
    private SessionManager sessionManager;

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
        getDataFromPrev();
        llResendOtp.setOnClickListener(v -> {
            progressDialog.show();
            Map<String, String> map = new HashMap<>();
            map.put("iUserId", sessionManager.getUserId());
            Call signUpWsCall = WsFactory.signUp(map);
            WsUtils.getReponse(signUpWsCall, StaticUtils.REQUEST_OTP_SEND_PASSWORD, this);
        });

        txtDone.setOnClickListener(v -> {
            String enteredOtp = edtFirstDigit.getText().toString().trim()
                    + edtSecondDigit.getText().toString().trim()
                    + edtThirdDigit.getText().toString().trim()
                    + edtFourthDigit.getText().toString().trim();

            if (!responseData.getVVerificationcode().equalsIgnoreCase(enteredOtp)) {
                PoupUtils.showAlertDailog(this, "OTP not match");
             } else {
                Intent intent = new Intent(OtpActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    private void getDataFromPrev() {
        responseData = getIntent().getParcelableExtra("signUpResponse");
        if (responseData != null) {

        } else {
        }
    }

    @Override
    public void successResponse(Object response, int code) {


    }

    @Override
    public void failureRespons(Throwable error, int code) {

    }
}
