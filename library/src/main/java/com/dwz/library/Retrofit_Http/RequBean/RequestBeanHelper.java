package com.dwz.library.Retrofit_Http.RequBean;

import com.dwz.library.App.HttpConstants;
import com.dwz.library.Retrofit_Http.RequBean.baseBean.BaseRequestBean;

/**
 * @author dongweizhou
 * @createTime 2019/4/9
 * @describe
 * @DWZ
 */
public class RequestBeanHelper {

    public static RequestBean POST(BaseRequestBean baseRequestBean, String path, boolean... query){
        RequestBean requestBean = new RequestBean();
        requestBean.setPath(path);
        requestBean.setRequestMethod(HttpConstants.METHOD_POST);
        requestBean.setBsrqBean(baseRequestBean);
        if(query == null){
            requestBean.setPostQuery(false);
        }else{
            if(query.length>=1){
                requestBean.setPostQuery(query[0]);
            }
        }
        return requestBean;
    }


    public static RequestBean GET(BaseRequestBean baseRequestBean, String path, boolean... query){
        RequestBean requestBean = new RequestBean();
        requestBean.setPath(path);
        requestBean.setRequestMethod(HttpConstants.METHOD_GET);
        requestBean.setBsrqBean(baseRequestBean);
        if(query == null){
            requestBean.setPostQuery(false);
        }else{
            if(query.length>=1){
                requestBean.setPostQuery(query[0]);
            }
        }
        return requestBean;
    }


    public static RequestBean DELETE(BaseRequestBean baseRequestBean, String path, boolean... query){
        RequestBean requestBean = new RequestBean();
        requestBean.setPath(path);
        requestBean.setRequestMethod(HttpConstants.METHOD_DELETE);
        requestBean.setBsrqBean(baseRequestBean);
        if(query == null){
            requestBean.setPostQuery(false);
        }else{
            if(query.length>=1){
                requestBean.setPostQuery(query[0]);
            }
        }
        return requestBean;
    }


    public static RequestBean PUT(BaseRequestBean baseRequestBean, String path, boolean... query){
        RequestBean requestBean = new RequestBean();
        requestBean.setPath(path);
        requestBean.setRequestMethod(HttpConstants.METHOD_PUT);
        requestBean.setBsrqBean(baseRequestBean);
        if(query == null){
            requestBean.setPostQuery(false);
        }else{
            if(query.length>=1){
                requestBean.setPostQuery(query[0]);
            }
        }
        return requestBean;
    }

    public static RequestBean Load(BaseRequestBean baseRequestBean, String path){
        RequestBean requestBean = new RequestBean();
        requestBean.setPath(path);
        requestBean.setRequestMethod(HttpConstants.METHOD_POST);
        requestBean.setBsrqBean(baseRequestBean);
        requestBean.setDownLoad(true);
        requestBean.setPostQuery(false);
        return requestBean;
    }


    public static RequestBean POST_QUERY(BaseRequestBean baseRequestBean, String path){
        return POST(baseRequestBean,path,true);
    }


    public static RequestBean DELETE_QUERY(BaseRequestBean baseRequestBean, String path){
        return DELETE(baseRequestBean,path,true);
    }

    public static RequestBean PUT_QUERY(BaseRequestBean baseRequestBean, String path){
        return PUT(baseRequestBean,path,true);
    }

    public static RequestBean GET_QUERY(BaseRequestBean baseRequestBean, String path){
        return GET(baseRequestBean,path,true);
    }

}
