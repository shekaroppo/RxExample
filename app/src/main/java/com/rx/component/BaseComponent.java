package com.rx.component;

import com.rx.module.BaseModule;
import com.rx.module.ExampleModule;
import com.rx.module.NetworkModule;

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
         * ExampleComponent is added to BaseComponent in ExampleActivity
         */
        ExampleComponent plus(ExampleModule exampleModule);

    int getGreeting();
}
