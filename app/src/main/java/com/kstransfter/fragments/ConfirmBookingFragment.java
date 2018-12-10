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

    private TextView txtHide;
    private LinearLayout llDetails;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.confirm_booking_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtHide = view.findViewById(R.id.txtHide);
        llDetails = view.findViewById(R.id.llDetails);
        try {
            initital();
           } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initital() {
        txtHide.setOnClickListener(v -> {
            if (txtHide.getText().toString().trim().equalsIgnoreCase("Hide Fare Details")) {
                llDetails.setVisibility(View.GONE);
                txtHide.setText("Show Fare Details");
            } else {
                llDetails.setVisibility(View.VISIBLE);
                txtHide.setText("Hide Fare Details");
            }
        });
    }
}
