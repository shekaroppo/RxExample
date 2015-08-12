package com.rx.component;

import com.rx.activity.ExampleActivity;
import com.rx.module.ExampleModule;
import com.rx.scope.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Shekar on 7/1/15.
 */


/**
 * ExampleComponent is added as subcomponent to BaseComponent using plus method declared in BaseComponent,
 * subcomponent can't be annotated @Singleton
 */
@ActivityScope
@Subcomponent(
        modules = {
        ExampleModule.class
})
public interface ExampleComponent {
    /**
     * inject is used here to make ExampleActivity aware
     * of Injectable elements available in graph, This is called in ExampleActivity in onCreate
     */
    public void inject(ExampleActivity exampleActivity);
}
