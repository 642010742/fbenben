package library.presenter;

import library.Retrofit_Http.RequBean.RequestBean;
import library.commonModel.BaseModel;

/**
 * @author dongweizhou
 * @createTime 2019/7/11
 * @describe
 * @DWZ
 */
public abstract class BasePresenter {

  public abstract void loadData(RequestBean requestBean, int type, Class<? extends BaseModel> cls);

}
