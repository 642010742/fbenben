package com.dwz.mvvmdemo.ui.fragment.tabfragment;

import android.content.Intent;
import android.view.View;

import com.dwz.mvvmdemo.R;
import com.dwz.mvvmdemo.ui.activity.FeedBackActivity;

import library.utils.pop.ISelectListener;
import library.utils.pop.PopUtils;

import com.dwz.mvvmdemo.vm.MineVModel;

import library.baseView.BaseFragment;
import library.utils.upapk.VersionInfoHelper;

public class MineFragment extends BaseFragment<MineVModel> implements View.OnClickListener {
    @Override
    protected int getLayoutID() {
        return R.layout.fragment_mine;
    }

    @Override
    protected Class<MineVModel> getVModelClass() {
        return MineVModel.class;
    }

    @Override
    public void initView() {
        vm.bind.version.setText("v" + VersionInfoHelper.getInstance(getActivity()).getVersionName());
    }

    @Override
    public void initListener() {
        super.initListener();
        vm.bind.loginLayout.setOnClickListener(this);
        vm.bind.cancleAccount.setOnClickListener(this);
        vm.bind.loginOut.setOnClickListener(this);
        vm.bind.feedback.setOnClickListener(this);
        vm.bind.userInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginLayout:
                toLogin();
                break;
            case R.id.userInfo:
                PopUtils.showSelectPhotoPop(getActivity());
                break;
            case R.id.cancleAccount:
                showLoginOutAndCancleAccountPop(0);
                break;
            case R.id.loginOut:
                showLoginOutAndCancleAccountPop(1);
                break;
            case R.id.feedback:
                Intent intentFeedBack = new Intent(getActivity(), FeedBackActivity.class);
                pStartActivity(intentFeedBack, false);
                break;
        }
    }

    /**
     * 0-注销账户
     * 1-退出登录
     * @param popType
     */
    public void showLoginOutAndCancleAccountPop(int popType) {
        PopUtils.showLoginOutPop(getActivity(), popType, new ISelectListener() {
            @Override
            public void select() {
                clearAllActivityToLogin();
            }
        });
    }
}
