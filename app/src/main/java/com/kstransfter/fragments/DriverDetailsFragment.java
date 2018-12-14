package com.kstransfter.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kstransfter.R;

public class DriverDetailsFragment extends BaseFragment {

    private TextView txtConfirmBooking;
    public static String TAG = DriverDetailsFragment.class.getSimpleName();

    public static DriverDetailsFragment getInstance() {
        DriverDetailsFragment driverDetailsFragment = new DriverDetailsFragment();
        return driverDetailsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.driver_details, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtConfirmBooking = view.findViewById(R.id.txtConfirmBooking);
    }

    @Override
    public void initital() {

    }


}
