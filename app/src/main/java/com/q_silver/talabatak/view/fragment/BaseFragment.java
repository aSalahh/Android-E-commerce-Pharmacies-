package com.q_silver.talabatak.view.fragment;

import androidx.fragment.app.Fragment;

import com.q_silver.talabatak.view.activity.BaseActivity;


public class BaseFragment extends Fragment {

    public BaseActivity baseActivity;

    public void intiFragment() {
        baseActivity = (BaseActivity) getActivity();
        baseActivity.baseFragment = this;
    }

    public void onBack() {
        baseActivity.superBackPressed();

    }
}