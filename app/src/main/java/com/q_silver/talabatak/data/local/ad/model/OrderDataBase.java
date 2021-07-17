package com.q_silver.talabatak.data.local.ad.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.q_silver.talabatak.data.local.ad.OrdersDao;


@Database(entities = {Order.class}, version = 1, exportSchema = false)
public abstract class OrderDataBase extends RoomDatabase {

    private static final String DATABASE_NAME = "orders.db";
    private static OrderDataBase instance;
    private static final RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new OrderDataBase.populateDbAsyncTask(instance).execute();
        }
    };

    public static synchronized OrderDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), OrderDataBase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigrationFrom()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    public abstract OrdersDao orderDao();

    private static class populateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private final OrdersDao ordersDao;

        private populateDbAsyncTask(OrderDataBase db) {
            ordersDao = db.orderDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }


}
