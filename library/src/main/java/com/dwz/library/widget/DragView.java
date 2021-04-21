package com.dwz.library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.dwz.library.listener.ViewMoveListener;

/**
 * @author dongweizhou
 * @createTime 2019/5/9
 * @describe
 * @DWZ
 */
public class DragView extends LinearLayout {
    public float moveX;
    public float moveY;
    public int width,heigth;

    public int realW,realH;

    private ViewMoveListener viewMoveListener;

    public void setViewMoveListener(ViewMoveListener viewMoveListener) {
        this.viewMoveListener = viewMoveListener;
    }

    public int getRealW() {
        return realW;
    }

    public void setRealW(int realW) {
        this.realW = realW;
    }

    public int getRealH() {
        return realH;
    }

    public void setRealH(int realH) {
        this.realH = realH;
    }

    // 状态栏高度
    private int stateHeight = 72;
    private int bottomKeyboardHeight;
    private int layoutLeft,layoutRight;

    public int getLayoutLeft() {
        return layoutLeft;
    }

    public void setLayoutLeft(int layoutLeft) {
        this.layoutLeft = layoutLeft;
    }

    public int getLayoutRight() {
        return layoutRight;
    }

    public void setLayoutRight(int layoutRight) {
        this.layoutRight = layoutRight;
    }

    public int getStateHeight() {
        return stateHeight;
    }

    public void setStateHeight(int stateHeight) {
        this.stateHeight = stateHeight;
    }

    public int getBottomKeyboardHeight() {
        return bottomKeyboardHeight;
    }

    public void setBottomKeyboardHeight(int bottomKeyboardHeight) {
        this.bottomKeyboardHeight = bottomKeyboardHeight;
    }

    // 如果是华为手机  底部
    public DragView(Context context) {
        super(context);
        width = context.getResources().getDisplayMetrics().widthPixels;
        heigth = context.getResources().getDisplayMetrics().heightPixels;
    }
    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                moveX = event.getX();
                moveY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float v = getX() + (event.getX() - moveX);
                float y = getY() + (event.getY() - moveY);

                Log.e("zmf","==============移动的x===="+v+"==="+getX()+"=="+event.getX()+"==="+event.getRawX());
                Log.e("zmf","==============移动的Y===="+y+"===="+getY()+"==="+event.getY()+"===="+event.getRawY());

//                Log.e("dwz","===========0===="+moveY);
//                Log.e("dwz","===========1===="+moveX);
//                Log.e("dwz","===========2===="+getX());
//                Log.e("dwz","===========3===="+getY());
//                Log.e("dwz","===========4===="+v);
//                Log.e("dwz","===========5===="+y);
//                if(v>=(width-(getRight()-getLeft()))){
//                    setTranslationX(width-(getRight()-getLeft()));
//                }else if(v<=0){
//                    setTranslationX(0);
//                }else{
//                    setTranslationX(v);
//                }
//
//                if(y>=(heigth-stateHeight-bottomKeyboardHeight-(getBottom()-getTop()))){
//                    setTranslationY(heigth-stateHeight-bottomKeyboardHeight-(getBottom()-getTop()));
//                }else if(y<=0){
//                    setTranslationY(0);
//                }else{
//                    setTranslationY(y);
//                }
                // 适配华为 y-1 华为0 0 view放入位置 为0.0  其他就是安卓原生坐标系
                viewMoveListener.move(v,y-1);
                  //  setTranslationX(v);
                    //setTranslationY(y);

                break;
            case MotionEvent.ACTION_UP:
                viewMoveListener.moveUp(event.getRawX(),event.getRawY());
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }


//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        int x = (int) event.getX();
//        int y = (int) event.getY();
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN: //记录触摸点的坐标
//            moveX = x;
//            moveY = y;
//            break;
//            case MotionEvent.ACTION_MOVE: //计算偏移量
//                int offsetX = (int) (x - moveX);
//                int offsetY = (int) (y - moveY);
//                //在当前left,top,right,bottom的基础上加上便宜量
//                int left = getLeft() + offsetX;
//                int top = getTop() + offsetY;
//                int right = getRight() + offsetX;
//                int bottom = getBottom() + offsetY;
//              if((left)>=(width-(getRight()-getLeft()))){
//                  left = width-(getRight()-getLeft());
//                }else if(left<=0){
//                  left =0;
//                }else{
//
//                }
//
//                if(right>=width){
//                    right = width;
//                }else if(right<=getRight()-getLeft()){
//                    right = getRight()-getLeft();
//                }else{
//
//                }
//
//
//
//                if(top>=(heigth-stateHeight-bottomKeyboardHeight)){
//                   top = heigth-stateHeight-bottomKeyboardHeight;
//                }else if(top<=0){
//                   top = 0;
//                }else{
//
//                }
//
//                if(bottom>=(heigth-stateHeight-bottomKeyboardHeight-(getBottom()-getTop()))){
//                    bottom = heigth-stateHeight-bottomKeyboardHeight-(getBottom()-getTop());
//                }else if(bottom<=getBottom()-getTop()){
//                    bottom =getBottom()-getTop();
//                }else{
//                    // setTranslationY(y);
//                }
//                //layout(getLeft() + offsetX, getTop() + offsetY, getRight() + offsetX, getBottom() + offsetY);
//                layout(left, top, right, bottom);
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//
//        } return true;
//    }



//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//
//        int width = 0;
//        int height = 0;
//
//        int childCount = getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            View child = getChildAt(i);
//            ViewGroup.LayoutParams lp = child.getLayoutParams();
//            int childWidthSpec = getChildMeasureSpec(widthMeasureSpec, 0, lp.width);
//            int childHeightSpec = getChildMeasureSpec(heightMeasureSpec, 0, lp.height);
//            child.measure(childWidthSpec, childHeightSpec);
//        }
//
//        switch (widthMode) {
//            case MeasureSpec.EXACTLY:
//                width = widthSize;
//                break;
//            case MeasureSpec.AT_MOST:
//            case MeasureSpec.UNSPECIFIED:
//                for (int i = 0; i < childCount; i++) {
//                    View child = getChildAt(i);
//                    int widthAddOffset = child.getMeasuredWidth();
//                    width = Math.max(width, widthAddOffset);
//                }
//                break;
//            default:
//                break;
//
//        }
//
//        switch (heightMode) {
//            case MeasureSpec.EXACTLY:
//                height = heightSize;
//                break;
//            case MeasureSpec.AT_MOST:
//            case MeasureSpec.UNSPECIFIED:
//                for (int i = 0; i < childCount; i++) {
//                    View child = getChildAt(i);
//                    height = height + child.getMeasuredHeight();
//                }
//                break;
//            default:
//                break;
//
//        }
//
//        setMeasuredDimension(width, height);
  //  }

//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        super.onLayout(changed, l, t, r, b);
//        int left = 0;
//        int right = 0;
//        int top = 0;
//        int bottom = 0;
//        View childAt = getChildAt(0);
//        bottom = width-stateHeight-bottomKeyboardHeight-300;
//        top = width-300;
//        left = this.layoutLeft-144;
//        right =  this.layoutLeft+144;
//        Log.e("zmf","+++++++++++++"+top+"==="+left+"==="+right+"=="+bottom);
//        childAt.layout(left,top,right,bottom);

  //  }
}
