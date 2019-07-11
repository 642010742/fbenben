package com.dwz.mvvmdemo.ui.fragment;

import com.dwz.mvvmdemo.R;
import com.dwz.mvvmdemo.vm.MainVModel;

import library.baseView.BaseFragment;

/**
 * @author dongweizhou
 * @createTime 2019/5/8
 * @describe
 * @DWZ
 */
public class TestFragment extends BaseFragment<MainVModel> {


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_test;
    }

    @Override
    protected Class getVModelClass() {
        return MainVModel.class;
    }

    @Override
    public void initView() {

    }
}
