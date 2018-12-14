package com.kstransfter.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kstransfter.R;

/**
 * Created by SONI on 12/9/2018.
 */

public class ConfirmBookingFragment extends BaseFragment {

    private TextView txtHideAndShow;
    private LinearLayout llFairDetails;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.confirm_booking_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtHideAndShow = view.findViewById(R.id.txtHideAndShow);
        llFairDetails = view.findViewById(R.id.llFairDetails);
        try {
            initital();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initital() {
        txtHideAndShow.setOnClickListener(v -> {
            if (txtHideAndShow.getText().toString().trim().equalsIgnoreCase("Hide Fare Details")) {
                txtHideAndShow.setText("Show Fare Details");
                llFairDetails.setVisibility(View.GONE);
            } else {
                txtHideAndShow.setText("Hide Fare Details");
                llFairDetails.setVisibility(View.VISIBLE);
            }
        });
    }
}
