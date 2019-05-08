package com.dwz.mvvmdemo.ui.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dwz.mvvmdemo.R;
import com.dwz.mvvmdemo.databinding.ActivityTestBinding;

/**
 * @author dongweizhou
 * @createTime 2019/5/8
 * @describe
 * @DWZ
 */
public class TestActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTestBinding activityTestBinding = DataBindingUtil.setContentView(this, R.layout.activity_test);
    }
}
