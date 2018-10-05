package com.kstransfter.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.kstransfter.R;
import com.kstransfter.activities.MainActivity;

public class UpadateProfileFragment extends BaseFragment {

    private RelativeLayout rlName, rlMobile, rlEmail, rlPassword;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_update_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rlName = view.findViewById(R.id.rlName);
        rlMobile = view.findViewById(R.id.rlMobile);
        rlEmail = view.findViewById(R.id.rlEmail);
        rlPassword = view.findViewById(R.id.rlPassword);
        rlName = view.findViewById(R.id.rlName);
        rlMobile = view.findViewById(R.id.rlMobile);
        rlEmail = view.findViewById(R.id.rlEmail);
        rlPassword = view.findViewById(R.id.rlPassword);
        try {
            initital();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initital() {
        MainActivity mainActivity = (MainActivity) getActivity();
        rlName.setOnClickListener(v -> {
            UpadateFieldName upadateFieldName = new UpadateFieldName();
            Bundle bundle = new Bundle();
            bundle.putString("update field", "Update Name");
            upadateFieldName.setArguments(bundle);
            mainActivity.replaceFragmenr(upadateFieldName, upadateFieldName.getTag(), false);
        });

        rlMobile.setOnClickListener(v -> {
            UpadateFieldName upadateFieldName = new UpadateFieldName();
            Bundle bundle = new Bundle();
            bundle.putString("update field", "Update Phone");
            upadateFieldName.setArguments(bundle);
            mainActivity.replaceFragmenr(upadateFieldName, upadateFieldName.getTag(), false);
        });

        rlEmail.setOnClickListener(v -> {
            UpadateFieldName upadateFieldName = new UpadateFieldName();
            Bundle bundle = new Bundle();
            bundle.putString("update field", "Update Emai Id");
            upadateFieldName.setArguments(bundle);
            mainActivity.replaceFragmenr(upadateFieldName, upadateFieldName.getTag(), false);
        });

        rlPassword.setOnClickListener(v -> {
            UpadateFieldName upadateFieldName = new UpadateFieldName();
            Bundle bundle = new Bundle();
            bundle.putString("update field", "Set Password");
            upadateFieldName.setArguments(bundle);
            mainActivity.replaceFragmenr(upadateFieldName, upadateFieldName.getTag(), false);
        });
    }

}
