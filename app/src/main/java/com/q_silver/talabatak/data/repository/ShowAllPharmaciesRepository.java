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

public class ShowAllPharmaciesRepository {
    PharmaciesDao pharmaciesDao;
    private LiveData<List<PharmaciesData>> pharmaciesData;



    public ShowAllPharmaciesRepository(Application application) {
        PharmaciesDataBase pharmaciesDataBase=PharmaciesDataBase.getInstance(application);
        pharmaciesDao =pharmaciesDataBase.pharmacieDao();
        pharmaciesData= pharmaciesDao.getAllPharmacies();

    }

    public  LiveData<List<PharmaciesData>> getPharmaciesList() {
        return pharmaciesData;
    }
}
