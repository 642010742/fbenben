package library.learnhttp;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 接口文件
 */
public interface AppService {

    /**
     * get请求
     *
     * @return
     */
    @GET(HttpApiPath.login)
    public Call<List<App>> getAppData();

    //eg http://example.com/<page>/get_data.json  拼接一个值
    @GET("{page}" + HttpApiPath.getData)
    public Call<Data> getData(@Path("page") int page);

    //eg  http://example.com/get_data.json?u=<user>&t=<token> 标准的带参的get请求
    @GET(HttpApiPath.getData)
    public Call<Data> getData(@Query("u") String user, @Query("t") String token);

    //eg  http://example.com/get_data.json/<id> 删除服务器上的数据

    @DELETE(HttpApiPath.getData + "{id}")
    public Call<ResponseBody> deleteData(@Path("id") int id);

    //eg  http://example.com/data/create上传数据到服务器
    //{"id":1,"contnet":"the description for this data"}

    @POST("data/create")
    public Call<ResponseBody> postData(@Body Data data);
}
