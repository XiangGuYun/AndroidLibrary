package yxd.retrofit2.case2_rxjava.service;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by asus on 2017/12/20.
 */
/*
第一次请求下面地址
www.test.com/login?usename=zhanghua&password=123456
返回token字符串
xnmedjfnweorcmwlqqnmvied

第二次请求下面地址
www.test.com/user?token=xnmedjfnweorcmwlqqnmvied
返回Observable<UserInfo>

 */
public interface TokenService {

    //登录，获取token
    @GET("/login")
    Observable<String> login(@Query("username") String username, @Query("password") String password);

    //根据token获取用户信息
    //baseURL+/user?token=token
    @GET("/user")
    Observable<UserInfo> getUser(@Query("token") String token);

}
