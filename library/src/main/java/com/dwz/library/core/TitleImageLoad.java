package com.dwz.library.core;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

/**
 * 董维周 加载本地资源图片
 */

public class TitleImageLoad {
    @BindingAdapter("imageTitle")
    public static void setTitleImage(ImageView view, int imageRes){
        if(imageRes == -1) return;
        else  view.setImageResource(imageRes);
    }
}
