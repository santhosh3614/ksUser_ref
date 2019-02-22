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
import com.kstransfter.models.app.CarListtModel;

import java.util.ArrayList;

public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.BookHolder> {

    private Context context;
    private ArrayList<CarListtModel> carListModels;
    private LayoutInflater layoutInflater;
    private RvListeners rvListeners;

    public CarListAdapter(Context context, ArrayList<CarListtModel> carListModels, RvListeners rvListeners) {
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
