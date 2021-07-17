package com.q_silver.talabatak.view.activity;

import android.os.Bundle;

import com.q_silver.talabatak.R;
import com.q_silver.talabatak.view.fragment.userCycle.LoginFragment;

import static com.q_silver.talabatak.helper.HelperMethod.replaceFragment;

public class UserCycleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cycle);
        replaceFragment(getSupportFragmentManager(), R.id.user_cycle_activity_fl_frame, new LoginFragment());

    }
}