package com.rx.common.injection.component;

import com.rx.common.injection.module.ExampleModule;
import com.rx.common.injection.scope.ActivityScope;
import com.rx.guide.network.RetrofitActivity;

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
     * inject is used here to make RetrofitActivity aware
     * of Injectable elements available in graph, This is called in RetrofitActivity in onCreate
     */
    public void inject(RetrofitActivity retrofitActivity);
}