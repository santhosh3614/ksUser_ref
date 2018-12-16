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

import com.kstransfter.R;
import com.kstransfter.activities.MainActivity;
import com.kstransfter.adapters.DriverListAdapter;
import com.kstransfter.models.app.Driver;
import com.kstransfter.models.app.DriverListModel;
import com.kstransfter.utils.StaticUtils;
import com.kstransfter.webservice.WsFactory;
import com.kstransfter.webservice.WsResponse;
import com.kstransfter.webservice.WsUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;

public class DriverListFragment extends BaseFragment implements WsResponse {

    private RecyclerView rvDriverList;
    public static String TAG = DriverListFragment.class.getSimpleName();
    private ArrayList<Driver> drivers = new ArrayList<>();
    private MainActivity mainActivity;
    private AlertDialog progressDialog;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_driver_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvDriverList = view.findViewById(R.id.rvDriverList);
        rvDriverList.setLayoutManager(new LinearLayoutManager(getContext()));
        try {
            initital();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initital() {
        mainActivity = (MainActivity) getActivity();
        progressDialog = new SpotsDialog(getContext(), R.style.Custom);
        getDrivertList();
    }


    private void getDrivertList() {
        progressDialog.show();
        Map<String, String> map = new HashMap<>();
        map.put("dtLeavingDateTime", "2018-11-22 11:11:00");
        map.put("dtReturningDateTime", "2018-11-24 11:11:00");
        map.put("distance", "1234");
        Call signUpWsCall = WsFactory.driverList(map);
        WsUtils.getReponse(signUpWsCall, StaticUtils.REQUEST_DRIVER_LIST, this);
    }

    @Override
    public void successResponse(Object response, int code) {
        progressDialog.cancel();
        switch (code) {
            case StaticUtils.REQUEST_DRIVER_LIST:
                DriverListModel driverListModel = (DriverListModel) response;
                DriverListAdapter driverListAdapter = new DriverListAdapter(getContext(), driverListModel.getResponseData(), (view, pos) -> {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("DriverDetails",driverListModel);
                    mainActivity.replaceFragmenr(DriverDetailsFragment.getInstance(bundle), DriverDetailsFragment.TAG, false);
                });
                rvDriverList.setAdapter(driverListAdapter);
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
