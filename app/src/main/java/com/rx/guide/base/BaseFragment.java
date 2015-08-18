package com.rx.guide.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rx.MainActivity;

import butterknife.ButterKnife;


/**
 * Created by Shekar on 6/14/15.
 */

public abstract class BaseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    public void setToolbarTitle(String toolbarTitle) {
        ((MainActivity)getActivity()).getToolbar().setTitle(toolbarTitle);
    }
    protected abstract  @LayoutRes int getLayout();
}
