package com.dwz.library.core;

import android.widget.TextView;

import com.dwz.library.App.AppContexts;
import com.dwz.library.utils.LogUtils;

import androidx.annotation.Dimension;
import androidx.databinding.BindingAdapter;

/**
 * @author dongweizhou
 * @createTime 2019/3/28
 * @describe title 设置文字颜色
 * @DWZ
 */
public class TitleTextViewSizeLoad {
    @BindingAdapter("android:textSize")
    public static void setTilteTvSize(TextView view,@Dimension float color){
        LogUtils.loge("========size==="+color);
        view.setTextSize(color/ AppContexts.sScale);
    }
}
