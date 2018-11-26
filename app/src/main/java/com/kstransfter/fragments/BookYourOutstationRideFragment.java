package com.kstransfter.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kstransfter.R;
import com.kstransfter.adapters.BookOutSideStaionAdapter;

import java.util.ArrayList;

/**
 * Created by SONI on 11/18/2018.
 */

public class BookYourOutstationRideFragment extends BaseFragment {

    public static String TAG = BookYourOutstationRideFragment.class.getSimpleName();
    private RecyclerView rvItem;
    private TextView txtFrom, txtTo, txtStart, txtEnd;
    private LinearLayout llOneTrip, llRound;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_book_outsation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvItem = view.findViewById(R.id.rvItem);
        txtFrom = view.findViewById(R.id.txtFrom);
        txtTo = view.findViewById(R.id.txtTo);
        txtStart = view.findViewById(R.id.txtStart);
        txtEnd = view.findViewById(R.id.txtEnd);
        llOneTrip = view.findViewById(R.id.llOneTrip);
        llRound = view.findViewById(R.id.llRound);
        rvItem.setLayoutManager(new LinearLayoutManager(getContext()));
        try {
            initital();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initital() {

        llOneTrip.setOnClickListener(v -> {
            Toast.makeText(getContext(), "One trip", Toast.LENGTH_SHORT).show();
        });
        llRound.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Round trip", Toast.LENGTH_SHORT).show();
        });

        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            names.add("test");
        }
        BookOutSideStaionAdapter bookOutSideStaionAdapter = new BookOutSideStaionAdapter(getContext(), names, (v, pos) -> {

        });
        rvItem.setAdapter(bookOutSideStaionAdapter);
    }
}
