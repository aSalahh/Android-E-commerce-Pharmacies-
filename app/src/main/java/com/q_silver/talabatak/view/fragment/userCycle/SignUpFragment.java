package com.q_silver.talabatak.view.fragment.userCycle;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.q_silver.talabatak.helper.SharedPreferencesManger;


import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;
import com.q_silver.talabatak.R;
import com.q_silver.talabatak.data.model.User;
import com.q_silver.talabatak.databinding.FragmentSignupBinding;
import com.q_silver.talabatak.helper.HelperMethod;
import com.q_silver.talabatak.view.activity.HomeCycleActivity;
import com.q_silver.talabatak.view.activity.UserCycleActivity;
import com.q_silver.talabatak.view.fragment.BaseFragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;

import static android.content.Context.MODE_PRIVATE;
import static com.q_silver.talabatak.helper.HelperMethod.CURRENT_USER_DATA;
import static com.q_silver.talabatak.helper.HelperMethod.isNetworkAvailable;
import static com.q_silver.talabatak.helper.HelperMethod.replaceFragment;


public class SignUpFragment extends BaseFragment implements View.OnClickListener {
    FragmentSignupBinding fragmentSignupBinding;

    public SignUpFragment() {
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
        fragmentSignupBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, null, false);
        initialViews();

        return fragmentSignupBinding.getRoot();
    }

    private void initialViews() {
        fragmentSignupBinding.signUpEtEmail.setOnClickListener(this);
        fragmentSignupBinding.signUpEtName.setOnClickListener(this);
        fragmentSignupBinding.signUpEtAge.setOnClickListener(this);
        fragmentSignupBinding.signUpEtAdress.setOnClickListener(this);
        fragmentSignupBinding.signupEtPassword.setOnClickListener(this);
        fragmentSignupBinding.signUpBtnSignUp.setOnClickListener(this);
        fragmentSignupBinding.signupGoogle.setOnClickListener(this);
        fragmentSignupBinding.signupFacebook.setOnClickListener(this);
        fragmentSignupBinding.signupTwitter.setOnClickListener(this);
        fragmentSignupBinding.tvSignIn.setOnClickListener(this);
    }

    @Override
    public void onBack() {
        replaceFragment(getActivity().getSupportFragmentManager(), R.id.user_cycle_activity_fl_frame, new LoginFragment());
    }

    @Override
    public void onClick(View v) {
        if (v == fragmentSignupBinding.signUpBtnSignUp ||
                v == fragmentSignupBinding.signupFacebook ||
                v == fragmentSignupBinding.signUpEtEmail ||
                v == fragmentSignupBinding.signupTwitter) {
            checkValidation();
        }
        if (v == fragmentSignupBinding.tvSignIn) {
            replaceFragment(getActivity().getSupportFragmentManager(), R.id.user_cycle_activity_fl_frame, new LoginFragment());

        }

    }

    private void checkValidation() {
        String getEmail = fragmentSignupBinding.signUpEtEmail.getText().toString();
        String getPassword = fragmentSignupBinding.signupEtPassword.getText().toString();
        String getName = fragmentSignupBinding.signUpEtName.getText().toString();
        String getAddress = fragmentSignupBinding.signUpEtAdress.getText().toString();
        String getAge = fragmentSignupBinding.signUpEtAge.getText().toString();

        Pattern p = Pattern.compile(HelperMethod.EMAIL_PATTERN);
        Matcher m = p.matcher(getEmail);

        if (getEmail.equals("") || getEmail.length() == 0 ||
                getPassword.equals("") || getPassword.length() == 0 ||
                getName.equals("") || getName.length() == 0 ||
                getAddress.equals("") || getAddress.length() == 0 ||
                getAge.equals("") || getAge.length() == 0) {
            Toasty.info(getContext(), getString(R.string.error_all_fildes_are_required), Toast.LENGTH_SHORT, true).show();

        } else if (!m.matches()) {
            Toasty.info(getContext(), getString(R.string.error_your_email_is_invalid), Toast.LENGTH_SHORT, true).show();

        } else if (!isNetworkAvailable(getActivity())) {
            Toasty.info(getContext(), getString(R.string.error_check_internet), Toast.LENGTH_SHORT, true).show();

        } else {

            doSignUp(getEmail, getPassword, getName, getAddress, getAge);
            Toasty.info(getContext(), getString(R.string.wait_to_checj_info), Toast.LENGTH_SHORT, true).show();


        }

    }

    private void doSignUp(String getEmail, String getPassword, String getName, String getAddress, String getAge) {
        Intent intent = new Intent(getActivity(), HomeCycleActivity.class);
        startActivity(intent);

    }


}
