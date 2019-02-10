package com.nvqrcode.yoosanghyeon.noadvertiasmentqrcode.util;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;

public class ActivityUtil {

    private static final String TAG = ActivityUtil.class.getSimpleName();

    public static volatile ActivityUtil activityUtil;

    private ActivityUtil() {
    }

    public static ActivityUtil getInstance() {
        if (activityUtil == null) {
            activityUtil = new ActivityUtil();
        }
        return activityUtil;
    }

    public void replaceToActivityFragment(@NonNull FragmentManager manager, @NonNull Fragment fragment
            , @NonNull int fragmentId) {

        manager.beginTransaction()
                .replace(fragmentId, fragment)
                .commit();
    }


    public void replaceToActivityFragment(@NonNull FragmentManager manager, @NonNull Fragment fragment
            , @NonNull int fragmentId ,String tag) {

        manager.beginTransaction()
                .replace(fragmentId, fragment , tag)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    public void addToActivityFragment(@NonNull FragmentManager manager, @NonNull Fragment fragment, @NonNull int fragmentId, String tag) {

        manager.beginTransaction()
                .add(fragmentId, fragment, tag)
                .addToBackStack(tag)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();

    }

    public void onShowFragment(FragmentManager fragmentManager , Fragment fragment){
        fragmentManager.beginTransaction()
                .show(fragment)
                .commit();
    }


    public boolean onCheckBackStackFragment(@NonNull FragmentManager manager, String tag){

        boolean isBackstack = true;

        Log.e(TAG ,"stack count :: " + manager.getBackStackEntryCount());

        for (int i= 0; i < manager.getBackStackEntryCount() ; i++){
            FragmentManager.BackStackEntry backStackEntry = manager.getBackStackEntryAt(i);
            String fragmentTag = backStackEntry.getName();
            Log.e(TAG ,fragmentTag);
            if (TextUtils.equals(tag , fragmentTag)){
                manager.executePendingTransactions();
                Log.e(TAG , fragmentTag);
                isBackstack = false;
                manager.popBackStack(tag, 0);
                return isBackstack;
            }
        }



        return isBackstack;
    }


    public void addToActivityFragmentWithAnimation(@NonNull FragmentManager manager, @NonNull Fragment fragment, @NonNull int fragmentId, String tag) {

/*
        manager.beginTransaction()
                .setCustomAnimations(R.anim.slide_up, R.anim.slide_down)
                .add(fragmentId, fragment, tag)
                .addToBackStack(tag)
                .commit();*/
    }


    public void addFragment(@NonNull FragmentManager manager, @NonNull Fragment fragment, @NonNull int fragmentId, @NonNull String tag) {
        manager.beginTransaction()
                .add(fragmentId, fragment, tag)
                .addToBackStack(tag)
                .commit();


    }

    private void onFragmentFlaginLog(FragmentManager manager, String log) {
        Log.e(ActivityUtil.class.getName(), log + "::" + manager.getBackStackEntryCount());
    }


}