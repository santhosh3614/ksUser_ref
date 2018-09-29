package com.kstransfter.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.kstransfter.R;

import java.util.ArrayList;

public class TutorialActivity extends BaseActivity {

    private TextView txtNext;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ViewPager viewpager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        try {
            initial();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initial() {
        txtNext = findViewById(R.id.txtNext);
        viewpager = findViewById(R.id.viewpager);

        txtNext.setOnClickListener(v -> {
            Intent intent = new Intent(TutorialActivity.this, EnterMobilNumberActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
        ScreenSlidePagerAdapter screenSlidePagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), this, fragments);
        viewpager.setAdapter(screenSlidePagerAdapter);
      }
}


class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

    private Context context;
    private ArrayList<Fragment> fragments;

    public ScreenSlidePagerAdapter(FragmentManager fm, Context context, ArrayList<Fragment> fragments) {
        super(fm);
        this.context = context;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }
    @Override
    public int getCount() {
        return fragments.size();
    }

}
