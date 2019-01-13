package com.kstransfter.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kstransfter.R;
import com.kstransfter.activities.MainActivity;
import com.kstransfter.adapters.BookOutSideStaionAdapter;
import com.kstransfter.models.app.CarListtModel;
import com.kstransfter.utils.PoupUtils;
import com.kstransfter.utils.SessionManager;
import com.kstransfter.utils.StaticUtils;
import com.kstransfter.webservice.WsFactory;
import com.kstransfter.webservice.WsResponse;
import com.kstransfter.webservice.WsUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;

/**
 * Created by SONI on 11/18/2018.
 */

public class BookYourOutstationRideFragment extends BaseFragment implements WsResponse {

    public static String TAG = BookYourOutstationRideFragment.class.getSimpleName();
    private TextView txtFrom, txtTo, txtStart, txtEnd, txtLeaveDate, txtReturn, txtPrice, txtbaseFare, txtEstimatePrice;
    private RecyclerView rvCarList;
    private ImageView imgOneWay, imgRoundWay;
    private CardView cardLeave, cardReturn;
    private AlertDialog progressDialog;
    private MainActivity mainActivity;
    private TextView txtSelectLeave, txtReturnBy;
    private CardView cardOneWay, carRoundWay;
    private TextView txtGetCarList;
    private SessionManager sessionManager;
    private RelativeLayout rlOneAndRound;
    private LinearLayout llCarView;
    private TextView txtDis;
    private List<CarListtModel.ResponseDatum> responseDatumList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_book_outsation, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtFrom = view.findViewById(R.id.txtFrom);
        txtTo = view.findViewById(R.id.txtTo);
        rvCarList = view.findViewById(R.id.rvCarList);
        imgOneWay = view.findViewById(R.id.imgOneWay);
        imgRoundWay = view.findViewById(R.id.imgRoundWay);
        txtGetCarList = view.findViewById(R.id.txtGetCarList);
        cardLeave = view.findViewById(R.id.cardLeave);
        cardReturn = view.findViewById(R.id.cardReturn);
        txtLeaveDate = view.findViewById(R.id.txtLeaveDate);
        txtReturn = view.findViewById(R.id.txtReturn);
        cardOneWay = view.findViewById(R.id.cardOneWay);
        carRoundWay = view.findViewById(R.id.carRoundWay);
        rlOneAndRound = view.findViewById(R.id.rlOneAndRound);
        llCarView = view.findViewById(R.id.llCarView);
        txtPrice = view.findViewById(R.id.txtPrice);
        txtbaseFare = view.findViewById(R.id.txtbaseFare);
        txtEstimatePrice = view.findViewById(R.id.txtEstimatePrice);
        txtDis = view.findViewById(R.id.txtDis);
        imgOneWay.setSelected(true);
        cardReturn.setVisibility(View.GONE);
        rvCarList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCarList.setNestedScrollingEnabled(false);

        cardOneWay.setOnClickListener(v -> {
            imgOneWay.setSelected(true);
            imgRoundWay.setSelected(false);
            cardReturn.setVisibility(View.GONE);


            BookOutSideStaionAdapter bookOutSideStaionAdapter = new BookOutSideStaionAdapter(getContext(), responseDatumList, (view1, pos) -> {

                if (imgRoundWay.isSelected()) {
                    if (TextUtils.isEmpty(txtReturn.getText().toString().trim())) {
                        PoupUtils.showAlertDailog(mainActivity, "Please select return date");
                    } else {
                        CarListtModel.ResponseDatum responseDatum = responseDatumList.get(pos);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("carModel", responseDatum);
                        ConfirmBookingFragment confirmBookingFragment = new ConfirmBookingFragment();
                        confirmBookingFragment.setArguments(bundle);
                        mainActivity.replaceFragmenr(confirmBookingFragment, "ConfirmBookingFragment", false);
                    }
                } else {
                    CarListtModel.ResponseDatum responseDatum = responseDatumList.get(pos);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("carModel", responseDatum);
                    ConfirmBookingFragment confirmBookingFragment = new ConfirmBookingFragment();
                    confirmBookingFragment.setArguments(bundle);
                    mainActivity.replaceFragmenr(confirmBookingFragment, "ConfirmBookingFragment", false);
                }
            }, false);
            rvCarList.setAdapter(bookOutSideStaionAdapter);
        });

        carRoundWay.setOnClickListener(v -> {
            imgOneWay.setSelected(false);
            imgRoundWay.setSelected(true);
            cardReturn.setVisibility(View.VISIBLE);
            BookOutSideStaionAdapter bookOutSideStaionAdapter = new BookOutSideStaionAdapter(getContext(), responseDatumList, (view1, pos) -> {
                if (imgRoundWay.isSelected()) {
                    if (TextUtils.isEmpty(txtReturn.getText().toString().trim())) {
                        PoupUtils.showAlertDailog(mainActivity, "Please select return date");
                    } else {
                        CarListtModel.ResponseDatum responseDatum = responseDatumList.get(pos);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("carModel", responseDatum);
                        ConfirmBookingFragment confirmBookingFragment = new ConfirmBookingFragment();
                        confirmBookingFragment.setArguments(bundle);
                        mainActivity.replaceFragmenr(confirmBookingFragment, "ConfirmBookingFragment", false);
                    }
                } else {
                    CarListtModel.ResponseDatum responseDatum = responseDatumList.get(pos);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("carModel", responseDatum);
                    ConfirmBookingFragment confirmBookingFragment = new ConfirmBookingFragment();
                    confirmBookingFragment.setArguments(bundle);
                    mainActivity.replaceFragmenr(confirmBookingFragment, "ConfirmBookingFragment", false);
                }
            }, true);
            rvCarList.setAdapter(bookOutSideStaionAdapter);
        });

