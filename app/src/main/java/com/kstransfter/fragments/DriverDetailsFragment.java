package com.kstransfter.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kstransfter.R;
import com.kstransfter.activities.MainActivity;
import com.kstransfter.models.app.DriverListModel;
import com.kstransfter.utils.SessionManager;

public class DriverDetailsFragment extends BaseFragment {

    private TextView txtConfirmBooking;
    public static String TAG = DriverDetailsFragment.class.getSimpleName();
    private RatingBar rate;
    private ImageView imgDriverProfile;
    private TextView txtStartDate, txtEndDate, txtName, txtExprence, txtPrice, txtExpCar;
    private EditText edtEmail, edtMobile, edtAddress;
    private SessionManager sessionManager;
    private MainActivity mainActivity;

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
        imgDriverProfile = view.findViewById(R.id.imgDriverProfile);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtMobile = view.findViewById(R.id.edtMobile);
        edtAddress = view.findViewById(R.id.edtAddress);
        txtStartDate = view.findViewById(R.id.txtStartDate);
        txtEndDate = view.findViewById(R.id.txtEndDate);
        txtName = view.findViewById(R.id.txtName);
        txtExprence = view.findViewById(R.id.txtExprence);
        txtPrice = view.findViewById(R.id.txtPrice);
        txtExpCar = view.findViewById(R.id.txtExpCar);
        try {
            initital();
         } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initital() {
        mainActivity = (MainActivity) getActivity();
        sessionManager = new SessionManager(mainActivity);
        setHeader(true, "Driver Details");
        DriverListModel.ResponseDatum driverListModel = getArguments().getParcelable("driverDetails");
        if (driverListModel != null) {
            txtPrice.setText(driverListModel.getTotalPrice().toString());
            txtExprence.setText(driverListModel.getVDriverExp());
            txtStartDate.setText(sessionManager.getStartDate());
            txtEndDate.setText(sessionManager.getEndDate());
            txtExpCar.setText(driverListModel.getvCarExp());
            Glide.with(mainActivity).load(driverListModel.getVDriverImage()).into(imgDriverProfile);

        }
    }
}
