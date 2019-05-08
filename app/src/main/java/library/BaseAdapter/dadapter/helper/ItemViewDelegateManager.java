package library.BaseAdapter.dadapter.helper;

import android.support.v4.util.SparseArrayCompat;
import android.util.Log;

import library.BaseAdapter.dadapter.holder.IMGroupViewHolder;
import library.BaseAdapter.dadapter.holder.ShareChooseViewHolder;
import library.BaseAdapter.dadapter.holder.XXViewHolder;

import library.commonModel.BaseModel;


/**
 * Created by zhy on 16/6/22.
 */
public class ItemViewDelegateManager<T extends BaseModel>
{
    SparseArrayCompat<ItemViewDelegate<T>> delegates = new SparseArrayCompat();

    public int getItemViewDelegateCount()
    {
        return delegates.size();
    }

    public ItemViewDelegateManager<T> addDelegate(ItemViewDelegate<T> delegate)
    {
        int viewType = delegates.size();
        if (delegate != null)
        {
            delegates.put(viewType, delegate);
            viewType++;
        }
        return this;
    }

    public ItemViewDelegateManager<T> addDelegate(int viewType, ItemViewDelegate<T> delegate)
    {
        if (delegates.get(viewType) != null)
        {
            throw new IllegalArgumentException(
                    "An ItemViewDelegate is already registered for the viewType = "
                            + viewType
                            + ". Already registered ItemViewDelegate is "
                            + delegates.get(viewType));
        }
        delegates.put(viewType, delegate);
        return this;
    }

    public ItemViewDelegateManager<T> removeDelegate(ItemViewDelegate<T> delegate)
    {
        if (delegate == null)
        {
            throw new NullPointerException("ItemViewDelegate is null");
        }
        int indexToRemove = delegates.indexOfValue(delegate);

        if (indexToRemove >= 0)
        {
            delegates.removeAt(indexToRemove);
        }
        return this;
    }

    public ItemViewDelegateManager<T> removeDelegate(int itemType)
    {
        int indexToRemove = delegates.indexOfKey(itemType);

        if (indexToRemove >= 0)
        {
            delegates.removeAt(indexToRemove);
        }
        return this;
    }

    public int getItemViewType(T item, int position)
    {
        int delegatesCount = delegates.size();
        Log.e("dwz","-----------------delegates"+delegatesCount);
        for (int i = delegatesCount - 1; i >= 0; i--)
        {
            ItemViewDelegate<T> delegate = delegates.valueAt(i);
            if (delegate.isForViewType( item, position))
            {
                return delegates.keyAt(i);
            }
        }
        throw new IllegalArgumentException(
                "No ItemViewDelegate added that matches position=" + position + " in data source");
    }

    // 绑定数据( 使用databing)
    public void convert(XXViewHolder holder, T t, int position)
    {
        int delegatesCount = delegates.size();
        for (int i = 0; i < delegatesCount; i++)
        {
            ItemViewDelegate<T> delegate = delegates.valueAt(i);

            if (delegate.isForViewType(t, position))
            {
                holder.getBinding().setVariable(delegate.getItemDataBingId(),t);
                holder.getBinding().executePendingBindings();
                return;
            }
        }
        throw new IllegalArgumentException(
                "No ItemViewDelegateManager added that matches position=" + position + " in data source");
    }

    public void convert(ShareChooseViewHolder holder, T t, int position)
    {
        int delegatesCount = delegates.size();
        for (int i = 0; i < delegatesCount; i++)
        {
            ItemViewDelegate<T> delegate = delegates.valueAt(i);

            if (delegate.isForViewType(t, position))
            {
                holder.getBinding().setVariable(delegate.getItemDataBingId(),t);
                holder.getBinding().executePendingBindings();
                return;
            }
        }
        throw new IllegalArgumentException(
                "No ItemViewDelegateManager added that matches position=" + position + " in data source");
    }

    public void convert(IMGroupViewHolder holder, T t, int position)
    {
        int delegatesCount = delegates.size();
        for (int i = 0; i < delegatesCount; i++)
        {
            ItemViewDelegate<T> delegate = delegates.valueAt(i);

            if (delegate.isForViewType(t, position))
            {
                holder.getBinding().setVariable(delegate.getItemDataBingId(),t);
                holder.getBinding().executePendingBindings();
                return;
            }
        }
        throw new IllegalArgumentException(
                "No ItemViewDelegateManager added that matches position=" + position + " in data source");
    }


    public ItemViewDelegate getItemViewDelegate(int viewType)
    {
        return delegates.get(viewType);
    }

    public int getItemViewLayoutId(int viewType)
    {
        return getItemViewDelegate(viewType).getItemViewLayoutId();
    }

    public int getItemViewType(ItemViewDelegate itemViewDelegate)
    {
        return delegates.indexOfValue(itemViewDelegate);
    }
}
