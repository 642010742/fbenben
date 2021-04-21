package com.dwz.library.BaseAdapter.dadapter.listener;


import com.dwz.library.BaseAdapter.dadapter.holder.XXViewHolder;
import com.dwz.library.commonModel.BaseModel;

/**
 * Created by Administrator on 2018/2/11.
 */

public interface OnItemClickListener {
    void onItemClick(XXViewHolder holder, int position, BaseModel baseModel);
}
