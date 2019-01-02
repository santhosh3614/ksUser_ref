package com.kstransfter.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.kstransfter.R;
import com.kstransfter.activities.MainActivity;
import com.kstransfter.utils.SessionManager;

public class MainFragment extends BaseFragment {
    private LinearLayout llCar, llBookDriver;
    private SessionManager sessionManager;
    private MainActivity mainActivity;

    public static String TAG = MainActivity.class.getSimpleName();

    public static MainFragment getInstance(Bundle bundle) {
        MainFragment mainFragment = new MainFragment();
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.activity_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        llCar = view.findViewById(R.id.llCar);
        llBookDriver = view.findViewById(R.id.llBookDriver);
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
        llCar.setOnClickListener(v -> {
            sessionManager.setSearchType("Car");
            Bundle bundle = new Bundle();
            mainActivity.replaceFragmenr(HomeFragment.getInstance(bundle), HomeFragment.TAG, true);
        });
        llBookDriver.setOnClickListener(v -> {
            sessionManager.setSearchType("Driver");
            Bundle bundle = new Bundle();
            mainActivity.replaceFragmenr(HomeFragment.getInstance(bundle), HomeFragment.TAG, true);
        });
    }

}
