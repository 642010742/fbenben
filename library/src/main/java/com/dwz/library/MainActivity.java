package com.dwz.library;

import android.content.Intent;
import com.dwz.library.baseView.BaseActivity;
import com.dwz.library.commonModel.TitleOptions;
import com.dwz.library.vm.MainVModel;

public class MainActivity extends BaseActivity<MainVModel> {

    @Override
    public Class<MainVModel> getVMClass() {
        return MainVModel.class;
    }

    @Override
    public int LayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void typeTitle(TitleOptions titleOptions, Intent intent) {

    }
}