        txtGetCarList.setOnClickListener(v -> {
            DriverListFragment driverListFragment = new DriverListFragment();
            mainActivity.replaceFragmenr(driverListFragment, DriverListFragment.TAG, false);
        });
        try {
            initital();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void callApiForCar() {
        progressDialog = new SpotsDialog(mainActivity, R.style.Custom);
        progressDialog.show();
        Map<String, String> map = new HashMap<>();
        map.put("dtLeavingDateTime", txtLeaveDate.getText().toString().trim());
        map.put("dtReturningDateTime", txtReturn.getText().toString().trim());
        map.put("vDistance", sessionManager.getDistance());
        Call signUpWsCall = WsFactory.carList(map);
        WsUtils.getReponse(signUpWsCall, StaticUtils.REQUEST_CAR_LIST, this);
    }

    @Override
    public void onPause() {
//        progressDialog.cancel();
        super.onPause();
    }


    @Override
    public void onDestroy() {
//        progressDialog.cancel();
        super.onDestroy();
    }

    private void getDataFromArgument() {
        String pickupPoint = getArguments().getString("pickupPoint");
        String endPoint = getArguments().getString("endPoint");
        sessionManager.setFrom(pickupPoint);
        sessionManager.setTo(endPoint);
        txtFrom.setText(pickupPoint);
        txtTo.setText(endPoint);
    }


    @Override
    public void initital() {
        mainActivity = (MainActivity) getActivity();
        sessionManager = new SessionManager(mainActivity);
        txtDis.setText(sessionManager.getDistanceString());
        setHeader(true, "Car List");
        sessionManager.setStartDate(StaticUtils.getDateAndTime());
        txtLeaveDate.setText(sessionManager.getStartDate());
        cardLeave.setOnClickListener(v -> {
            PoupUtils.showDatePicker(getContext(), txtLeaveDate, view -> {
                sessionManager.setStartDate(txtLeaveDate.getText().toString().trim());
            });
        });
        cardReturn.setOnClickListener(v -> {
            PoupUtils.showDatePicker(getContext(), txtReturn, view -> {
                sessionManager.setEndDate(txtReturn.getText().toString().trim());
            });
        });
        getDataFromArgument();

        if (sessionManager.getSearchType().equalsIgnoreCase("Car")) {
            txtGetCarList.setText("Get Car List");
            txtGetCarList.setVisibility(View.GONE);
            llCarView.setVisibility(View.VISIBLE);
            rlOneAndRound.setVisibility(View.VISIBLE);
            cardReturn.setVisibility(View.GONE);
            callApiForCar();
        } else {
            txtGetCarList.setText("Get Driver List");
            txtGetCarList.setVisibility(View.VISIBLE);
            llCarView.setVisibility(View.GONE);
            rlOneAndRound.setVisibility(View.GONE);
            cardReturn.setVisibility(View.VISIBLE);
        }
    }
/*
    private void showDefaultData() {
        List<CarListtModel.ResponseDatum> responseDatumList = new ArrayList<>();
     *//*   for (int i = 0; i < 5; i++) {
            CarListtModel.ResponseDatum responseDatum = new CarListtModel.ResponseDatum();
            responseDatum.setExtraKm(10.0);
            responseDatumList.add(responseDatum);*//*
        }
     *//*   BookOutSideStaionAdapter bookOutSideStaionAdapter = new BookOutSideStaionAdapter(getContext(), responseDatumList, (v, pos) -> {
            CarListtModel.ResponseDatum responseDatum = responseDatumList.get(pos);
            Bundle bundle = new Bundle();
            bundle.putParcelable("carModel", responseDatum);
            ConfirmBookingFragment confirmBookingFragment = new ConfirmBookingFragment();
            confirmBookingFragment.setArguments(bundle);
            mainActivity.replaceFragmenr(confirmBookingFragment, "ConfirmBookingFragment", false);
        });*//*
//        rvCarList.setAdapter(bookOutSideStaionAdapter);
    }*/


    @Override
    public void successResponse(Object response, int code) {
        progressDialog.cancel();
        switch (code) {
            case StaticUtils.REQUEST_CAR_LIST:
                CarListtModel carListModel = (CarListtModel) response;
                responseDatumList = carListModel.getResponseData();
                BookOutSideStaionAdapter bookOutSideStaionAdapter = new BookOutSideStaionAdapter(getContext(), responseDatumList, (v, pos) -> {
                    if (imgRoundWay.isSelected()) {
                        if (TextUtils.isEmpty(txtReturn.getText().toString().trim())) {
                            PoupUtils.showAlertDailog(mainActivity, "Please select return date");
                        } else {
                            CarListtModel.ResponseDatum responseDatum = carListModel.getResponseData().get(pos);
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("carModel", responseDatum);
                            ConfirmBookingFragment confirmBookingFragment = new ConfirmBookingFragment();
                            confirmBookingFragment.setArguments(bundle);
                            mainActivity.replaceFragmenr(confirmBookingFragment, "ConfirmBookingFragment", false);
                        }
                    } else {
                        CarListtModel.ResponseDatum responseDatum = carListModel.getResponseData().get(pos);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("carModel", responseDatum);
                        ConfirmBookingFragment confirmBookingFragment = new ConfirmBookingFragment();
                        confirmBookingFragment.setArguments(bundle);
                        mainActivity.replaceFragmenr(confirmBookingFragment, "ConfirmBookingFragment", false);
                    }
                }, false);

                rvCarList.setAdapter(bookOutSideStaionAdapter);
                break;
            default:
                break;
        }
    }

    @Override
    public void failureRespons(Throwable error, int code) {
        progressDialog.cancel();

    }


    private void setAdapetrForCar() {

    }


}
