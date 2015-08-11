package com.example.development.rx.activity;

import android.os.Bundle;
import android.util.Log;

import com.example.development.rx.BaseApplication;
import com.example.development.rx.R;
import com.example.development.rx.component.BaseComponent;
import com.example.development.rx.component.DaggerDependenciesExampleComponent;
import com.example.development.rx.module.DependenciesExampleModule;

import javax.inject.Inject;

/**
 * Created by Shekar on 7/10/15.
 */
public class MainActivity extends BaseActivity {

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
