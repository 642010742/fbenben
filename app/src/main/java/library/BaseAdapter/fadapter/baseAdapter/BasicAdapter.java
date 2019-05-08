package library.BaseAdapter.fadapter.baseAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import library.BaseAdapter.fadapter.baseAdapterCallBack.IDestroyCallBack;
import library.BaseAdapter.fadapter.baseAdapterCallBack.IMultiItemsSupply;

/***
 * BasicAdapter in which you only need to provide the convert() implementation.
 *
 * @param <T>	The type of the items in the list.
 */

public abstract class BasicAdapter<T, H extends ViewHoldHelper> extends
		BaseAdapter implements IDestroyCallBack {

	protected int layoutResId;
	protected final Context mContext;
	protected List<T> mData;
	protected IMultiItemsSupply<T> mMultiItemsSupply;

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
	public BasicAdapter(Context context, int layoutResId) {
		this(context, layoutResId, null);
	}

	/***
	 * Create a adapter.
	 *
	 * @param context: The context.
	 * @param layoutResId: The layout resource id of each item.
	 * @param data:
	 */
	public BasicAdapter(Context context, int layoutResId, List<T> data) {
		this.mData = (data == null) ? new ArrayList<T>() : data;
		this.mContext = context;
		this.layoutResId = layoutResId;
	}

	/***
	 * Create a adapter.
	 *
	 * @param context
	 * @param data
	 * @param multiItemsSupply
	 */
	public BasicAdapter(Context context, List<T> data, IMultiItemsSupply<T> multiItemsSupply) {
		this.mMultiItemsSupply = multiItemsSupply;
		this.mData = data;
		this.mContext = context;
	}

	@Override
	public int getCount() {
//		int extra = displayIndeterminateProgress ? 1 : 0;
//		return mData.size() + extra;
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
//		if (getItemViewType(position) == 0) {
//			return createIndeterminateProgressView(convertView, parent);
//		}

		final H holder = getAdapterHelper(position, convertView, parent);
		T item = getItem(position);
		holder.setAssociatedObject(item);
		convert(holder, item);

		return holder.getConvertView();

	}

//	private View createIndeterminateProgressView(View convertView,
//			ViewGroup parent) {
//		if (convertView == null) {
//			FrameLayout container = new_w FrameLayout(mContext);
//			container.setForegroundGravity(Gravity.CENTER);
//			ProgressBar progress = new_w ProgressBar(mContext);
//			container.addView(progress);
//			convertView = container;
//		}
//		
//		return convertView;
//	}

	@Override
	public boolean isEnabled(int position) {
		return position < mData.size();
	}

	public void add(T elem) {
		mData.add(elem);
		notifyDataSetChanged();
	}

	public void addAll(List<T> elem) {
		mData.addAll(elem);
		notifyDataSetChanged();
	}

	public void set(T oldElem, T newElem) {
		set(mData.indexOf(oldElem), newElem);
	}

	public void set(int index, T elem) {
		mData.set(index, elem);
		notifyDataSetChanged();
	}

	public void remove(T elem) {
		mData.remove(elem);
		notifyDataSetChanged();
	}

	public void remove(int index) {
		mData.remove(index);
		notifyDataSetChanged();
	}

	public void replaceAll(List<T> elem) {
		mData.clear();
		mData.addAll(elem);
		notifyDataSetChanged();
	}

	public boolean contains(T elem) {
		return mData.contains(elem);
	}

	public void clear() {
		mData.clear();
		notifyDataSetChanged();
	}

	/***
	 * Implement this method and use the holder to adapt the view
	 *
	 * @param holder
	 * @param item: The item needs to be displayed.
	 */
	protected abstract void convert(H holder, T item);

	/***
	 * Override this to use a ViewHoldHelper in order to fit your needs
	 *
	 * @param position: The position of the item within the adapter's data set of the
	 *            		 item whose view we want.
	 * @param convertView
	 * @param parent: The parent that this view will eventually be attached to
	 * @return An instance of ViewHoldHelper.
	 */
	protected abstract H getAdapterHelper(int position, View convertView,
			ViewGroup parent);

	public void startActivity(Intent intent) {
		mContext.startActivity(intent);
	}

	public void startActivityForResult(Intent intent, int requestCode) {
		((Activity) mContext).startActivityForResult(intent, requestCode);
	}

	public void setResults(int resultCode) {
		Activity activity = ((Activity) mContext);
		activity.setResult(resultCode);
		activity.finish();
	}

	public void setResults(int resultCode, Intent intent) {
		Activity activity = ((Activity) mContext);
		activity.setResult(resultCode, intent);
		activity.finish();
	}

	public void destroy() {
		if (mData != null) {
			mData.clear();
			mData = null;
		}
	}
}
