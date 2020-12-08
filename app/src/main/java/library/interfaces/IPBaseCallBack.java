package library.interfaces;


/**
 * @author dongweizhou
 * @createTime 2019/7/11
 * @describe
 * @DWZ
 */
public interface IPBaseCallBack {

    void onSuccess(int type, Object oj);

    void onError(int type, int code, String msg);
}
