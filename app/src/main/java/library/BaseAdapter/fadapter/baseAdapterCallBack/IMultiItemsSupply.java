package library.BaseAdapter.fadapter.baseAdapterCallBack;

/**
 * Created by Administrator on 2016/12/7.
 */

public interface IMultiItemsSupply<T> {

    int getLayoutId(int position, T t);

    int getViewTypeCount();

    int getItemViewType(int postion, T t);

}
