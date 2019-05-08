package com.dwz.mvvmdemo.ui.fragment;

import com.dwz.mvvmdemo.R;
import com.dwz.mvvmdemo.vm.TestVModel;

import library.baseView.BaseFragment;

/**
 * @author dongweizhou
 * @createTime 2019/5/8
 * @describe
 * @DWZ
 */
public class TestFragment extends BaseFragment<TestVModel> {


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_test;
    }

    @Override
    protected Class getVModelClass() {
        return TestVModel.class;
    }

    @Override
    public void initView() {

    }
}
