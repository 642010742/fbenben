package library.interfaces;


/**
 * @author dongweizhou
 * @createTime 2019/7/11
 * @describe
 * @DWZ
 */
public interface IPBaseCallBack {

     void onSuccess(Object oj,int type);

     void onError(int code,String msg);
}
