package library.baseVModel;

import android.app.Activity;
import androidx.databinding.BaseObservable;
import androidx.databinding.ViewDataBinding;

import library.interfaces.IPresenter;

import library.commonModel.TitleOptions;
import library.listener.IUpView;
import library.listener.TitleListener;

import java.io.Serializable;

/**
 *  页面方法实现基类
 * @param <B>
 */
public abstract class BaseVModel<B extends ViewDataBinding> extends BaseObservable implements Serializable,TitleListener, IPresenter {
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
