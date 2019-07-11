package com.dwz.mvvmdemo.vm;

import com.dwz.mvvmdemo.HttpApiPath;
import com.dwz.mvvmdemo.databinding.FragmentTestBinding;
import com.dwz.mvvmdemo.model.TestModel;
import com.dwz.mvvmdemo.presenter.Contract;
import com.dwz.mvvmdemo.presenter.TestPresenter;

import library.Retrofit_Http.RequBean.baseBean.BaseRequestBean;
import library.baseVModel.BaseVModel;
import library.utils.RequestBeanHelper;

/**
 * @author dongweizhou
 * @createTime 2019/5/8
 * @describe
 * @DWZ
 */
public class TestVModel extends BaseVModel<FragmentTestBinding> implements Contract.ICallBack<TestModel> {

    private TestPresenter testPresenter;



    @Override
    public void initPresenter() {
        testPresenter = new TestPresenter(mContext,this);
    }

    /**
     * 请求
     */
    public void requestData(){
        BaseRequestBean baseRequestBean = new BaseRequestBean();
        testPresenter.loadData(RequestBeanHelper.POST(baseRequestBean, HttpApiPath.advert),0);
    }

    @Override
    public void onSuccess(TestModel testModel, int type) {

    }

    @Override
    public void onError(int code, String msg) {

    }
}
