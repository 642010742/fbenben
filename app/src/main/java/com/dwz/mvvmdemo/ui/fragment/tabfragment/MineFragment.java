package com.dwz.mvvmdemo.ui.fragment.tabfragment;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import com.dwz.mvvmdemo.R;
import com.dwz.mvvmdemo.ui.activity.CommWebview;
import com.dwz.mvvmdemo.vm.MineVModel;
import library.App.AppConstants;
import library.baseView.BaseFragment;
import library.utils.BackGroundUtils;
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
        vm.bind.changePwd.setOnClickListener(this);
        vm.bind.feedback.setOnClickListener(this);
        vm.bind.userInfo.setOnClickListener(this);
        vm.bind.contactUs.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginLayout:
                toLogin();
                break;
            case R.id.cancleAccount:
//                showCommPopLoginOut(0);
                break;
            case R.id.loginOut:
//                showCommPopLoginOut(1);
                break;
            case R.id.feedback:
//                Intent intentFeedBack = new Intent(getActivity(), FeedBackActivity.class);
//                pStartActivity(intentFeedBack, false);
                break;
        }
    }

//    private LoginOutPopupWindow commPopLoginOut;
//
//    /**
//     * 0-注销账户
//     * 1-退出登录
//     */
//    public void showCommPopLoginOut(int loginOutType) {
//        commPopLoginOut = new LoginOutPopupWindow(mContext, loginOutType, new ISelectTypeListener() {
//            @Override
//            public void select(int type, Object o) {
//                commPopLoginOut.dismiss();
//                clearAllActivityToLogin();
//            }
//        });
//
//        commPopLoginOut.showAtLocation(vm.bind.getRoot(), Gravity.BOTTOM, 0, 0);
//        BackGroundUtils.backgroundAlpha(getActivity(), 0.8f);
//    }
}
