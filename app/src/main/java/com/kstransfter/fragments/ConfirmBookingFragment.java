package com.kstransfter.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kstransfter.R;
import com.kstransfter.activities.MainActivity;
import com.kstransfter.models.app.BookedCarModel;
import com.kstransfter.models.app.CarListtModel;
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

/**
 * Created by SONI on 12/9/2018.
 */

public class ConfirmBookingFragment extends BaseFragment implements WsResponse {

    private TextView txtHideAndShow;
    private LinearLayout llFairDetails;
    private ImageView imgCar;
    private TextView txtCar, txtSheeter, txtLeaveDate, txtRetrurnBy, txtPrice, txtConfirmBooking, txtFrom, txtTo, txtKmPerH,
            txtbaseFare, txtEstimatePrice, txtFareRule, txtDetails;
    private AlertDialog progressDialog;
    private MainActivity mainActivity;
    private SessionManager sessionManager;
    private CardView cardApplyCoupn;
    private CarListtModel.ResponseDatum responseDatum;
    private TextView txtHrs, txtDis, totalExtraFare, txtAcAndNon;
    private LinearLayout llHideAndShow, llTaxDetails;
    private TextView txtGstPrice, txtDriverCharge, txtNightAllownce;


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
        txtCar = view.findViewById(R.id.txtCar);
        txtSheeter = view.findViewById(R.id.txtSheeter);
        txtLeaveDate = view.findViewById(R.id.txtLeaveDate);
        txtRetrurnBy = view.findViewById(R.id.txtRetrurnBy);
        txtPrice = view.findViewById(R.id.txtPrice);
        txtConfirmBooking = view.findViewById(R.id.txtConfirmBooking);
        txtFrom = view.findViewById(R.id.txtFrom);
        txtTo = view.findViewById(R.id.txtTo);
        txtKmPerH = view.findViewById(R.id.txtKmPerH);
        txtbaseFare = view.findViewById(R.id.txtbaseFare);
        txtEstimatePrice = view.findViewById(R.id.txtEstimatePrice);
        txtFareRule = view.findViewById(R.id.txtFareRule);
        cardApplyCoupn = view.findViewById(R.id.cardApplyCoupn);
        txtDetails = view.findViewById(R.id.txtDetails);
        imgCar = view.findViewById(R.id.imgCar);
        txtHrs = view.findViewById(R.id.txtHrs);
        txtDis = view.findViewById(R.id.txtDis);
        totalExtraFare = view.findViewById(R.id.totalExtraFare);
        llHideAndShow = view.findViewById(R.id.llHideAndShow);
        llTaxDetails = view.findViewById(R.id.llTaxDetails);
        txtAcAndNon = view.findViewById(R.id.txtAcAndNon);
        txtGstPrice = view.findViewById(R.id.txtGstPrice);
        txtDriverCharge = view.findViewById(R.id.txtDriverCharge);
        txtNightAllownce = view.findViewById(R.id.txtNightAllownce);
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
        setHeader(true, "Confirm Car Booking");
        totalExtraFare.setOnClickListener(v -> {
            llTaxDetails.setVisibility(View.VISIBLE);
        });

        llHideAndShow.setOnClickListener(v -> {
            if (txtHideAndShow.getText().toString().trim().equalsIgnoreCase("Hide Fare Details")) {
                txtHideAndShow.setText("Show Fare Details");
                llFairDetails.setVisibility(View.GONE);
            } else {
                txtHideAndShow.setText("Hide Fare Details");
                llFairDetails.setVisibility(View.VISIBLE);
            }
        });
        cardApplyCoupn.setOnClickListener(v -> {
            Toast.makeText(mainActivity, "aaply card coupon", Toast.LENGTH_SHORT).show();
        });
        responseDatum = getArguments().getParcelable("carModel");
        if (responseDatum != null) {
            if (responseDatum.getVCar() != null) {
                txtCar.setText(responseDatum.getVCar().toString());
                txtFrom.setText(sessionManager.getFrom());
                txtTo.setText(sessionManager.getTo());
                txtLeaveDate.setText(StaticUtils.converDateFormate(sessionManager.getStartDate()));
                txtRetrurnBy.setText(sessionManager.getEndDate());
                txtbaseFare.setText(responseDatum.getTotalPrice() + "");
                txtEstimatePrice.setText(responseDatum.getTotalPrice() + "");
                txtPrice.setText(responseDatum.getTotalPrice().toString());
                txtFareRule.setText("");
                txtSheeter.setText(responseDatum.getISeater() + " - " + "Sheeters");
                txtHrs.setText(sessionManager.getDuration() + " total Extra Fareround trip of about " + sessionManager.getDistanceString());
                if (Integer.parseInt(responseDatum.getTiIsAc() + "") == 1) {
                    txtAcAndNon.setText("Ac");
                } else {
                    txtAcAndNon.setText("Non Ac");
                }
                txtGstPrice.setText(responseDatum.getGSTRs() + "");
                txtDriverCharge.setText(responseDatum.getDriverAllownace() + "");
                txtNightAllownce.setText(responseDatum.getDriverNightCharge() + "");
                Glide.with(mainActivity).load(responseDatum.getVCarImage()).into(imgCar);
            }
        }
        txtConfirmBooking.setOnClickListener(v -> {
            confirmBooking();
        });
    }

    private void confirmBooking() {
        progressDialog.show();
        Map<String, String> map = new HashMap<>();
        map.put("txPickUpAddress", txtTo.getText().toString().trim());
        map.put("iDriverId", responseDatum.getIDriverId());
        map.put("iUserId", sessionManager.getUserId());
        map.put("dcPickUpLatitude", sessionManager.getPickUpLat());
        map.put("dcPickUpLongitude", sessionManager.getPickUpLong());
        map.put("vPickUpCity", txtFrom.getText().toString().trim());
        map.put("dtLeavingDateTime", sessionManager.getEndDate());
        map.put("iWaitingHour", "0");
        map.put("vDistance", sessionManager.getDistance());
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
                    PoupUtils.showAlertDailog(mainActivity, "Booking confirm your car on the way.");
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

    }
}