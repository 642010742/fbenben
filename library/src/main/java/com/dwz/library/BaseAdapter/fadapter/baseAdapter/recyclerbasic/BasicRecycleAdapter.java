package com.dwz.library.BaseAdapter.fadapter.baseAdapter.recyclerbasic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.dwz.library.BaseAdapter.fadapter.baseAdapterCallBack.IDestroyCallBack;

import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;


public abstract class BasicRecycleAdapter<T> extends RecyclerView.Adapter<BasicRecycleAdapter.ItemViewHolder> implements IDestroyCallBack {

    protected List<T> mData;
    protected int layoutResId;
    protected Context mContext;
    protected LayoutInflater inflate;
    protected IRecycleMultiItems<T> mMultiItems;

    // 点击监听事件（可以针对item中任意控件，而非单一的recyclerview）
    protected OnItemClickListener onClickListener;
    protected OnItemLongClickListener onLongClickListener;

    public interface OnItemClickListener {
        void onClick(View v, int position);
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
    public BasicRecycleAdapter(Context context, int layoutResId) {
        this(context, layoutResId, null);
    }


    /***
     * Create a adapter.
     *
     * @param context:     The context.
     * @param layoutResId: The layout resource id of each item.
     * @param data:
     */
    public BasicRecycleAdapter(Context context, int layoutResId, List<T> data) {
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
    public BasicRecycleAdapter(Context context, List<T> data, IRecycleMultiItems<T> multiItems) {
        this.mContext = context;
        this.mMultiItems = multiItems;
        this.inflate = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public T getData(int pos) {
        return mData.get(pos);
    }

    @Override
    public int getItemViewType(int position) {
        return mMultiItems == null ? 0 : mMultiItems.getItemViewType(position);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        @LayoutRes int layoutId = (mMultiItems != null) ?
                mMultiItems.getItemLayoutId(viewType) : layoutResId;

        return new ItemViewHolder(mContext, inflate.inflate(layoutId, viewGroup, false));
    }

    public final static class ItemViewHolder extends RecyclerView.ViewHolder {
        private View root;
        private final SparseArray<View> views;
        private Context context;

        public ItemViewHolder(Context context, View itemView) {
            super(itemView);
            this.root = itemView;
            this.context = context;
            this.views = new SparseArray<View>();
        }

        @SuppressWarnings("unchecked")
        public <T extends View> T getViewById(int viewId) {
            View view = views.get(viewId);

            if (view == null) {
                view = root.findViewById(viewId);
                views.put(viewId, view);
            }

            return (T) view;
        }


        public int getLayoutId() {
            return itemView.getId();
        }

        /***
         * Will set the text of a TextView.
         *
         * @param viewId
         * @param value
         * @return The ItemViewHolder for chaining.
         */
        public ItemViewHolder setText(int viewId, String value) {
            TextView view = getViewById(viewId);
            view.setText(value);

            return this;
        }

        /***
         * Will set the image of an ImageView from a resource id.
         *
         * @param viewId
         * @param imageResId
         * @return The ItemViewHolder for chaining.
         */
        public ItemViewHolder setImageResource(int viewId, int imageResId) {
            ImageView view = getViewById(viewId);
            view.setImageResource(imageResId);

            return this;
        }

        /***
         * Will set background color of a view.
         *
         * @param viewId
         * @param color
         * @return The ItemViewHolder for chaining.
         */
        public ItemViewHolder setBackgroundColor(int viewId, int color) {
            View view = getViewById(viewId);
            view.setBackgroundColor(color);

            return this;
        }

        /***
         * Will set background of a view.
         *
         * @param viewId
         * @param backgroundRes
         * @return The ItemViewHolder for chaining.
         */
        public ItemViewHolder setBackgroundRes(int viewId, int backgroundRes) {
            View view = getViewById(viewId);
            view.setBackgroundResource(backgroundRes);

            return this;
        }

        /***
         * Will set text color of a TextView.
         *
         * @param viewId
         * @param textColor
         * @return The ItemViewHolder for chaining.
         */
        public ItemViewHolder setTextColor(int viewId, int textColor) {
            TextView view = getViewById(viewId);
            view.setTextColor(textColor);

            return this;
        }

        /***
         * Will set text color of a TextView.
         *
         * @param viewId
         * @param textColorRes
         * @return The ItemViewHolder for chaining.
         */
        public ItemViewHolder setTextColorRes(int viewId, int textColorRes) {
            TextView view = getViewById(viewId);
            view.setTextColor(context.getResources().getColor(textColorRes));

            return this;
        }

        /***
         * Will set the image of an ImageView from a drawable.
         *
         * @param viewId
         * @param drawable
         * @return The ItemViewHolder for chaining.
         */
        public ItemViewHolder setImageDrawable(int viewId, Drawable drawable) {
            ImageView view = getViewById(viewId);
            view.setImageDrawable(drawable);

            return this;
        }

        /***
         * Will download an image from a URL and put it in an ImageView.
         *
         * @param viewId
         * @param imageUrl
         * @return The ItemViewHolder for chaining.
         */
        public ItemViewHolder setImageUrl(int viewId, String imageUrl) {
            ImageView view = getViewById(viewId);
            RequestOptions options = new RequestOptions();
            options.diskCacheStrategy(DiskCacheStrategy.RESOURCE).centerCrop();
            Glide.with(context).load(imageUrl).apply(options).into(view);

            return this;
        }

        public ItemViewHolder setImageUrl(int viewId, String imageUrl, int defaultId) {
            ImageView view = getViewById(viewId);
            RequestOptions options = new RequestOptions();
            options.diskCacheStrategy(DiskCacheStrategy.RESOURCE).placeholder(defaultId).centerCrop();
            Glide.with(context).load(imageUrl).apply(options).into(view);

            return this;
        }

        /***
         * Add an action to set the image of an image view. Can be called multiple
         * times.
         */
        public ItemViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
            ImageView view = getViewById(viewId);
            view.setImageBitmap(bitmap);

            return this;
        }

        /***
         * Add an action to set the alpha of a view. Can be called multiple times.
         * Alpha between 0-1.
         */
        @SuppressLint("NewApi")
        public ItemViewHolder setAlpha(int viewId, float value) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                getViewById(viewId).setAlpha(value);
            } else {
                // Pre-honeycomb hack to set Alpha value
                AlphaAnimation alpha = new AlphaAnimation(value, value);
                alpha.setDuration(0);
                alpha.setFillAfter(true);
                getViewById(viewId).startAnimation(alpha);
            }

            return this;
        }

