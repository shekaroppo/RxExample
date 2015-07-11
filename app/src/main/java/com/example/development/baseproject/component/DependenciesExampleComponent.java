package com.example.development.baseproject.component;

import com.example.development.baseproject.activity.DependenciesExampleActivity;
import com.example.development.baseproject.module.DependenciesExampleModule;
import com.example.development.baseproject.scope.ActivityScope;

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
     * inject is used here to make ExampleActivity aware
     * of Injectable elements available in graph, This is called in ExampleActivity in onCreate
     */
    public void inject(DependenciesExampleActivity exampleActivity);
}
