package com.dwz.mvvmdemo.ui.fragment;

import android.view.View;
import android.widget.Toast;

import com.dwz.mvvmdemo.R;
import com.dwz.mvvmdemo.vm.TestFragmentVmodel;
import com.qmuiteam.qmui.skin.QMUISkinManager;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

import library.baseView.BaseFragment;

/**
 * @author dongweizhou
 * @createTime 2019/5/8
 * @describe
 * @DWZ
 */
public class TestFragment extends BaseFragment<TestFragmentVmodel> implements View.OnClickListener {
    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;
//    private int mCurrentDialogStyle = R.style.DialogTheme2;

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
        vm.bind.changeAlpha.setChangeAlphaWhenPress(true);

        initListener();
    }

    public void initListener() {
        vm.bind.showDialog1.setOnClickListener(this);
        vm.bind.showDialog2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.showDialog1:
                showMessagePositiveDialog();
                break;
            case R.id.showDialog2:
                showLongMessageDialog();
                break;
        }

    }

    // ================================ 生成不同类型的对话框
    private void showMessagePositiveDialog() {
        new QMUIDialog.MessageDialogBuilder(getActivity())
                .setTitle("标题")
                .setMessage("确定要发送吗？")
                .setSkinManager(QMUISkinManager.defaultInstance(getContext()))
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                //QMUIDialogAction.ACTION_PROP_POSITIVE  蓝色
                //QMUIDialogAction.ACTION_PROP_NEGATIVE  红色
                //QMUIDialogAction.ACTION_PROP_NEUTRAL  蓝色
                .addAction(0, "确定", QMUIDialogAction.ACTION_PROP_NEUTRAL, new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                        Toast.makeText(getActivity(), "发送成功", Toast.LENGTH_SHORT).show();
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    private void showLongMessageDialog() {
        new QMUIDialog.MessageDialogBuilder(getActivity())
                .setTitle("标题")
                .setSkinManager(QMUISkinManager.defaultInstance(getContext()))
                .setMessage("这是一段很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长" +
                        "很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很" +
                        "很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很" +
                        "很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很" +
                        "长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长" +
                        "很长很长很长很长很很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长" +
                        "很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长" +
                        "很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长" +
                        "很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长" +
                        "很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长" +
                        "很长很长很长很长很长很长很长很长很长很长很长很长很长很长长很长的文案")
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle).show();
    }
}
