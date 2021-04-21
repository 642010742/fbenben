package com.dwz.library.Retrofit_Http.HttpTools;


import android.text.TextUtils;

import com.dwz.library.App.HttpConstants;
import com.dwz.library.Retrofit_Http.ProgressRequestBody;
import com.dwz.library.Retrofit_Http.RequBean.RequestBean;
import com.dwz.library.Retrofit_Http.RequBean.ResponseBean;
import com.dwz.library.Retrofit_Http.RequBean.baseBean.BaseUploadBean;
import com.dwz.library.Retrofit_Http.RequBean.baseBean.BaseUploadItem;
import com.dwz.library.listener.UploadProgressListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class HttpDataTools {


    /**
     * 处理请求bean
     *
     * @param bean
     * @return
     */
    public static RequestBody getPostSendData(RequestBean bean) {

        if (bean == null) {
            return RequestBody.create(HttpConstants.JSON, "");
        }
        Gson gson = new Gson();
        String sentData = gson.toJson(bean.getBsrqBean());
        // String sentData = JsonTool.toJson(bean.bsrqBean);
        // 创建一个RequestBody(参数1：数据类型 参数2传递的json串)
        RequestBody requestBody = RequestBody.create(HttpConstants.JSON, sentData);
        RxOkHttpLog.d("请求参数---->" + sentData);

        return requestBody;
    }

    /**
     * 处理请求bean
     *
     * @param bean
     * @return
     */
    public static Map<String, RequestBody> getUploadSendData(RequestBean bean, UploadProgressListener listener) {

        Map<String, RequestBody> reqMap = new HashMap<String, RequestBody> ();
        RxOkHttpLog.d("参数---->");
        if (bean.getBsrqBean() == null) {
            return reqMap;
        }
        for (BaseUploadItem item : ((BaseUploadBean) bean.getBsrqBean()).getPathList()) {
            File file = new File(item.getPath());
            if (file.exists()) {

                RxOkHttpLog.d("path-->" + item.getPath() + "--fileName-->" + item.getFileName());
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                ProgressRequestBody progressRequestBody = new ProgressRequestBody(requestBody,listener);
                //MultipartBody.Part part= MultipartBody.Part.createFormData("images",item.getFileName(),progressRequestBody);
                reqMap.put("images\";images=\""+file.getName()+"\"", progressRequestBody);
            }

        }
        return reqMap;
    }


    /**
     * 处理请求bean
     *
     * @param bean
     * @return
     */
    public static List<MultipartBody.Part> getUploadPart(RequestBean bean, UploadProgressListener listener) {

       List<MultipartBody.Part> list =new ArrayList<>();
        RxOkHttpLog.d("参数---->");
        if (bean.getBsrqBean() == null) {
            return list;
        }
        for (BaseUploadItem item : ((BaseUploadBean) bean.getBsrqBean()).getPathList()) {
            File file = new File(item.getPath());
            if (file.exists()) {

                RxOkHttpLog.d("path-->" + item.getPath() + "--fileName-->" + item.getFileName());
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                ProgressRequestBody progressRequestBody = new ProgressRequestBody(requestBody,listener);
                MultipartBody.Part part= MultipartBody.Part.createFormData("images",item.getFileName(),progressRequestBody);
                list.add(part);
            }

        }
        return list;
    }


    /**
     * 处理多文件
     */

    private RequestBody buildMultipartRequestBody(List<File> files, String filesKey, HashMap<String, String> params) {
        if (params == null) {
            params = new HashMap<>();
        }
        MultipartBody.Builder builder = new MultipartBody.Builder();
        Set<String> strings = params.keySet();
        for (String key : strings) {
            builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + key + "\""),
                    RequestBody.create(null, params.get(key)));
        }
        if (files == null) {
            files = new ArrayList<>();
        }
        int size = files.size();
        if (size == 0) {
            builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + filesKey + "\""),
                    RequestBody.create(null, "[]"));
        }
        for (int i = 0; i < size; i++) {
            //TODO 根据文件名设置contentType
            builder.addPart(Headers.of("Content-Disposition",
                    "form-data; name=\"" + filesKey + "\"; fileName=\"" + System.currentTimeMillis() + "\""),
                    RequestBody.create(MediaType.parse("multipart/form-data"), files.get(i)));
        }
        return builder.build();

    }


    /**
     * 处理请求bean
     *
     * @return
     */
    public static Map<String, String> getGetSendData(Object obj) {

        Map<String, String> map = new LinkedHashMap<>();
        if (obj == null) {
            return map;
        }
        //key值 应该是 Person类中的属性名，利用反射机制
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            String field = fields[i].toString();
            String[] keys = field.split("\\.");
            String key = keys[keys.length - 1];
            char[] cs = key.toCharArray();
            cs[0] -= 32;
            String keyUpper = String.valueOf(cs);
//            char toUpperCase = (char) (key.charAt(0) - 32);
//            String keyUpper = key.replace(key.charAt(0), toUpperCase);
            Method getMethod;
            try {
                if (!keyUpper.toLowerCase().equals("SerialVerSionUID".toLowerCase())) {

                    getMethod = obj.getClass().getDeclaredMethod("get" + keyUpper);//根据 field得到对应的get方法
                    String value = getMethod.invoke(obj) + "";
                    map.put(key, value);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        RxOkHttpLog.d("请求参数-------->" + map);
        return map;
    }

    /**
     * 处理URL改装
     *
     * @return
     */
    public static String getPath(String path) {
        // 域名后缀的方法名
//        String allowedChars = "._-$,;~()/";
//        String pathEncoded = Uri.encode(path, allowedChars);
//        RxOkHttpLog.d("请求URL---->" + (0 == baseUrlType ? HttpConstants.BASE_DEV_VIDEO_URL : HttpConstants.BASE_DEV_URL) + path);
        return path;
    }

    /***
     * 获取泛型的类型
     *
     * @param raw:要获取Type类型的目标类
     * @param args
     * @return
     */
    public static ParameterizedType type(final Class<?> raw, final java.lang.reflect.Type... args) {
        return new ParameterizedType() {
            public java.lang.reflect.Type getRawType() {
                return raw;
            }

            public java.lang.reflect.Type[] getActualTypeArguments() {
                return args;
            }

            public java.lang.reflect.Type getOwnerType() {
                return null;
            }
        };
    }

    /**
     * 处理返回数据for okhttp
     *
     * @param responseBean
     * @param clazz
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static ResponseBean getResponseData(ResponseBean responseBean, Class<?> clazz) {

        RxOkHttpLog.d("请求返回---------->" + responseBean.toString());
        if (TextUtils.isEmpty(responseBean.getData() + "")) {
            //数据请求成功，只是个成功标示
            responseBean.state = ResponseBean.N0_EMPTY;
            return responseBean;
        }
        try {
            Gson gson = new GsonBuilder().create();
            String jsonStr = gson.toJson(responseBean.getData());
//            responseBean.setCode(0);
//            responseBean.setMsg("数据解析成功！");
            if (clazz == null) {
                responseBean.setData(jsonStr);
            } else {
                responseBean.setData(gson.fromJson(jsonStr, clazz));
            }

        } catch (Exception e) {
            responseBean.state = ResponseBean.N0_EMPTY;
//            responseBean.setCode(1);
//            responseBean.setMsg("数据解析失败！");
        }

        return responseBean;
    }


}
