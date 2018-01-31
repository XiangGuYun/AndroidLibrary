package yxd.retrofit2.case1_base;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by asus on 2017/12/15.
 */
/*
思考一下：这个接口的作用是什么？
    这个接口是网络数据和实体类之间的接口
    @GET说明了请求类型是GET请求，请求路径为注解参数，{}表示占位符
    Call说明了请求回调会返回一个TestModel实体类
    @Path说明了方法传参是一个占位名为“user”的字符串
 */
public interface APIInterface {
    @GET("/user/{user}")//在此处 GET 的意思是 发送一个 GET请求，请求的地址为：baseUrl + "/users/{user}"。
    Call<TestModel> repo(@Path("user")String user);
    //{user} 类似于占位符的作用，具体类型由 repo(@Path("user") String user) 指定，这里表示 {user} 将是一段字符串。
    //Call<TestModel> 是一个请求对象，<TestModel>表示返回结果是一个 TestModel 类型的实例。
}
