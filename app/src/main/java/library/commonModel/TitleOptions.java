package library.commonModel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import androidx.databinding.Bindable;
import androidx.annotation.ColorRes;
import androidx.annotation.Dimension;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.dwz.mvvmdemo.BR;
import com.dwz.mvvmdemo.R;

import library.App.AppContexts;
import library.listener.TitleListener;
import library.utils.LogUtils;


/**
 * Created by Administrator on 2018/1/25.
 * app 头部装载类
 */

public class TitleOptions extends BaseModel {
    public static String TEXT_NULL = null;
    public static String TEXT_EMPTY = "";
    public static int SRC_NULL = -1;
    private String center_title;
    private int left_image;
    private String left_text;
    private String right_text;
    private int right_image;
    private boolean leftImage;
    private boolean rightImage;
    public int leftType = 0;
    public int rigthType = 0;
    public TitleListener titleListener;
    public Activity activity;

    // title 背景色
    @SuppressLint("ResourceType")
    @ColorRes
    public int titleBackgroudColor = AppContexts.App().getResources().getColor(R.color.ffffff);

    @Bindable
    public int getTitleBackgroudColor() {
        return titleBackgroudColor;
    }

    public void setTitleBackgroudColor(@ColorRes int titleBackgroudColor) {
        this.titleBackgroudColor = titleBackgroudColor;
        notifyPropertyChanged(BR.titleBackgroudColor);
    }

    // title 右边字体颜色
    @SuppressLint("ResourceType")
    @ColorRes
    public int rightColor = AppContexts.App().getResources().getColor(R.color.c1c1c1d);

    // title 左边颜色
    @SuppressLint("ResourceType")
    @ColorRes
    public int leftColor = AppContexts.App().getResources().getColor(R.color.f3f3f3);

    //  title 中间颜色
    @SuppressLint("ResourceType")
    @ColorRes
    public int centerColor = AppContexts.App().getResources().getColor(R.color.c1c1c1d);


    //  title 右边文字的大小
    @Dimension
    public float rightSize = AppContexts.App().getResources().getDimension(R.dimen.txt30)/AppContexts.sScale;

    //  title 左边文字的大小
    @Dimension
    public float leftSize = AppContexts.App().getResources().getDimension(R.dimen.txt30)/AppContexts.sScale;

    //  title 中间文字的大小
    @Dimension
    public float centerSize = AppContexts.App().getResources().getDimension(R.dimen.txt34)/AppContexts.sScale;


    @Bindable
    public int getCenterColor() {
        LogUtils.loge("======centerColor======"+centerColor+"===="+AppContexts.App().getResources().getColor(R.color.c1c1c1d));
        return centerColor;
    }

    public void setCenterColor(@ColorRes int centerColor) {
        this.centerColor = centerColor;
        notifyPropertyChanged(BR.centerColor);
    }

    @Bindable
    public float getCenterSize() {
        return centerSize;
    }

    public void setCenterSize(@Dimension float centerSize) {
        this.centerSize = centerSize;
        notifyPropertyChanged(BR.centerSize);
    }

    @Bindable
    public float getRightSize() {
        return rightSize;
    }

    public void setRightSize(@Dimension float rightSize) {
        this.rightSize = rightSize;
        notifyPropertyChanged(BR.rightSize);
    }

    @Bindable
    public float getLeftSize() {
        return leftSize;
    }

    public void setLeftSize(@Dimension float leftSize) {
        this.leftSize = leftSize;
        notifyPropertyChanged(BR.leftSize);
    }

    @Bindable
    public int getLeftColor() {
        return leftColor;
    }

    public void setLeftColor(@ColorRes int leftColor) {
        this.leftColor = leftColor;
        notifyPropertyChanged(BR.leftColor);
    }

    @Bindable
    public int getRightColor() {
        return rightColor;
    }

