package com.kstransfter.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.kstransfter.activities.LoginActivity;

import java.util.HashMap;

public class SessionManager {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context _context;
    private int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "AndroidHivePref";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_USER_TYPE = "user_type";
    public static final String KEY_FROM = "from";
    public static final String KEY_TO = "to";
    public static final String KEY_START_DATE = "start_date";
    public static final String KEY_END_DATE = "end_date";
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_NOTIFACTION_KEY = "notification_key";
    public static final String KEY_PICKUP_LAT = "dcPickUpLatitude";
    public static final String KEY_PICKUP_LONG = "dcPickUpLongitude";
    public static final String KEY_DURATION = "duration";
    public static final String KEY_DISTANCE_STRING = "distance_string";
    public static final String KEY_DISTANCE = "distance";
    public static final String KEY_IS_ROUND = "isRound";


    // Constructor
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void checkLogin() {
        if (!this.isLoggedIn()) {
            Intent i = new Intent(_context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        return user;
    }

    public String getName() {
        return pref.getString(KEY_NAME, "");
    }

    public void setName(String name) {
        editor.putString(KEY_NAME, name);
        editor.commit();
    }

    public String getEmail() {
        return pref.getString(KEY_EMAIL, "");
    }

    public void setEmail(String name) {
        editor.putString(KEY_EMAIL, name);
        editor.commit();
    }


    public void logoutUser() {
        editor.clear();
        editor.commit();
    }

    public void setLoginTrue() {
        editor.putBoolean(IS_LOGIN, true);
        editor.commit();
    }

    public String getSearchType() {
        return pref.getString(KEY_USER_TYPE, "");
    }

    public void setSearchType(String searchType) {
        editor.putString(KEY_USER_TYPE, searchType);
        editor.commit();
    }

    public boolean isRaundTrip() {
        return pref.getBoolean(KEY_IS_ROUND, false);
    }

    public void setRound(Boolean isRoundOrNot) {
        editor.putBoolean(KEY_IS_ROUND, isRoundOrNot);
        editor.commit();
    }


    public String getPickUpLat() {
        return pref.getString(KEY_PICKUP_LAT, "");
    }

    public void setPickUpLat(String pickup) {
        editor.putString(KEY_PICKUP_LAT, pickup);
        editor.commit();
    }

    public String getPickUpLong() {
        return pref.getString(KEY_PICKUP_LONG, "");
    }

    public void setPickUpLong(String pickup) {
        editor.putString(KEY_PICKUP_LONG, pickup);
        editor.commit();
    }


    public String getNotificationToken() {
        return pref.getString(KEY_NOTIFACTION_KEY, "abc123456");
    }

    public void setNoificationToken(String token) {
        editor.putString(KEY_NOTIFACTION_KEY, token);
        editor.commit();
    }


    public String getUserId() {
        return pref.getString(KEY_USER_ID, "5");
    }

    public void setUserId(String userId) {
        editor.putString(KEY_USER_ID, userId);
        editor.commit();
    }

    public String getDistance() {
        return pref.getString(KEY_DISTANCE, "");
    }

    public void setDistance(String distance) {
        editor.putString(KEY_DISTANCE, distance);
        editor.commit();
    }

    public String getDistanceString() {
        return pref.getString(KEY_DISTANCE_STRING, "");
    }

    public void setDistanceString(String distanceString) {
        editor.putString(KEY_DISTANCE_STRING, distanceString);
        editor.commit();
    }

    public void setStartDate(String startDate) {
        editor.putString(KEY_START_DATE, startDate);
        editor.commit();
    }

    public void setEndDate(String endDate) {
        editor.putString(KEY_END_DATE, endDate);
        editor.commit();
    }

    public String getStartDate() {
        return pref.getString(KEY_START_DATE, "");
    }

    public String getEndDate() {
        return pref.getString(KEY_END_DATE, "");
    }


    public String getFrom() {
        return pref.getString(KEY_FROM, "");
    }

    public void setFrom(String searchType) {
        editor.putString(KEY_FROM, searchType);
        editor.commit();
    }


    public String getDuration() {
        return pref.getString(KEY_DURATION, "");
    }

    public void setDuration(String searchType) {
        editor.putString(KEY_DURATION, searchType);
        editor.commit();
    }


    public String getTo() {
        return pref.getString(KEY_TO, "");
    }

    public void setTo(String searchType) {
        editor.putString(KEY_TO, searchType);
        editor.commit();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }


    public void clearPrefrences() {
        editor.remove(KEY_DISTANCE);
        editor.remove(KEY_DURATION);
        editor.remove(KEY_START_DATE);
        editor.remove(KEY_END_DATE);
        editor.remove(KEY_DISTANCE_STRING);
        editor.remove(KEY_PICKUP_LAT);
        editor.remove(KEY_PICKUP_LONG);
        editor.commit();
    }

    public void clearEndDate() {
        editor.remove(KEY_END_DATE);
        editor.commit();
    }


}
