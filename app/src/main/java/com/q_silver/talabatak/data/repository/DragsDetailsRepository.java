package com.q_silver.talabatak.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.q_silver.talabatak.data.local.ad.OrdersDao;
import com.q_silver.talabatak.data.local.ad.model.Order;
import com.q_silver.talabatak.data.local.ad.model.OrderDataBase;

import java.util.List;

public class DragsDetailsRepository {
    OrdersDao ordersDao;

    public DragsDetailsRepository(Application application) {
        OrderDataBase dataBase=OrderDataBase.getInstance(application);
        ordersDao=dataBase.orderDao();
    }

    public void insert(Order order) {
        new DragsDetailsRepository.InsertOrderAsyncTask(ordersDao).execute(order);
    }








    private static class InsertOrderAsyncTask extends AsyncTask<Order, Void, Void> {
        private OrdersDao ordereDao;

        private InsertOrderAsyncTask(OrdersDao ordereDao) {
            this.ordereDao = ordereDao;
        }

        @Override
        protected Void doInBackground(Order... order) {
            ordereDao.insertOrder(order[0]);
            return null;
        }
    }




}
