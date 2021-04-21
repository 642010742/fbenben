package com.dwz.library.core;

import android.widget.TextView;
import com.dwz.library.utils.LogUtils;
import androidx.databinding.BindingAdapter;

/**
 * @author dongweizhou
 * @createTime 2019/3/28
 * @describe title 设置文字颜色
 * @DWZ
 */
public class TitleTextViewColorLoad {
    @BindingAdapter("android:textColor")
    public static void setTilteTvColor(TextView view,int color){
        LogUtils.loge("========size==yy="+color);
        view.setTextColor(color);
    }
}
