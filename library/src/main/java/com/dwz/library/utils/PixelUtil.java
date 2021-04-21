package com.dwz.library.utils;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.WindowManager;

import com.dwz.library.App.AppContexts;


/**
 * 像素转换工具类
 * Date&Time: 2015-10-12 11:23
 */
public class PixelUtil {
	/**
	 * 得到像素密度
	 *
	 * @param context
	 * @return
	 */
	public static float getMyScale(Context context) {
		return context.getResources().getDisplayMetrics().density;
	}
	public static int dpToPx(int dpValue) {
		return (int) (dpValue * AppContexts.sScale + 0.5f);
	}
	/**
	 * 得到屏幕宽度
	 *
	 * @param context
	 * @return
	 */
	public static int getWindowWidth(Context context) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		return wm.getDefaultDisplay().getWidth();
	}

	/**
	 * 得到屏幕高度
	 *
	 * @param context
	 * @return
	 */
	public static int getWindowHeight(Context context) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		return wm.getDefaultDisplay().getHeight();
	}
	/**
	 * The context.
	 */
	private static Context mContext = AppContexts.App();

	/**
	 * dp转 px.
	 * 
	 * @param value
	 *            the value
	 * @return the int
	 */
	public static int dp2px(float value) {
		final float scale = mContext.getResources().getDisplayMetrics().densityDpi;
		return (int) (value * (scale / 160) + 0.5f);
	}

	/**
	 * dp转 px.
	 * 
	 * @param value
	 *            the value
	 * @param context
	 *            the context
	 * @return the int
	 */
	public static int dp2px(float value, Context context) {
		final float scale = context.getResources().getDisplayMetrics().densityDpi;
		return (int) (value * (scale / 160) + 0.5f);
	}

	/**
	 * px转dp.
	 * 
	 * @param value
	 *            the value
	 * @return the int
	 */
	public static int px2dp(float value) {
		final float scale = mContext.getResources().getDisplayMetrics().densityDpi;
		return (int) ((value * 160) / scale + 0.5f);
	}

	/**
	 * px转dp.
	 * 
	 * @param value
	 *            the value
	 * @param context
	 *            the context
	 * @return the int
	 */
	public static int px2dp(float value, Context context) {
		final float scale = context.getResources().getDisplayMetrics().densityDpi;
		return (int) ((value * 160) / scale + 0.5f);
	}

	/**
	 * sp转px.
	 * 
	 * @param value
	 *            the value
	 * @return the int
	 */
	public static int sp2px(float value) {
		Resources r;
		if (mContext == null) {
			r = Resources.getSystem();
		} else {
			r = mContext.getResources();
		}
		float spvalue = value * r.getDisplayMetrics().scaledDensity;
		return (int) (spvalue + 0.5f);
	}

	/**
	 * sp转px.
	 * 
	 * @param value
	 *            the value
	 * @param context
	 *            the context
	 * @return the int
	 */
	public static int sp2px(float value, Context context) {
		Resources r;
		if (context == null) {
			r = Resources.getSystem();
		} else {
			r = context.getResources();
		}
		float spvalue = value * r.getDisplayMetrics().scaledDensity;
		return (int) (spvalue + 0.5f);
	}

	/**
	 * px转sp.
	 * 
	 * @param value
	 *            the value
	 * @return the int
	 */
	public static int px2sp(float value) {
		final float scale = mContext.getResources().getDisplayMetrics().scaledDensity;
		return (int) (value / scale + 0.5f);
	}

	/**
	 * px转sp.
	 * 
	 * @param value
	 *            the value
	 * @param context
	 *            the context
	 * @return the int
	 */
	public static int px2sp(float value, Context context) {
		final float scale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (value / scale + 0.5f);
	}

	/**
	 * 描述：根据分辨率获得字体大小.
	 * 
	 * @param screenWidth
	 *            the screen width
	 * @param screenHeight
	 *            the screen height
	 * @param textSize
	 *            the text size
	 * @return the int
	 */
	public static int resizeTextSize(int screenWidth, int screenHeight,
			int textSize) {
		float ratio = 1;
		try {
			float ratioWidth = (float) screenWidth / 480;
			float ratioHeight = (float) screenHeight / 800;
			ratio = Math.min(ratioWidth, ratioHeight);
		} catch (Exception e) {
		}
		return Math.round(textSize * ratio);
	}

	/**
	 * 测量控件的尺寸
	 * 
	 * @param view
	 */
	public static void calcViewMeasure(View view) {
		// int width =
		// View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
		// int height =
		// View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
		// view.measure(width,height);

		int width = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		int expandSpec = View.MeasureSpec.makeMeasureSpec(
				Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
		view.measure(width, expandSpec);
	}

	/**
	 * 获取控件的高度，如果获取的高度为0，则重新计算尺寸后再返回高度
	 * 
	 * @param view
	 * @return
	 */
	public static int getViewMeasuredHeight(View view) {
		// int height = view.getMeasuredHeight();
		// if(0 < height){
		// return height;
		// }
		calcViewMeasure(view);
		return view.getMeasuredHeight();
	}

}
