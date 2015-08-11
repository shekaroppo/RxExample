package com.example.development.rx;

import android.app.Application;
import android.content.Context;

import com.example.development.rx.component.BaseComponent;
import com.example.development.rx.component.DaggerBaseComponent;
import com.example.development.rx.module.BaseModule;

public class BaseApplication extends Application {

    private BaseComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerBaseComponent.builder().baseModule(new BaseModule(this)).build();
    }

    public static BaseComponent getComponent(Context context) {
        return ((BaseApplication) context.getApplicationContext()).component;
    }
}

