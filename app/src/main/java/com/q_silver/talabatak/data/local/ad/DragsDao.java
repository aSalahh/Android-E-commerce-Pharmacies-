package com.q_silver.talabatak.data.local.ad;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import com.q_silver.talabatak.data.local.ad.model.DragsData;

import java.util.List;

@Dao
public interface DragsDao {

    @Query("SELECT * FROM drags")
    LiveData<List<DragsData>> getAllDrags();


    @Insert
    void insertAllDrags(DragsData[] dragsData);

}
