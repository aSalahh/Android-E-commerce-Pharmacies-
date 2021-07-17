package com.q_silver.talabatak.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.q_silver.talabatak.data.local.ad.model.Order;
import com.q_silver.talabatak.data.repository.OrderRepo;

import java.util.List;

public class OrderViewModel extends AndroidViewModel {
    private OrderRepo repository;
    private LiveData<List<Order>> allNOrders;
    public OrderViewModel(@NonNull Application application) {
        super(application);
        repository=new OrderRepo(application);
        allNOrders=repository.getAllOrders();
    }
    public void insert(Order order){
        repository.insert(order);
    }
    public void update(Order order){
        repository.update(order);
    }
    public void delete(Order order){
        repository.delete(order);
    }   public void increaseCount(Order order){
        repository.incrementValue(order);
    }public void deleteAllOrders( ){
        repository.deleteAllOrders();
    }

    public void decrementCount(Order order) {
        repository.decrementValue(order);
    }

    public LiveData<List<Order>> getAllOrder(){
        return allNOrders;
    }
}