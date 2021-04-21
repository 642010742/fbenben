package com.dwz.library.transformer;

import android.view.View;

import com.dwz.library.utils.LogUtils;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

/**
 * @author Administrator
 * @Create 2019/11/27
 * @Description TODO
 * @zmf 缩小动画
 */
public class ScaleTransformer implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 0.75f;
    private static final float MIN_ALPHA = 0.5F;

    @Override
    public void transformPage(@NonNull View view, float pos) {
        LogUtils.logd("ScaleTransformer==" + view.getTag() + "==" + pos);

        //a-->b
        //a,pos(0,-1)
        //a,pos(1,0)

        //b-->a
        //a,pos(-1,0);
        //b,pos(0,1);


        if (pos < -1) {
            //[,-1]
            view.setScaleX(MIN_SCALE);
            view.setScaleY(MIN_SCALE);
            view.setAlpha(MIN_ALPHA);
        } else if (pos < 1) {
            //[-1,1]
            if (pos < 0) {//左边页面

                //a->b pos(0,-1) sacle[1,0.75]
                float scaleA = MIN_SCALE + (1 - MIN_SCALE) * (1 + pos);
                view.setScaleX(scaleA);
                view.setScaleY(scaleA);

                //b->a pos(-1,0) scale[0.75,1]
                //同上

                //透明度
                float alphaA = MIN_ALPHA + (1 - MIN_ALPHA) * (1 + pos);
                view.setAlpha(alphaA);

            } else { //右面页面
                //a->b pos(1,0) sacle[0.75,1]
                float scaleB = MIN_SCALE + (1 - MIN_SCALE) * (1 - pos);
                view.setScaleX(scaleB);
                view.setScaleY(scaleB);
                //b->a pos(0,1) scale[1,0.75]
                //同上

                //透明度
                float alphaB = MIN_ALPHA + (1 - MIN_ALPHA) * (1 - pos);
                view.setAlpha(alphaB);
            }
        } else {
            //[1,]
            view.setScaleX(MIN_SCALE);
            view.setScaleY(MIN_SCALE);
            view.setAlpha(MIN_ALPHA);
        }
    }
}
