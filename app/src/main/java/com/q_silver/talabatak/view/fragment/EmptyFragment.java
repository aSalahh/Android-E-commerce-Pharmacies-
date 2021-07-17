package com.q_silver.talabatak.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.q_silver.talabatak.R;
import com.q_silver.talabatak.view.fragment.BaseFragment;


public class EmptyFragment extends BaseFragment {


    public EmptyFragment() {
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
        return inflater.inflate(R.layout.fragment_empty, container, false);
    }

    @Override
    public void onBack() {
        baseActivity.superBackPressed();
    }
}