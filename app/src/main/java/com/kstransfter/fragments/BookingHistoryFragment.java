package com.kstransfter.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.kstransfter.R;
import com.kstransfter.activities.MainActivity;
import com.kstransfter.adapters.BookHistoryAdapter;
import java.util.ArrayList;

public class BookingHistoryFragment extends BaseFragment {
    private RecyclerView rvBookingHistory;
    private ArrayList<String> bookings = new ArrayList<>();
    private MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_booking_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvBookingHistory = view.findViewById(R.id.rvBookingHistory);
        rvBookingHistory.setLayoutManager(new LinearLayoutManager(getContext()));
        try {
             initital();
            } catch (Exception e) {
             e.printStackTrace();
        }
    }

    @Override
    public void initital() {
        mainActivity = (MainActivity) getActivity();
        for (int i = 0; i < 20; i++) {
            bookings.add("one");
             }
        BookHistoryAdapter bookHistoryAdapter = new BookHistoryAdapter(getContext(), bookings);
        rvBookingHistory.setAdapter(bookHistoryAdapter);
        setVisibleAndGone();
     }

    private void setVisibleAndGone() {
        mainActivity.txtLocalRides.setVisibility(View.GONE);
        mainActivity.txtOutSideRide.setVisibility(View.GONE);
        mainActivity.imgMenu.setVisibility(View.GONE);
        mainActivity.imgBack.setVisibility(View.VISIBLE);
        mainActivity.txtTitle.setVisibility(View.VISIBLE);
        mainActivity.txtTitle.setText("Booking History");
     }

}
