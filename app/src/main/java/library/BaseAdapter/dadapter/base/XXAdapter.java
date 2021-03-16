package library.BaseAdapter.dadapter.base;

import android.content.Context;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import library.BaseAdapter.dadapter.helper.ItemViewDelegate;
import library.BaseAdapter.dadapter.helper.ItemViewDelegateManager;
import library.BaseAdapter.dadapter.holder.XXViewHolder;
import library.BaseAdapter.dadapter.listener.OnChildItemClickListener;
import library.BaseAdapter.dadapter.listener.OnItemLongClickListener;
import library.commonModel.BaseModel;
import library.BaseAdapter.dadapter.listener.OnItemClickListener;
import library.utils.LogUtils;

/**
 * Created by Administrator on 2018/1/12.
 */

public class XXAdapter<T extends BaseModel> extends RecyclerView.Adapter<XXViewHolder> {
    public static final String TAG = "XXAdapter";
    private List<T> list;
    private Context context;
    private LayoutInflater inflater;
    private ItemViewDelegateManager itemViewDelegateManager;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private OnChildItemClickListener onChildItemClickListener;
    private XXViewHolder xxViewHolder;
    // 应该设置在数据集合之前
    // item 里面子View id的集合 的点击事件
    private int[] ids;
    // item 事件相应的type
    private String[] types;

    public int[] getIds() {
        return ids;
    }

    public void setIds(int[] ids) {
        this.ids = ids;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnChildItemClickListener(OnChildItemClickListener onChildItemClickListener) {
        this.onChildItemClickListener = onChildItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public XXAdapter(List<T> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        itemViewDelegateManager = new ItemViewDelegateManager();
    }

    public XXAdapter(List<T> list, Context context, int[] ids, String[] types) {
        this.list = list;
        this.context = context;
        this.ids = ids;
        this.types = types;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        itemViewDelegateManager = new ItemViewDelegateManager();
    }

    @Override
    public XXViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int itemViewLayoutId = itemViewDelegateManager.getItemViewLayoutId(viewType);
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, itemViewLayoutId, parent, false);
        xxViewHolder = new XXViewHolder(binding);
        return xxViewHolder;
    }

    @Override
    public void onBindViewHolder(final XXViewHolder holder, final int position) {

        if (changeStyle != null) {
            changeStyle.setRes(holder, list.get(position), position);
        }

        itemViewDelegateManager.convert(holder, list.get(position), position);
        if (ids != null && ids.length > 0 && types != null && types.length > 0) {
            if (ids.length == types.length) {
                for (int i = 0; i < ids.length; i++) {
                    final String type = types[i];
                    holder.getView(ids[i]).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            LogUtils.loge(TAG, "=========idssssssssssssssssss");
                            if (onChildItemClickListener != null)
                                onChildItemClickListener.onChildItemClick(holder, position, type);
                        }
                    });
                }
            } else {
                LogUtils.loge(TAG, "=========ids的数量必须和types的数量保持一致=======");
            }
        }

        holder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null)
                    onItemClickListener.onItemClick(holder, position, list.get(position));
            }
        });

        holder.getRootView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (onItemLongClickListener != null)
                    return onItemLongClickListener.onItemLongClick(holder, position);
                else return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list == null || list.size() == 0 ? 0 : list.size();
    }


    public List<T> getDatas() {
        return list;
    }

    public int getCount() {
        return list.size();
    }

    public changeStyle<T> changeStyle;

    public void setChangeStyle(XXAdapter.changeStyle<T> changeStyle) {
        this.changeStyle = changeStyle;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public XXAdapter addItemViewDelegate(ItemViewDelegate<T> itemViewDelegate) {
        itemViewDelegateManager.addDelegate(itemViewDelegate);
        return this;
    }

    public XXAdapter addItemViewDelegate(int viewType, ItemViewDelegate<T> itemViewDelegate) {
        itemViewDelegateManager.addDelegate(viewType, itemViewDelegate);
        return this;
    }

    @Override
    public int getItemViewType(int position) {
        if (!useItemViewDelegateManager()) return super.getItemViewType(position);
        else return itemViewDelegateManager.getItemViewType(list.get(position), position);
    }

    protected boolean useItemViewDelegateManager() {
        return itemViewDelegateManager.getItemViewDelegateCount() > 0;
    }


    public void upDatas(List<T> list) {
        if (list != null && list.size() > 0) {
            this.list.clear();
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void upDatasEmpty(List<T> list) {
        if (list != null) {
            this.list.clear();
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void upDatasNoClear(List<T> list) {
        if (list != null && list.size() > 0) {
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void upDatasNoClear(List<T> list, int start) {
        if (list != null && list.size() > 0) {
            this.list.addAll(start, list);
            notifyDataSetChanged();
        }
    }

    public void upData(int position, T t) {
        if (list != null && list.size() > 0) {
            this.list.remove(position);
            list.add(position, t);
            notifyItemChanged(position);
            //notifyItemRangeChanged(0,list.size());
        }
    }

    public void upData(int position) {
        if (list != null && list.size() > 0) {
            notifyItemChanged(position);
        }
    }

    public void addData(T t) {
        if (list != null) {
            list.add(t);
            notifyDataSetChanged();
        }
    }

    public void addData(int position, T t) {
        if (list != null) {
            list.add(position, t);
            notifyDataSetChanged();
        }
    }

    public void removeItem(int position) {
        if (list != null && list.size() > 0) {
            this.list.remove(position);
            notifyItemChanged(position);
            notifyItemRangeChanged(0, list.size());
        }
    }

    public void emptyAll() {
        list.clear();
        notifyDataSetChanged();
    }

    public XXViewHolder getViewHolder() {
        return xxViewHolder;
    }


    public interface changeStyle<T extends BaseModel> {
        void setRes(XXViewHolder viewHolder, T t, int position);
    }
}
