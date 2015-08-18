package com.rx;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.rx.common.app.BaseApplication;
import com.rx.common.injection.component.BaseComponent;
import com.rx.common.injection.component.DaggerDependenciesExampleComponent;
import com.rx.common.injection.module.DependenciesExampleModule;
import com.rx.guide.base.BaseActivity;
import com.rx.common.utils.NavigationManager;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements DrawerLayout.DrawerListener
        , NavigationView.OnNavigationItemSelectedListener {

    @Inject
    NavigationManager mNavigationManager;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.navigation_view)
    NavigationView mNavigationView;

    private ActionBarDrawerToggle mDrawerToggle;
    private int mCurrentMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        BaseComponent component = BaseApplication.getComponent(this);
        DaggerDependenciesExampleComponent.builder()
                .dependenciesExampleModule(new DependenciesExampleModule())
                .baseComponent(component)
                .build().inject(this);
        setupNavDrawer();
        if(savedInstanceState==null){
            mNavigationManager.setRootContainer(R.id.container);
            mNavigationManager.goToFlatmapVsConcatmapFragment(true);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    private void setupNavDrawer() {
        mDrawerLayout.setDrawerListener(this);
        mDrawerToggle = new ActionBarDrawerToggle(this
                , mDrawerLayout
                , null
                , R.string.navigation_drawer_open
                , R.string.navigation_drawer_close);
        mDrawerToggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        mDrawerToggle.onDrawerSlide(drawerView, slideOffset);
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        mDrawerToggle.onDrawerOpened(drawerView);
    }

    public void openDrawer(){
        mDrawerLayout.openDrawer(Gravity.LEFT);
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        mDrawerToggle.onDrawerClosed(drawerView);
    }

    @Override
    public void onDrawerStateChanged(int newState) {
        mDrawerToggle.onDrawerStateChanged(newState);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == mCurrentMenuItem) {
            mDrawerLayout.closeDrawers();
            return false;
        }
        switch (id) {
            case R.id.operators:
                break;
            case R.id.lifecycle:
                break;
        }
        mCurrentMenuItem = id;
        menuItem.setChecked(true);
        return false;
    }
}
