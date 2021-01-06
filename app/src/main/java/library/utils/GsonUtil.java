package library.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by Administrator on 2018/3/27.
 */

public class GsonUtil {

    // private static final Gson GSONIOS = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private static final Gson GSONIOS = new GsonBuilder().disableHtmlEscaping().create();
    private static final Gson GSON = new Gson();
    private static final String SEPARATOR = "\n";
    private static final JsonParser JSON_PARSER = new JsonParser();


    public static <T> T jsonToBeanSeparator(String msg, Class<T> t) {
        return GSON.fromJson(msg, t);
    }


    public static String beanToJsonSeparator(Object baseModel) {
        return GSON.toJson(baseModel) + SEPARATOR;
    }

    public static String iosBeanToJsonSeparator(Object baseModel) {
        return GSON.toJson(baseModel) + SEPARATOR;
    }


    /* 对象转换成Json */
    public static String beanToJson(Object obj) {
        return GSON.toJson(obj);
    }

    /* 对象转换成发送Json */
    public static String beanToSendJson(Object obj) {
        return GSON.toJson(obj) + SEPARATOR;
    }

    /* Json转换成Bean */
    public static <T> T jsonToBean(String json, Class<T> classs) {
        return GSON.fromJson(json, classs);
    }

    /* Json转换成Bean */
    public static <T> T jsonToBean(String json, Type typeOfT) {
        return GSON.fromJson(json, typeOfT);
    }

    /* Json转换成JsonObject */
    public static JsonObject jsonToJsonObject(String json) {
        return JSON_PARSER.parse(json).getAsJsonObject();
    }

    /**
     * String 转list
     */

    public static <T> List<T> parseStringList(String json, Class clazz) {
        try {
            Type type = new ParameterizedTypeImpl(clazz);
            List<T> list = new Gson().fromJson(json, type);
            return list;
        } catch (Exception e) {
            Log.e("Exception", e.getMessage());
            return new ArrayList<>();
        }
    }


    /**
     * String 转list
     */

//    public static <T> List<T> parseStringList(String json, T t) {
//        Type type = new ParameterizedTypeImpl(t.getClass());
//        List<T> list = new Gson().fromJson(json, type);
//        return list;
//    }

    private static class ParameterizedTypeImpl implements ParameterizedType {
        Class clazz;

        public ParameterizedTypeImpl(Class clz) {
            clazz = clz;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{clazz};
        }

        @Override
        public Type getRawType() {
            return List.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }

    /**
     * json string 转换为 map 对象
     *
     * @param json
     * @return
     */
    public static <T> Map<String, List<T>> jsonToMap(String json) {
        Map<String, List<T>> map = new HashMap<String, List<T>>();
        map = GSON.fromJson(json, map.getClass());
        return map;
    }


    public static Map<String, String> parseJSON2Map(String jsonStr) {
        Map<String, String> map = new HashMap<>();
        map = GSON.fromJson(jsonStr, map.getClass());
        return map;
    }

    public static Map<String, Double> jsonToMap2(String jsonStr) {
        Map<String, Double> map = new HashMap<>();
        map = GSON.fromJson(jsonStr, map.getClass());
        return map;
    }

    /**
     * 处理map 格式的字符串  并且key 为数字
     *
     * @param json
     * @param <T>
     * @return
     */
    public static <T> Map<String, List<T>> jsonToMap3(String json, T t) {
        Map<String, List<T>> stringListMap = jsonToMap(json);
        Set<String> strings = stringListMap.keySet();
        List<String> keyList = new ArrayList<>();
        keyList.addAll(strings);
        HashMap<String, List<T>> hashMap = new HashMap<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            for (int i = 0; i < keyList.size(); i++) {
                JSONArray jsonArray = jsonObject.getJSONArray(keyList.get(i));
                String s = jsonArray.toString();
                List<T> objects = parseStringList(s, t.getClass());
                hashMap.put(keyList.get(i), objects);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return hashMap;
        }
        return hashMap;
    }
}
