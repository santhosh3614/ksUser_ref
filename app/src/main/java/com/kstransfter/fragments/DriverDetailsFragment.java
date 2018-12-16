package com.kstransfter.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.kstransfter.R;
import com.kstransfter.models.app.DriverListModel;

public class DriverDetailsFragment extends BaseFragment {

    private TextView txtConfirmBooking;
    public static String TAG = DriverDetailsFragment.class.getSimpleName();
    private TextView txtExprence, txtPrice;
    private RatingBar rate;

    public static DriverDetailsFragment getInstance(Bundle bundle) {
        DriverDetailsFragment driverDetailsFragment = new DriverDetailsFragment();
        driverDetailsFragment.setArguments(bundle);
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
        txtExprence = view.findViewById(R.id.txtExprence);
        txtPrice = view.findViewById(R.id.txtPrice);
        rate = view.findViewById(R.id.rate);
        try {
            initital();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initital() {
        DriverListModel.ResponseDatum driverListModel = getArguments().getParcelable("DriverDetails");
        if (driverListModel != null) {
            if (driverListModel.getTotalPrice() != null)
                txtPrice.setText(driverListModel.getTotalPrice().toString());
            txtExprence.setText(driverListModel.getVDriverExp());
        }
    }

}
