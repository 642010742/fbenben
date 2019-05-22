package com.dwz.mvvmdemo.commom.di.module;



import dagger.Module;
import dagger.Provides;
import library.Retrofit_Http.RxRetrofitClient;

/**
 * @author dongweizhou
 * @createTime 2019/5/8
 * @describe
 * @DWZ
 */
@Module
public class DApiModule {

    @Provides
    protected RxRetrofitClient provideITService() {
        return RxRetrofitClient.getClient();
    }

}
