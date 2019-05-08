package library.core;

import android.databinding.BindingAdapter;
import android.widget.TextView;

import library.utils.LogUtils;

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
