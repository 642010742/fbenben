package library.listener;

/**
 * @author dongweizhou
 * @createTime 2019/4/1
 * @describe  dialog 监听回调
 * @DWZ
 */
public interface CommonDialogListener {

    void sure(String type);
    void cancel(String type);
}
