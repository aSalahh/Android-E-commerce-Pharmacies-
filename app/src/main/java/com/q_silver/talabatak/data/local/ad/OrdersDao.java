package com.q_silver.talabatak.data.local.ad;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.q_silver.talabatak.data.local.ad.model.Order;

import java.util.List;

@Dao
public interface OrdersDao {
    @Insert
    void insertOrder(Order order);

    @Update
    void updateOrder(Order order);

    @Delete
    void DeleteOrder(Order order);

    @Query("SELECT * FROM orders")
    LiveData<List<Order>> getAllOrders();

    @Query("UPDATE orders SET count = count + 1 WHERE id = :id")
    public void incrementValue(int id);

    @Query("UPDATE orders SET count = count - 1 WHERE id = :id")
    public void decrementValue(int id);

    @Query("DELETE FROM orders")
    void deleteAllOrders();



}
