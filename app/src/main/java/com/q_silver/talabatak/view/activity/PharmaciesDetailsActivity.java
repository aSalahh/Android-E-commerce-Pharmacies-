package com.q_silver.talabatak.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.q_silver.talabatak.R;
import com.q_silver.talabatak.adapters.HomeSliderAdapter;
import com.q_silver.talabatak.data.model.HomeSliderItem;
import com.q_silver.talabatak.databinding.ActivityPharmaciesDetailsBinding;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class PharmaciesDetailsActivity extends AppCompatActivity {
    ActivityPharmaciesDetailsBinding activityPharmaciesDetailsBinding;
    HomeSliderAdapter homeSliderAdapter = new HomeSliderAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacies_details);
        activityPharmaciesDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_pharmacies_details);

        //slider
        activityPharmaciesDetailsBinding.detailsImageSlider.setSliderAdapter(homeSliderAdapter);
        activityPharmaciesDetailsBinding.detailsImageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        activityPharmaciesDetailsBinding.detailsImageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        activityPharmaciesDetailsBinding.detailsImageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        activityPharmaciesDetailsBinding.detailsImageSlider.setIndicatorSelectedColor(Color.WHITE);
        activityPharmaciesDetailsBinding.detailsImageSlider.setIndicatorUnselectedColor(Color.GRAY);
        activityPharmaciesDetailsBinding.detailsImageSlider.setScrollTimeInSec(4); //set scroll delay in seconds :
        activityPharmaciesDetailsBinding.detailsImageSlider.startAutoCycle();
        renewItems(activityPharmaciesDetailsBinding.sliderViewPager);


        activityPharmaciesDetailsBinding.pharmacyLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getIntent().getStringExtra("pharmacy_location")));
                startActivity(browserIntent);
            }
        });
        activityPharmaciesDetailsBinding.pharmacyName.setText(getIntent().getStringExtra("pharmacy_name"));
        activityPharmaciesDetailsBinding.textDescription.setText(getIntent().getStringExtra("pharmacy_description"));
         activityPharmaciesDetailsBinding.imagePharmacy.setImageDrawable(ContextCompat.getDrawable(this,getIntent().getIntExtra("pharmacy_image",R.drawable.ph_6)));
    }
    public void renewItems(View view) {
        List<HomeSliderItem> sliderItemList = new ArrayList<>();
        int counter = 0;
        //dummy data
        for (int i = 0; i < 5; i++) {
            HomeSliderItem sliderItem = new HomeSliderItem();
            if (counter == 0) {
                sliderItem.setImageUrl("https://www.rightmedicinepharmacy.co.uk/wp-content/themes/right-medicine/img/about2.jpg");
                counter++;
            } else if (counter == 1) {
                sliderItem.setImageUrl("https://cdna1.yellowpages.com.eg/uploads/contract-services/english/2020/37/el-ezaby-pharmacies-photo_99370_2020_wa_07_60954.jpg?25");
                counter++;
            } else if (counter == 2) {
                sliderItem.setImageUrl("http://www.skymalleg.com/storage/app/uploads/public/5d1/746/b69/5d1746b6953fa967656676.jpg");
                counter++;
            } else if (counter == 3) {
                sliderItem.setImageUrl("https://cdna1.yellowpages.com.eg/uploads/contract-services/english/2020/37/el-ezaby-pharmacies-photo_99370_2020_wa_20_59235.jpg?26");
                counter++;
            } else {
                sliderItem.setImageUrl("http://www.skymalleg.com/storage/app/uploads/public/59f/ae6/19e/59fae619efeb8188174136.jpg");
                counter = 0;
            }
            sliderItemList.add(sliderItem);
        }
        homeSliderAdapter.renewItems(sliderItemList);
    }
}

