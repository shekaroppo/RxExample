package com.rx.common.injection.component;

import com.rx.common.injection.module.BaseModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Shekar on 7/3/15.
 */

@Singleton
@Component(modules = {
        BaseModule.class
})
public interface BaseComponent {

}
