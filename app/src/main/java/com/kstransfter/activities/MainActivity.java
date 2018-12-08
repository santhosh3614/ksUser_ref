package com.kstransfter.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kstransfter.R;
import com.kstransfter.fragments.BookingHistoryFragment;
import com.kstransfter.fragments.HomeFragment;
import com.kstransfter.fragments.UpadateProfileFragment;
import com.kstransfter.utils.PoupUtils;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private TextView txtDriver, txtBookingHistoy, txtSupport, txtTermAndCond, txtRegisterCar, txtRegisterDriver, txtLogOut;
    public LinearLayout llCenterButton;
    public ImageView imgBack;
    public ImageView imgMenu;
    public TextView txtLocalRides, txtOutSideRide, txtTitle, txtCenterTitle;
    private RelativeLayout rlGoForUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        imgMenu = findViewById(R.id.imgMenu);
        imgBack = findViewById(R.id.imgBack);
        llCenterButton = findViewById(R.id.llCenterButton);
        txtDriver = findViewById(R.id.txtDriver);
        txtBookingHistoy = findViewById(R.id.txtBookingHistoy);
        txtSupport = findViewById(R.id.txtSupport);
        txtTermAndCond = findViewById(R.id.txtTermAndCond);
        txtRegisterCar = findViewById(R.id.txtRegisterCar);
        txtRegisterDriver = findViewById(R.id.txtRegisterDriver);
        txtLogOut = findViewById(R.id.txtLogOut);
        txtLocalRides = findViewById(R.id.txtLocalRides);
        txtOutSideRide = findViewById(R.id.txtOutSideRide);
        rlGoForUpdate = findViewById(R.id.rlGoForUpdate);
        txtTitle = findViewById(R.id.txtTitle);
        txtCenterTitle = findViewById(R.id.txtCenterTitle);

        txtDriver.setOnClickListener(this);
        txtBookingHistoy.setOnClickListener(this);
        txtSupport.setOnClickListener(this);
        txtTermAndCond.setOnClickListener(this);
        txtRegisterCar.setOnClickListener(this);
        txtRegisterDriver.setOnClickListener(this);
        txtLogOut.setOnClickListener(this);
        navigationView.setNavigationItemSelectedListener(this);
        imgMenu.setOnClickListener(this);
        imgBack.setOnClickListener(this);

        txtLocalRides.setOnClickListener(v -> {
            Toast.makeText(this, "local ride", Toast.LENGTH_SHORT).show();
        });

        txtOutSideRide.setOnClickListener(v -> {
            Toast.makeText(this, "outside ride", Toast.LENGTH_SHORT).show();
        });

        rlGoForUpdate.setOnClickListener(v -> {
            closeNavigation();
            UpadateProfileFragment upadateProfileFragment = new UpadateProfileFragment();
            replaceFragmenr(upadateProfileFragment, upadateProfileFragment.getTag(), false);
        });

        try {
            initial();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initial() {
        //Relace Fragment
        HomeFragment homeFragment = new HomeFragment();
        replaceFragmenr(homeFragment, homeFragment.getTag(), false);
    }

    private void openNavigation() {
        if (!drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.openDrawer(GravityCompat.START);
        }
    }

    private void closeNavigation() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onBackPressed() {
        closeNavigation();
        FragmentManager fragmentManager = getSupportFragmentManager();
        int count = fragmentManager.getBackStackEntryCount();
        if (count > 1) {
            fragmentManager.popBackStackImmediate();
        } else {
            /*PopUtils.showDailog(this, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (view.getId()) {
                        case R.id.btnOk:
                            finish();
                            break;
                        case R.id.btnCancel:
                            break;
                         default:
                      }
                }
            }, "Are you sure exit from app.");*/
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
        } else if (id == R.id.nav_gallery) {
        } else if (id == R.id.nav_slideshow) {
        } else if (id == R.id.nav_manage) {
        } else if (id == R.id.nav_share) {
        } else if (id == R.id.nav_send) {
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgMenu:
                openNavigation();
                break;
            case R.id.txtDriver:
                closeNavigation();
                break;
            case R.id.txtBookingHistoy:
                closeNavigation();
                BookingHistoryFragment bookingHistoryFragment = new BookingHistoryFragment();
                replaceFragmenr(bookingHistoryFragment, bookingHistoryFragment.getTag(), false);
                break;
            case R.id.txtSupport:
                closeNavigation();
                break;
            case R.id.txtTermAndCond:
                closeNavigation();
                break;
            case R.id.txtRegisterCar:
                closeNavigation();
                break;
            case R.id.txtLogOut:
                closeNavigation();
                PoupUtils.showConfirmationDailog(MainActivity.this, "Are You sure want exit from application ?",
                        yes -> {
                            Toast.makeText(MainActivity.this, "click yes", Toast.LENGTH_SHORT).show();
                        }, no -> {
                            Toast.makeText(MainActivity.this, "click no", Toast.LENGTH_SHORT).show();
                        });
                break;
            default:
                break;
        }
    }


}