        /***
         * Set a view visibility to VISIBLE (true) or GONE (false).
         *
         * @param viewId
         * @param visible
         * @return The ItemViewHolder for chaining.
         */
        public ItemViewHolder setVisible(int viewId, boolean visible) {
            View view = getViewById(viewId);
            view.setVisibility(visible ? View.VISIBLE : View.GONE);

            return this;
        }

        /***
         * Add links into a TextView.
         *
         * @param viewId
         * @return The ItemViewHolder for chaining.
         */
        public ItemViewHolder linkify(int viewId) {
            TextView view = getViewById(viewId);
            Linkify.addLinks(view, Linkify.ALL);

            return this;
        }

        /***
         * Apply the typeface to the given viewId, and enable subpixel rendering.
         */
        public ItemViewHolder setTypeface(int viewId, Typeface typeface) {
            TextView view = getViewById(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);

            return this;
        }

        /***
         * Apply the typeface to all the given viewIds, and enable subpixel
         * rendering.
         */
        public ItemViewHolder setTypeface(Typeface typeface, int... viewIds) {
            for (int viewId : viewIds) {
                TextView view = getViewById(viewId);
                view.setTypeface(typeface);
                view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
            }

            return this;
        }

        /***
         * Sets the progress of a ProgressBar.
         *
         * @param viewId
         * @param progress
         * @return The ItemViewHolder for chaining.
         */
        public ItemViewHolder setProgress(int viewId, int progress) {
            ProgressBar view = getViewById(viewId);
            view.setProgress(progress);

            return this;
        }

