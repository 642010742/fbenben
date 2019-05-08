package library.BaseAdapter.dadapter.holder;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;


/**
 *
 */

public class ShareChooseViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder{
    private T binging;
    private SparseArray<View> views;
    private View rootView;
    private Context mContext;
    public ShareChooseViewHolder(T binging) {
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
