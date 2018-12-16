package com.kstransfter.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;

/**
 * Created by SONI on 11/18/2018.
 */

public class BookYourOutstationRideFragment extends BaseFragment implements WsResponse {

    public static String TAG = BookYourOutstationRideFragment.class.getSimpleName();
    private TextView txtFrom, txtTo, txtStart, txtEnd, txtLeaveDate, txtReturn;
    private RecyclerView rvCarList;
    private ImageView imgOneWay, imgRoundWay;
    private CardView cardLeave, cardReturn;
    private AlertDialog progressDialog;
    private MainActivity mainActivity;
    private TextView txtSelectLeave, txtReturnBy;
    private CardView cardOneWay, carRoundWay;
    private TextView txtGetCarList;
    private SessionManager sessionManager;

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
        progressDialog = new SpotsDialog(getContext(), R.style.Custom);
        mainActivity = (MainActivity) getActivity();
        sessionManager = new SessionManager(mainActivity);
        imgOneWay.setSelected(true);
        cardReturn.setVisibility(View.GONE);
        rvCarList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCarList.setNestedScrollingEnabled(false);

        cardOneWay.setOnClickListener(v -> {
            imgOneWay.setSelected(true);
            imgRoundWay.setSelected(false);
            cardReturn.setVisibility(View.GONE);
        });
        carRoundWay.setOnClickListener(v -> {
            imgOneWay.setSelected(false);
            imgRoundWay.setSelected(true);
            cardReturn.setVisibility(View.VISIBLE);
        });
        if (sessionManager.getSearchType().equalsIgnoreCase("Car")) {
            txtGetCarList.setText("Get Car List");
        } else {
            txtGetCarList.setText("Get Driver List");
        }

        txtGetCarList.setOnClickListener(v -> {
            String value = txtGetCarList.getText().toString().trim();
            if (value.equalsIgnoreCase("Get Car List")) {
                progressDialog.show();
                Map<String, String> map = new HashMap<>();
                map.put("dtLeavingDateTime", "2018-11-22 11:11:00");
                map.put("dtReturningDateTime", "2018-11-24 11:11:00");
                map.put("distance", sessionManager.getDistance());
                Call signUpWsCall = WsFactory.carList(map);
                WsUtils.getReponse(signUpWsCall, StaticUtils.REQUEST_CAR_LIST, this);
            } else if (value.equalsIgnoreCase("Get Driver List")) {
                DriverListFragment driverListFragment = new DriverListFragment();
                mainActivity.replaceFragmenr(driverListFragment, DriverListFragment.TAG, false);
            }
        });
        try {
            initital();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getDataFromArgument() {
        String pickupPoint = getArguments().getString("pickupPoint");
        String endPoint = getArguments().getString("endPoint");
        txtFrom.setText(pickupPoint);
        txtTo.setText(endPoint);
    }


    @Override
    public void initital() {
        cardLeave.setOnClickListener(v -> {
            PoupUtils.showDatePicker(getContext(), txtLeaveDate);
        });
        cardReturn.setOnClickListener(v -> {
            PoupUtils.showDatePicker(getContext(), txtReturn);
        });
        getDataFromArgument();
    }

    @Override
    public void successResponse(Object response, int code) {
        progressDialog.cancel();
        switch (code) {
            case StaticUtils.REQUEST_CAR_LIST:
                CarListtModel carListModel = (CarListtModel) response;
                BookOutSideStaionAdapter bookOutSideStaionAdapter = new BookOutSideStaionAdapter(getContext(), carListModel.getResponseData(), (v, pos) -> {
                    CarListtModel.ResponseDatum responseDatum = carListModel.getResponseData().get(pos);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("carModel",responseDatum);
                    ConfirmBookingFragment confirmBookingFragment = new ConfirmBookingFragment();
                    mainActivity.replaceFragmenr(confirmBookingFragment, "ConfirmBookingFragment", false);
                });
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
}
