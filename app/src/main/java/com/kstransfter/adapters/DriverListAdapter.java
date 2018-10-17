package com.kstransfter.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.kstransfter.R;
import com.kstransfter.interfaces.RvListeners;
import com.kstransfter.models.app.Driver;
import java.util.ArrayList;

public class DriverListAdapter extends RecyclerView.Adapter<DriverListAdapter.DriverHolder> {

    private Context context;
    private ArrayList<Driver> drivers;
    private LayoutInflater inflater;
    private RvListeners rvListeners;

    public DriverListAdapter(Context context, ArrayList<Driver> drivers, RvListeners rvListeners) {
        this.context = context;
        this.drivers = drivers;
        this.rvListeners = rvListeners;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
     }

    @NonNull
    @Override
    public DriverHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DriverHolder(inflater.inflate(R.layout.item_driver, parent, false));
     }

    @Override
    public void onBindViewHolder(@NonNull DriverHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return drivers.size();
    }

    public class DriverHolder extends RecyclerView.ViewHolder {
        private RelativeLayout rlDriver;
        public DriverHolder(View view) {
            super(view);
            rlDriver = view.findViewById(R.id.rlDriver);
        }
    }
}
