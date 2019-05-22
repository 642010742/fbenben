package library.Retrofit_Http.RequBean;

import java.io.Serializable;

import library.App.HttpConstants;
import library.Retrofit_Http.RequBean.baseBean.BaseRequestBean;


/***
 * 请求基类Bean
 *
 */
public class RequestBean implements Serializable {

    protected static final long serialVersionUID = 1L;

    private BaseRequestBean bsrqBean;
    private boolean isDownLoad;

    private String path = ""; // 方法名

    private String requestMethod = HttpConstants.METHOD_POST;

    // post 请求方式 参数拼接 query
    private boolean postQuery = false;

    public boolean isPostQuery() {
        return postQuery;
    }

    public void setPostQuery(boolean postQuery) {
        this.postQuery = postQuery;
    }

    public BaseRequestBean getBsrqBean() {
        return bsrqBean;
    }

    public void setBsrqBean(BaseRequestBean bsrqBean) {
        this.bsrqBean = bsrqBean;
    }

    public String getPath() {

        path = path.startsWith("/") ? path.substring(1, path.length()) : path;
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    // 是否是上传文件
    public boolean isDownLoad() {
        return isDownLoad;
    }

    public void setDownLoad(boolean downLoad) {
        isDownLoad = downLoad;
    }
}
