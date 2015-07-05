package com.example.development.baseproject.component;

import com.example.development.baseproject.module.BaseModule;
import com.example.development.baseproject.module.NetworkModule;

import dagger.Component;

/**
 * Created by Shekar on 7/3/15.
 */

@Component(modules = {
        BaseModule.class,
        NetworkModule.class,
})
public interface BaseComponent {
}