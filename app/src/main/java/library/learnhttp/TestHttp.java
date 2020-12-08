package library.learnhttp;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestHttp {
    private void testokHttp() {
        //get
        Request.Builder builder = new Request.Builder();
        builder.url("https://www.baidu.com/");
        Request request = builder.build();

        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
        try {
            Response response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //post

        FormBody.Builder builder2 = new FormBody.Builder();
        builder2.add("name", "admin");
        builder2.add("pwd", "1234156");
        FormBody build = builder2.build();

        Request.Builder builder1 = new Request.Builder();
        builder1.url("https://www.baidu.com/");
        builder1.post(build);
        Request request1 = builder1.build();
        Call call1 = new OkHttpClient().newCall(request1);
        try {
            call1.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //get
        OkHttpClient okHttpClient1 = new OkHttpClient();
        Request request2 = new Request.Builder().url("https://www.baidu.com/").build();
        try {
            Response response = okHttpClient1.newCall(request2).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //post
        OkHttpClient okHttpClient2 = new OkHttpClient();
        FormBody.Builder builder3 = new FormBody.Builder();
        builder3.add("name", "coco");
        builder3.add("ped", "dedwe");
        FormBody build2 = builder3.build();
        Request build1 = new Request.Builder().url("https://www.baidu.com/").post(build2).build();
        try {
            Response execute = okHttpClient2.newCall(build1).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(HttpConstants.BASE_DEV_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        AppService appService = retrofit.create(AppService.class);
        //无参
        appService.getAppData().enqueue(new retrofit2.Callback<List<App>>() {
            @Override
            public void onResponse(retrofit2.Call<List<App>> call, retrofit2.Response<List<App>> response) {

            }

            @Override
            public void onFailure(retrofit2.Call<List<App>> call, Throwable t) {

            }
        });

        //一个参数
        appService.getData(1).enqueue(null);
    }


}
