package com.rx.common.injection.component;

import com.rx.MainActivity;
import com.rx.common.injection.module.DependenciesExampleModule;
import com.rx.common.injection.scope.ActivityScope;

import dagger.Component;

/**
 * Created by Shekar on 7/10/15.
 */

@ActivityScope
@Component(
        dependencies = BaseComponent.class,
        modules = {
                DependenciesExampleModule.class
        })
public interface DependenciesExampleComponent {
    /**
     * inject is used here to make RetrofitActivity aware
     * of Injectable elements available in graph, This is called in RetrofitActivity in onCreate
     */
    public void inject(MainActivity mainActivity);
}
