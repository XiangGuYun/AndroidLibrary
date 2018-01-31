package yxd.retrofit2.case_test;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by asus on 2017/12/30.
 */

public interface NewsService {

    @GET("/news/qihoo")
    Call<News> getNews(@Query("kw")String kw,
                       @Query("site")String site,
                       @Query("apikey")String apikey);

}
