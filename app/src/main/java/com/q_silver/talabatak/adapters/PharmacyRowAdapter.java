package com.q_silver.talabatak.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.q_silver.talabatak.R;
import com.q_silver.talabatak.data.local.ad.model.DragsData;
import com.q_silver.talabatak.data.local.ad.model.PharmaciesData;

import java.util.List;

public class PharmacyRowAdapter extends RecyclerView.Adapter<PharmacyRowAdapter.PharmacyAdapterViewHolder> {
   private OnItemClickListener listener;
    Context context;
    List<PharmaciesData> pharmacyItems;


    public PharmacyRowAdapter(Context context, List<PharmaciesData> pharmacyItems) {
        this.context = context;
        this.pharmacyItems = pharmacyItems;
    }

    @NonNull
    @Override
    public PharmacyAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.pharmacy_items, parent, false);
        return new PharmacyAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PharmacyAdapterViewHolder holder, int position) {
        PharmaciesData currenPharmacy = pharmacyItems.get(position);
        holder.discountImageView.setImageResource(pharmacyItems.get(position).getImageUrl());
        holder.name.setText(pharmacyItems.get(position).getName());
        holder.description.setText(pharmacyItems.get(position).getDesc());

    }

    @Override
    public int getItemCount() {
        return pharmacyItems.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(PharmaciesData pharmaciesData);

    }

    public  class PharmacyAdapterViewHolder extends RecyclerView.ViewHolder {

        ImageView discountImageView;
        TextView name,description;

        public PharmacyAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            discountImageView = itemView.findViewById(R.id.pharmacy_details_image);
            name = itemView.findViewById(R.id.pharmacy_details_name);
            description= itemView.findViewById(R.id.pharmacy_description);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(pharmacyItems.get(position));

                    }
                }
            });

        }

    }
    public void filterList(List<PharmaciesData> dragsDataSearch){
        pharmacyItems=dragsDataSearch;
        notifyDataSetChanged();
    }
}

