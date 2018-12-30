package com.kstransfter.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kstransfter.R;
import com.kstransfter.activities.MainActivity;
import com.kstransfter.adapters.TermsAndConditionAdapter;
import com.kstransfter.models.app.GetPages;
import com.kstransfter.utils.PoupUtils;
import com.kstransfter.utils.StaticUtils;
import com.kstransfter.webservice.WsFactory;
import com.kstransfter.webservice.WsResponse;
import com.kstransfter.webservice.WsUtils;


import dmax.dialog.SpotsDialog;
import retrofit2.Call;

/**
 * Created by SONI on 12/30/2018.
 */

public class TermsAndCondtionFragment extends BaseFragment implements WsResponse {

    public static TermsAndCondtionFragment getInstance() {
        TermsAndCondtionFragment termsAndCondtionFragment = new TermsAndCondtionFragment();
        return termsAndCondtionFragment;
    }

    public static String TAG = TermsAndCondtionFragment.class.getSimpleName();
    private View view;
    private AlertDialog progressDialog;
    private MainActivity mainActivity;
    private RecyclerView rvPages;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_terms_and_condition, container, false);
            rvPages = view.findViewById(R.id.rvPages);
            try {
                initital();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return view;
    }

    @Override
    public void initital() {
        mainActivity = (MainActivity) getActivity();
        progressDialog = new SpotsDialog(mainActivity, R.style.Custom);
        rvPages.setLayoutManager(new LinearLayoutManager(mainActivity));
        setHeader(false, "Term and condition");
        getPage();
    }

    private void getPage() {
        progressDialog.show();
        Call signUpWsCall = WsFactory.termsAndCondition();
        WsUtils.getReponse(signUpWsCall, StaticUtils.REQUEST_GET_PAGES, this);
    }

    @Override
    public void successResponse(Object response, int code) {
        progressDialog.cancel();
        switch (code) {
            case StaticUtils.REQUEST_GET_PAGES:
                GetPages getPages = (GetPages) response;
                if (getPages != null) {
                    TermsAndConditionAdapter termsAndConditionAdapter = new TermsAndConditionAdapter(mainActivity, getPages.getResponseData(), (view, pos) -> {
                        GetPages.ResponseDatum responseDatum = getPages.getResponseData().get(pos);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("responseData", responseDatum);
                        TermsConditionDetailsFragment termsConditionDetailsFragment = new TermsConditionDetailsFragment();
                        termsConditionDetailsFragment.setArguments(bundle);
                        mainActivity.replaceFragmenr(termsConditionDetailsFragment, TermsConditionDetailsFragment.TAG, false);
                    });
                    rvPages.setAdapter(termsAndConditionAdapter);
                } else {
                    PoupUtils.showAlertDailog(mainActivity, "Somthing went wrong, please try again.");
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
}
