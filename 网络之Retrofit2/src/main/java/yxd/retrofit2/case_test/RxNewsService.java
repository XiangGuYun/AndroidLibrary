package yxd.retrofit2.case_test;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by asus on 2017/12/30.
 */

public interface RxNewsService {

    @GET("/news/qihoo")
    Observable<News> getNews(@Query("kw")String kw,
                             @Query("site")String site,
                             @Query("apikey")String apikey);

}
