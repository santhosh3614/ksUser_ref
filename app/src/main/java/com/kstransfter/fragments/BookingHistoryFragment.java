package com.kstransfter.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.kstransfter.R;
import com.kstransfter.activities.MainActivity;
import com.kstransfter.utils.StaticUtils;
import com.kstransfter.webservice.WsFactory;
import com.kstransfter.webservice.WsResponse;
import com.kstransfter.webservice.WsUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;

public class BookingHistoryFragment extends BaseFragment implements WsResponse {
    private RecyclerView rvBookingHistory;
    private ArrayList<String> bookings = new ArrayList<>();
    private MainActivity mainActivity;
    private AlertDialog progressDialog;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_booking_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvBookingHistory = view.findViewById(R.id.rvBookingHistory);
        rvBookingHistory.setLayoutManager(new LinearLayoutManager(getContext()));
        try {
            initital();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initital() {
        mainActivity = (MainActivity) getActivity();
        progressDialog = new SpotsDialog(mainActivity, R.style.Custom);
        mainActivity.imgBack.setOnClickListener(v -> {
            mainActivity.onBackPressed();
        });
        progressDialog.show();
        Map<String, String> map = new HashMap<>();
        map.put("iUserId", "8");
        Call signUpWsCall = WsFactory.getBookingHistory(map);
        WsUtils.getReponse(signUpWsCall, StaticUtils.REQUEST_BOOKING_HISTORY, this);
    /*    for (int i = 0; i < 20; i++) {
            bookings.add("one");
        }
        BookHistoryAdapter bookHistoryAdapter = new BookHistoryAdapter(getContext(), bookings);
        rvBookingHistory.setAdapter(bookHistoryAdapter);
        setHeader(true, "Booking History");*/
    }


    @Override
    public void successResponse(Object response, int code) {
        progressDialog.cancel();
        switch (code) {
            case StaticUtils.REQUEST_BOOKING_HISTORY:
                JsonObject jsonObject = (JsonObject) response;
                Toast.makeText(mainActivity, "respone :" + jsonObject, Toast.LENGTH_LONG).show();
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
