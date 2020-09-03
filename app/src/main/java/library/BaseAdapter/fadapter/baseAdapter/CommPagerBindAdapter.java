package library.BaseAdapter.fadapter.baseAdapter;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.core.util.Pools;
import androidx.viewpager.widget.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.dwz.mvvmdemo.BR;

import java.util.List;

/***
 * BasicAdapter in which you only need to provide the convert() implementation.
 *
 * @param <T>	The type of the items in the list.
 */

public abstract class CommPagerBindAdapter<T, B extends ViewDataBinding> extends PagerAdapter {

    protected final Context mContext;

    protected List<T> mData;
    private int layoutResId;

    private Pools.Pool<View> pool = new Pools.SimplePool<>(4);
    //自己造一个池，可以提高加载效率，与复用率，

    // 点击监听事件（可以针对item中任意控件）
    protected OnItemClickListener onClickListener;

    public interface OnItemClickListener {
        void onClick(View v, int position, String type);
    }

    public void setOnClickListener(OnItemClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public CommPagerBindAdapter(Context mContext, int layoutResId, List<T> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.layoutResId = layoutResId;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = pool.acquire();
        if (view == null) {
            view = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()), layoutResId, container, false).getRoot();
        }
        B dataBinding = DataBindingUtil.bind(view);
        dataBinding.setVariable(BR.item, mData.get(position));
        container.addView(dataBinding.getRoot());
        convert(dataBinding, position, mData.get(position));
        return dataBinding.getRoot();

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
        pool.release(view);
    }

    abstract protected void convert(B binding, int pos, T item);

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}