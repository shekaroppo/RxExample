package com.rx.common.app;

import android.app.Application;
import android.content.Context;

import com.rx.common.injection.component.BaseComponent;
import com.rx.common.dagger.component.DaggerBaseComponent;
import com.rx.common.injection.module.BaseModule;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class BaseApplication extends Application {

    private BaseComponent component;
    private RefWatcher refWatcher;
    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerBaseComponent.builder().baseModule(new BaseModule(this)).build();
        refWatcher = LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        BaseApplication application = (BaseApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    public static BaseComponent getComponent(Context context) {
        return ((BaseApplication) context.getApplicationContext()).component;
    }
}

