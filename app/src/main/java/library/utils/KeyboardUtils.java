package library.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

public class KeyboardUtils {
	/**
	 * 隐藏软键盘
	 * 
	 * @param activity
	 */
	public static void HiddenSoftInputMode(Activity activity) {
		activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	}

	/**
	 * 隐藏软键盘
	 * 
	 * @param activity
	 */
	public static void toggleSofchalkeyboard(Activity activity) {
		InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
	}

	/**
	 * 显示软键盘
	 * 
	 * @param activity
	 */
	public static void showKeywordMethod(View v, Activity activity) {
		InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
	}

	/**
	 * 隐藏或显示软键盘
	 * 如果现在是显示调用后则隐藏 反之则显示
	 * @param activity
	 */
	public static void showORhideSoftKeyboard(Activity activity){
		InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
	}


	/**
	 * 清除当前焦点
	 * 
	 * @param activity
	 */
	public static void clearCurrentFocus(Activity activity) {
		View focusView = activity.getCurrentFocus();
		if (focusView != null) {
			focusView.clearFocus();
		}
	}

	public static void hideKeywordMethod(Activity activity) {
		InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
		View focusView = activity.getCurrentFocus();
		if (focusView != null) {
			imm.hideSoftInputFromWindow(focusView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	
	public static void hideKeywordMethod(Activity activity, View v) {
		InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (v != null) {
			imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	/***
	 * 布局平移（解决输入法覆盖布局请问题）
	 * 
	 * @author lxy
	 */
	public static void controlKeyboardLayout(final View root, final View scrollToView) {
		root.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				Rect rect = new Rect();
				// 获取root在窗体的可视区域
				root.getWindowVisibleDisplayFrame(rect);
				// 获取root在窗体的不可视区域高度(被其他View遮挡的区域高度)
				int rootInvisibleHeight = root.getRootView().getHeight() - rect.bottom;
				// 若不可视区域高度大于100，则键盘显示
				if (rootInvisibleHeight > 100) {
					int[] location = new int[2];
					// 获取scrollToView在窗体的坐标
					scrollToView.getLocationInWindow(location);
					// 计算root滚动高度，使scrollToView在可见区域的底部
					int srollHeight = (location[1] + scrollToView.getHeight()) - rect.bottom;
					root.scrollTo(0, srollHeight);
				} else {
					// 键盘隐藏
					root.scrollTo(0, 0);
				}
			}
		});
	}

	public static void backgroundAlpha(Activity activity, float bgAlpha) {
		WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
		lp.alpha = bgAlpha;
		activity.getWindow().setAttributes(lp);
	}
}
