package com.dwz.mvvmdemo.presenter;

import library.Retrofit_Http.RequBean.RequestBean;
import library.commonModel.BaseModel;

/**
 * @author dongweizhou
 * @createTime 2019/7/11
 * @describe
 * @DWZ
 */
public interface Contract {

    interface IView {
        void loadData(RequestBean requestBean,int type);
    }

    interface ICallBack<T extends BaseModel>{
        void success(T t,int type);
        void failOrErr(int code,String msg);
    }
}
