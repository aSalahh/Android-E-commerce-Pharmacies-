package com.q_silver.talabatak.view.fragment.userCycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.q_silver.talabatak.R;
import com.q_silver.talabatak.databinding.FragmentLoginBinding;
import com.q_silver.talabatak.helper.HelperMethod;
import com.q_silver.talabatak.view.activity.HomeCycleActivity;
import com.q_silver.talabatak.view.fragment.BaseFragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;

import static com.q_silver.talabatak.helper.HelperMethod.isNetworkAvailable;
import static com.q_silver.talabatak.helper.HelperMethod.replaceFragment;


public class LoginFragment extends BaseFragment implements View.OnClickListener {
    FragmentLoginBinding fragmentLoginBinding;

    public LoginFragment() {
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
        fragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, null, false);
        initialViews();


        return fragmentLoginBinding.getRoot();
    }

    private void initialViews() {
        fragmentLoginBinding.loginBtnLogin.setOnClickListener(this);
        fragmentLoginBinding.loginGoogle.setOnClickListener(this);
        fragmentLoginBinding.loginFacebook.setOnClickListener(this);
        fragmentLoginBinding.loginTwitter.setOnClickListener(this);
        fragmentLoginBinding.tvSignUp.setOnClickListener(this);
    }

    @Override
    public void onBack() {
        getActivity().finish();

    }

    @Override
    public void onClick(View v) {

        if (v == fragmentLoginBinding.loginBtnLogin ||
                v == fragmentLoginBinding.loginFacebook ||
                v == fragmentLoginBinding.loginEtEmail ||
                v == fragmentLoginBinding.loginTwitter) {
            checkValidation();
        }
        if (v == fragmentLoginBinding.tvSignUp) {
            replaceFragment(getActivity().getSupportFragmentManager(), R.id.user_cycle_activity_fl_frame, new SignUpFragment());

        }
    }

    private void checkValidation() {
        String getEmail = fragmentLoginBinding.loginEtEmail.getText().toString();
        String getPassword = fragmentLoginBinding.loginEtPassword.getText().toString();

        Pattern p = Pattern.compile(HelperMethod.EMAIL_PATTERN);
        Matcher m = p.matcher(getEmail);

        if (getEmail.equals("") || getEmail.length() == 0 ||
                getPassword.equals("") || getPassword.length() == 0) {
            Toasty.info(getContext(), getString(R.string.error_all_fildes_are_required), Toast.LENGTH_SHORT, true).show();

        } else if (!m.matches()) {
            Toasty.info(getContext(), getString(R.string.error_your_email_is_invalid), Toast.LENGTH_SHORT, true).show();

        } else if (!isNetworkAvailable(getActivity())) {
            Toasty.info(getContext(), getString(R.string.error_check_internet), Toast.LENGTH_SHORT, true).show();

        } else {

            doLogin(getEmail, getPassword);
            Toasty.info(getContext(), getString(R.string.wait_to_checj_info), Toast.LENGTH_SHORT, true).show();


        }

    }

    private void doLogin(String email, String password) {
        //add to dataBase status ...


        Intent intent = new Intent(getActivity(), HomeCycleActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}