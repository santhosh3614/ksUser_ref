package com.kstransfter.fragments;

import android.support.v4.app.Fragment;
import android.view.View;
import com.kstransfter.activities.MainActivity;

public abstract class BaseFragment extends Fragment {
    public abstract void initital();

    public void setHeader(boolean isBack, String header) {
        MainActivity mainActivity = (MainActivity) getActivity();
        if (isBack) {
            mainActivity.imgBack.setVisibility(View.VISIBLE);
            mainActivity.imgMenu.setVisibility(View.GONE);
            mainActivity.imgBack.setOnClickListener(v -> {
                mainActivity.onBackPressed();
            });
        } else {
            mainActivity.imgBack.setVisibility(View.GONE);
            mainActivity.imgMenu.setVisibility(View.VISIBLE);
        }
        mainActivity.txtCenterTitle.setText(header);
    }
}
