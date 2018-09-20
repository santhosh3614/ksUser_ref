package com.kstransfter.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.kstransfter.R;
import com.kstransfter.interfaces.RvListeners;
import com.kstransfter.models.app.CarListModel;

import java.util.ArrayList;

public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.BookHolder> {

    private Context context;
    private ArrayList<CarListModel> carListModels;
    private LayoutInflater layoutInflater;
    private RvListeners rvListeners;

    public CarListAdapter(Context context, ArrayList<CarListModel> carListModels, RvListeners rvListeners) {
        this.context = context;
        this.carListModels = carListModels;
        this.rvListeners = rvListeners;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookHolder(layoutInflater.inflate(R.layout.item_car, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position) {

        if (carListModels.get(position).isSelected()) {
            holder.llCar.setSelected(true);
        } else {
            holder.llCar.setSelected(false);
        }

        holder.llCar.setOnClickListener(v -> {
            CarListModel carListModel = carListModels.get(position);
            if (holder.llCar.isSelected()) {
                carListModel.setSelected(false);
            } else {
                carListModel.setSelected(true);
            }
            carListModels.remove(position);
            carListModels.add(position, carListModel);
            notifyDataSetChanged();
            rvListeners.onItemclick(holder.llCar, position);
        });

    }

    @Override
    public int getItemCount() {
        return carListModels.size();
    }

    class BookHolder extends RecyclerView.ViewHolder {
        private LinearLayout llCar;

        public BookHolder(View view) {
            super(view);
            llCar = view.findViewById(R.id.llCar);

        }
    }

}