    public void setRightColor(@ColorRes int rightColor) {
        this.rightColor = rightColor;
        notifyPropertyChanged(BR.rightColor);
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setTitleListener(TitleListener titleListener) {
        this.titleListener = titleListener;
    }


    public void setRight_image(int right_image) {
        this.rightImage = true;
        this.right_image = right_image;
        notifyPropertyChanged(BR.right_image);
    }

    @Bindable
    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
        notifyPropertyChanged(BR.leftType);
    }

    @Bindable
    public int getRigthType() {
        return rigthType;
    }

    public void setRigthType(int rigthType) {
        this.rigthType = rigthType;
        notifyPropertyChanged(BR.rigthType);
    }

    @Bindable
    public int getRight_image() {
        return right_image;
    }

    public boolean isLeftImage() {
        return leftImage;
    }

    public boolean isRightImage() {
        return rightImage;
    }

    public TitleOptions() {
    }

    /**
     * 基础样式,左边返回键,中建文字
     *
     * @param center_title
     */
    public TitleOptions(String center_title) {
        this.center_title = center_title;
        this.left_image = R.mipmap.ic_launcher;
        this.left_text = TEXT_NULL;
        this.right_text = TEXT_NULL;
        this.leftImage = left_image == SRC_NULL ? false : true;
        this.rightImage = right_image == SRC_NULL ? false : true;
        this.right_image = right_image;
    }

    public TitleOptions(String center_title, int left_image, int right_image, String left_text, String right_text) {
        this.center_title = center_title;
        this.left_image = left_image;
        this.left_text = left_text;
        this.right_text = right_text;
        this.leftImage = left_image == SRC_NULL ? false : true;
        this.rightImage = right_image == SRC_NULL ? false : true;
        this.right_image = right_image;
    }


    public TitleOptions(String center_title, int left_image, int right_image, String left_text, String right_text, int leftType) {
        this.center_title = center_title;
        this.left_image = left_image;
        this.left_text = left_text;
        this.right_text = right_text;
        this.leftImage = left_image == SRC_NULL ? false : true;
        this.rightImage = right_image == SRC_NULL ? false : true;
        this.right_image = right_image;
        this.leftType = leftType;
    }

    public TitleOptions(String center_title, int left_image, int right_image, String left_text, String right_text, int rigthType, int leftType) {
        this.center_title = center_title;
        this.left_image = left_image;
        this.left_text = left_text;
        this.right_text = right_text;
        this.leftImage = left_image == SRC_NULL ? false : true;
        this.rightImage = right_image == SRC_NULL ? false : true;
        this.right_image = right_image;
        this.rigthType = rigthType;
        this.leftType = leftType;
    }


    @Bindable
    public String getCenter_title() {
        return center_title;
    }


    public void setCenter_title(String center_title) {
        this.center_title = center_title;
        notifyPropertyChanged(BR.center_title);

    }

    @Bindable
    public int getLeft_image() {
        return left_image;
    }

    public void setLeft_image(int left_image) {
        this.leftImage = true;
        this.left_image = left_image;
        notifyPropertyChanged(BR.left_image);
    }


    @Bindable
    public String getLeft_text() {
        return left_text;
    }

    public void setLeft_text(String left_text) {
        this.left_text = left_text;
        notifyPropertyChanged(BR.left_text);

    }


    @Bindable
    public String getRight_text() {
        return right_text;
    }

    public void setRight_text(String right_text) {
        this.right_text = right_text;
        notifyPropertyChanged(BR.right_text);

    }


    public void leftEvent(int type) {
        // 头部点击事件 左边的点击事件
        if (titleListener != null) {
            hideSoftKeyboard();
            titleListener.leftEvent(type);
        }
        activity.finish();
    }

    public void rigthEvent(int type) {
        if (titleListener != null) {
            hideSoftKeyboard();
            titleListener.rigthEvent(type);
        }
    }

    public void centerEvent() {
        // 头部title 中间文字的点击事件
        if (titleListener != null) {
            titleListener.centerEvent();
        }
    }


    /**
     * 关闭输入框
     */
    public void hideSoftKeyboard() {
        if (activity != null) {
            View view = activity.getWindow().peekDecorView();
            if (view != null) {
                InputMethodManager inputmanger = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

}
