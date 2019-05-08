package library.listener;

import android.content.Intent;

public interface IUpView {

     void updataView(Object obj, int mark);

     void pStartActivity(Intent intent, boolean isClose);

     void pStartSingleActivity(Intent intent, boolean isClose);

     void pCloseActivity();

     void pStartActivityForResult(Intent intent, int requestCode);

     void showDialog(String msg);

     void dismissDialog();

}
