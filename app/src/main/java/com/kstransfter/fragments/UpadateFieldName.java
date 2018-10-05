package com.kstransfter.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kstransfter.R;
import com.kstransfter.activities.MainActivity;

public class UpadateFieldName extends BaseFragment {

    private RelativeLayout rlName, rlMobile, rlEmail, rlPassword;
    private MainActivity mainActivity;
    private UpadateProfileFragment upadateProfileFragment;
    private Button btnUpdate;
    private TextView txtFieldName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_update_field, container, false);
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
        btnUpdate = view.findViewById(R.id.btnUpdate);
        txtFieldName = view.findViewById(R.id.txtFieldName);
        try {
            initital();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initital() {
        mainActivity = (MainActivity) getActivity();
        Bundle bundle = getArguments();
        txtFieldName.setText(bundle.getCharSequence("update field"));

        btnUpdate.setOnClickListener(v -> {
            Toast.makeText(mainActivity, "Update Success!", Toast.LENGTH_SHORT).show();
            mainActivity.onBackPressed();
        });
    }
}
