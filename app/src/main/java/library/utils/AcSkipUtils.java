package library.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;


/**
 * @author dongweizhou
 * @createTime 2019/4/2
 * @describe 页面跳转工具类
 * @DWZ
 */
public class AcSkipUtils {


    public static void startActivity(Context context, Class<? extends Activity> cls){
//        if (NetworkUtils.getNetWorkState(AppContexts.App()) == NetworkUtils.NETWORK_NONE) {
//            Intent noNetIntent = new Intent(context, NetLoadErrActivity.class);
//            context.startActivity(noNetIntent);
//        }else{
            Intent intent = new Intent(context,cls);
            context.startActivity(intent);
       // }
    }


    /**
     *
     * @param context
     * @param intent
     * @param isCloseAc  是否关闭当前ac
     */
    public static void startActivity(Context context, Intent intent,boolean isCloseAc){
//        if (NetworkUtils.getNetWorkState(AppContexts.App()) == NetworkUtils.NETWORK_NONE) {
//            Intent noNetIntent = new Intent(context, NetLoadErrActivity.class);
//            context.startActivity(noNetIntent);
//        }else{
            context.startActivity(intent);
            if(isCloseAc){
                if( context instanceof Activity){
                    ((Activity) context).finish();
                }
            }
       //}
    }


    /**
     *  关闭点前ac 站内所有的ac
     * @param context
     * @param intent
     * @param isCloseAc
     */
    public static void startSingleActivity(Context context, Intent intent,boolean isCloseAc){
//        if (NetworkUtils.getNetWorkState(AppContexts.App()) == NetworkUtils.NETWORK_NONE) {
//            Intent noNetIntent = new Intent(context, NetLoadErrActivity.class);
//            context.startActivity(noNetIntent);
//        }else{
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
            if(isCloseAc){
                if( context instanceof Activity){
                    ((Activity) context).finish();
                }
            }
      //  }
    }

    /**
     *
     * @param context
     * @param intent
     * @param code
     */

    public static void startActivityForResult(Activity context, Intent intent,int code){
//        if (NetworkUtils.getNetWorkState(AppContexts.App()) == NetworkUtils.NETWORK_NONE) {
//            Intent noNetIntent = new Intent(context, NetLoadErrActivity.class);
//            context.startActivity(noNetIntent);
//        }else{
            context.startActivityForResult(intent,code);
      //  }
    }
}
