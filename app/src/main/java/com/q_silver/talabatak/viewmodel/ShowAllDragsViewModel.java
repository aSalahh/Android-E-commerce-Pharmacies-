package com.q_silver.talabatak.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.q_silver.talabatak.data.local.ad.model.DragsData;
import com.q_silver.talabatak.data.local.ad.model.PharmaciesData;
import com.q_silver.talabatak.data.repository.ShowAllDragsRepository;
import com.q_silver.talabatak.data.repository.ShowAllPharmaciesRepository;

import java.util.List;

public class ShowAllDragsViewModel extends AndroidViewModel {
    ShowAllDragsRepository showAllPharmaciesRepository;
    private final LiveData<List<DragsData>> dragsData;

    public ShowAllDragsViewModel(@NonNull Application application) {
        super(application);
        showAllPharmaciesRepository = new ShowAllDragsRepository(application);
        dragsData = showAllPharmaciesRepository.getDragsList();
    }

    public LiveData<List<DragsData>> getDragsList() {
        return dragsData;
    }


}
