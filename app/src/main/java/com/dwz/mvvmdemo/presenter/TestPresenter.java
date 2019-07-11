package com.dwz.mvvmdemo.presenter;
import android.content.Context;

import com.dwz.mvvmdemo.model.TestModel;

import library.Retrofit_Http.RequBean.RequestBean;
import library.Retrofit_Http.RequBean.ResponseBean;
import library.Retrofit_Http.RxRetrofitClient;
import library.Retrofit_Http.icallBack.ICallBack;


/**
 * @author dongweizhou
 * @createTime 2019/7/11
 * @describe
 * @DWZ
 */
public class TestPresenter implements Contract.IView{


    private Context context;

    private Contract.ICallBack<TestModel> modelICallBack;


    public TestPresenter(Context context, Contract.ICallBack<TestModel> modelICallBack) {
        this.context = context;
        this.modelICallBack = modelICallBack;
    }

    @Override
    public void loadData(final RequestBean requestBean, final int type) {
        RxRetrofitClient.getClient().execute(requestBean, TestModel.class, new ICallBack(context,true) {
            @Override
            public void onSuccess(ResponseBean responseBean) {
                if (responseBean.state != ResponseBean.N0_EMPTY) {
                    modelICallBack.success((TestModel) responseBean.getData(),type);
                }
            }

            @Override
            public void onError(int code, String msg) {
                modelICallBack.failOrErr(code,msg);
            }
        });
    }

}
