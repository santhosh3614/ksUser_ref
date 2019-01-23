package com.kstransfter.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kstransfter.R;
import com.kstransfter.utils.PoupUtils;

public class PaymentReceiptFragment extends BaseFragment {

    public static String TAG = PaymentReceiptFragment.class.getSimpleName();

    public static PaymentReceiptFragment getInatance(Bundle bundle) {
        PaymentReceiptFragment paymentReceiptFragment = new PaymentReceiptFragment();
        paymentReceiptFragment.setArguments(bundle);
        return paymentReceiptFragment;
    }

    private TextView txtReviewAndRate;
    private FragmentActivity mainFragment;

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
        mainFragment = getActivity();
        txtReviewAndRate.setOnClickListener(v -> {
            PoupUtils.ratingDialog(mainFragment, "Rate Us", submit -> {
                float rating = (float) submit.getTag();
                Toast.makeText(mainFragment, "Rating: " + rating, Toast.LENGTH_SHORT).show();
            });
        });
    }
}
