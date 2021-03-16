package library.BaseAdapter.dadapter.listener;

import library.BaseAdapter.dadapter.holder.XXViewHolder;
import library.commonModel.BaseModel;


/**
 * Created by Administrator on 2018/2/11.
 */

public interface OnChildItemClickListener {
    void onChildItemClick(XXViewHolder holder, int position, String type);
}
