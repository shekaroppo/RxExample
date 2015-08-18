package com.rx.common.utils;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.rx.common.injection.scope.ActivityScope;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by Shekar on 7/28/15.
 */
@ActivityScope
public class Navigator {
    @NonNull
    protected final FragmentManager mFragmentManager;

    @IdRes
    protected int mDefaultContainer;

    @Inject
    public Navigator(@NonNull AppCompatActivity activity) {
        mFragmentManager = activity.getSupportFragmentManager();
    }

    public void setFragmentContainer(int containerViewId) {
        this.mDefaultContainer = containerViewId;
    }

    public void navigateToFragment(Fragment fragment, boolean addToBackStack, String tag) {
        printStack();
        navigateToFragment(fragment, mDefaultContainer, addToBackStack, tag);
    }

    /*
    * Navigate to the specified Fragment.
    *
    * @param fragment to navigate to
    * @param addToBackStack if true adds the fragment to back stack
    * @param tag represent the pointer to fragment in backstack
    */
    public void navigateToFragment(Fragment fragment, int containerViewId, boolean addToBackStack, String tag) {
        if (fragment != null && mFragmentManager != null) {
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            ft.replace(containerViewId, fragment, tag);
            if (addToBackStack) {
                ft.addToBackStack(tag);
            }
            ft.commitAllowingStateLoss();
        }
    }

    public void printStack() {
        int count = mFragmentManager.getBackStackEntryCount();
        for (int i = 0; i < count; i++) {
            FragmentManager.BackStackEntry entry = (FragmentManager.BackStackEntry) mFragmentManager.getBackStackEntryAt(i);
            Timber.d(i + ": FRAGMENT IN BACKSTACK: " + entry.getName());
        }
        if (mFragmentManager.getFragments() != null)
            for (Fragment fragm : mFragmentManager.getFragments()) {
                Timber.d(": FRAGMENT FROM LIST: " + fragm);
            }
    }

    public void clearHistory() {
        while (mFragmentManager.popBackStackImmediate()) ;
    }
}

