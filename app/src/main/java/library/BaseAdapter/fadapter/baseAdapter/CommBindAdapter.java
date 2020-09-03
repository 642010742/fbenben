package library.BaseAdapter.fadapter.baseAdapter;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.dwz.mvvmdemo.BR;

import java.util.ArrayList;
import java.util.List;

import library.BaseAdapter.fadapter.baseAdapterCallBack.IMultiItemsSupply;


/***
 * BasicAdapter in which you only need to provide the convert() implementation.
 *
 * @param <T>	The type of the items in the list.
 */

public abstract class CommBindAdapter<T, B extends ViewDataBinding> extends BaseAdapter {

    protected int layoutResId;
    protected final Context mContext;
    protected List<T> mData;
    protected IMultiItemsSupply<T> mMultiItemsSupply;

    protected LayoutInflater inflate;

    private int pos;

    // 点击监听事件（可以针对item中任意控件）
    protected OnItemClickListener onClickListener;

    //	protected boolean displayIndeterminateProgress = false;
    public interface OnItemClickListener {
        void onClick(View v, int position, String type);
    }

    public void setOnClickListener(OnItemClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    /***
     * Create a adapter.
     *
     * @param context: The context.
     * @param layoutResId: The layout resource id of each item.
     */
    public CommBindAdapter(Context context, int layoutResId) {
        this(context, layoutResId, null);
    }

    /***
     * Create a adapter.
     *
     * @param context: The context.
     * @param layoutResId: The layout resource id of each item.
     * @param data:
     */
    public CommBindAdapter(Context context, int layoutResId, List<T> data) {
        this.mData = (data == null) ? new ArrayList<T>() : data;
        this.mContext = context;
        this.layoutResId = layoutResId;
        inflate = LayoutInflater.from(mContext);
    }

    /***
     * Create a adapter.
     *
     * @param context
     * @param data
     * @param multiItemsSupply
     */
    public CommBindAdapter(Context context, List<T> data, IMultiItemsSupply<T> multiItemsSupply) {
        this.mMultiItemsSupply = multiItemsSupply;
        this.mData = data;
        this.mContext = context;
        inflate = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        if (position >= mData.size()) {
            return null;
        }

        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        if (mMultiItemsSupply != null) {
            return mMultiItemsSupply.getViewTypeCount() + 1;
        }

        return 2;
    }


    @Override
    public int getItemViewType(int position) {
        if (mMultiItemsSupply != null) {
            return mMultiItemsSupply.getItemViewType(position,
                    mData.get(position));
        }

        return position >= mData.size() ? 0 : 1;
    }

    abstract protected void convert(B binding, int pos,T item);

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        T item = getItem(position);

        if (mMultiItemsSupply != null) {
            layoutResId = mMultiItemsSupply.getLayoutId(position, item);
        }

        B binding = null;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), layoutResId, parent, false);
        } else {
            binding = DataBindingUtil.getBinding(convertView);
        }

        binding.setVariable(BR.item, item);

        convert(binding, position,item);

        return binding.getRoot();

    }

}