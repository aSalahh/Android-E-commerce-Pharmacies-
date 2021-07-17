package com.q_silver.talabatak.view.fragment.homeCycle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.q_silver.talabatak.R;
import com.q_silver.talabatak.adapters.DragsAdapter;
import com.q_silver.talabatak.adapters.HomeSliderAdapter;
import com.q_silver.talabatak.adapters.PharmacyAdapter;
import com.q_silver.talabatak.data.local.ad.model.DragsData;
import com.q_silver.talabatak.data.local.ad.model.PharmaciesData;
import com.q_silver.talabatak.data.model.HomeSliderItem;
import com.q_silver.talabatak.databinding.FragmentHomeBinding;
import com.q_silver.talabatak.view.activity.DragsDetailsActivity;
import com.q_silver.talabatak.view.activity.PharmaciesDetailsActivity;
import com.q_silver.talabatak.view.activity.ShowAllDrags;
import com.q_silver.talabatak.view.activity.ShowAllPharmacies;
import com.q_silver.talabatak.view.fragment.BaseFragment;
import com.q_silver.talabatak.viewmodel.HomeFragmentViewModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class HomeFragment extends BaseFragment {
    FragmentHomeBinding fragmentHomeBinding;
    HomeSliderAdapter homeSliderAdapter = new HomeSliderAdapter(getActivity());
    PharmacyAdapter pharmacyAdapter;
    DragsAdapter dragsAdapter;
    HomeFragmentViewModel homeViewModel;
    private static final String TAG = "dragggs";

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        intiFragment();
        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, null, false);
        //slider
        fragmentHomeBinding.imageSlider.setSliderAdapter(homeSliderAdapter);
        fragmentHomeBinding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        fragmentHomeBinding.imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        fragmentHomeBinding.imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        fragmentHomeBinding.imageSlider.setIndicatorSelectedColor(Color.WHITE);
        fragmentHomeBinding.imageSlider.setIndicatorUnselectedColor(Color.GRAY);
        fragmentHomeBinding.imageSlider.setScrollTimeInSec(4); //set scroll delay in seconds :
        fragmentHomeBinding.imageSlider.startAutoCycle();
        fragmentHomeBinding.imageSlider.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                Log.i("GGG", "onIndicatorClicked: " + fragmentHomeBinding.imageSlider.getCurrentPagePosition());
            }
        });
        fragmentHomeBinding.seeAllPharmacies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShowAllPharmacies.class);
                startActivity(intent);
            }
        });

        fragmentHomeBinding.seeAllDrags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShowAllDrags.class);
                startActivity(intent);
            }
        });

        renewItems(fragmentHomeBinding.getRoot());
        setPharmacyRecycler();
       setDragsRecycler();
        return fragmentHomeBinding.getRoot();
    }

    private void setPharmacyRecycler() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        fragmentHomeBinding.pharmacyRecycler.setLayoutManager(layoutManager);
        homeViewModel = ViewModelProviders.of(this).get(HomeFragmentViewModel.class);
        homeViewModel.getPharmaciesList().observe(Objects.requireNonNull(getActivity()), new Observer<List<PharmaciesData>>() {
            @Override
            public void onChanged(List<PharmaciesData> pharmaciesData) {
                pharmacyAdapter = new PharmacyAdapter(getActivity(), pharmaciesData);
                fragmentHomeBinding.pharmacyRecycler.setAdapter(pharmacyAdapter);
                pharmacyAdapter.setOnItemClickListener(new PharmacyAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(PharmaciesData pharmaciesData) {
                        Intent intent = new Intent(getActivity(), PharmaciesDetailsActivity.class);
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


    }

    private void setDragsRecycler() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        fragmentHomeBinding.dragsRecycler.setLayoutManager(layoutManager);
        homeViewModel = ViewModelProviders.of(this).get(HomeFragmentViewModel.class);
        homeViewModel.getDragsList().observe(Objects.requireNonNull(getActivity()), new Observer<List<DragsData>>() {
            @Override
            public void onChanged(List<DragsData> dragsData) {
                dragsAdapter = new DragsAdapter(getActivity(), dragsData);
                fragmentHomeBinding.dragsRecycler.setAdapter(dragsAdapter);
                dragsAdapter.setOnItemClickListener(new DragsAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(DragsData dragsData) {
                        Intent intent = new Intent(getActivity(), DragsDetailsActivity.class);
                        intent.putExtra("drags_id", dragsData.getId());
                        intent.putExtra("drags_name", dragsData.getName());
                        intent.putExtra("drags_description", dragsData.getDescription());
                        intent.putExtra("drags_image", dragsData.getImageUrl());
                        intent.putExtra("drags_price", dragsData.getPrice());
                        intent.putExtra("drags_type", dragsData.getType());
                        Log.d(TAG, "onCreate: salahh"+dragsData.getDescription());

                        startActivity(intent);
                    }
                });
            }
        });

    }

    public void renewItems(View view) {
        List<HomeSliderItem> sliderItemList = new ArrayList<>();
        int counter = 0;
        //dummy data
        for (int i = 0; i < 5; i++) {
            HomeSliderItem sliderItem = new HomeSliderItem();
            if (counter == 0) {
                sliderItem.setImageUrl("https://mostaql.hsoubcdn.com/uploads/118695-cYTIp-1518567571-IMG-20180204-WA0077.jpg");
                counter++;
            } else if (counter == 1) {
                sliderItem.setImageUrl("https://www.gardeniapharmacy.com/wp-content/uploads/2021/02/specialoffersbanner-scaled.jpg");
                counter++;
            } else if (counter == 2) {
                sliderItem.setImageUrl("https://www.nahdionline.com/media/wysiwyg/23May/OnlineExDeskAr.jpg");
                counter++;
            } else if (counter == 3) {
                sliderItem.setImageUrl("https://eyp4.iypcdn.com/static//modules/uploads/mobile/english/2019-11-07/el-ezaby_220212_msca_mr_e.jpg");
                counter++;
            } else {
                sliderItem.setImageUrl("https://cdnprod.mafretailproxy.com/cdn-cgi/image/format=auto,onerror=redirect/sys-master-prod/h6f/h03/9645746061342/24112019_ksa_carrefour_friday_banner_desktop_ar.jpg");
                counter = 0;
            }
            sliderItemList.add(sliderItem);
        }
        homeSliderAdapter.renewItems(sliderItemList);
    }

    @Override
    public void onBack() {
        baseActivity.superBackPressed();
    }
}