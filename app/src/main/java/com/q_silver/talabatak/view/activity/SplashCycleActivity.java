package com.q_silver.talabatak.view.activity;

import android.os.Bundle;

import com.q_silver.talabatak.R;
import com.q_silver.talabatak.view.fragment.splashCycle.SplashFragment;

import static com.q_silver.talabatak.helper.HelperMethod.replaceFragment;

public class SplashCycleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        replaceFragment(getSupportFragmentManager(), R.id.splash_cycle_activity_fl_frame, new SplashFragment());

    }
}