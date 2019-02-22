package com.kstransfter.fragments;

import android.app.AlertDialog;
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
import com.kstransfter.models.app.BookedCarModel;
import com.kstransfter.models.app.DriverListModel;
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

public class DriverDetailsFragment extends BaseFragment implements WsResponse {

    private TextView txtConfirmBooking;
    public static String TAG = DriverDetailsFragment.class.getSimpleName();
    private RatingBar rate;
    private ImageView imgDriverProfile;
    private TextView txtStartDate, txtEndDate, txtName, txtExprence, txtPrice, txtExpCar;
    private EditText edtEmail, edtMobile, edtAddress;
    private SessionManager sessionManager;
    private MainActivity mainActivity;
    private DriverListModel.ResponseDatum driverListModel;
    private AlertDialog progressDialog;

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
        progressDialog = new SpotsDialog(mainActivity, R.style.Custom);
        setHeader(true, "Driver Details");
        driverListModel = getArguments().getParcelable("driverDetails");
        if (driverListModel != null) {
            txtPrice.setText(driverListModel.getTotalPrice().toString());
            txtExprence.setText(driverListModel.getVDriverExp());
            txtStartDate.setText(StaticUtils.converDateFormate(sessionManager.getStartDate()));
            txtEndDate.setText(StaticUtils.converDateFormate(sessionManager.getEndDate()));
            txtExpCar.setText(driverListModel.getvCarExp());
            Glide.with(mainActivity).load(driverListModel.getVDriverImage()).into(imgDriverProfile);
        }
        txtConfirmBooking.setOnClickListener(v -> {
            confirmBooking();
        });
    }

    private void confirmBooking() {
        progressDialog.show();
        Map<String, String> map = new HashMap<>();
        map.put("txPickUpAddress", "asdfasdfasdfsdf");
        map.put("iDriverId", driverListModel.getIDriverId());
        map.put("iUserId", sessionManager.getUserId());
        map.put("dcPickUpLatitude", sessionManager.getPickUpLat());
        map.put("dcPickUpLongitude", sessionManager.getPickUpLong());
        map.put("vPickUpCity", "rajkot");
        map.put("dtLeavingDateTime", "2018-11-28 11:11:00");
        map.put("iWaitingHour", "2");
        map.put("vDistance", "200");
        Call signUpWsCall = WsFactory.carBooked(map);
        WsUtils.getReponse(signUpWsCall, StaticUtils.REQUEST_DRIVER_CONFIRM_BOOKING, this);
    }

    @Override
    public void successResponse(Object response, int code) {
        progressDialog.cancel();
        switch (code) {
            case StaticUtils.REQUEST_DRIVER_CONFIRM_BOOKING:
                BookedCarModel bookedCarModel = (BookedCarModel) response;
                if (bookedCarModel != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("iDriverId", driverListModel.getIDriverId());
                    bundle.putString("iUserId", sessionManager.getUserId());
                    mainActivity.replaceFragmenr(PaymentReceiptFragment.getInatance(bundle), PaymentReceiptFragment.TAG, false);
                } else {
                    PoupUtils.showAlertDailog(mainActivity, "Somthing went wrong,Please try again with some change");
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
