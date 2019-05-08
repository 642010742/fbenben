package library.Retrofit_Http.inerceptor;

import java.io.IOException;

import library.Retrofit_Http.HttpTools.RxOkHttpLog;
import library.utils.SpManager;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * The Http XgoLog filter
 */
public class HeaderInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        String contentType = "";
        if (original.body() != null) {
            contentType = original.body().contentType() + "";
        }

        String token = SpManager.getLString(SpManager.KEY.token);
//      String token = "bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxODIwOTI3MDc4NyIsInJvbGUiOiJST0xFX0xPR0lOIiwiaXNzIjoiZWNoaXNhbiIsImV4cCI6MTU1NDk3MzU4NiwiaWF0IjoxNTU0MzY4Nzg2fQ.EcrRBoZ50f8rhFZ0ySo8gRgKUAUG6OyKbb2SQ8btC_BNKRu5xF6Uc3cBoYN6LMDd7pGx_PIKUtGZaRm2r7Ei0g";
        RxOkHttpLog.d("token---->" + token);

        Request.Builder requestBuilder = original.newBuilder()
                .header("Authorization",token)
                .header("product", "Android")
                .header("content-type", contentType.contains("multipart/form-data;") ? contentType : "application/json")
                .method(original.method(), original.body());

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
