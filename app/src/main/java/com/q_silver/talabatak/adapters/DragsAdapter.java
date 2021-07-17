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

public class DragsAdapter extends RecyclerView.Adapter<DragsAdapter.DragsAdapterViewHolder> {

    Context context;
    List<DragsData> dragsData;
    private OnItemClickListener listener;

    public DragsAdapter(Context context, List<DragsData> dragsData) {
        this.context = context;
        this.dragsData = dragsData;
    }

    @NonNull
    @Override
    public DragsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.drags_row_items, parent, false);
        return new DragsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DragsAdapterViewHolder holder, int position) {

        holder.drags.setImageResource(dragsData.get(position).getImageUrl());
        holder.name.setText(dragsData.get(position).getName());
        holder.price.setText(String.valueOf(dragsData.get(position).getPrice()));
        holder.type.setText(dragsData.get(position).getType());

    }

    @Override
    public int getItemCount() {
        return dragsData.size();
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(DragsData pharmaciesData);

    }
    public  class DragsAdapterViewHolder extends  RecyclerView.ViewHolder{

        ImageView drags;
        TextView name;
        TextView type;
        TextView price;

        public DragsAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            drags = itemView.findViewById(R.id.drags_image);
            name=itemView.findViewById(R.id.drags_item_name);
            type=itemView.findViewById(R.id.type);
            price=itemView.findViewById(R.id.price);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(dragsData.get(position));

                    }
                }
            });

        }
    }
}
