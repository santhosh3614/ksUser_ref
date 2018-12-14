package com.kstransfter.fragments;

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

import java.util.ArrayList;

public class DriverListFragment extends BaseFragment {

    private RecyclerView rvDriverList;
    public static String TAG = DriverListFragment.class.getSimpleName();
    private ArrayList<Driver> drivers = new ArrayList<>();
    private MainActivity mainActivity;

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
        rvDriverList.setLayoutManager(new LinearLayoutManager(getContext()));
        for (int i = 0; i < 5; i++) {
            Driver driver = new Driver();
            driver.setName("rahul Kumar");
            driver.setName("exp:  6 years");
            drivers.add(driver);
        }
        DriverListAdapter driverListAdapter = new DriverListAdapter(getContext(), drivers, (view, pos) -> {
            mainActivity.replaceFragmenr(DriverDetailsFragment.getInstance(), DriverDetailsFragment.TAG, false);
        });
        rvDriverList.setAdapter(driverListAdapter);
    }


}
