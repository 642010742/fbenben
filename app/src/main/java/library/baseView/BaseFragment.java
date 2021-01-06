package library.baseView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;


import com.dwz.mvvmdemo.BR;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import library.App.AppManager;
import library.baseVModel.BaseVModel;
import library.common.eventbus.model.EventModel;
import library.listener.IUpView;
import library.listener.RegisterEventBus;

/**
 *
 * @param <VM>
 *     fragment 的所有父类  fragment都应该集成此BaseFragment
 */
public abstract class BaseFragment<VM extends BaseVModel> extends Fragment implements RegisterEventBus, IUpView {


    protected VM vm;
    protected View rootView;

    protected Activity mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (initEvent()) {
            register();
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        init(inflater,container);
        // 解决fragment UI重复加载
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }

        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initListener();
    }

    protected void hideSoftKeyboard() {
        View view = getActivity().getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventModel event) {
        // TODO Auto-generated method stub

    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEventAsync(EventModel event) {
        // TODO Auto-generated method stub
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEventBackgroundThread(EventModel event) {
        // TODO Auto-generated method stub
    }

    @Override
    public void register() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void unRegister() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean initEvent() {
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (initEvent()) {
            unRegister();
        }
    }

    /***
     * 初始化view(加载布局)
     */
    protected abstract int getLayoutID();

    /**
     * 初始化VModel
     * @return
     */
    protected abstract Class<VM> getVModelClass();

    /**
     * 处理界面相关
     */
    public abstract void initView();

    @Override
    public void onPause() {
        super.onPause();
        hideSoftKeyboard();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){
            hideSoftKeyboard();
        }
    }

    private void init(LayoutInflater inflater, ViewGroup container) {
        try {
            rootView = inflater.inflate(getLayoutID(), container, false);
            vm = getVModelClass().newInstance();
            vm.bind = DataBindingUtil.bind(rootView);
            vm.bind.setVariable(BR.vm,vm);
            vm.setmView(this);
            vm.mContext = getActivity();
            mContext = getActivity();
        } catch (java.lang.InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void updataView(Object obj, int mark) {

    }

    @Override
    public void pStartActivity(Intent intent, boolean isClose) {
//        if (NetworkUtils.getNetWorkState(AppContexts.App()) == NetworkUtils.NETWORK_NONE) {
//            Intent noNetIntent = new Intent( getActivity(), NetLoadErrActivity.class);
//            startActivity(noNetIntent);
//        }else{
            getActivity().startActivity(intent);
            if(isClose) getActivity().finish();
       // }
    }

    @Override
    public void pStartSingleActivity(Intent intent, boolean isClose) {
//        if (NetworkUtils.getNetWorkState(AppContexts.App()) == NetworkUtils.NETWORK_NONE) {
//            Intent noNetIntent = new Intent( getActivity(), NetLoadErrActivity.class);
//            startActivity(noNetIntent);
//        }else{
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            getActivity().startActivity(intent);
            if(isClose) getActivity().finish();
        //}
    }

    @Override
    public void pCloseActivity() {
        getActivity().finish();
    }

    @Override
    public void pStartActivityForResult(Intent intent, int requestCode) {
//        if (NetworkUtils.getNetWorkState(AppContexts.App()) == NetworkUtils.NETWORK_NONE) {
//            Intent noNetIntent = new Intent( getActivity(), NetLoadErrActivity.class);
//            startActivity(noNetIntent);
//        }else{
            getActivity().startActivityForResult(intent,requestCode);
       // }
    }

    @Override
    public void showDialog(String msg) {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void initListener() {

    }
    public void clearAllActivityToLogin(){
//        AppManager.getAppManager().finishAllActivity();
//        Intent intent = new Intent(getActivity(), LoginActivity.class);
//        pStartActivity(intent, true);
    }

    public void toLogin(){
//        Intent intent = new Intent(getActivity(), LoginActivity.class);
//        pStartActivity(intent, false);
    }

}
