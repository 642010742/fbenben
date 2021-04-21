package com.dwz.library.listener;

import com.dwz.library.commonModel.BaseModel;

import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by Administrator on 2018/2/11.
 */

public interface OnCommonItemClickListener {
    void onItemClick(RecyclerView.ViewHolder holder, int position, BaseModel baseModel);
    boolean onItemLongClick(RecyclerView.ViewHolder holder, int position);
    void onChildItemClick(RecyclerView.ViewHolder holder, int position, String type);
}
