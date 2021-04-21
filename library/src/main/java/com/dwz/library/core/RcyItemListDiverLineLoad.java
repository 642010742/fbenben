package com.dwz.library.core;

import android.view.View;

import com.dwz.library.utils.StringUtils;

import androidx.databinding.BindingAdapter;

/**
 * @author dongweizhou
 * @createTime 2019/3/25
 * @describe recyclerView item的分割线
 * @DWZ
 */
public class RcyItemListDiverLineLoad {

    @BindingAdapter("ItemDiver")
    public static void recyclerViewListDiver(View view,String type){
        if(StringUtils.isNotBlank(type)){
            if(type.equals("init")){
                view.setVisibility(View.VISIBLE);
            }else{
                view.setVisibility(View.GONE);
            }
        }else{
            view.setVisibility(View.VISIBLE);
        }
    }
}
