package com.dwz.library.utils.refreshLayout.listener;

/**
 * @author dongweizhou
 * @createTime 2019/4/8
 * @describe
 * @DWZ
 */
public abstract class RefreshListenerAdapter implements PullListener{
    @Override
    public void onPullingDown(XRefreshLayout refreshLayout, float fraction) {
    }

    @Override
    public void onPullingUp(XRefreshLayout refreshLayout, float fraction) {
    }

    @Override
    public void onPullDownReleasing(XRefreshLayout refreshLayout, float fraction) {
    }

    @Override
    public void onPullUpReleasing(XRefreshLayout refreshLayout, float fraction) {
    }

    @Override
    public void onRefresh(XRefreshLayout refreshLayout) {
    }

    @Override
    public void onLoadMore(XRefreshLayout refreshLayout) {
    }

    @Override
    public void onFinishRefresh() {

    }

    @Override
    public void onFinishLoadMore() {

    }

    @Override
    public void onRefreshCanceled() {

    }

    @Override
    public void onLoadmoreCanceled() {

    }
}
