package com.q_silver.talabatak.data.local.ad;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.q_silver.talabatak.data.local.ad.model.PhLocalData;
import com.q_silver.talabatak.data.local.ad.model.PharmaciesData;

import java.util.concurrent.Executors;

@Database(entities = {PharmaciesData.class}, version = 1, exportSchema = false)
public abstract class PharmaciesDataBase extends RoomDatabase {


    private static final String DATABASE_NAME = "pharmacies.db";
    private static PharmaciesDataBase mInstance;
    private static final RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new populateDbAsyncTask(mInstance).execute();
        }
    };

    public static synchronized PharmaciesDataBase getInstance(final Context context) {
        if (mInstance == null) {
            mInstance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    PharmaciesDataBase.class,
                    PharmaciesDataBase.DATABASE_NAME
            ).fallbackToDestructiveMigrationFrom()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull final SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                                @Override
                                public void run() {
                                    getInstance(context).pharmacieDao().insertAllPharmacies(PhLocalData.pharmaciesData());
                                }
                            });
                        }
                    })
                    .build();
        }
        return mInstance;


    }

    public abstract PharmaciesDao pharmacieDao();

    private static class populateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private final PharmaciesDao pharmaciesDao;

        private populateDbAsyncTask(PharmaciesDataBase db) {
            pharmaciesDao = db.pharmacieDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
