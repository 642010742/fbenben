package com.dwz.library.utils.pop;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import com.dwz.library.R;
import com.dwz.library.databinding.LayoutBottomDialogBinding;
import com.dwz.library.databinding.PopLoginoutBinding;
import com.dwz.library.utils.BackGroundUtils;
import androidx.databinding.DataBindingUtil;


public class PopUtils {

    /**
     * 选择图片
     * @param activity
     */
    public static void showSelectPhotoPop(final Activity activity) {
        View view = View.inflate(activity, R.layout.layout_bottom_dialog, null);
        LayoutBottomDialogBinding bind = DataBindingUtil.bind(view);

        final PopupWindow pop = new PopupWindow(bind.getRoot());
        pop.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        pop.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pop.setOutsideTouchable(true);
        //弹出窗体可点击
        pop.setFocusable(true);
        BackGroundUtils.backgroundAlpha(activity, 0.8f);
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                BackGroundUtils.backgroundAlpha(activity, 1.0f);
            }
        });
        pop.setAnimationStyle(R.style.AnimationBottomFade);
        pop.showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);

        bind.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
            }
        });
    }

    /**
     *0-注销账户
     * 1-退出登录
     * @param activity
     * @param popType
     * @param iSelectListener
     */
    public static void showLoginOutPop(final Activity activity, int popType, final ISelectListener iSelectListener) {
        View view = View.inflate(activity, R.layout.pop_loginout, null);
        PopLoginoutBinding bind = DataBindingUtil.bind(view);

        final PopupWindow pop = new PopupWindow(bind.getRoot());
        pop.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        pop.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pop.setOutsideTouchable(true);
        //弹出窗体可点击
        pop.setFocusable(true);
        BackGroundUtils.backgroundAlpha(activity, 0.8f);
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                BackGroundUtils.backgroundAlpha(activity, 1.0f);
            }
        });
        pop.setAnimationStyle(R.style.AnimationBottomFade);
        pop.showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);

        bind.content.setText(0 == popType ? "注销账户" : "退出登录");
        bind.warning.setVisibility(0 == popType ? View.VISIBLE : View.GONE);

        bind.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iSelectListener.select();
                pop.dismiss();
            }
        });
        bind.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
            }
        });
    }
}
