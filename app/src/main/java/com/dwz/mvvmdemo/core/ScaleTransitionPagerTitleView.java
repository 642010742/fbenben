package com.dwz.mvvmdemo.core;

import android.content.Context;
import android.graphics.Typeface;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

/**
 * @author Administrator
 * @Create 2019/12/17
 * @Description TODO
 * @zmf
 */
public class ScaleTransitionPagerTitleView extends ColorTransitionPagerTitleView {

    private float mMinScale = 0.9f;

    public ScaleTransitionPagerTitleView(Context context) {
        super(context);
    }

    @Override
    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
        super.onLeave(index, totalCount, leavePercent, leftToRight);
//        setScaleX(1.0f + (mMinScale - 1.0f) * leavePercent);
//        setScaleY(1.0f + (mMinScale - 1.0f) * leavePercent);
        setTypeface(Typeface.DEFAULT);
    }

    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
        super.onEnter(index, totalCount, enterPercent, leftToRight);
//        setScaleX(mMinScale + (1.0f - mMinScale) * enterPercent);
//        setScaleY(mMinScale + (1.0f - mMinScale) * enterPercent);
        setTypeface(Typeface.DEFAULT_BOLD);
    }
}
