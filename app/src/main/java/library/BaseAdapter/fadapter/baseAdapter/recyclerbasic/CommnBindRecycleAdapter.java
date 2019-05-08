package library.BaseAdapter.fadapter.baseAdapter.recyclerbasic;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.dwz.mvvmdemo.BR;

import java.util.List;

import library.BaseAdapter.fadapter.baseAdapterCallBack.IDestroyCallBack;


public abstract class CommnBindRecycleAdapter<T, B extends ViewDataBinding> extends RecyclerView.Adapter<CommnBindRecycleAdapter.ItemViewHolder> implements IDestroyCallBack {

    protected List<T> mData;
    protected int layoutResId;
    protected Context mContext;
    protected LayoutInflater inflate;
    protected IRecycleMultiItems<T> mMultiItems;

    // 点击监听事件（可以针对item中任意控件，而非单一的recyclerview）
    protected OnItemClickListener onClickListener;
    protected OnItemLongClickListener onLongClickListener;

    public interface OnItemClickListener {
        void onClick(View v, int position, String type);
    }

    interface OnItemLongClickListener {
        void onLongClick(View v, int position);
    }

    public void setOnClickListener(OnItemClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnLongClickListener(OnItemLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }

    /***
     * Create a adapter.
     *
     * @param context:     The context.
     * @param layoutResId: The layout resource id of each item.
     */
    public CommnBindRecycleAdapter(Context context, int layoutResId) {
        this(context, layoutResId, null);
    }

    /***
     * Create a adapter.
     *
     * @param context:     The context.
     * @param layoutResId: The layout resource id of each item.
     * @param data:
     */
    public CommnBindRecycleAdapter(Context context, int layoutResId, List<T> data) {
        this.mContext = context;
        this.layoutResId = layoutResId;
        this.inflate = LayoutInflater.from(context);
        this.mData = data;
    }

    /***
     * Create a adapter.
     *
     * @param context
     * @param data
     * @param :multiItemSupport
     */
    public CommnBindRecycleAdapter(Context context, List<T> data, IRecycleMultiItems<T> multiItems) {
        this.mContext = context;
        this.mMultiItems = multiItems;
        this.inflate = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mMultiItems == null ? 0 : mMultiItems.getItemViewType(position);
    }

    //将数据与界面进行绑定的操作
    protected abstract void convert(B bind, ItemViewHolder holder, T item, int layoutResId);


    @Override
    public void onBindViewHolder(CommnBindRecycleAdapter.ItemViewHolder holder, int position) {

        B bind = (B) holder.getBinding();
        bind.setVariable(BR.item, mData.get(position));
        convert(bind, holder, mData.get(position), mMultiItems == null ? layoutResId : mMultiItems.getItemLayoutId(getItemViewType(position)));
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        @LayoutRes int layoutId = (mMultiItems != null) ?
                mMultiItems.getItemLayoutId(viewType) : layoutResId;
        B bind = DataBindingUtil.inflate(inflate, layoutId, viewGroup, false);
        ItemViewHolder holder = new ItemViewHolder(bind.getRoot());
        holder.setBinding(bind);
        holder.setLayoutId(layoutId);
        return holder;
    }

    public final class ItemViewHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder {

        private B binding;
        private int layoutId;

        public ItemViewHolder(View view) {
            super(view);
        }

        public void setBinding(B binding) {
            this.binding = binding;
        }

        private B getBinding() {
            return binding;
        }

        public int getLayoutId() {
            return layoutId;
        }

        public void setLayoutId(int layoutId) {
            this.layoutId = layoutId;
        }
    }


    public void destroy() {
        if (mData != null) {
            mData.clear();
            mData = null;
        }
    }
}
