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

public class ShowAllDragsRepository {
    DragsDao dragsDao;
    private LiveData<List<DragsData>> dragsData;



    public ShowAllDragsRepository(Application application) {
        DragsDataBase dragsDataBase=DragsDataBase.getInstance(application);
        dragsDao =dragsDataBase.dragsDao();
        dragsData= dragsDao.getAllDrags();

    }

    public  LiveData<List<DragsData>> getDragsList() {
        return dragsData;
    }
}
