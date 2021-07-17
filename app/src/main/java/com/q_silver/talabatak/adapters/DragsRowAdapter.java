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

import java.util.ArrayList;
import java.util.List;

public class DragsRowAdapter extends RecyclerView.Adapter<DragsRowAdapter.PharmacyAdapterViewHolder> {
   private OnItemClickListener listener;
    Context context;
    List<DragsData> dragsItems;


    public DragsRowAdapter(Context context, List<DragsData> dragsItems) {
        this.context = context;
        this.dragsItems = dragsItems;
    }

    @NonNull
    @Override
    public PharmacyAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.drags_items, parent, false);
        return new PharmacyAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PharmacyAdapterViewHolder holder, int position) {
        holder.discountImageView.setImageResource(dragsItems.get(position).getImageUrl());
        holder.name.setText(dragsItems.get(position).getName());
        holder.description.setText(dragsItems.get(position).getDescription());
        holder.type.setText(dragsItems.get(position).getType());

    }

    @Override
    public int getItemCount() {
        return dragsItems.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(DragsData dragsData);

    }

    public  class PharmacyAdapterViewHolder extends RecyclerView.ViewHolder {

        ImageView discountImageView;
        TextView name,description,type;

        public PharmacyAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            discountImageView = itemView.findViewById(R.id.drags_details_image);
            name = itemView.findViewById(R.id.drags_details_name);
            description= itemView.findViewById(R.id.drags_description);
            type= itemView.findViewById(R.id.drags_details_type);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(dragsItems.get(position));

                    }
                }
            });

        }



    }

    public void filterList(List<DragsData> dragsDataSearch){
         dragsItems=dragsDataSearch;
         notifyDataSetChanged();
    }
}

