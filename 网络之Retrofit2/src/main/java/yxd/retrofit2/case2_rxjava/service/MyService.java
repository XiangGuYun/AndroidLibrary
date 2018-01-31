package yxd.retrofit2.case2_rxjava.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by asus on 2017/12/20.
 */
/*
baseURL+user/login?username=username&password=password
 */
public interface MyService {
    @GET("user/login")
    Call<UserInfo> login(@Query("username")String username,
                         @Query("password")String password);
}
