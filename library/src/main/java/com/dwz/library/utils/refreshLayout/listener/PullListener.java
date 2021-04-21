package com.dwz.library.utils.refreshLayout.listener;

/**
 * @author dongweizhou
 * @createTime 2019/4/8
 * @describe
 * @DWZ
 */
public interface PullListener {
    /**
     * 下拉中
     *
     * @param refreshLayout
     * @param fraction
     */
    void onPullingDown(XRefreshLayout refreshLayout, float fraction);

    /**
     * 上拉
     */
    void onPullingUp(XRefreshLayout refreshLayout, float fraction);

    /**
     * 下拉松开
     *
     * @param refreshLayout
     * @param fraction
     */
    void onPullDownReleasing(XRefreshLayout refreshLayout, float fraction);

    /**
     * 上拉松开
     */
    void onPullUpReleasing(XRefreshLayout refreshLayout, float fraction);

    /**
     * 刷新中。。。
     */
    void onRefresh(XRefreshLayout refreshLayout);

    /**
     * 加载更多中
     */
    void onLoadMore(XRefreshLayout refreshLayout);

    /**
     * 手动调用finishRefresh或者finishLoadmore之后的回调
     */
    void onFinishRefresh();

    void onFinishLoadMore();

    /**
     * 正在刷新时向上滑动屏幕，刷新被取消
     */
    void onRefreshCanceled();

    /**
     * 正在加载更多时向下滑动屏幕，加载更多被取消
     */
    void onLoadmoreCanceled();
}
