package com.rx.dagger.component;

import com.rx.dagger.module.BaseModule;
import com.rx.dagger.module.ExampleModule;
import com.rx.dagger.module.NetworkModule;

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
