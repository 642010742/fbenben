package com.dwz.mvvmdemo.commom.di.component;

import android.content.Context;

import com.dwz.mvvmdemo.commom.di.module.AppModule;
import com.dwz.mvvmdemo.commom.di.module.DApiModule;

import dagger.Component;
import library.Retrofit_Http.RxRetrofitClient;

/**
 * @author yuyh.
 * @date 2016/8/3.
 */
@Component(modules = {AppModule.class,DApiModule.class})
public interface AppComponent {

    Context getContext();

    RxRetrofitClient getApi();

}