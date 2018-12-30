package com.kstransfter.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.kstransfter.R;
import com.kstransfter.models.app.GetPages;

/**
 * Created by SONI on 12/30/2018.
 */

public class TermsConditionDetailsFragment extends BaseFragment {

    public static TermsConditionDetailsFragment getInstance() {
        TermsConditionDetailsFragment termsConditionDetails = new TermsConditionDetailsFragment();
        return termsConditionDetails;
    }

    public static String TAG = TermsConditionDetailsFragment.class.getSimpleName();
    private View view;
    private WebView webView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_terms_and_condition_details, container, false);
            webView = view.findViewById(R.id.webView);
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
        GetPages.ResponseDatum responseDatum = getArguments().getParcelable("responseData");
        setHeader(true, responseDatum.getVTitle());
        webView.loadData(responseDatum.getLtxDescription(), "text/html; charset=utf-8", "UTF-8");
    }

}
