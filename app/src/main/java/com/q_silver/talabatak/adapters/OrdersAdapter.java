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
import com.q_silver.talabatak.data.local.ad.model.Order;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrderAdapterViewHolder> {

    Context context;
    List<Order> orderData;
    private OnItemClickListener listener;


    public OrdersAdapter(Context context, List<Order> orderData) {
        this.context = context;
        this.orderData = orderData;
    }

    @NonNull
    @Override
    public OrderAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.orders_items, parent, false);
        return new OrderAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapterViewHolder holder, int position) {

        holder.drags.setImageResource(orderData.get(position).getImageUrl());
        holder.name.setText(orderData.get(position).getName());
        holder.price.setText(String.valueOf(orderData.get(position).getPrice()));
        holder.type.setText(orderData.get(position).getType());
        if (orderData.get(position).getCount()>0){
            holder.count.setText(String.valueOf(orderData.get(position).getCount()));

        }else {
            holder.count.setText("1");
        }

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return orderData.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Order order);

        void onDelete(Order order);

        void onIncreaseCount(Order order);

        void onDecreaseCount(Order order);

    }

    public class OrderAdapterViewHolder extends RecyclerView.ViewHolder {

        ImageView drags;
        ImageView increase;
        ImageView decrease;
        ImageView delete;
        TextView name;
        TextView type;
        TextView price;
        TextView count;

        public OrderAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            drags = itemView.findViewById(R.id.order_image);
            increase = itemView.findViewById(R.id.order_increase);
            decrease = itemView.findViewById(R.id.order_decrees);
            delete = itemView.findViewById(R.id.order_delete);
            name = itemView.findViewById(R.id.order_name);
            type = itemView.findViewById(R.id.order_type);
            price = itemView.findViewById(R.id.order_price);
            count = itemView.findViewById(R.id.order_count);


            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onDelete(orderData.get(position));

                    }
                }
            });
            increase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onIncreaseCount(orderData.get(position));

                    }
                }
            });
            decrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onDecreaseCount(orderData.get(position));

                    }
                }
            });


        }
    }
}
