package library.listener;

import android.support.v7.widget.RecyclerView;

import library.BaseAdapter.dadapter.holder.XXViewHolder;
import library.commonModel.BaseModel;


/**
 * Created by Administrator on 2018/2/11.
 */

public interface OnCommonItemClickListener {
    void onItemClick(RecyclerView.ViewHolder holder, int position, BaseModel baseModel);
    boolean onItemLongClick(RecyclerView.ViewHolder holder, int position);
    void onChildItemClick(RecyclerView.ViewHolder holder, int position, String type);
}
