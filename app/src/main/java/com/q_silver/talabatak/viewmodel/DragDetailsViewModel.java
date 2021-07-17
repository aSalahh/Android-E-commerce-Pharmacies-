package com.q_silver.talabatak.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.q_silver.talabatak.data.local.ad.model.Order;
import com.q_silver.talabatak.data.repository.OrderRepo;

import java.util.List;

public class DragDetailsViewModel extends AndroidViewModel {
    private OrderRepo repository;
    public DragDetailsViewModel(@NonNull Application application) {
        super(application);
        repository=new OrderRepo(application);
    }
    public void insert(Order order){
        repository.insert(order);
    }

}