        /***
         * Sets the progress and max of a ProgressBar.
         *
         * @param viewId
         * @param progress
         * @param max
         * @return The ItemViewHolder for chaining.
         */
        public ItemViewHolder setProgress(int viewId, int progress, int max) {
            ProgressBar view = getViewById(viewId);
            view.setMax(max);
            view.setProgress(progress);

            return this;
        }

        /***
         * Sets the range of a ProgressBar to 0...max.
         *
         * @param viewId
         * @param max
         * @return The ItemViewHolder for chaining.
         */
        public ItemViewHolder setMax(int viewId, int max) {
            ProgressBar view = getViewById(viewId);
            view.setMax(max);

            return this;
        }

        /***
         * Sets the rating (the number of stars filled) of a RatingBar.
         *
         * @param viewId
         * @param rating
         * @return The ItemViewHolder for chaining.
         */
        public ItemViewHolder setRating(int viewId, float rating) {
            RatingBar view = getViewById(viewId);
            view.setRating(rating);

            return this;
        }

        /***
         * Sets the rating (the number of stars filled) and max of a RatingBar.
         *
         * @param viewId
         * @param rating
         * @param max
         * @return The ItemViewHolder for chaining.
         */
        public ItemViewHolder setRating(int viewId, float rating, int max) {
            RatingBar view = getViewById(viewId);
            view.setMax(max);
            view.setRating(rating);

            return this;
        }

        /***
         * Sets the tag of the view.
         *
         * @param viewId
         * @param tag
         * @return The ItemViewHolder for chaining.
         */
        public ItemViewHolder setTag(int viewId, Object tag) {
            View view = getViewById(viewId);
            view.setTag(tag);

            return this;
        }

        /***
         * Sets the tag of the view.
         *
         * @param viewId
         * @param key
         * @param tag
         * @return The BaseAdapterHelper for chaining.
         */
        public ItemViewHolder setTag(int viewId, int key, Object tag) {
            View view = getViewById(viewId);
            view.setTag(key, tag);

            return this;
        }

        /***
         * Sets the checked status of a checkable.
         *
         * @param viewId
         * @param checked
         * @return The ItemViewHolder for chaining.
         */
        public ItemViewHolder setChecked(int viewId, boolean checked) {
            Checkable view = (Checkable) getViewById(viewId);
            view.setChecked(checked);

            return this;
        }

        /***
         * Sets the adapter of a adapter view.
         *
         * @param viewId
         * @param adapter
         * @return The ItemViewHolder for chaining.
         */
        public ItemViewHolder setAdapter(int viewId, Adapter adapter) {
            AdapterView<Adapter> view = getViewById(viewId);
            view.setAdapter(adapter);

            return this;
        }

        /***
         * Sets the on click listener of the view.
         *
         * @param viewId
         * @param listener
         * @return The ItemViewHolder for chaining.
         */
        public ItemViewHolder setOnClickListener(int viewId,
                                                 View.OnClickListener listener) {
            View view = getViewById(viewId);
            view.setOnClickListener(listener);

            return this;
        }

        /***
         * Sets the on touch listener of the view.
         *
         * @param viewId
         * @param listener
         * @return The ItemViewHolder for chaining.
         */
        public ItemViewHolder setOnTouchListener(int viewId,
                                                 View.OnTouchListener listener) {
            View view = getViewById(viewId);
            view.setOnTouchListener(listener);

            return this;
        }

        /***
         * Sets the on long click listener of the view.
         *
         * @param viewId
         * @param listener
         * @return The ItemViewHolder for chaining.
         */
        public ItemViewHolder setOnLongClickListener(int viewId,
                                                     View.OnLongClickListener listener) {
            View view = getViewById(viewId);
            view.setOnLongClickListener(listener);

            return this;
        }
    }

    public void destroy() {
        if (mData != null) {
            mData.clear();
            mData = null;
        }
    }
}
