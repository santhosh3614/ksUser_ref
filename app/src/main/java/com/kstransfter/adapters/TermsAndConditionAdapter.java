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

import com.kstransfter.R;
import com.kstransfter.interfaces.RvListeners;
import com.kstransfter.models.app.DriverListModel;
import com.kstransfter.models.app.GetPages;

import java.util.List;

public class TermsAndConditionAdapter extends RecyclerView.Adapter<TermsAndConditionAdapter.DriverHolder> {

    private Context context;
    private List<GetPages.ResponseDatum> pages;
    private LayoutInflater inflater;
    private RvListeners rvListeners;

    public TermsAndConditionAdapter(Context context, List<GetPages.ResponseDatum> pages, RvListeners rvListeners) {
        this.context = context;
        this.pages = pages;
        this.rvListeners = rvListeners;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public DriverHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DriverHolder(inflater.inflate(R.layout.item_terms_condition, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DriverHolder holder, int position) {
        GetPages.ResponseDatum responseDatum = pages.get(position);
        holder.termsAndContion.setText(responseDatum.getVTitle());
        holder.termsAndContion.setOnClickListener(v -> {
            rvListeners.onItemclick(holder.termsAndContion, position);
        });
    }

    @Override
    public int getItemCount() {
        return pages.size();
    }

    public class DriverHolder extends RecyclerView.ViewHolder {
        private TextView termsAndContion;

        public DriverHolder(View view) {
            super(view);
            termsAndContion = view.findViewById(R.id.termsAndContion);
        }
    }
}
