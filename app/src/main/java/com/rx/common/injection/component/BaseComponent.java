package com.rx.common.injection.component;

import com.rx.common.injection.module.BaseModule;
import com.rx.common.injection.module.ExampleModule;
import com.rx.common.injection.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Shekar on 7/3/15.
 */

@Singleton
@Component(modules = {
        BaseModule.class,
        NetworkModule.class,
})
public interface BaseComponent {
    /**
     * This is the way how we can add subcomponent in main component.
     * ExampleComponent is added to BaseComponent in RetrofitActivity
     */
    ExampleComponent plus(ExampleModule exampleModule);

    int getGreeting();
}
