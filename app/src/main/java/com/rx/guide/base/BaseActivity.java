package com.rx.guide.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rx.common.app.BaseApplication;
import com.squareup.leakcanary.RefWatcher;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RefWatcher refWatcher = BaseApplication.getRefWatcher(this);
        refWatcher.watch(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
