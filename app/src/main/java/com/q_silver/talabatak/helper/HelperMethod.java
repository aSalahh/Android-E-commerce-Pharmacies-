package com.q_silver.talabatak.helper;

import android.content.Context;
import android.net.ConnectivityManager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class HelperMethod {
    public static final String EMAIL_PATTERN  = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
    public static final String CURRENT_USER_DATA  = "ownUser";

    public static void replaceFragment(FragmentManager getChildFragmentManager, int id, Fragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager.beginTransaction();
        transaction.replace(id, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

}
