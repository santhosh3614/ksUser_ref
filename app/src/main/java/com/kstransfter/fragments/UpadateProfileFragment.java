package com.kstransfter.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.kstransfter.R;
import com.kstransfter.activities.MainActivity;
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

public class UpadateProfileFragment extends BaseFragment implements WsResponse {

    private EditText edtUserName, edtEmail;
    private TextView txtUpdate;
    private MainActivity mainActivity;
    private AlertDialog progressDialog;
    private SessionManager sessionManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_update_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtUserName = view.findViewById(R.id.edtUserName);
        edtEmail = view.findViewById(R.id.edtEmail);
        txtUpdate = view.findViewById(R.id.txtUpdate);
        try {
            initital();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initital() {
        mainActivity = (MainActivity) getActivity();
        progressDialog = new SpotsDialog(mainActivity, R.style.Custom);
        sessionManager = new SessionManager(mainActivity);
        setHeader(true, "Update Profile");
        txtUpdate.setOnClickListener(v -> {
            String userName = edtUserName.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();
            if (TextUtils.isEmpty(userName)) {
                PoupUtils.showAlertDailog(mainActivity, "Pelase enter user name.");
            } else if (TextUtils.isEmpty(email)) {
                PoupUtils.showAlertDailog(mainActivity, "Pelase enter your email.");
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
                    PoupUtils.showAlertDailog(mainActivity, "Update success");
                    sessionManager.setEmail(signUpModel.getResponseData().getVEmail());
                    sessionManager.setName(signUpModel.getResponseData().getVUserName());
                    mainActivity.txtName.setText(sessionManager.getName());
                 } else {
                    PoupUtils.showAlertDailog(mainActivity, "Something went wrong,Please try again.");
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
