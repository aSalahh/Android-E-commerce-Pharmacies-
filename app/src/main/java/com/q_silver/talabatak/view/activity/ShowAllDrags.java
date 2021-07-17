package com.q_silver.talabatak.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.q_silver.talabatak.R;
import com.q_silver.talabatak.adapters.DragsRowAdapter;
import com.q_silver.talabatak.adapters.PharmacyRowAdapter;
import com.q_silver.talabatak.data.local.ad.model.DragsData;
import com.q_silver.talabatak.data.local.ad.model.PharmaciesData;
import com.q_silver.talabatak.databinding.ActivityShowAllDragsBinding;
import com.q_silver.talabatak.databinding.ActivityShowAllPharmaciesBinding;
import com.q_silver.talabatak.viewmodel.ShowAllDragsViewModel;
import com.q_silver.talabatak.viewmodel.ShowAllPharmaciesViewModel;

import java.util.ArrayList;
import java.util.List;

public class ShowAllDrags extends AppCompatActivity {
    DragsRowAdapter adapter;
    ShowAllDragsViewModel showAllDragsViewModel;
    ActivityShowAllDragsBinding activityShowAllDragsBinding;
   List<DragsData> myDragsData=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityShowAllDragsBinding = DataBindingUtil.setContentView(this, R.layout.activity_show_all_drags);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        activityShowAllDragsBinding.drags.setLayoutManager(layoutManager);
        showAllDragsViewModel = ViewModelProviders.of(this).get(ShowAllDragsViewModel.class);
        showAllDragsViewModel.getDragsList().observe(this, new Observer<List<DragsData>>() {
            @Override
            public void onChanged(List<DragsData> dragsData) {
               myDragsData=dragsData;
                adapter = new DragsRowAdapter(ShowAllDrags.this, dragsData);
                activityShowAllDragsBinding.drags.setAdapter(adapter);

                adapter.setOnItemClickListener(new DragsRowAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(DragsData dragsData1) {
                        Intent intent = new Intent(ShowAllDrags.this, DragsDetailsActivity.class);
                        intent.putExtra("drags_id", dragsData1.getId());
                        intent.putExtra("drags_name", dragsData1.getName());
                        intent.putExtra("drags_type", dragsData1.getType());
                        intent.putExtra("drags_description", dragsData1.getDescription());
                        intent.putExtra("drags_image", dragsData1.getImageUrl());
                        intent.putExtra("drags_price", dragsData1.getPrice());
                        startActivity(intent);
                    }
                });
            }
        });

        activityShowAllDragsBinding.showAllDragsEtSearch.addTextChangedListener(new TextWatcher() {
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
        List<DragsData> mySearchDragsData=new ArrayList<>();
        for (DragsData item : myDragsData){
           if( item.getName().toLowerCase().contains(text.toLowerCase())){
                mySearchDragsData.add(item);
           }
        }

        adapter.filterList(mySearchDragsData);

    }
}