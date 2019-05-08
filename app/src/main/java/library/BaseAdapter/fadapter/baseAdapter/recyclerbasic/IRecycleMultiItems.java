package library.BaseAdapter.fadapter.baseAdapter.recyclerbasic;

/**
 * Created by Administrator on 2016/12/7.
 */

public interface IRecycleMultiItems<T> {

    int getItemLayoutId(int viewType);

    int getViewTypeCount();

    int getItemViewType(int postion);

}