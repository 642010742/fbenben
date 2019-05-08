package library.weiget;

/**
 * @author dongweizhou
 * @createTime 2019/4/3
 * @describe
 * @DWZ
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.dwz.mvvmdemo.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ErrStateView extends FrameLayout {
    public static final int VIEW_STATE_UNKNOWN = -1;
    public static final int VIEW_STATE_CONTENT = 0;
    public static final int VIEW_STATE_ERROR = 1;
    public static final int VIEW_STATE_EMPTY = 2;
    public static final int VIEW_STATE_LOADING = 3;
    private LayoutInflater mInflater;
    private View mContentView;
    private View mLoadingView;
    private View mErrorView;
    private View mEmptyView;
    private boolean mAnimateViewChanges;
    @Nullable
    private ErrStateView.StateListener mListener;
    private int mViewState;

    public ErrStateView(Context context) {
        this(context, (AttributeSet)null);
    }

    public ErrStateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mAnimateViewChanges = false;
        this.mViewState = -1;
        this.init(attrs);
    }

    public ErrStateView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mAnimateViewChanges = false;
        this.mViewState = -1;
        this.init(attrs);
    }

    private void init(AttributeSet attrs) {
        this.mInflater = LayoutInflater.from(this.getContext());
        TypedArray a = this.getContext().obtainStyledAttributes(attrs, R.styleable.ErrStateView);
        int loadingViewResId = a.getResourceId(R.styleable.ErrStateView_msv_loadingView, -1);
        if (loadingViewResId > -1) {
            this.mLoadingView = this.mInflater.inflate(loadingViewResId, this, false);
            this.addView(this.mLoadingView, this.mLoadingView.getLayoutParams());
        }

        int emptyViewResId = a.getResourceId(R.styleable.ErrStateView_msv_emptyView, -1);
        if (emptyViewResId > -1) {
            this.mEmptyView = this.mInflater.inflate(emptyViewResId, this, false);
            this.addView(this.mEmptyView, this.mEmptyView.getLayoutParams());
        }

        int errorViewResId = a.getResourceId(R.styleable.ErrStateView_msv_errorView, -1);
        if (errorViewResId > -1) {
            this.mErrorView = this.mInflater.inflate(errorViewResId, this, false);
            this.addView(this.mErrorView, this.mErrorView.getLayoutParams());
        }

        int viewState = a.getInt(R.styleable.ErrStateView_msv_viewState, 0);
        this.mAnimateViewChanges = a.getBoolean(R.styleable.ErrStateView_msv_animateViewChanges, false);
        switch(viewState) {
            case -1:
            default:
                this.mViewState = -1;
                break;
            case 0:
                this.mViewState = 0;
                break;
            case 1:
                this.mViewState = 1;
                break;
            case 2:
                this.mViewState = 2;
                break;
            case 3:
                this.mViewState = 3;
        }

        a.recycle();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mContentView == null) {
            throw new IllegalArgumentException("Content view is not defined");
        } else {
            this.setView(-1);
        }
    }

    public void addView(View child) {
        if (this.isValidContentView(child)) {
            this.mContentView = child;
        }

        super.addView(child);
    }

    public void addView(View child, int index) {
        if (this.isValidContentView(child)) {
            this.mContentView = child;
        }

        super.addView(child, index);
    }

    public void addView(View child, int index, LayoutParams params) {
        if (this.isValidContentView(child)) {
            this.mContentView = child;
        }

        super.addView(child, index, params);
    }

    public void addView(View child, LayoutParams params) {
        if (this.isValidContentView(child)) {
            this.mContentView = child;
        }

        super.addView(child, params);
    }

    public void addView(View child, int width, int height) {
        if (this.isValidContentView(child)) {
            this.mContentView = child;
        }

        super.addView(child, width, height);
    }

    protected boolean addViewInLayout(View child, int index, LayoutParams params) {
        if (this.isValidContentView(child)) {
            this.mContentView = child;
        }

        return super.addViewInLayout(child, index, params);
    }

    protected boolean addViewInLayout(View child, int index, LayoutParams params, boolean preventRequestLayout) {
        if (this.isValidContentView(child)) {
            this.mContentView = child;
        }

        return super.addViewInLayout(child, index, params, preventRequestLayout);
    }

    @Nullable
    public View getView(int state) {
        switch(state) {
            case 0:
                return this.mContentView;
            case 1:
                return this.mErrorView;
            case 2:
                return this.mEmptyView;
            case 3:
                return this.mLoadingView;
            default:
                return null;
        }
    }

    public int getViewState() {
        return this.mViewState;
    }

    public void setViewState(int state) {
        if (state != this.mViewState) {
            int previous = this.mViewState;
            this.mViewState = state;
            this.setView(previous);
            if (this.mListener != null) {
                this.mListener.onStateChanged(this.mViewState);
            }
        }

    }

    private void setView(int previousState) {
        switch(this.mViewState) {
            case 0:
            default:
                if (this.mContentView == null) {
                    throw new NullPointerException("Content View");
                }

                if (this.mLoadingView != null) {
                    this.mLoadingView.setVisibility(GONE);
                }

                if (this.mErrorView != null) {
                    this.mErrorView.setVisibility(GONE);
                }

                if (this.mEmptyView != null) {
                    this.mEmptyView.setVisibility(GONE);
                }

                if (this.mAnimateViewChanges) {
                    this.animateLayoutChange(this.getView(previousState));
                } else {
                    this.mContentView.setVisibility(VISIBLE);
                }
                break;
            case 1:
                if (this.mErrorView == null) {
                    throw new NullPointerException("Error View");
                }

                if (this.mLoadingView != null) {
                    this.mLoadingView.setVisibility(GONE);
                }

                if (this.mContentView != null) {
                    this.mContentView.setVisibility(GONE);
                }

                if (this.mEmptyView != null) {
                    this.mEmptyView.setVisibility(GONE);
                }

                if (this.mAnimateViewChanges) {
                    this.animateLayoutChange(this.getView(previousState));
                } else {
                    this.mErrorView.setVisibility(GONE);
                }
                break;
            case 2:
                if (this.mEmptyView == null) {
                    throw new NullPointerException("Empty View");
                }

                if (this.mLoadingView != null) {
                    this.mLoadingView.setVisibility(GONE);
                }

                if (this.mErrorView != null) {
                    this.mErrorView.setVisibility(GONE);
                }

                if (this.mContentView != null) {
                    this.mContentView.setVisibility(GONE);
                }

                if (this.mAnimateViewChanges) {
                    this.animateLayoutChange(this.getView(previousState));
                } else {
                    this.mEmptyView.setVisibility(VISIBLE);
                }
                break;
            case 3:
                if (this.mLoadingView == null) {
                    throw new NullPointerException("Loading View");
                }

                if (this.mContentView != null) {
                    this.mContentView.setVisibility(GONE);
                }

                if (this.mErrorView != null) {
                    this.mErrorView.setVisibility(GONE);
                }

                if (this.mEmptyView != null) {
                    this.mEmptyView.setVisibility(GONE);
                }

                if (this.mAnimateViewChanges) {
                    this.animateLayoutChange(this.getView(previousState));
                } else {
                    this.mLoadingView.setVisibility(VISIBLE);
                }
        }

    }

    private boolean isValidContentView(View view) {
        if (this.mContentView != null && this.mContentView != view) {
            return false;
        } else {
            return view != this.mLoadingView && view != this.mErrorView && view != this.mEmptyView;
        }
    }

    public void setViewForState(View view, int state, boolean switchToState) {
        switch(state) {
            case 0:
                if (this.mContentView != null) {
                    this.removeView(this.mContentView);
                }

                this.mContentView = view;
                this.addView(this.mContentView);
                break;
            case 1:
                if (this.mErrorView != null) {
                    this.removeView(this.mErrorView);
                }

                this.mErrorView = view;
                this.addView(this.mErrorView);
                break;
            case 2:
                if (this.mEmptyView != null) {
                    this.removeView(this.mEmptyView);
                }

                this.mEmptyView = view;
                this.addView(this.mEmptyView);
                break;
            case 3:
                if (this.mLoadingView != null) {
                    this.removeView(this.mLoadingView);
                }

                this.mLoadingView = view;
                this.addView(this.mLoadingView);
        }

        this.setView(-1);
        if (switchToState) {
            this.setViewState(state);
        }

    }

    public void setViewForState(View view, int state) {
        this.setViewForState(view, state, false);
    }

    public void setViewForState(@LayoutRes int layoutRes, int state, boolean switchToState) {
        if (this.mInflater == null) {
            this.mInflater = LayoutInflater.from(this.getContext());
        }

        View view = this.mInflater.inflate(layoutRes, this, false);
        this.setViewForState(view, state, switchToState);
    }

    public void setViewForState(@LayoutRes int layoutRes, int state) {
        this.setViewForState(layoutRes, state, false);
    }

    public void setAnimateLayoutChanges(boolean animate) {
        this.mAnimateViewChanges = animate;
    }

    public void setStateListener(ErrStateView.StateListener listener) {
        this.mListener = listener;
    }

    private void animateLayoutChange(@Nullable final View previousView) {
        if (previousView == null) {
            this.getView(this.mViewState).setVisibility(VISIBLE);
        } else {
            previousView.setVisibility(VISIBLE);
            ObjectAnimator anim = ObjectAnimator.ofFloat(previousView, "alpha", new float[]{1.0F, 0.0F}).setDuration(250L);
            anim.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animation) {
                    previousView.setVisibility(GONE);
                    ErrStateView.this.getView(ErrStateView.this.mViewState).setVisibility(VISIBLE);
                    ObjectAnimator.ofFloat(ErrStateView.this.getView(ErrStateView.this.mViewState), "alpha", new float[]{0.0F, 1.0F}).setDuration(250L).start();
                }
            });
            anim.start();
        }
    }

    public interface StateListener {
        void onStateChanged(int var1);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ViewState {
    }
}
