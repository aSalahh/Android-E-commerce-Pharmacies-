package com.q_silver.talabatak.view.fragment.homeCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.q_silver.talabatak.R;
import com.q_silver.talabatak.databinding.FragmentProfileBinding;
import com.q_silver.talabatak.view.fragment.BaseFragment;

import static com.q_silver.talabatak.helper.HelperMethod.replaceFragment;


public class ProfileFragment extends BaseFragment {
    FragmentProfileBinding fragmentProfileBinding;

    public ProfileFragment() {
        // Required empty public constructor
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


        fragmentProfileBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, null, false);

        return fragmentProfileBinding.getRoot();
    }

    @Override
    public void onBack() {
        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_cycle_activity_fl_frame, new HomeFragment());
    }
}