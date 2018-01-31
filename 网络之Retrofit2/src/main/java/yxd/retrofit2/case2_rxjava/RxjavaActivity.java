package yxd.retrofit2.case2_rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import yxd.retrofit2.R;
import yxd.retrofit2.case2_rxjava.service.MyService;
import yxd.retrofit2.case2_rxjava.service.RxService;
import yxd.retrofit2.case2_rxjava.service.TokenService;
import yxd.retrofit2.case2_rxjava.service.UserInfo;
/*
http://www.jianshu.com/p/1fb294ec7e3b

OnNext和doOnNext都是请求成功后调用。当请求成功后会先调用doOnNext中保存用户信息的方法，
然后才去执行OnNext中的方法。若请求失败，则不会调用doOnNext和OnNext中的方法。
 */
public class RxjavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);

        traditionalStyle();//传统写法
        combineRxjavaStyle();//结合Rxjava写法
        combineRxjavaStyleWithToken();//使用token
    }

    private void combineRxjavaStyleWithToken() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//新的代码
                .addConverterFactory(GsonConverterFactory.create()).build();
        final TokenService service = retrofit.create(TokenService.class);
        service.login("11111", "22222")
                //在子线程请求获取token
                .flatMap(token -> service.getUser(token)).subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())//回调到IO线程
                .doOnNext(userInfo -> saveUserInfo(userInfo))//保存信息
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(new Subscriber<UserInfo>() {//订阅
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UserInfo userInfo) {
                        //处理用户信息
                    }
                });

    }

    private void combineRxjavaStyle() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//新的代码
                .addConverterFactory(GsonConverterFactory.create()).build();
        RxService rxService = retrofit.create(RxService.class);
        rxService.login("xiaohua", "123456")//获取Observable对象
        .subscribeOn(Schedulers.newThread())//请求在新的线程中执行
        .observeOn(Schedulers.io())//请求在新的线程中执行
        .doOnNext(new Action1<UserInfo>() {
            @Override
            public void call(UserInfo userInfo) {
                saveUserInfo(userInfo);
            }
        })
        .observeOn(AndroidSchedulers.mainThread())//最后在主线程中执行
        .subscribe(new Subscriber<UserInfo>() {
            @Override
            public void onCompleted() {
                
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(UserInfo userInfo) {

            }
        });
    }

    private void saveUserInfo(UserInfo userInfo) {
    }

    private void traditionalStyle() {
        //①构建retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("").addConverterFactory(GsonConverterFactory.create())
                .build();
        //②获得Call
        MyService myService = retrofit.create(MyService.class);
        Call<UserInfo> call = myService.login("xiaoming", "123456");
        //③异步回调
        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {

            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {

            }
        });
    }
}
