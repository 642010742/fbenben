package com.dwz.library.core;

import android.view.View;
import com.dwz.library.R;
import androidx.annotation.ColorRes;
import androidx.databinding.BindingAdapter;

/**
 * @author dongweizhou
 * @createTime 2019/3/28
 * @describe
 * @DWZ
 */
public class TitleBackgroudColorLoad {
    @BindingAdapter("titleColor")
    public static void setTitleBackgroudColor(View view,@ColorRes int color){
        if(color !=0){
            view.setBackgroundColor(view.getContext().getResources().getColor(color));
        }else{
            view.setBackgroundColor(view.getContext().getResources().getColor(R.color.f3f3f3));
        }
    }
}
