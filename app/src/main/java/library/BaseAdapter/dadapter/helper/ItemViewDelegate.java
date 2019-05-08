package library.BaseAdapter.dadapter.helper;


import library.commonModel.BaseModel;

/**
 * Created by zhy on 16/6/22.
 */
public interface ItemViewDelegate<T extends BaseModel>
{

    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    int getItemDataBingId();

}
