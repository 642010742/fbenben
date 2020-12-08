package library.Retrofit_Http.HttpTools;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.ConnectException;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import library.Retrofit_Http.RequBean.ResponseBean;
import library.Retrofit_Http.icallBack.ICallBack;
import library.Retrofit_Http.icallBack.ICallBackPro;
import library.utils.LogUtils;
import library.utils.ToastUtil;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;


/**
 * Created by Administrator on 2017/3/16.
 */

public class BaseObserver implements Observer<ResponseBean> {

    private String identifyString = "访问此资源需要完全的身份验证";
    private ICallBack iCallBack;
    private ICallBackPro iCallBackPro;
    private Class clazz;
    private CompositeDisposable compositeDisposable;
    private long startTime;
    private Disposable mDisposable;

    public BaseObserver(ICallBack iCallBack, Class clazz, CompositeDisposable compositeDisposable) {
        this.iCallBack = iCallBack;
        this.clazz = clazz;
        this.compositeDisposable = compositeDisposable;
    }

    public BaseObserver(ICallBackPro iCallBack, Class clazz, CompositeDisposable compositeDisposable) {
        this.iCallBackPro = iCallBack;
        this.clazz = clazz;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void onComplete() {
        if (compositeDisposable != null) {
            if (mDisposable != null) {
                compositeDisposable.delete(this.mDisposable);
            }
        }

        if (iCallBack == null) {
            iCallBackPro.onFinish();
        } else {
            iCallBack.onFinish();
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        LogUtils.loge("============http请求错误=======");
        long endTime = System.currentTimeMillis();
        //数据请求成功，只是个成功标示
        if (e.toString().contains("com.google.gson.JsonSyntaxException")) {
            return;
        }

        if (e instanceof ConnectException) {
            ToastUtil.showShort("服务器超时");
        }

        /*******************************自己加的****************************************/
        if (e instanceof HttpException) {
            HttpException exception = (HttpException) e;
            Response<?> response = exception.response();
            ResponseBody body = response.errorBody();
            try {
                String out = new String(body.bytes());
                try {
                    JSONObject jsonObject = new JSONObject(out);
                    String error_description1 = jsonObject.optString("error_description");
                    int code = jsonObject.optInt("code");
                    String msg = jsonObject.optString("msg");
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
/*******************************自己加的****************************************/
        RxOkHttpLog.d("请求失败---------->" + e.toString());
        if ((endTime - startTime) / 1000 > 5) {
            startTime = System.currentTimeMillis();
//            ToastUtil.showShort("服务器超时");
        }
        onComplete();
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (compositeDisposable != null) {
            this.mDisposable = d;
            compositeDisposable.add(d);
        }
    }

    @Override
    public void onNext(ResponseBean responseBean) {
        ResponseBean respBean = HttpDataTools.getResponseData(responseBean, clazz);
        switch (respBean.getCode()) {
            case 0:
                if (!TextUtils.isEmpty(responseBean.getOpenid())) {
                    if (iCallBack == null) {
                        iCallBackPro.onSuccess(responseBean);
                    } else {
                        iCallBack.onSuccess(responseBean);
                    }
                } else {
                    if (iCallBack == null) {
                        iCallBackPro.onError(responseBean.getCode(), responseBean.getMsg());
                    } else {
                        iCallBack.onError(responseBean.getCode(), responseBean.getMsg());
                    }
                }
                break;
            case 1:
                if (iCallBack == null) {
                    iCallBackPro.onSuccess(responseBean);
                } else {
                    iCallBack.onSuccess(responseBean);
                }
                break;
            default:
                if (iCallBack == null) {
                    iCallBackPro.onError(respBean.getCode(), respBean.getMsg());
                } else {
                    iCallBack.onError(respBean.getCode(), respBean.getMsg());
                }
                break;
        }
        if (compositeDisposable != null) {
            if (mDisposable != null) {
                compositeDisposable.delete(this.mDisposable);
            }
        }
    }

}
