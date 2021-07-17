package com.q_silver.talabatak.view.activity;

import android.os.Bundle;

import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;
import com.q_silver.talabatak.R;
import com.q_silver.talabatak.view.fragment.homeCycle.CartFragment;
import com.q_silver.talabatak.view.fragment.homeCycle.HomeFragment;
import com.q_silver.talabatak.view.fragment.homeCycle.ProfileFragment;

import static com.q_silver.talabatak.helper.HelperMethod.replaceFragment;

public class HomeCycleActivity extends BaseActivity {
    SpaceNavigationView spaceNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_cycle);

        replaceFragment(getSupportFragmentManager(), R.id.home_cycle_activity_fl_frame, new HomeFragment());
        spaceNavigationView = findViewById(R.id.activity_home_cycle_space_nav);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.addSpaceItem(new SpaceItem("Profile", R.drawable.person_outline));
        spaceNavigationView.addSpaceItem(new SpaceItem("Car", R.drawable.cart));


        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                replaceFragment(getSupportFragmentManager(), R.id.home_cycle_activity_fl_frame, new HomeFragment());

            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                switch (itemIndex) {
                    case 0:
                        replaceFragment(getSupportFragmentManager(), R.id.home_cycle_activity_fl_frame, new ProfileFragment());
                        break;
                    case 1:
                        replaceFragment(getSupportFragmentManager(), R.id.home_cycle_activity_fl_frame, new CartFragment());
                        break;
                }

            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {


            }
        });

    }


    @Override
    public void onBackPressed() {
        this.finish();
    }

}