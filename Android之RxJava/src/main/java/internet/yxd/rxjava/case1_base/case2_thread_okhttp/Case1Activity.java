package internet.yxd.rxjava.case1_base.case2_thread_okhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import internet.yxd.rxjava.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/*
内置的Scheduler
RxJava内置了5个Scheduler：
Schedulers.immediate()：默认的，直接在当前线程运行，相当于不指定线程。
Schedulers.newThread()：总是启用新线程，并在新线程执行操作。
Schedulers.io()：I/O 操作（读写文件、读写数据库、网络信息交互等）所使用的 Scheduler。行为模式和 newThread() 差不多，
区别在于 io() 的内部实现是是用一个无数量上限的线程池，可以重用空闲的线程，因此多数情况下 io() 比 newThread() 更有效率。
Schedulers.computation()：计算所使用的 Scheduler，例如图形的计算。这个 Scheduler 使用的固定的线程池，大小为 CPU 核数。
不要把 I/O 操作放在 computation() 中，否则 I/O 操作的等待时间会浪费 CPU。
Schedulers.trampoline()：当我们想在当前线程执行一个任务时，并不是立即时，可以用.trampoline()将它入队。
这个调度器将会处理它的队列并且按序运行队列中每一个任务。
/*
RxAndroid提供的Scheduler：
AndroidSchedulers.mainThread()：RxAndroid库提供的Scheduler，它指定的操作在主线程中运行。
*/
/*
控制线程(SO)
subscribeOn()方法指定subscribe()这个方法所在的线程，即事件产生的线程。
observeOn()方法指定Subscriber回调所运行在的线程，即事件消费的线程。
Action1<String> onNextAction = new Action1<String>() {
        @Override
        public void call(String s) {
            Log.i("Test", "onNext" + s);
            Log.i("Test", "当前线程是"+Thread.currentThread().getName());

        }
};
Observable.just("杨影枫", "月眉儿")
        .subscribeOn(Schedulers.io())//事件产生在IO线程
        .observeOn(AndroidSchedulers.mainThread())//事件回到在主线程
        .subscribe(onNextAction);
 */
public class Case1Activity extends AppCompatActivity {
    //http://m.weather.com.cn/d/town/index?lat=32&lon=121
    private static final String URL = "http://m.weather.com.cn/d/town/index";
    private OkHttpClient mOkHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case3);
        postByRxJava();
    }

    private void postByRxJava(){
        getObservable(32, 121)//获取发布者
                .subscribeOn(Schedulers.io())//事件产生在子线程
                .observeOn(AndroidSchedulers.mainThread())//事件消费在主线程
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.i("Test", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("Test", e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.i("Test", s);
                        //我们将访问网络回调设置为主线程，所以Toast是能正常显示的。
                        Toast.makeText(getApplicationContext(), "请求成功", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private Observable<String> getObservable(int lat, int lon){
        Observable observable = Observable.create((Observable.OnSubscribe<String>) subscriber -> {
            mOkHttpClient=new OkHttpClient();
            RequestBody formBody = new FormBody.Builder()
                    .add("lat",lat+"")
                    .add("lon",lon+"")
                    .build();
            Request request = new Request.Builder()
                    .url(URL)
                    .post(formBody)
                    .build();
            Call call = mOkHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    subscriber.onError(new Exception("error"));
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String str = response.body().string();
                    subscriber.onNext(str);//将相应字符串交由订阅者处理
                    subscriber.onCompleted();
                }
            });
        });
        return observable;
    }

}
