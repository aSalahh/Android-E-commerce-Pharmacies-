package com.q_silver.talabatak.data.local.ad;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.q_silver.talabatak.data.local.ad.model.DragsData;
import com.q_silver.talabatak.data.local.ad.model.PharmaciesData;

import java.util.List;

@Dao
public interface PharmaciesDao {
    @Query("SELECT * FROM pharmacies")
    LiveData<List<PharmaciesData>> getAllPharmacies();


    @Insert
    void insertAllPharmacies(PharmaciesData[] pharmaciesData);



}
