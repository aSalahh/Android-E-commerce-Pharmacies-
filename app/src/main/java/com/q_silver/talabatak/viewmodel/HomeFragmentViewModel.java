package com.q_silver.talabatak.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.q_silver.talabatak.data.local.ad.model.DragsData;
import com.q_silver.talabatak.data.local.ad.model.PharmaciesData;
import com.q_silver.talabatak.data.repository.HomeRepository;

import java.util.List;

public class HomeFragmentViewModel extends AndroidViewModel {
    HomeRepository homeRepository;
    private final LiveData<List<PharmaciesData>> pharmaciesData;
    private final LiveData<List<DragsData>> dragsData;

    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);
        homeRepository = new HomeRepository(application);
        pharmaciesData = homeRepository.getPharmaciesList();
        dragsData = homeRepository.getDragsList();
    }

    public LiveData<List<PharmaciesData>> getPharmaciesList() {
        return pharmaciesData;
    }
    public LiveData<List<DragsData>> getDragsList() {
        return dragsData;
    }

}
