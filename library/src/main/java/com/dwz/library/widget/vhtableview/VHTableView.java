package com.dwz.library.widget.vhtableview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.dwz.library.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jian on 2016/7/20.
 * 可左右上下滑动的控件
 */
public class VHTableView extends LinearLayout implements HListViewScrollView.ScrollChangedListener {


    private Context context;
    private LayoutInflater inflater;
    //是否显示标题行
    private boolean showTitle;
    //第一列是否可移动
    private boolean firstColumnIsMove;
    //第二列是否可移动
    private boolean secondColumnIsMove;


    //用于显示表格正文内容
    private ListView listView;


    //存放标题行中的每一列的宽度，所有的行里的每一列都是基于标题行的每一列的宽度，都跟标题行的每一列的宽度相等
    private HashMap<String, Integer> widthMap = new HashMap<>();

    //存放所有的HScrollView
    protected List<HListViewScrollView> mHScrollViews = new ArrayList<HListViewScrollView>();


    public VHTableView(Context context) {
        this(context, null);
    }

    public VHTableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        this.inflater = LayoutInflater.from(context);
        //默认显示标题行
        showTitle = true;
        //默认第一列不可滑动
        firstColumnIsMove = false;
        //默认第二列不可滑动
        secondColumnIsMove = false;
    }

    //设置是否显示标题
    public void setShowTitle(boolean showTitle) {
        this.showTitle = showTitle;
    }

    //设置第一列是否可以滑动
    public void setFirstColumnIsMove(boolean firstColumnIsMove) {
        this.firstColumnIsMove = firstColumnIsMove;
    }

    public void setSecondColumnIsMove(boolean secondColumnIsMove) {
        this.secondColumnIsMove = secondColumnIsMove;
    }


    //设置adapter
    public void setAdapter(final VHBaseAdapter conentAdapter) {
        //清楚各原有数据
        cleanup();
        //载入标题行
        initTitles(conentAdapter);
        //载入表格正文
        initContentList(conentAdapter);
        if (!showTitle) {
            //假如设置了不显示标题行，在这里隐藏掉
            getChildAt(0).setVisibility(View.GONE);
        }

    }

    public void cleanup() {
        removeAllViews();
        widthMap.clear();
        mHScrollViews.clear();
    }

    private void initTitles(VHBaseAdapter conentAdapter) {
        View titleView = inflater.inflate(R.layout.vhtable_item_listview, this, false);
        LinearLayout ll_firstcolumn = (LinearLayout) titleView.findViewById(R.id.ll_firstcolumn);
        LinearLayout ll_secondColumn = (LinearLayout) titleView.findViewById(R.id.ll_secondColumn);
        int i = 0;

        if (firstColumnIsMove) {
            //假如设置是可移动的，则把不可移动的部分ll_firstcolumn隐藏掉，把所有数据都加在HListViewScrollView中
            ll_firstcolumn.setVisibility(View.GONE);
        } else {
            //假如是设置了第一列不可移动的，则把第一列的数据加到ll_firstcolumn中，其余的都加到HListViewScrollView中
            ll_firstcolumn.removeAllViews();
            View view = conentAdapter.getTitleView(0, ll_firstcolumn);
            //测量view的高度，都采用自适应的模式测量
            view.measure(0, 0);
            ll_firstcolumn.addView(view, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            //存起来以便设置表格正文的时候进行宽度设置
            widthMap.put("0", view.getMeasuredWidth());
            //之后的titleview就都放在CHListViewScrollView中，因为0 title已经设置了，所以从1开始
            i = 1;
        }

        if (secondColumnIsMove) {
            //假如设置是可移动的，则把不可移动的部分ll_firstcolumn隐藏掉，把所有数据都加在HListViewScrollView中
            ll_secondColumn.setVisibility(View.GONE);
        } else {
            //假如是设置了第一列不可移动的，则把第一列的数据加到ll_firstcolumn中，其余的都加到HListViewScrollView中
            ll_secondColumn.removeAllViews();
            View view = conentAdapter.getTitleView(1, ll_secondColumn);
            //测量view的高度，都采用自适应的模式测量
            view.measure(0, 0);
            ll_secondColumn.addView(view, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            //存起来以便设置表格正文的时候进行宽度设置
            widthMap.put("1", view.getMeasuredWidth());
            //之后的titleview就都放在CHListViewScrollView中，因为0 title已经设置了，所以从1开始
            i = 2;
        }


        HListViewScrollView chs_datagroup = (HListViewScrollView) titleView.findViewById(R.id.chs_datagroup);
        //把CHListViewScrollView加入管理
        addHViews(chs_datagroup);

        LinearLayout ll_datagroup = (LinearLayout) titleView.findViewById(R.id.ll_datagroup);

        ll_datagroup.removeAllViews();
        for (; i < conentAdapter.getContentColumn(); i++) {
            View view = conentAdapter.getTitleView(i, ll_datagroup);
            view.measure(0, 0);
            ll_datagroup.addView(view, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            widthMap.put(i + "", view.getMeasuredWidth());
        }
        addView(titleView);

    }


    private void initContentList(VHBaseAdapter conentAdapter) {
        listView = new ListView(context);
        //设置间隔线为0，以便在view中自己定义间隔线
        listView.setDividerHeight(0);
        ContentAdapter adapter = new ContentAdapter(conentAdapter);
        listView.setAdapter(adapter);
        addView(listView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }


    public void addHViews(final HListViewScrollView hScrollView) {
        if (!mHScrollViews.isEmpty()) {
            int size = mHScrollViews.size();
            HListViewScrollView scrollView = mHScrollViews.get(size - 1);
            final int scrollX = scrollView.getScrollX();
            //这是给第一次满屏，或者快速下滑等情况时，新创建的会再创建一个convertView的时候，把这个新进入的convertView里的HListViewScrollView移到对应的位置
            if (scrollX != 0) {
                listView.post(new Runnable() {
                    @Override
                    public void run() {
                        //在主线程中去移动到对应的位置
                        hScrollView.scrollTo(scrollX, 0);
                    }
                });
            }
        }
        hScrollView.setScrollChangedListener(this);
        mHScrollViews.add(hScrollView);
    }


    private HListViewScrollView currentTouchView;

    @Override
    public void setCurrentTouchView(HListViewScrollView currentTouchView) {
        this.currentTouchView = currentTouchView;
    }

    @Override
    public HListViewScrollView getCurrentTouchView() {
        return currentTouchView;
    }

    @Override
    public void onUIScrollChanged(int l, int t, int oldl, int oldt) {
        for (HListViewScrollView scrollView : mHScrollViews) {
            //防止重复滑动
            if (currentTouchView != scrollView)
                scrollView.smoothScrollTo(l, t);
        }
    }

    public class ContentAdapter extends BaseAdapter {
        private VHBaseAdapter conentAdapter;

        public ContentAdapter(VHBaseAdapter conentAdapter) {
            this.conentAdapter = conentAdapter;
        }

        @Override
        public int getCount() {
            return conentAdapter.getContentRows();
        }

        @Override
        public Object getItem(int position) {
            return conentAdapter.getItem(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {
            ViewHolder viewHolder;
            int maxHeight = 0;
            if (null == convertView) {
                convertView = inflater.inflate(R.layout.vhtable_item_listview, parent, false);

                HListViewScrollView chs_datagroup = (HListViewScrollView) convertView.findViewById(R.id.chs_datagroup);
                //把CHListViewScrollView加入管理
                addHViews(chs_datagroup);
                viewHolder = new ViewHolder();
                viewHolder.views = new View[conentAdapter.getContentColumn()];
                viewHolder.ll_firstcolumn = (LinearLayout) convertView.findViewById(R.id.ll_firstcolumn);
                viewHolder.ll_secondColumn = (LinearLayout) convertView.findViewById(R.id.ll_secondColumn);
                viewHolder.ll_datagroup = (LinearLayout) convertView.findViewById(R.id.ll_datagroup);
                viewHolder.rowClickListener = new RowClickListener();
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            //更新每行的views的数据
            updateViews(conentAdapter, viewHolder, viewHolder.ll_firstcolumn, viewHolder.ll_secondColumn, viewHolder.ll_datagroup, position);
            //更熟的views数据后重新测量高度，取那一行的最大高度作为整行的高度
            maxHeight = getMaxHeight(conentAdapter, viewHolder.views);
            //重新更新view到表格上
            updateUI(conentAdapter, viewHolder.ll_firstcolumn, viewHolder.ll_secondColumn, viewHolder.ll_datagroup, viewHolder.views, maxHeight);

            //为了尽可能少的影响ScrollView的触摸事件，所以点击事件这里取个巧，直接设置在这两个Linearlayout上
            viewHolder.rowClickListener.setData(conentAdapter, position, convertView);
            viewHolder.ll_firstcolumn.setOnClickListener(viewHolder.rowClickListener);
            viewHolder.ll_secondColumn.setOnClickListener(viewHolder.rowClickListener);
            viewHolder.ll_datagroup.setOnClickListener(viewHolder.rowClickListener);
            return convertView;
        }


    }


    private ViewHolder updateViews(VHBaseAdapter conentAdapter, ViewHolder viewHolder, LinearLayout ll_firstcolumn, LinearLayout ll_secondColumn, LinearLayout ll_datagroup, int row) {
        for (int i = 0; i < conentAdapter.getContentColumn(); i++) {

            if (!firstColumnIsMove && i == 0) {
                View view = conentAdapter.getTableCellView(row, 0, viewHolder.views[0], ll_firstcolumn);
                viewHolder.views[0] = view;
            } else if (!secondColumnIsMove && i == 1) {
                View view = conentAdapter.getTableCellView(row, 1, viewHolder.views[1], ll_secondColumn);
                viewHolder.views[1] = view;
            } else {
                View view = conentAdapter.getTableCellView(row, i, viewHolder.views[i], ll_datagroup);
                viewHolder.views[i] = view;
            }
        }
        return viewHolder;
    }

    private int getMaxHeight(VHBaseAdapter conentAdapter, View[] views) {
        int maxHeight = 0;
        for (int i = 0; i < conentAdapter.getContentColumn(); i++) {
            //测量模式：宽度以标题行各列的宽度为准，高度为自适应
            int w = MeasureSpec.makeMeasureSpec(widthMap.get("" + i), MeasureSpec.EXACTLY);
            int h = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
            views[i].measure(w, h);
            maxHeight = Math.max(maxHeight, views[i].getMeasuredHeight());
        }
        return maxHeight;
    }

    private void updateUI(VHBaseAdapter conentAdapter, LinearLayout ll_firstcolumn, LinearLayout ll_secondColumn, LinearLayout ll_datagroup, View[] views, int maxHeight) {
        //其实这里可以优化一下，不用remove掉全部又加一次，以后再优化一下。。。。
        ll_firstcolumn.removeAllViews();
        ll_secondColumn.removeAllViews();
        ll_datagroup.removeAllViews();
        for (int i = 0; i < conentAdapter.getContentColumn(); i++) {

            if (!firstColumnIsMove && i == 0) {
                ll_firstcolumn.addView(views[0], widthMap.get("0"), maxHeight);
            } else if (!secondColumnIsMove && i == 1) {
                ll_secondColumn.addView(views[1], widthMap.get("1"), maxHeight);
            } else {
                ll_datagroup.addView(views[i], widthMap.get("" + i), maxHeight);
            }
        }

    }

    public class RowClickListener implements OnClickListener {

        private VHBaseAdapter conentAdapter;
        private int row;
        private View convertView;

        public void setData(VHBaseAdapter conentAdapter, int row, View convertView) {
            this.conentAdapter = conentAdapter;
            this.row = row;
            this.convertView = convertView;
        }

        @Override
        public void onClick(View v) {
            if (null != conentAdapter && null != convertView) {
                conentAdapter.OnClickContentRowItem(row, convertView);
            }
        }
    }

    public class ViewHolder {
        LinearLayout ll_firstcolumn;
        LinearLayout ll_secondColumn;
        LinearLayout ll_datagroup;
        View[] views;
        RowClickListener rowClickListener;
    }


}