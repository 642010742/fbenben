package library.Retrofit_Http.inerceptor;

import java.io.IOException;

import library.Retrofit_Http.HttpTools.RxOkHttpLog;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static library.App.AppConstants.NEW_URL;

/**
 * Created by Administrator on 2017/10/9 0009.
 */

public class BaseUrlInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        //获取request
        Request request = chain.request();
        //从request中获取原有的HttpUrl实例oldHttpUrl
        HttpUrl oldHttpUrl = request.url();
        //获取request的创建者builder
        Request.Builder builder = request.newBuilder();
        HttpUrl newBaseUrl = oldHttpUrl;
        int isNewUrl = 0;

        if (oldHttpUrl.url().toString().contains("/device/open/camera/")) {
            isNewUrl = 1;
        } else {
            for (String newStr : NEW_URL) {
                if (oldHttpUrl.url().toString().contains(newStr)) {
                    isNewUrl = 2;
                }
            }
        }

        if (isNewUrl != 0) {

            String oldBasePath = oldHttpUrl.url().toString().split("/memorial-hall-app/")[1];

            if (1 == isNewUrl) {
//                newBaseUrl = HttpUrl.parse(HttpConstants.BASE_DEV_LIVE_URL + oldBasePath);
//                newBaseUrl = HttpUrl.parse(HttpConstants.BASE_DEV_VIDEO_URL + oldBasePath);
            } else if (2 == isNewUrl) {
//                newBaseUrl = HttpUrl.parse(HttpConstants.BASE_DEV_VIDEO_URL + oldBasePath);
            }

//            newBaseUrl = isNewUrl ? HttpUrl.parse(HttpConstants.BASE_DEV_URL + oldBasePath) : oldHttpUrl;
        }
        //重建新的HttpUrl，修改需要修改的url部分
        HttpUrl newFullUrl = newBaseUrl
                .newBuilder()
//                .scheme("https")//更换网络协议
                .host(newBaseUrl.host())//更换主机名
                .port(newBaseUrl.port())//更换端口
//                .removePathSegment(0)//移除第一个参数
                .build();
        //重建这个request，通过builder.url(newFullUrl).build()；
        // 然后返回一个response至此结束修改
        RxOkHttpLog.d("请求URL---->" + newFullUrl.toString());
        return chain.proceed(builder.url(newFullUrl).build());
    }
}
