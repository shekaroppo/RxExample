package com.rx.dagger.component;

import com.rx.TestActivity;
import com.rx.dagger.module.DependenciesExampleModule;
import com.rx.dagger.scope.ActivityScope;

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
    public void inject(TestActivity exampleActivity);
}
