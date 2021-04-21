package com.dwz.library.baseVModel;

import android.app.Activity;
import com.dwz.library.commonModel.TitleOptions;
import com.dwz.library.interfaces.IPresenter;
import com.dwz.library.listener.IUpView;
import com.dwz.library.listener.TitleListener;
import java.io.Serializable;
import androidx.databinding.BaseObservable;
import androidx.databinding.ViewDataBinding;


/**
 *  页面方法实现基类
 * @param <B>
 */
public abstract class BaseVModel<B extends ViewDataBinding> extends BaseObservable implements Serializable, TitleListener, IPresenter {
    // vvm
    public B bind;

    public Activity mContext;

    //关于界面跳转 dialog 操作
    public IUpView mView;

    public TitleOptions titleOptions;

    public void setmView(IUpView iUpView) {
        this.mView = iUpView;
    }

    // 设置监听者 和activity
    public void setTitleOptions(TitleOptions titleOptions,Activity activity,BaseVModel<B> baseVModel) {
        if(titleOptions !=null){
            this.mContext = activity;
            this.titleOptions = titleOptions;
            titleOptions.setActivity(activity);
            titleOptions.setTitleListener(baseVModel);
        }
    }

    @Override
    public void leftEvent(int type) {

    }

    @Override
    public void rigthEvent(int type) {

    }

    @Override
    public void centerEvent() {

    }

}
