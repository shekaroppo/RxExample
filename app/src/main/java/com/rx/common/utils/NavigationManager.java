package com.rx.common.utils;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.rx.common.injection.scope.ActivityScope;
import com.rx.guide.operators.transformingObservables.FlatmapVsConcatmapFragment;

import javax.inject.Inject;

/**
 * Created by Shekar on 7/28/15.
 */
@ActivityScope
public class NavigationManager {

    private Navigator mNavigator;

    @Inject
    public NavigationManager(@NonNull Navigator navigator){
        mNavigator = navigator;
    }

    public void goToFlatmapVsConcatmapFragment(boolean addToBackStack) {
        Bundle args = new Bundle();
        Fragment genreListFragment = new FlatmapVsConcatmapFragment();
        genreListFragment.setArguments(args);
        mNavigator.navigateToFragment(genreListFragment, addToBackStack, FlatmapVsConcatmapFragment.class.getSimpleName());
    }

    public void setRootContainer(int rootContainer) {
        mNavigator.setFragmentContainer(rootContainer);
    }
}

