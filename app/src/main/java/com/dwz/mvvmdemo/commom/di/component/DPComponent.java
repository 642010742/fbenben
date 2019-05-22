package com.dwz.mvvmdemo.commom.di.component;

import com.dwz.mvvmdemo.ui.activity.MainActivity;
import com.dwz.mvvmdemo.vm.MainVModel;
import com.dwz.mvvmdemo.vm.TestVModel;

import dagger.Component;

/**
 * @author dongweizhou
 * @createTime 2019/5/8
 * @describe
 * @DWZ
 */
@Component(dependencies = AppComponent.class)
public interface DPComponent {
    MainActivity inject(MainActivity mainActivity);
}
