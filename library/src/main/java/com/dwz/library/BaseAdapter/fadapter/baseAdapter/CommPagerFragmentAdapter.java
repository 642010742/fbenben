package com.dwz.library.BaseAdapter.fadapter.baseAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


/***
 * BasicAdapter in which you only need to provide the convert() implementation.
 *
 */

public class CommPagerFragmentAdapter extends FragmentPagerAdapter {

    protected final Context mContext;

    private List<Fragment> viewList;
    private FragmentManager fm;

    // 点击监听事件（可以针对item中任意控件）
    protected OnItemClickListener onClickListener;


    //	protected boolean displayIndeterminateProgress = false;
    public interface OnItemClickListener {
        void onClick(View v, int position, String type);
    }

    public void setOnClickListener(OnItemClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public CommPagerFragmentAdapter(Context mContext, FragmentManager fm, List<Fragment> viewList) {
        super(fm);
        this.mContext = mContext;
        this.viewList = viewList;
        this.fm = fm;
        fm.beginTransaction().commitAllowingStateLoss();
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public Fragment getItem(int position) {

        return this.viewList.get(position);
    }


    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}