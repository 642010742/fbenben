package com.dwz.library.BaseAdapter.fadapter.baseAdapter;

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

import androidx.annotation.StringRes;

public class ViewHoldHelper {

    // Views indexed with their id
    public int layoutId;
    private int position;
    private View convertView;
    private final Context mContext;
    private final SparseArray<View> views;

    // Package private field to retain the associated user object and detect a change
    Object associatedObject;

    protected ViewHoldHelper(Context context, ViewGroup parent,
                             int layoutId, int position) {
        this.mContext = context;
        this.position = position;
        this.layoutId = layoutId;
        this.views = new SparseArray<View>();
        convertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        convertView.setTag(this);
    }

    /***
     * This method is to get a ViewHoldHelper.
     *
     * @param context
     * @param convertView
     * @param parent
     * @return A ViewHoldHelper instance.
     */
    public static ViewHoldHelper getViewHolder(Context context, View convertView,
                                               ViewGroup parent, int layoutId) {
        return getViewHolder(context, convertView, parent, layoutId, -1);
    }

    /**
     * This method is package private and should only be used by QuickAdapter.
     */
    static ViewHoldHelper getViewHolder(Context context, View convertView,
                                        ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {

            return new ViewHoldHelper(context, parent, layoutId, position);
        }

        // Retrieve the existing helper and update its position
        ViewHoldHelper existingHelper = (ViewHoldHelper) convertView
                .getTag();

        if (existingHelper.layoutId != layoutId) {
            return new ViewHoldHelper(context, parent, layoutId, position);
        }

        existingHelper.position = position;
        return existingHelper;
    }

    /***
     *
     * @param viewId: The id of the view you want to retrieve.
     */
    public <T extends View> T getView(int viewId) {
        return getViewById(viewId);
    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T getViewById(int viewId) {
        View view = views.get(viewId);

        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }

        return (T) view;
    }

    /***
     * Will set the text of a TextView.
     *
     * @param viewId
     * @param value
     * @return The ViewHoldHelper for chaining.
     */
    public ViewHoldHelper setText(int viewId, String value) {
        TextView view = getViewById(viewId);
        view.setText(value);

        return this;
    }

    /***
     * Will set the text of a TextView.
     *
     * @param viewId
     * @param resId
     * @return The ViewHoldHelper for chaining.
     */
    public ViewHoldHelper setText(int viewId, @StringRes int resId) {
        TextView view = getViewById(viewId);
        view.setText(resId);

        return this;
    }

    /***
     * Will set the text of a TextView.
     *
     * @param viewId
     * @param value
     * @return The ViewHoldHelper for chaining.
     */
    public ViewHoldHelper setText(int viewId, CharSequence value) {
        TextView view = getViewById(viewId);
        view.setText(value);

        return this;
    }

    /***
     * Will set the image of an ImageView from a resource id.
     *
     * @param viewId
     * @param imageResId
     * @return The ViewHoldHelper for chaining.
     */
    public ViewHoldHelper setImageResource(int viewId, int imageResId) {
        ImageView view = getViewById(viewId);
        view.setImageResource(imageResId);

        return this;
    }

    /***
     * Will set background color of a view.
     *
     * @param viewId
     * @param color
     * @return The ViewHoldHelper for chaining.
     */
    public ViewHoldHelper setBackgroundColor(int viewId, int color) {
        View view = getViewById(viewId);
        view.setBackgroundColor(color);

        return this;
    }

    public ViewHoldHelper setBackgroundDrawable(int viewId, Drawable drawable) {
        View view = getViewById(viewId);
        view.setBackgroundDrawable(drawable);

        return this;
    }

    /***
     * Will set background of a view.
     *
     * @param viewId
     * @param backgroundRes
     * @return The ViewHoldHelper for chaining.
     */
    public ViewHoldHelper setBackgroundRes(int viewId, int backgroundRes) {
        View view = getViewById(viewId);
        view.setBackgroundResource(backgroundRes);

        return this;
    }

    /***
     * Will set text color of a TextView.
     *
     * @param viewId
     * @param textColor
     * @return The ViewHoldHelper for chaining.
     */
    public ViewHoldHelper setTextColor(int viewId, int textColor) {
        TextView view = getViewById(viewId);
        view.setTextColor(textColor);

        return this;
    }

    /***
     * Will set text color of a TextView.
     *
     * @param viewId
     * @param textColorRes
     * @return The ViewHoldHelper for chaining.
     */
    public ViewHoldHelper setTextColorRes(int viewId, int textColorRes) {
        TextView view = getViewById(viewId);
        view.setTextColor(mContext.getResources().getColor(textColorRes));

        return this;
    }

    /***
     * Will set the image of an ImageView from a drawable.
     *
     * @param viewId
     * @param drawable
     * @return The ViewHoldHelper for chaining.
     */
    public ViewHoldHelper setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getViewById(viewId);
        view.setImageDrawable(drawable);

