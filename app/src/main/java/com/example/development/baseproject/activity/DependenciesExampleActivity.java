package com.example.development.baseproject.activity;

import android.os.Bundle;
import android.util.Log;

import com.example.development.baseproject.BaseApplication;
import com.example.development.baseproject.R;
import com.example.development.baseproject.component.BaseComponent;
import com.example.development.baseproject.component.DaggerDependenciesExampleComponent;
import com.example.development.baseproject.module.DependenciesExampleModule;

import javax.inject.Inject;

/**
 * Created by Shekar on 7/10/15.
 */
public class DependenciesExampleActivity extends BaseActivity {

    @Inject
    String name;
    @Inject
    int attempt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_main);
        BaseComponent component = BaseApplication.getComponent(this);
        DaggerDependenciesExampleComponent.builder()
                .dependenciesExampleModule(new DependenciesExampleModule())
                .baseComponent(component)
                .build().inject(this);
        Log.d("Name==", name + "==");
        Log.d("attempt==", attempt + "==");

    }
}
