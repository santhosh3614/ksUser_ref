package com.kstransfter.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kstransfter.R;

import java.util.ArrayList;

public class BookHistoryAdapter extends RecyclerView.Adapter<BookHistoryAdapter.BookHolder> {

    private Context context;
    private ArrayList<String> bookingHistory;
    private LayoutInflater layoutInflater;

    public BookHistoryAdapter(Context context, ArrayList<String> bookingHistory) {
        this.context = context;
        this.bookingHistory = bookingHistory;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookHolder(layoutInflater.inflate(R.layout.item_book_hostory,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return bookingHistory.size();
    }

    class BookHolder extends RecyclerView.ViewHolder {

        public BookHolder(View itemView) {
            super(itemView);
        }
    }

}
