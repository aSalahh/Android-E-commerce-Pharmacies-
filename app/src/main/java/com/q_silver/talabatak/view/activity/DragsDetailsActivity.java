package com.q_silver.talabatak.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.q_silver.talabatak.R;
import com.q_silver.talabatak.adapters.HomeSliderAdapter;
import com.q_silver.talabatak.data.local.ad.model.Order;
import com.q_silver.talabatak.data.model.HomeSliderItem;
import com.q_silver.talabatak.databinding.ActivityDragsDetailsBinding;
import com.q_silver.talabatak.viewmodel.DragDetailsViewModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class DragsDetailsActivity extends AppCompatActivity {
    ActivityDragsDetailsBinding activityDragsDetailsBinding;
    HomeSliderAdapter homeSliderAdapter = new HomeSliderAdapter(this);
    DragDetailsViewModel dragDetailsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDragsDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_drags_details);
        dragDetailsViewModel = ViewModelProviders.of(this).get(DragDetailsViewModel.class);

        //slider
        activityDragsDetailsBinding.dragsDetailsImageSlider.setSliderAdapter(homeSliderAdapter);
        activityDragsDetailsBinding.dragsDetailsImageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        activityDragsDetailsBinding.dragsDetailsImageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        activityDragsDetailsBinding.dragsDetailsImageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        activityDragsDetailsBinding.dragsDetailsImageSlider.setIndicatorSelectedColor(Color.WHITE);
        activityDragsDetailsBinding.dragsDetailsImageSlider.setIndicatorUnselectedColor(Color.GRAY);
        activityDragsDetailsBinding.dragsDetailsImageSlider.setScrollTimeInSec(4); //set scroll delay in seconds :
        activityDragsDetailsBinding.dragsDetailsImageSlider.startAutoCycle();
        renewItems(activityDragsDetailsBinding.dragsSliderViewPager);


        //Add to cart
        activityDragsDetailsBinding.dragsAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dragDetailsViewModel.insert(new Order(
                        getIntent().getIntExtra("drags_id", -1),
                        getIntent().getStringExtra("drags_name"),
                        getIntent().getIntExtra("drags_image", R.drawable.dr_1),
                        getIntent().getDoubleExtra("drags_price",20.0),
                        getIntent().getStringExtra("drags_type"),
                        getIntent().getStringExtra("drags_description"),
                        1
                ));

                Toasty.info(DragsDetailsActivity.this, "Success!", Toast.LENGTH_SHORT, true).show();

            }
        });

        activityDragsDetailsBinding.dragsPharmacyName.setText(getIntent().getStringExtra("drags_name"));
        activityDragsDetailsBinding.dragsTextDescription.setText(getIntent().getStringExtra("drags_description"));
        activityDragsDetailsBinding.dragsDetailsActivityType.setText(getIntent().getStringExtra("drags_type"));
        activityDragsDetailsBinding.dragsPrice.setText(getIntent().getStringExtra("drags_price"));
        activityDragsDetailsBinding.dragsImagePharmacy.setImageDrawable(ContextCompat.getDrawable(this, getIntent().getIntExtra("drags_image", R.drawable.dr_1)));
    }

    public void renewItems(View view) {
        List<HomeSliderItem> sliderItemList = new ArrayList<>();
        int counter = 0;
        //dummy data
        for (int i = 0; i < 5; i++) {
            HomeSliderItem sliderItem = new HomeSliderItem();
            if (counter == 0) {
                sliderItem.setImageUrl("https://cdn2.howtostartanllc.com/images/business-ideas/business-idea-images/Pharmacy.jpg");
                counter++;
            } else if (counter == 1) {
                sliderItem.setImageUrl("https://www.lsretail.com/hubfs/BLOG_innovative-pharmacies-that-are-setting-new-standards-in-customer-service.jpg");
                counter++;
            } else if (counter == 2) {
                sliderItem.setImageUrl("https://img.yicaiglobal.com/cdn/news/e-pharmacies-health-centers-stay-strong-in-china-drug-sales-downturn/top.jpg");
                counter++;
            } else if (counter == 3) {
                sliderItem.setImageUrl("https://images.theconversation.com/files/379676/original/file-20210120-19-ot4pnl.jpg?ixlib=rb-1.1.0&q=45&auto=format&w=1000&fit=clip");
                counter++;
            } else {
                sliderItem.setImageUrl("https://static.reuters.com/resources/r/?m=02&d=20190812&t=2&i=1418067412&r=LYNXNPEF7B0CP&w=800");
                counter = 0;
            }
            sliderItemList.add(sliderItem);
        }
        homeSliderAdapter.renewItems(sliderItemList);
    }
}

