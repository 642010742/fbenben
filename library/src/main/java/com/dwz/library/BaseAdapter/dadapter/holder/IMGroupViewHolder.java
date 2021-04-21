package com.dwz.library.BaseAdapter.dadapter.holder;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;


/**
 *  通用viewholder
 */

public class IMGroupViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder{
    private T binging;
    private SparseArray<View> views;
    private View rootView;
    private Context mContext;
    public IMGroupViewHolder(T binging) {
        super(binging.getRoot());
        this.binging = binging;
        this.rootView = binging.getRoot();
        this.mContext = rootView.getContext();
        this.views = new SparseArray<>();

    }
    public T getBinding(){
        return binging;
    }
    public View getRootView(){
        return rootView;
    }

    public <T extends View> T getView(int viewId){
        View view = views.get(viewId);
        if (view == null ){
            view = rootView.findViewById(viewId);
            views.put(viewId,view);
        }
        return (T) view;
    }

    public SparseArray<View> getViews(){
        return this.views;
    }





}
