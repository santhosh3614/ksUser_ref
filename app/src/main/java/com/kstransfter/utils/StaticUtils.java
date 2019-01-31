package com.kstransfter.utils;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.kstransfter.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class StaticUtils {

    // global topic to receive app wide push notifications
    public static final String TOPIC_GLOBAL = "global";

    // broadcast receiver intent filters
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String PUSH_NOTIFICATION = "pushNotification";
    // id to handle the notification in the notification tray
    public static final int NOTIFICATION_ID = 100;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 101;
    public static final String SHARED_PREF = "ah_firebase";
    public static final int REQUEST_FOR_MOVIE_RESPONSE = 5001;
    public static final int REQUEST_FOR_AUOTOCOMPLETE_ADDRESS = 5002;
    public static final int REQUEST_SIGN_UP = 5003;
    public static final int REQUEST_OTP_SEND_PASSWORD = 5004;
    public static final int REQUEST_CAR_LIST = 5004;
    public static final int REQUEST_DRIVER_LIST = 5005;
    public static final int REQUEST_DRIVER_CONFIRM_BOOKING = 5006;
    public static final int REQUEST_GET_PAGES = 5007;
    public static final int REQUEST_BOOKING_HISTORY = 5008;
    public static final int REQUEST_RATING_REVIEW = 5010;




    public static void showSnakBar(Context context, ViewGroup viewGroup, String message) {
        Snackbar mSnackBar = Snackbar.make(viewGroup, message, Snackbar.LENGTH_LONG);
        View view = mSnackBar.getView();
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
        params.gravity = Gravity.TOP;
        view.setLayoutParams(params);
        view.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        TextView mainTextView = (TextView) (view).findViewById(android.support.design.R.id.snackbar_text);
        mainTextView.setTextColor(Color.WHITE);
        mSnackBar.show();
    }

    public static boolean checkMailValidation(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }

    //given in km
    public static long distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515 * 2;
        return (long) dist;
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    public static String getDateAndTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    public static String converDateFormate(String inputDate) {
        String inputPattern = "dd-MM-yyyy HH:mm";
        String outputPattern = "dd MMM, hh:mm a";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date date = null;
        String str = null;
        try {
            date = inputFormat.parse(inputDate);
            str = outputFormat.format(date);
         } catch (ParseException e) {
            e.printStackTrace();
         }
        return str;
    }

}
