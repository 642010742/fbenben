package library.utils.upapk;

import android.content.Context;


import com.dwz.mvvmdemo.databinding.ItemUpversionBinding;

import java.util.List;

import library.BaseAdapter.fadapter.baseAdapter.CommBindAdapter;
import library.BaseAdapter.fadapter.baseAdapter.recyclerbasic.CommnBindRecycleAdapter;


/**
 * Created by zmf on 2017/11/21 0021.
 */

public class UpVersionAdapter extends CommnBindRecycleAdapter<String, ItemUpversionBinding> {

    public UpVersionAdapter(Context context, int layoutResId, List<String> data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(ItemUpversionBinding bind, ItemViewHolder holder, String item, int layoutResId) {
        bind.tvContent.setText(item);
    }

}
