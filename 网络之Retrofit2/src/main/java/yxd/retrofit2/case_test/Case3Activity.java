package yxd.retrofit2.case_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import yxd.retrofit2.R;
import yxd.retrofit2.case2_rxjava.service.RxService;
import yxd.retrofit2.case2_rxjava.service.UserInfo;

public class Case3Activity extends AppCompatActivity {

    public static final String APIKEY="JHQm5BKMIG3iqzJodxYvdbdcgz7o9nDbWcNNmXJWZbjRKzfKAuIzZAVZ7ZuRhsbg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case3);

        //commonStyle();
        rxjavaStyle();

    }

    private void rxjavaStyle() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//新的代码
                .addConverterFactory(GsonConverterFactory.create()).build();
        retrofit.create(RxNewsService.class).getNews("%E7%99%BD","qq.com",APIKEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(news -> Log.d("Test", "将News保存"+news.getAppCode()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<News>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Test", "请求失败！");
                    }

                    @Override
                    public void onNext(News news) {
                        Log.d("Test", "请求成功！");
                        assert news != null;
                        Log.d("Test", news.getDataType());
                    }
                });
    }

    private void commonStyle() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofit.create(NewsService.class)
                .getNews("%E7%99%BD","qq.com",APIKEY)
                .enqueue(new Callback<News>() {
                    @Override
                    public void onResponse(Call<News> call, Response<News> response) {
                        Log.d("Test", "请求成功！");
                        News news = response.body();
                        assert news != null;
                        Log.d("Test", news.getDataType());
                    }

                    @Override
                    public void onFailure(Call<News> call, Throwable t) {
                        Log.d("Test", "请求失败！");
                    }
                });
    }
}
