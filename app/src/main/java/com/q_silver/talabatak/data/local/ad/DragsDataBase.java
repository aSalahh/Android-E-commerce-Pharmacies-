package com.q_silver.talabatak.data.local.ad;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.q_silver.talabatak.data.local.ad.model.DragsData;
import com.q_silver.talabatak.data.local.ad.model.PhLocalData;

import java.util.concurrent.Executors;

@Database(entities = {DragsData.class},version = 1,exportSchema = false)
public abstract class DragsDataBase extends RoomDatabase {


    private static final String DATABASE_NAME = "drags.db";
    private static DragsDataBase mInstance;
    private static final RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new DragsDataBase.populateDbAsyncTask(mInstance).execute();
        }
    };

    public static synchronized DragsDataBase getInstance(final Context context) {
        if (mInstance == null) {
            mInstance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    DragsDataBase.class,
                    DragsDataBase.DATABASE_NAME
            ).fallbackToDestructiveMigrationFrom()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull final SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                                @Override
                                public void run() {
                                    getInstance(context).dragsDao().insertAllDrags(PhLocalData.dragsData());
                                }
                            });
                        }
                    })
                    .build();
        }
        return mInstance;


    }

    public abstract DragsDao dragsDao();

    private static class populateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private final DragsDao dragsDao;

        private populateDbAsyncTask(DragsDataBase db) {
            dragsDao = db.dragsDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }

}
