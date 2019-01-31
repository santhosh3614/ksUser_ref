package com.kstransfter.fragments;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kstransfter.R;
import com.kstransfter.activities.MainActivity;
import com.kstransfter.models.RatingAndReview;
import com.kstransfter.utils.PoupUtils;
import com.kstransfter.utils.StaticUtils;
import com.kstransfter.webservice.WsFactory;
import com.kstransfter.webservice.WsResponse;
import com.kstransfter.webservice.WsUtils;

import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;

public class PaymentReceiptFragment extends BaseFragment implements WsResponse {

    private AlertDialog progressDialog;
    private MainActivity mainActivity;

    public static String TAG = PaymentReceiptFragment.class.getSimpleName();

    public static PaymentReceiptFragment getInatance(Bundle bundle) {
        PaymentReceiptFragment paymentReceiptFragment = new PaymentReceiptFragment();
        paymentReceiptFragment.setArguments(bundle);
        return paymentReceiptFragment;
    }

    private TextView txtReviewAndRate;
    private String iDriverId, iUserId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_payment_receipt, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtReviewAndRate = view.findViewById(R.id.txtReviewAndRate);
        try {
            initital();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initital() {
        mainActivity = (MainActivity) getActivity();
        progressDialog = new SpotsDialog(mainActivity, R.style.Custom);
        iDriverId = getArguments().getString("iDriverId");
        iUserId = getArguments().getString("iUserId");

        txtReviewAndRate.setOnClickListener(v -> {
            PoupUtils.ratingDialog(mainActivity, "Rate Us", (rating, comment) -> {
                progressDialog.show();
                Map<String, String> map = new HashMap<>();
                map.put("txPickUpAddress", "asdfasdfasdfsdf");
                map.put("iDriverId", iDriverId);
                map.put("iUserId", iUserId);
                map.put("fRating", rating);
                map.put("txComment", comment);
                Call signUpWsCall = WsFactory.getRatingAndReview(map);
                WsUtils.getReponse(signUpWsCall, StaticUtils.REQUEST_RATING_REVIEW, this);
            });
        });
    }

    @Override
    public void successResponse(Object response, int code) {
        switch (code) {
            case StaticUtils.REQUEST_RATING_REVIEW:
                RatingAndReview ratingAndReview = (RatingAndReview) response;
                if (ratingAndReview != null) {
                    PoupUtils.showAlertDailog(mainActivity, ratingAndReview.getResponseMessage());
                } else {
                    PoupUtils.showAlertDailog(mainActivity, "Somthing went Wrong");
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void failureRespons(Throwable error, int code) {

    }


}
