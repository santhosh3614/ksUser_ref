package com.kstransfter.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kstransfter.R;
import com.kstransfter.interfaces.RvListeners;
import com.kstransfter.models.app.CarListtModel;
import com.kstransfter.utils.StaticUtils;

import java.util.List;


public class BookOutSideStaionAdapter extends RecyclerView.Adapter<BookOutSideStaionAdapter.BookHolder> {

    private Context context;
    private List<CarListtModel.ResponseDatum> responseDatumList;
    private LayoutInflater layoutInflater;
    private RvListeners rvListeners;
    private boolean isRound;

    public BookOutSideStaionAdapter(Context context, List<CarListtModel.ResponseDatum> responseDatumList, RvListeners rvListeners, Boolean isRound) {
        this.context = context;
        this.responseDatumList = responseDatumList;
        this.rvListeners = rvListeners;
        this.isRound = isRound;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookHolder(layoutInflater.inflate(R.layout.item_book_out_station, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position) {
        CarListtModel.ResponseDatum responseDatum = responseDatumList.get(position);
        Glide.with(context).load(responseDatum.getVCarImage()).into(holder.imgCar);
        holder.txtDate.setText(StaticUtils.converDateFormate(StaticUtils.getDateAndTime()));
        holder.txtPricePerKm.setText(responseDatum.getIPrice() + " / " + "Km");
        if (isRound) {
            holder.txtPrice.setVisibility(View.GONE);
            holder.txtPricePerKm.setVisibility(View.VISIBLE);
        } else {
            holder.txtPrice.setVisibility(View.VISIBLE);
            holder.txtPricePerKm.setVisibility(View.GONE);
        }

        holder.llItem.setOnClickListener(v -> {
            rvListeners.onItemclick(holder.itemView, position);
        });
        if (responseDatum.getVDriverName() != null) {
            holder.txtCar.setText(responseDatum.getVDriverName().toString());
        }
        if (responseDatum.getTotalPrice() != null) {
            holder.txtPrice.setText("Rs. " + responseDatum.getTotalPrice().toString());
        }

    }

    @Override
    public int getItemCount() {
        return responseDatumList.size();
    }

    class BookHolder extends RecyclerView.ViewHolder {
        private LinearLayout llItem;
        private ImageView imgCar;
        private TextView txtCar, txtDate, txtPrice, txtPricePerKm;

        public BookHolder(View view) {
            super(view);
            llItem = view.findViewById(R.id.llItem);
            txtCar = view.findViewById(R.id.txtCar);
            txtDate = view.findViewById(R.id.txtDate);
            txtPrice = view.findViewById(R.id.txtPrice);
            imgCar = view.findViewById(R.id.imgCar);
            txtPricePerKm = view.findViewById(R.id.txtPricePerKm);
        }
    }

}
