package library.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import library.App.AppContexts;


public class SpManager {

    public static class KEY {
        public static final String token = "token";
    }

    // 登录后的配置
    private static final SharedPreferences spLogin = AppContexts.App()
            .getSharedPreferences("login", Context.MODE_PRIVATE);
    // 应用通用
    private static final SharedPreferences spApp = AppContexts.App()
            .getSharedPreferences("app", Context.MODE_PRIVATE);

    /***
     * 设置全局String
     *
     * @param key
     * @return
     */
    public static void setAppString(String key, String value) {
        spApp.edit().putString(key, value).commit();
    }

    /***
     * 获取全局String
     *
     * @param key
     * @return
     */
    public static String getAppString(String key) {
        return spApp.getString(key, "");
    }


    /**
     * 设置登录String
     * @param key
     * @param value
     */
    public static void setLString(String key, String value) {
        spLogin.edit().putString(key, value).commit();
    }

    /***
     * 获取登录String
     *
     * @param key
     * @return
     */
    public static String getLString(String key) {
        return spLogin.getString(key, "");

    }

    /**
     * 设置登录int
     * @param key
     * @param value
     */
    public static void setLInt(String key, int value) {
        spLogin.edit().putInt(key, value).commit();
    }

    /***
     * 获取登录int
     *
     * @param key
     * @return
     */
    public static int getLInt(String key) {
        return spLogin.getInt(key, 0);

    }

    /***
     * 清除全局信息
     */
    public static void clearAppInfo() {
        spApp.edit().clear().commit();
    }

    /***
     * 清除登录信息
     */
    public static void clearLoginInfo() {
        spLogin.edit().clear().commit();
    }

    /**
     * 获取登录状态
     */
    public static boolean isLogin() {
        return !TextUtils.isEmpty(SpManager.getLString(KEY.token));
    }


    /**
     * 针对复杂类型存储<对象>
     *
     * @param key    键名
     * @param object 储存的对象
     */
    public static void setObject(String key, Object object) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {

            out = new ObjectOutputStream(baos);
            out.writeObject(object);
            String objectVal = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));

            //将key加签
            if (!TextUtils.isEmpty(key))
                key = MD5.MD5(key).toUpperCase();

            SharedPreferences.Editor editor = spApp.edit();
            editor.putString(key, objectVal);
            editor.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取对象
     *
     * @param key   键名
     * @param clazz 需要获取出的对象class
     * @param <T>   对象的泛型
     * @return 对象
     */
    public static <T> T getObject(String key, Class<T> clazz) {
        //将key加签
        if (!TextUtils.isEmpty(key))
            key = MD5.MD5(key).toUpperCase();

        if (spApp.contains(key)) {
            String objectVal = spApp.getString(key, "");
            byte[] buffer = Base64.decode(objectVal, Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(bais);
                T t = (T) ois.readObject();
                return t;
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bais != null) {
                        bais.close();
                    }
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
