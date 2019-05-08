package library.BaseAdapter.fadapter.baseAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import java.util.ArrayList;
import java.util.List;

import library.BaseAdapter.fadapter.baseAdapterCallBack.IMultiItemsSupply;


/***
 * CommonAdapter in which you only need to provide the convert() implementation.
 * 
 * @param <T> The type of the items in the list.
 */
public abstract class CommonAdapter<T> extends BasicAdapter<T, ViewHoldHelper> {

	/***
	 * Create a CommonAdapter.
	 * 
	 * @param context: The context.
	 * @param layoutResId: The layout resource id of each item.
	 */
	public CommonAdapter(Context context, int layoutResId) {
		super(context, layoutResId);
	}

	/***
	 * Create a CommonAdapter.
	 * 
	 * @param context
	 * @param layoutResId
	 * @param data: A new_w list is created out of this one to avoid mutable list
	 */
	public CommonAdapter(Context context, int layoutResId, List<T> data) {
		super(context, layoutResId, data);
	}

	public CommonAdapter(Context context, ArrayList<T> data,
			IMultiItemsSupply<T> multiItemSupport) {
		super(context, data, multiItemSupport);
	}

	protected ViewHoldHelper getAdapterHelper(int position,
			View convertView, ViewGroup parent) {

		if (mMultiItemsSupply != null) {
			return ViewHoldHelper.getViewHolder(mContext, convertView, 
					parent, mMultiItemsSupply.getLayoutId(position, 
							mData.get(position)), position);
		} else {
			return ViewHoldHelper.getViewHolder(mContext, convertView, 
					parent, layoutResId, position);
		}
	}

	/***
	 * 
	 * @param absView
	 * @param itemIndex
	 */
	public void updateView(AbsListView absView ,int itemIndex) {
        int firstVisiblePosition = absView.getFirstVisiblePosition();
        int lastVisiblePosition =absView.getLastVisiblePosition();
        if(itemIndex >= firstVisiblePosition && itemIndex <= lastVisiblePosition) {
                View view = absView.getChildAt(itemIndex - firstVisiblePosition);
                int type = getItemViewType(itemIndex); 
                if (view.getTag() instanceof ViewHoldHelper) {
                	ViewHoldHelper mViewHolder = (ViewHoldHelper) view.getTag();
                    updateView(itemIndex, type, mViewHolder);
                }
        }
	}
	
	public void updateView(int index ,int type, ViewHoldHelper mViewHolder) {
		
	};
	
}
