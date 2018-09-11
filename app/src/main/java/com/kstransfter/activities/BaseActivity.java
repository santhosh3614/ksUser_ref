package com.kstransfter.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.kstransfter.R;

public abstract class BaseActivity extends AppCompatActivity {

    public abstract void initial();

    public void replaceFragmenr(Fragment fragment, String tag, boolean isNeedAnimation) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (isNeedAnimation)
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
        transaction.replace(R.id.main_cantainer, fragment);
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right); //slide_in_right
    }



}


