package com.q_silver.talabatak.view.fragment.splashCycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import com.q_silver.talabatak.R;
import com.q_silver.talabatak.adapters.SliderAdapter;
import com.q_silver.talabatak.data.model.SliderItem;
import com.q_silver.talabatak.databinding.FragmentSliderBinding;
import com.q_silver.talabatak.view.activity.UserCycleActivity;
import com.q_silver.talabatak.view.fragment.BaseFragment;


public class SliderFragment extends BaseFragment {
    FragmentSliderBinding fragmentSliderBinding;
    private int position = 0;

    public SliderFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        intiFragment();
        fragmentSliderBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_slider, null, false);
        setDataToSlider();
        setupSliderIndicators(3);
        fragmentSliderBinding.fragmentSliderTvSlideNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = fragmentSliderBinding.fragmentSliderVpSlider.getCurrentItem();
                if (position < 3) {
                    position++;
                    fragmentSliderBinding.fragmentSliderVpSlider.setCurrentItem(position);
                }
                if (position == 3) {
                    startActivity(new Intent(getActivity(), UserCycleActivity.class));
                }
            }
        });
        fragmentSliderBinding.fragmentSliderTvSlideSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UserCycleActivity.class));
            }
        });
        fragmentSliderBinding.fragmentSliderVpSlider.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                setCurrentSliderIndicators(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return fragmentSliderBinding.getRoot();
    }
    @Override
    public void onBack() {
        baseActivity.finish();
    }
    private void setupSliderIndicators(int count) {
        ImageView[] indicators = new ImageView[count];
        //set width and height of view
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //set margins of view
        layoutParams.setMargins(8, 0, 8, 0);
        //set view to xml
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getContext(),
                    R.drawable.tab_indicator_default
            ));
            indicators[i].setLayoutParams(layoutParams);
            fragmentSliderBinding.layoutSliderIndicators.addView(indicators[i]);
            setCurrentSliderIndicators(0);
        }
        fragmentSliderBinding.layoutSliderIndicators.setVisibility(View.VISIBLE);
    }

    private void setCurrentSliderIndicators(int position) {
        int childCount = fragmentSliderBinding.layoutSliderIndicators.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) fragmentSliderBinding.layoutSliderIndicators.getChildAt(i);
            if (i == position) {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getContext(), R.drawable.tab_indicator_selected)
                );
            } else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getContext(), R.drawable.tab_indicator_default)
                );
            }
        }
    }

    private void setDataToSlider() {
        SliderAdapter sliderAdapter = new SliderAdapter(getActivity());
        sliderAdapter.addpage(new SliderItem(R.drawable.slider_f_logo, "Buy Anything !"));
        sliderAdapter.addpage(new SliderItem(R.drawable.slider_s_logo, "Exclusive Products !"));
        sliderAdapter.addpage(new SliderItem(R.drawable.slider_t_logo, "Amazing Discount !"));
        fragmentSliderBinding.fragmentSliderVpSlider.setAdapter(sliderAdapter);
    }
}