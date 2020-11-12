package com.dwz.mvvmdemo.ui.fragment;

import com.dwz.mvvmdemo.BuildConfig;
import com.dwz.mvvmdemo.R;
import com.dwz.mvvmdemo.vm.TestFragmentVmodel;

import library.baseView.BaseFragment;
import library.utils.upapk.VersionInfoHelper;

/**
 * @author dongweizhou
 * @createTime 2019/5/8
 * @describe
 * @DWZ
 */
public class TestFragment extends BaseFragment<TestFragmentVmodel> {


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_test;
    }

    @Override
    protected Class getVModelClass() {
        return TestFragmentVmodel.class;
    }

    @Override
    public void initView() {
        VersionInfoHelper instance = VersionInfoHelper.getInstance(getActivity());
        vm.bind.showVersionInfo.setText(instance.getVersionCode()+"        "+instance.getVersionName());
        vm.bind.aboutTitle.setText(BuildConfig.ABOUTTITLE);
        vm.bind.buildType.setText(BuildConfig.API_ENVIRONMENT);
    }

}
