package com.q_silver.talabatak.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.q_silver.talabatak.data.local.ad.model.DragsData;
import com.q_silver.talabatak.data.local.ad.model.PharmaciesData;
import com.q_silver.talabatak.data.repository.HomeRepository;
import com.q_silver.talabatak.data.repository.ShowAllPharmaciesRepository;

import java.util.List;

public class ShowAllPharmaciesViewModel extends AndroidViewModel {
    ShowAllPharmaciesRepository showAllPharmaciesRepository;
    private final LiveData<List<PharmaciesData>> pharmaciesData;

    public ShowAllPharmaciesViewModel(@NonNull Application application) {
        super(application);
        showAllPharmaciesRepository = new ShowAllPharmaciesRepository(application);
        pharmaciesData = showAllPharmaciesRepository.getPharmaciesList();
    }

    public LiveData<List<PharmaciesData>> getPharmaciesList() {
        return pharmaciesData;
    }


}
