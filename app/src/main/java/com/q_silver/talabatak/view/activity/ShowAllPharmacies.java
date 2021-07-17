package com.q_silver.talabatak.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.q_silver.talabatak.R;
import com.q_silver.talabatak.adapters.HomeSliderAdapter;
import com.q_silver.talabatak.adapters.PharmacyAdapter;
import com.q_silver.talabatak.adapters.PharmacyRowAdapter;
import com.q_silver.talabatak.data.local.ad.model.DragsData;
import com.q_silver.talabatak.data.local.ad.model.PharmaciesData;
import com.q_silver.talabatak.databinding.ActivityShowAllPharmaciesBinding;
import com.q_silver.talabatak.viewmodel.HomeFragmentViewModel;
import com.q_silver.talabatak.viewmodel.ShowAllPharmaciesViewModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShowAllPharmacies extends AppCompatActivity {
   PharmacyRowAdapter adapter;
   ShowAllPharmaciesViewModel showAllPharmaciesViewModel;
   ActivityShowAllPharmaciesBinding activityShowAllPharmaciesBinding;
    List<PharmaciesData> myPharmacyData=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityShowAllPharmaciesBinding = DataBindingUtil.setContentView(this, R.layout.activity_show_all_pharmacies);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        activityShowAllPharmaciesBinding.pharmacies.setLayoutManager(layoutManager);
        showAllPharmaciesViewModel = ViewModelProviders.of(this).get(ShowAllPharmaciesViewModel.class);
        showAllPharmaciesViewModel.getPharmaciesList().observe(this, new Observer<List<PharmaciesData>>() {
            @Override
            public void onChanged(List<PharmaciesData> pharmaciesData) {
                myPharmacyData= pharmaciesData;
                adapter = new PharmacyRowAdapter(ShowAllPharmacies.this, pharmaciesData);
                activityShowAllPharmaciesBinding.pharmacies.setAdapter(adapter);
                adapter.setOnItemClickListener(new PharmacyRowAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(PharmaciesData pharmaciesData) {
                        Intent intent = new Intent(ShowAllPharmacies.this, PharmaciesDetailsActivity.class);
                        intent.putExtra("pharmacy_id", pharmaciesData.getId());
                        intent.putExtra("pharmacy_name", pharmaciesData.getName());
                        intent.putExtra("pharmacy_location", pharmaciesData.getLocation());
                        intent.putExtra("pharmacy_description", pharmaciesData.getDesc());
                        intent.putExtra("pharmacy_image", pharmaciesData.getImageUrl());
                        startActivity(intent);
                    }
                });
            }
        });

        activityShowAllPharmaciesBinding.showAllPharmaciesEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }

        });
    }
    private void filter(String text) {
        List<PharmaciesData> mySearchPharmacyData=new ArrayList<>();
        for (PharmaciesData item : myPharmacyData){
            if( item.getName().toLowerCase().contains(text.toLowerCase())){
                mySearchPharmacyData.add(item);
            }
        }

        adapter.filterList(mySearchPharmacyData);

    }


}