package library.utils;

import android.app.Activity;
import android.view.WindowManager;

/**
 * @author Administrator
 * @Create 2019/4/1
 * @Description TODO
 * @zmf
 */
public class BackGroundUtils {

    public static void backgroundAlpha(Activity activity, float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        activity.getWindow().setAttributes(lp);
    }
}
