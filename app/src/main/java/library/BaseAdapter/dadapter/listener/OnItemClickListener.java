package library.BaseAdapter.dadapter.listener;

import library.BaseAdapter.dadapter.holder.XXViewHolder;

import library.commonModel.BaseModel;


/**
 * Created by Administrator on 2018/2/11.
 */

public interface OnItemClickListener {
    void onItemClick(XXViewHolder holder, int position, BaseModel baseModel);
    boolean onItemLongClick(XXViewHolder holder, int position);
    void onChildItemClick(XXViewHolder holder, int position,String type);
}
