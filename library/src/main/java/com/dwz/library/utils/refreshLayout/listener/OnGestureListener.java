package com.dwz.library.utils.refreshLayout.listener;

import android.view.MotionEvent;

/**
 * @author dongweizhou
 * @createTime 2019/4/8
 * @describe
 * @DWZ
 */
public interface OnGestureListener {
    void onDown(MotionEvent ev);

    void onScroll(MotionEvent downEvent, MotionEvent currentEvent, float distanceX, float distanceY);

    void onUp(MotionEvent ev, boolean isFling);

    void onFling(MotionEvent downEvent, MotionEvent upEvent, float velocityX, float velocityY);
}
