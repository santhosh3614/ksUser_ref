package com.kstransfter.utils;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.kstransfter.R;


public class PoupUtils {

    public static void showConfirmationDailog(Activity activity, String message, View.OnClickListener yesClick, View.OnClickListener noClick) {
        final Dialog dialog = new Dialog(activity, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(R.color.black_tran_60);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.layout_dialog);
        TextView txtYes = dialog.findViewById(R.id.txtYes);
        TextView txtNo = dialog.findViewById(R.id.txtNo);
        TextView txtTitle = dialog.findViewById(R.id.txtTitle);
        txtTitle.setText(message);
        txtYes.setOnClickListener(v -> {
            dialog.cancel();
            yesClick.onClick(txtYes);

        });
        txtNo.setOnClickListener(v -> {
            dialog.cancel();
            noClick.onClick(txtNo);
        });
        dialog.show();
    }

    public static void showAlertDailog(Activity activity, String message) {

        final Dialog dialog = new Dialog(activity, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(R.color.black_tran_30);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.layout_alert_dailog);
        TextView txtOK = dialog.findViewById(R.id.txtOK);
        TextView txtTitle = dialog.findViewById(R.id.txtTitle);
        txtTitle.setText(message);

        txtOK.setOnClickListener(v -> {
            dialog.cancel();

        });

        dialog.show();


    }


}



