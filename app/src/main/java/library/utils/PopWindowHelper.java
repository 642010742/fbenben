package library.utils;

import android.app.Activity;

import androidx.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * Created by Administrator on 2018/3/6.
 */

public class PopWindowHelper {

    public static final int CANCEL_ORDER = 101;

    public static final int CHOOSE_ADDRESS = 102;

    private static PopWindowHelper popWindowHelper;

    public PopWindowHelper() {
    }

    public static PopWindowHelper getInstance() {
        if (popWindowHelper == null) {
            popWindowHelper = new PopWindowHelper();
        }
        return popWindowHelper;
    }


    public PopupWindow creatPopupWindowInitAnim(Activity activity,View view,int style){
        PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ColorDrawable dw = new ColorDrawable(0xffffffff);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.setAnimationStyle(style);
        popupWindow.setOnDismissListener(new PopupDismissListener(activity));
        popupWindow.setOutsideTouchable(true);
        return popupWindow;
    }


    class PopupDismissListener implements PopupWindow.OnDismissListener {
        private Activity activity;

        public PopupDismissListener(Activity activity) {
            this.activity = activity;
        }

        @Override
        public void onDismiss() {
            backgroundAlpha(activity, 1f);
        }
    }

     public static void backgroundAlpha(Activity activity, float bgAlpha) {
            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
            lp.alpha = bgAlpha;
            activity.getWindow().setAttributes(lp);
        }



}
