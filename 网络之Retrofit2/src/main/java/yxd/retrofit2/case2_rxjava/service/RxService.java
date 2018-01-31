package yxd.retrofit2.case2_rxjava.service;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by asus on 2017/12/20.
 */
/*
唯一的区别是将Call换成了Observable
 */
public interface RxService {
    @GET("user/login")
    Observable<UserInfo> login(
            @Query("username")String username,
            @Query("password")String password
    );
}
