package com.kstransfter.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kstransfter.R;
import com.kstransfter.interfaces.RvListeners;
import com.kstransfter.models.app.DriverListModel;

import java.util.ArrayList;
import java.util.List;

public class DriverListAdapter extends RecyclerView.Adapter<DriverListAdapter.DriverHolder> {

    private Context context;
    private List<DriverListModel.ResponseDatum> drivers;
    private LayoutInflater inflater;
    private RvListeners rvListeners;

    public DriverListAdapter(Context context, List<DriverListModel.ResponseDatum> drivers, RvListeners rvListeners) {
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
        DriverListModel.ResponseDatum responseDatum = drivers.get(position);
        holder.txtDriver.setOnClickListener(v -> {
            rvListeners.onItemclick(holder.rlDriver, position);
        });
        String image = responseDatum.getVDriverImage();
        if (!TextUtils.isEmpty(image))
            Glide.with(context).load(image).into(holder.imgDriverProfile);
        holder.txtDriverName.setText(responseDatum.getVDriverName());
        holder.txtExprence.setText(responseDatum.getVDriverExp());
    }

    @Override
    public int getItemCount() {
        return drivers.size();
    }

    public class DriverHolder extends RecyclerView.ViewHolder {
        private RelativeLayout rlDriver;
        private ImageView imgDriverProfile;
        private TextView txtDriverName, txtExprence, txtDriver;

        public DriverHolder(View view) {
            super(view);
            rlDriver = view.findViewById(R.id.rlDriver);
            txtDriverName = view.findViewById(R.id.txtDriverName);
            txtExprence = view.findViewById(R.id.txtExprence);
            txtDriver = view.findViewById(R.id.txtDriver);
            imgDriverProfile = view.findViewById(R.id.imgDriverProfile);
        }
    }
}
