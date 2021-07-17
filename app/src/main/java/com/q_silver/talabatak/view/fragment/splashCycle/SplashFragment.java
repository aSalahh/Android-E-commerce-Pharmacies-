package com.q_silver.talabatak.view.fragment.splashCycle;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.q_silver.talabatak.R;
import com.q_silver.talabatak.view.fragment.BaseFragment;

import java.util.Objects;

import static com.q_silver.talabatak.helper.HelperMethod.replaceFragment;


public class SplashFragment extends BaseFragment {

    public SplashFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        intiFragment();
        // Inflate the layout for this fragment
        com.q_silver.talabatak.databinding.FragmentSplashBinding fragmentSplashBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, null, false);
        new Handler(Looper.getMainLooper()).postDelayed(() -> replaceFragment(Objects.requireNonNull(getActivity()).getSupportFragmentManager(),
                R.id.splash_cycle_activity_fl_frame, new SliderFragment()), 3000);
        return fragmentSplashBinding.getRoot();
    }

    @Override
    public void onBack() {
        baseActivity.finish();
    }
}