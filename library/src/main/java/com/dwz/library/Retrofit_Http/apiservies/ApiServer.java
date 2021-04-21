package com.dwz.library.Retrofit_Http.apiservies;


import com.dwz.library.Retrofit_Http.RequBean.ResponseBean;
import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2016/11/11.
 */

public interface ApiServer {

    @GET("{path}")
    Observable<ResponseBean> get(@Path(value = "path", encoded = true) String path, @QueryMap Map<String, String> map);

    @POST("{path}")
    @FormUrlEncoded
        //form表单提交
    Observable<ResponseBean> post(@Path(value = "path", encoded = true) String path, @FieldMap Map<String, String> map);

    @POST("{path}")
        //json 实体类提交
    Observable<ResponseBean> post(@Path(value = "path", encoded = true) String path, @Body RequestBody body);

    @PUT("{path}")
        //json 实体类提交
    Observable<ResponseBean> put(@Path(value = "path", encoded = true) String path, @Body RequestBody body);

    @DELETE("{path}")
        //json 实体类提交
    Observable<ResponseBean> delete(@Path(value = "path", encoded = true) String path);

    @Multipart
    @POST("{path}")
    Observable<ResponseBean> upLoad(@Path(value = "path", encoded = true) String path, @PartMap Map<String, RequestBody> map);

    @Streaming
    @GET("{path}")
    Observable<ResponseBody> downLoad(@Path(value = "path", encoded = true) String path);

    @GET("{path}")
    Observable<ResponseBean> get(@Url String url, @Path(value = "path", encoded = true) String path, @QueryMap Map<String, String> map);

    @POST("{path}")
    Observable<ResponseBean> post(@Url String url, @Path(value = "path", encoded = true) String path, @Body RequestBody requestBean);

    @Multipart
    @POST("{path}")
    Observable<ResponseBean> upLoad(@Url String url, @Path(value = "path", encoded = true) String path, @PartMap Map<String, RequestBody> map);


    // post 拼接
    @POST("{path}")
        //json 实体类提交
    Observable<ResponseBean> postQuery(@Path(value = "path", encoded = true) String path, @QueryMap Map<String, String> map);


    @DELETE("{path}")
        //json 实体类提交
    Observable<ResponseBean> delete(@Path(value = "path", encoded = true) String path, @QueryMap Map<String, String> map);


    @PUT("{path}")
        //json 实体类提交
    Observable<ResponseBean> put(@Path(value = "path", encoded = true) String path, @QueryMap Map<String, String> map);


    // get 拼接
    @GET("{path}")
    Observable<ResponseBean> get(@Path(value = "path", encoded = true) String path);

    @Multipart
    @POST("{path}")
    Observable<ResponseBean> upLoadFile(@Path(value = "path", encoded = true) String path, @PartMap Map<String, MultipartBody.Part> map);


    @Multipart
    @POST("{path}")
    Observable<ResponseBean> upLoadFile(@Path(value = "path", encoded = true) String path, @Part() List<MultipartBody.Part> list);
}
