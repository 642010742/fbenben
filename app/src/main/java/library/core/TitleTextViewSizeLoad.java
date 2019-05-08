package library.core;

import android.databinding.BindingAdapter;
import android.support.annotation.Dimension;
import android.widget.TextView;

import library.App.AppContexts;
import library.utils.LogUtils;

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
        view.setTextSize(color/AppContexts.sScale);
    }
}
