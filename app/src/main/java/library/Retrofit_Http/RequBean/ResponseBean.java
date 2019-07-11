package library.Retrofit_Http.RequBean;

import com.google.gson.GsonBuilder;

import java.io.Serializable;

/***
 * 响应基类Bean
 *
 * @author cyf
 */
public class ResponseBean implements Serializable {

    /**
     *  返回为空(NULL)
     */
    public static int N0_EMPTY = -1;

    /**
     *  返回为空 (暂无数据)
     */
    public static int N0_DATA = -2;

    /**
     * 数据返回的状态
     */
    public int state;


    protected static final long serialVersionUID = 1L;

    private int code;
    private Object data;
    private String msg;
    private String url;

    //微信登录
    private String access_token;
    private String openid;
    private int errcode;
    private String errmsg;
    private String nickname;
    private String headimgurl;


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {

        String result = data + "";
        result = new GsonBuilder().serializeNulls().create().toJson(this);
        return "\ncode---->" + code
//                + "\ntraceId ---->" + traceId
                + "\nurl ---->" + url
                + "\nmsg ---->" + msg
//                + "\ndevMessage ---->" + devMessage
                + "\nResponse----->" + result;
    }
}
