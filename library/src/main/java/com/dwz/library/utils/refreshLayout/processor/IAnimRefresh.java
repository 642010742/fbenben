package com.dwz.library.utils.refreshLayout.processor;

/**
 * @author dongweizhou
 * @createTime 2019/4/8
 * @describe
 * @DWZ
 */
public interface IAnimRefresh {
    void scrollHeadByMove(float moveY);
    void scrollBottomByMove(float moveY);
    void animHeadToRefresh();
    void animHeadBack(boolean isFinishRefresh);
    void animHeadHideByVy(int vy);
    void animBottomToLoad();
    void animBottomBack(boolean isFinishRefresh);
    void animBottomHideByVy(int vy);
}
