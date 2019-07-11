package com.dwz.mvvmdemo.vm;



import com.dwz.mvvmdemo.databinding.ActivityMainBinding;

import library.baseVModel.BaseVModel;
import library.utils.ToastUtil;

/**
 * 处理业务逻辑
 */
public class MainVModel extends BaseVModel<ActivityMainBinding> {


    @Override
    public void leftEvent(int type) {
        //  type 区分一个页面不同返回操作
        switch (type) {
            case 1:
                ToastUtil.showShort("99999999999");
                break;
            case 2:
                ToastUtil.showShort("777777777777");
                break;
        }
    }

    @Override
    public void initPresenter() {

    }
}
