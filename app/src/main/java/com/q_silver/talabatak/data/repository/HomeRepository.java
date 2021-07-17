package com.q_silver.talabatak.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.q_silver.talabatak.data.local.ad.DragsDao;
import com.q_silver.talabatak.data.local.ad.DragsDataBase;
import com.q_silver.talabatak.data.local.ad.PharmaciesDao;
import com.q_silver.talabatak.data.local.ad.PharmaciesDataBase;
import com.q_silver.talabatak.data.local.ad.model.DragsData;
import com.q_silver.talabatak.data.local.ad.model.PharmaciesData;

import java.util.List;

public class HomeRepository {
    PharmaciesDao pharmaciesDao;
    DragsDao dragsDao;
    private LiveData<List<PharmaciesData>> pharmaciesData;
    private LiveData<List<DragsData>> dragsData;



    public HomeRepository(Application application) {
        PharmaciesDataBase pharmaciesDataBase=PharmaciesDataBase.getInstance(application);
        DragsDataBase dragsDataBase=DragsDataBase.getInstance(application);
        pharmaciesDao =pharmaciesDataBase.pharmacieDao();
        dragsDao =dragsDataBase.dragsDao();
        pharmaciesData= pharmaciesDao.getAllPharmacies();
        dragsData= dragsDao.getAllDrags();

    }

    public  LiveData<List<PharmaciesData>> getPharmaciesList() {
        return pharmaciesData;
    }
    public  LiveData<List<DragsData>> getDragsList() {
        return dragsData;
    }
}
