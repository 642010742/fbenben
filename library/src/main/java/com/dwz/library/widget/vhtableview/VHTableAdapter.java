package com.dwz.library.widget.vhtableview;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dwz.library.R;

import java.util.List;

import androidx.core.content.ContextCompat;

/**
 * Created by jian on 2016/7/20.
 * <p>
 * 都实现VHBaseAdapter接口
 */
public class VHTableAdapter implements VHBaseAdapter {
    private Context context;
    private List<String> titleData;
    private List<List<String>> dataList;
    private OnItemClick itemClickListener;

    public VHTableAdapter(Context context, List<String> titleData, List<List<String>> dataList) {
        this.context = context;
        this.titleData = titleData;
        this.dataList = dataList;
    }

    //表格内容的行数，不包括标题行
    @Override
    public int getContentRows() {
        return dataList.size();
    }

    //列数
    @Override
    public int getContentColumn() {
        return titleData.size();
    }

    //标题的view，这里从0开始，这里要注意，一定要有view返回去，不能为null，每一行
    // 各列的宽度就等于标题行的列的宽度，且边框的话，自己在这里和下文的表格单元格view里面设置
    @Override
    public View getTitleView(int columnPosition, ViewGroup parent) {

        TextView tv_item = new TextView(context);
        if (titleData.size() > 0) {
            //        tv_item.setBackgroundResource(R.drawable.bg_shape_gray);
            tv_item.setBackgroundColor(ContextCompat.getColor(context, R.color.f7f7f7));
            if (0 == columnPosition) {
                tv_item.setPadding(40, 40, 40, 40);
            } else {
                tv_item.setPadding(60, 40, 60, 40);
            }
            tv_item.setText(titleData.get(columnPosition));
            tv_item.setGravity(Gravity.CENTER);
            tv_item.setTextSize(12);
            tv_item.setLines(1);
            tv_item.setEllipsize(TextUtils.TruncateAt.END);
            tv_item.setTextColor(ContextCompat.getColor(context, R.color.c999999));
        }
        return tv_item;
    }


    //表格正文的view，行和列都从0开始，宽度的话在载入的时候，默认会是以标题行各列的宽度，高度的话自适应
    @Override
    public View getTableCellView(int contentRow, int contentColum, View view, ViewGroup parent) {
        Log.d("===zmf===contentRow==", contentRow + "==contentColum==" + contentColum);
        if (null == view) {
            view = new TextView(context);

        }

//        view.setBackgroundResource(R.drawable.bg_shape_gray);
        view.setBackgroundResource(R.drawable.bg_shape);
        view.setPadding(30, 30, 30, 30);
        ((TextView) view).setText(dataList.get(contentRow).get(contentColum));
        ((TextView) view).setGravity(Gravity.CENTER);
        //为了更灵活一些，在VHTableView没收做设置边框，在这里通过背景实现，我这里的背景边框是顺手设的，要是想美观点的话，对应的边框做一下对应的设置就好
        ((TextView) view).setTextColor(ContextCompat.getColor(context, R.color.c333333));
        ((TextView) view).setTextSize(13);
        ((TextView) view).setLines(1);
        ((TextView) view).setEllipsize(TextUtils.TruncateAt.END);
        return view;
    }


    @Override
    public Object getItem(int contentRow) {
        return dataList.get(contentRow);
    }


    //每一行被点击的时候的回调
    @Override
    public void OnClickContentRowItem(int row, View convertView) {
        if (itemClickListener != null) {
            itemClickListener.OnClickContentRowItem(row, convertView);
        }
        Log.d("===zmf===row==", row + "");
    }

    public void setItemClickListener(OnItemClick itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

}
