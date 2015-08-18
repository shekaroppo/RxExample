package com.rx.common.injection.module;

import android.support.v7.app.AppCompatActivity;

import com.rx.common.injection.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Shekar on 7/10/15.
 */
@Module
public class DependenciesExampleModule {
    private final AppCompatActivity activity;

    public DependenciesExampleModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    AppCompatActivity activity() {
        return activity;
    }
}
