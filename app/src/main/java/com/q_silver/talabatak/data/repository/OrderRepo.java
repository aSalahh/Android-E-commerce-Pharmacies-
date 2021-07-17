package com.q_silver.talabatak.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.q_silver.talabatak.data.local.ad.OrdersDao;
import com.q_silver.talabatak.data.local.ad.model.Order;
import com.q_silver.talabatak.data.local.ad.model.OrderDataBase;

import java.util.List;

public class OrderRepo {

    OrdersDao ordersDao;
    private final LiveData<List<Order>> allOrders;

    public OrderRepo(Application application) {
        OrderDataBase dataBase = OrderDataBase.getInstance(application);
        ordersDao = dataBase.orderDao();
        allOrders = ordersDao.getAllOrders();
    }

    public void insert(Order order) {
        new InsertOrderAsyncTask(ordersDao).execute(order);
    }

    public void update(Order order) {
        new UpdateOrderAsyncTask(ordersDao).execute(order);
    }

    public void delete(Order order) {
        new DeleteOrderAsyncTask(ordersDao).execute(order);

    }

    public void incrementValue(Order order) {
        new IncreseOrderAsyncTask(ordersDao).execute(order);


    }

    public void decrementValue(Order order) {
        new DecrementOrderAsyncTask(ordersDao).execute(order);


    }

    public void deleteAllOrders( ) {
        new DeleteOrdersAsyncTask(ordersDao).execute();


    }


    public LiveData<List<Order>> getAllOrders() {
        return allOrders;
    }


    private static class InsertOrderAsyncTask extends AsyncTask<Order, Void, Void> {
        private final OrdersDao ordereDao;

        private InsertOrderAsyncTask(OrdersDao ordereDao) {
            this.ordereDao = ordereDao;
        }

        @Override
        protected Void doInBackground(Order... order) {
            ordereDao.insertOrder(order[0]);
            return null;
        }
    }

    private static class UpdateOrderAsyncTask extends AsyncTask<Order, Void, Void> {
        private final OrdersDao ordereDao;

        private UpdateOrderAsyncTask(OrdersDao ordereDao) {
            this.ordereDao = ordereDao;
        }

        @Override
        protected Void doInBackground(Order... order) {
            ordereDao.updateOrder(order[0]);
            return null;
        }
    }

    private static class DeleteOrderAsyncTask extends AsyncTask<Order, Void, Void> {
        private final OrdersDao ordereDao;

        private DeleteOrderAsyncTask(OrdersDao ordereDao) {
            this.ordereDao = ordereDao;
        }

        @Override
        protected Void doInBackground(Order... order) {
            ordereDao.DeleteOrder(order[0]);
            return null;
        }
    }

    private static class IncreseOrderAsyncTask extends AsyncTask<Order, Void, Void> {
        private final OrdersDao ordereDao;

        private IncreseOrderAsyncTask(OrdersDao ordereDao) {
            this.ordereDao = ordereDao;
        }

        @Override
        protected Void doInBackground(Order... order) {
            ordereDao.incrementValue(order[0].getId());
            return null;
        }
    }

    private static class DecrementOrderAsyncTask extends AsyncTask<Order, Void, Void> {
        private final OrdersDao ordereDao;

        private DecrementOrderAsyncTask(OrdersDao ordereDao) {
            this.ordereDao = ordereDao;
        }

        @Override
        protected Void doInBackground(Order... order) {
            ordereDao.decrementValue(order[0].getId());
            return null;
        }
    }

    private static class DeleteOrdersAsyncTask extends AsyncTask<Void, Void, Void> {
        private final OrdersDao ordersDao;

        private DeleteOrdersAsyncTask(OrdersDao ordersDao) {
            this.ordersDao = ordersDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ordersDao.deleteAllOrders();
            return null;
        }

    }


}
