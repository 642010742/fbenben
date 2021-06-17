package com.dwz.mvvmdemo.ui.activity;

import android.view.View;

import com.dwz.library.App.AppContexts;
import com.dwz.library.App.AppManager;
import com.dwz.library.baseView.BaseActivity;
import com.dwz.library.common.FragmentTabMenuView;
import com.dwz.library.listener.OnTabMenuClickListener;
import com.dwz.library.utils.NetworkUtils;
import com.dwz.library.utils.ToastUtil;
import com.dwz.mvvmdemo.R;
//import com.dwz.mvvmdemo.commom.di.component.DaggerDPComponent;
import com.dwz.mvvmdemo.ui.fragment.TestFragment;
import com.dwz.mvvmdemo.ui.fragment.tabfragment.MineFragment;
import com.dwz.mvvmdemo.vm.MainVModel;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends BaseActivity<MainVModel> implements OnTabMenuClickListener {

    private FragmentTabMenuView tabMenuView;

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
        initTab();
    }

    @Override
    public void onTabMenuClick(View container, int lastChecked, int nowChecked) {
        switch (nowChecked) {
            case 0:
                // 第一个fragment 点击显示出来
                setBarColor(R.color.ff4a3c);
                break;
            case 1:
                setBarColor(R.color.ffffff);
                break;
            case 2:
                setBarColor(R.color.ffffff);
                if (NetworkUtils.getNetWorkState(AppContexts.App()) == NetworkUtils.NETWORK_NONE) {
                    vm.bind.noNetView.setVisibility(View.VISIBLE);
                } else {
                    vm.bind.noNetView.setVisibility(View.GONE);
                }
                break;
            case 3:
                setBarColor(R.color.c0f6eff);
                break;
        }
    }

    public void initTab() {
        tabMenuView = findViewById(R.id.tabmenu);
        tabMenuView.addMenu("1", R.mipmap.ic_launcher, R.mipmap.ic_launcher, TestFragment.class);
        tabMenuView.addMenu("2", R.mipmap.ic_launcher, R.mipmap.ic_launcher, TestFragment.class);
        tabMenuView.addMenu("3", R.mipmap.ic_launcher, R.mipmap.ic_launcher, TestFragment.class);
        tabMenuView.addMenu("4", R.mipmap.ic_launcher, R.mipmap.ic_launcher, MineFragment.class);
        tabMenuView.refreshMenuViewAt(getSupportFragmentManager(), 0);
        tabMenuView.setOnTabMenuClickListener(this);
    }

    @Override
    public void onBackPressed() {
        exitBy2Click();
    }

    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            ToastUtil.showShort("再按一次退出程序");
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
        } else {
            AppManager.getAppManager().exitApp();
        }
    }
}