        return this;
    }

    /***
     * Will download an image from a URL and put it in an ImageView.
     *
     * @param viewId
     * @param imageUrl
     * @return The ViewHoldHelper for chaining.
     */
    public ViewHoldHelper setImageUrl(int viewId, String imageUrl) {
        RequestOptions options = new RequestOptions();
        options.diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .centerCrop();
        ImageView view = getViewById(viewId);
        Glide.with(mContext).load(imageUrl).apply(options).into(view);

        return this;
    }

    /***
     * Will download an image from a URL and put it in an ImageView.
     *
     * @param viewId
     * @param imageUrl
     * @return The ViewHoldHelper for chaining.
     */
    public ViewHoldHelper setImageUrl(int viewId, String imageUrl, int placeId) {
        ImageView view = getViewById(viewId);
        RequestOptions options = new RequestOptions();
        options.diskCacheStrategy(DiskCacheStrategy.RESOURCE).placeholder(placeId).centerCrop();
        Glide.with(mContext).load(imageUrl).apply(options).into(view);

        return this;
    }

    /***
     * Add an action to set the image of an image view. Can be called multiple
     * times.
     */
    public ViewHoldHelper setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = getViewById(viewId);
        view.setImageBitmap(bitmap);

        return this;
    }

    /***
     * Add an action to set the alpha of a view. Can be called multiple times.
     * Alpha between 0-1.
     */
    @SuppressLint("NewApi")
    public ViewHoldHelper setAlpha(int viewId, float value) {
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
     * @return The ViewHoldHelper for chaining.
     */
    public ViewHoldHelper setVisible(int viewId, boolean visible) {
        View view = getViewById(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);

        return this;
    }

    /***
     * Set a view visibility to VISIBLE (true) or GONE (false).
     *
     * @param viewId
     * @param visible
     * @return The ViewHoldHelper for chaining.
     */
    public ViewHoldHelper setInVisible(int viewId, boolean visible) {
        View view = getViewById(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);

        return this;
    }

    /***
     * Add links into a TextView.
     *
     * @param viewId
     * @return The ViewHoldHelper for chaining.
     */
    public ViewHoldHelper linkify(int viewId) {
        TextView view = getViewById(viewId);
        Linkify.addLinks(view, Linkify.ALL);

        return this;
    }

    /***
     * Apply the typeface to the given viewId, and enable subpixel rendering.
     *
     */
    public ViewHoldHelper setTypeface(int viewId, Typeface typeface) {
        TextView view = getViewById(viewId);
        view.setTypeface(typeface);
        view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);

        return this;
    }

    /***
     * Apply the typeface to all the given viewIds, and enable subpixel
     * rendering.
     */
    public ViewHoldHelper setTypeface(Typeface typeface, int... viewIds) {
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
     * @return The ViewHoldHelper for chaining.
     */
    public ViewHoldHelper setProgress(int viewId, int progress) {
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
     * @return The ViewHoldHelper for chaining.
     */
    public ViewHoldHelper setProgress(int viewId, int progress, int max) {
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
     * @return The ViewHoldHelper for chaining.
     */
    public ViewHoldHelper setMax(int viewId, int max) {
        ProgressBar view = getViewById(viewId);
        view.setMax(max);

        return this;
    }

    /***
     * Sets the rating (the number of stars filled) of a RatingBar.
     *
     * @param viewId
     * @param rating
     * @return The ViewHoldHelper for chaining.
     */
    public ViewHoldHelper setRating(int viewId, float rating) {
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
     * @return The ViewHoldHelper for chaining.
     */
    public ViewHoldHelper setRating(int viewId, float rating, int max) {
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
     * @return The ViewHoldHelper for chaining.
     */
    public ViewHoldHelper setTag(int viewId, Object tag) {
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
    public ViewHoldHelper setTag(int viewId, int key, Object tag) {
        View view = getViewById(viewId);
        view.setTag(key, tag);

        return this;
    }

    /***
     * Sets the checked status of a checkable.
     *
     * @param viewId
     * @param checked
     * @return The ViewHoldHelper for chaining.
     */
    public ViewHoldHelper setChecked(int viewId, boolean checked) {
        Checkable view = (Checkable) getViewById(viewId);
        view.setChecked(checked);

        return this;
    }

    /***
     * Sets the adapter of a adapter view.
     *
     * @param viewId
     * @param adapter
     * @return The ViewHoldHelper for chaining.
     */
    public ViewHoldHelper setAdapter(int viewId, Adapter adapter) {
        AdapterView<Adapter> view = getViewById(viewId);
        view.setAdapter(adapter);

        return this;
    }

    /***
     * Sets the on click listener of the view.
     *
     * @param viewId
     * @param listener
     * @return The ViewHoldHelper for chaining.
     */
    public ViewHoldHelper setOnClickListener(int viewId,
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
     * @return The ViewHoldHelper for chaining.
     */
    public ViewHoldHelper setOnTouchListener(int viewId,
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
     * @return The ViewHoldHelper for chaining.
     */
    public ViewHoldHelper setOnLongClickListener(int viewId,
                                                 View.OnLongClickListener listener) {
        View view = getViewById(viewId);
        view.setOnLongClickListener(listener);

        return this;
    }

    /***
     *  get the convertView
     *
     */
    public View getConvertView() {
        return convertView;
    }

    /***
     * get the overall position of the data in the list.
     *
     * @throws IllegalArgumentException
     */
    public int getPosition() {
        if (position == -1) {
            throw new IllegalStateException(
                    "Use ViewHoldHelper constructor with position if you need to retrieve the position.");
        }

        return position;
    }

    /***
     *  get the last converted object on this view.
     *
     */
    public Object getAssociatedObject() {
        return associatedObject;
    }

    public void setAssociatedObject(Object associatedObject) {
        this.associatedObject = associatedObject;
    }
}
