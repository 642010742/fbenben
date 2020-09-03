package library.baseView;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import androidx.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.ColorRes;
import androidx.annotation.Dimension;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;


import com.dwz.mvvmdemo.BR;
import com.dwz.mvvmdemo.R;
import com.dwz.mvvmdemo.databinding.TitleBarBinding;
import com.dwz.mvvmdemo.ui.activity.MainActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import library.App.AppContexts;
import library.App.AppManager;
import library.baseVModel.BaseVModel;
import library.common.eventbus.model.EventModel;
import library.commonModel.TitleOptions;
import library.listener.ChangeTitleListener;
import library.listener.IUpView;
import library.listener.NoNetRefresh;
import library.listener.RegisterEventBus;
import library.utils.StatusBarUtil;


public abstract class BaseActivity<VM extends BaseVModel> extends AppCompatActivity implements ChangeTitleListener, IUpView, RegisterEventBus, NoNetRefresh {

    public VM vm = null;

    private TitleBarBinding title;
    public  TitleOptions titleOptions;
    public Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            mContext = this;
            vm = getVMClass().newInstance();
            vm.setmView(this);
            vm.mContext = this;
            title = DataBindingUtil.setContentView(this,R.layout.title_bar);
            titleOptions = title();
            vm.setTitleOptions(titleOptions,this,vm);
            typeTitle(titleOptions,getIntent());
            AppManager.getAppManager().activityCreated(this);
            this.title.setTitle(titleOptions);
            // 修改状态栏颜色
            setBarColor(initStatusBarColor());
            vm.bind =  DataBindingUtil.inflate(getLayoutInflater(),LayoutId(), (ViewGroup) this.title.getRoot(),true);
            if(titleOptions!=null)
                title.baseLayoutTitle.llBaseTitle.setVisibility(View.VISIBLE);
            else title.baseLayoutTitle.llBaseTitle.setVisibility(View.GONE);

            vm.bind.setVariable(BR.vm,vm);
           // else vm.bind =  DataBindingUtil.setContentView(this,LayoutId());
        } catch (IllegalAccessException e) {
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        initViews();
        register();
    }

    public abstract Class<VM> getVMClass();


    public abstract int LayoutId();

    public abstract void initViews();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegister();
        AppManager.getAppManager().activityDestroyed(this);
    }



    @Override
    public void typeTitle(TitleOptions titleOptions, Intent intent) {
        // 在此改变 title 内容
    }

    @Override
    public TitleOptions title() {
        return null;
    }

    /**
     * 沉浸式状态栏
     */
    public void setBarColor(int color){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(android.R.color.white));
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        StatusBarUtil.setColor(this, getResources().getColor(color));
    }



    @Override
    public int initStatusBarColor() {
        return android.R.color.white;
    }

    @Override
    public void updataView(Object obj, int mark) {

    }

    @Override
    public void pStartActivity(Intent intent, boolean isClose) {
//        if (NetworkUtils.getNetWorkState(AppContexts.App()) == NetworkUtils.NETWORK_NONE) {
//            Intent noNetIntent = new Intent(this, NetLoadErrActivity.class);
//            startActivity(noNetIntent);
//        }else{
            startActivity(intent);
            if(isClose){
                this.finish();
            }
        ///}
    }

    /**
     *
     * @param intent
     * @param isClose
     * 清除activity站内之上的activity
     */
    @Override
    public void pStartSingleActivity(Intent intent, boolean isClose) {
//        if (NetworkUtils.getNetWorkState(AppContexts.App()) == NetworkUtils.NETWORK_NONE) {
//            Intent noNetIntent = new Intent(this, NetLoadErrActivity.class);
//            startActivity(noNetIntent);
//        }else{
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            if(isClose) finish();
        //}
    }

    @Override
    public void pCloseActivity() {
        finish();
    }

    @Override
    public void pStartActivityForResult(Intent intent, int requestCode) {

//        if (NetworkUtils.getNetWorkState(AppContexts.App()) == NetworkUtils.NETWORK_NONE) {
//            Intent noNetIntent = new Intent(this, NetLoadErrActivity.class);
//            startActivity(noNetIntent);
//        }else{
            startActivityForResult(intent,requestCode);
        //}
    }

    @Override
    public void showDialog(String msg) {

    }

    @Override
    public void dismissDialog() {

    }



    @Override
    protected void onResume() {
        super.onResume();
        if (this instanceof MainActivity) {
            // 需要做下特殊处理
        }else{
            netLoadErr();
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
        if(initEvent())
        EventBus.getDefault().register(this);
    }

    @Override
    public void unRegister() {
        if(initEvent())
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean initEvent() {
        return false;
    }

    @Override
    public void refresh() {
        //刷新数据
    }

    public void setTitleRightTextColor(@ColorRes int color){
        title.baseLayoutTitle.textRight.setTextColor(getResources().getColor(color));
    }

    public void setTitleLeftTextColor(@ColorRes int color){
        title.baseLayoutTitle.textLeft.setTextColor(getResources().getColor(color));
    }

    public void setTitleCenterTextColor(@ColorRes int color){
        title.baseLayoutTitle.textTitleCenter.setTextColor(getResources().getColor(color));
    }

    public void setTitleBackgroudColor(@ColorRes int color){
        title.baseLayoutTitle.llBaseTitle.setBackgroundColor(getResources().getColor(color));
    }

    public void setTitleRightTextSize(@Dimension float size){
        title.baseLayoutTitle.textRight.setTextSize(size/ AppContexts.sScale);
    }

    public void setTitleLeftTextSize(@Dimension float size){
        title.baseLayoutTitle.textLeft.setTextSize(size/AppContexts.sScale);
    }

    public void setTitleCenterTextSize(@Dimension float size){
        title.baseLayoutTitle.textTitleCenter.setTextSize(size/AppContexts.sScale);
    }


    public void netLoadErr(){
        setBarColor(R.color.ffffff);
    }
}
