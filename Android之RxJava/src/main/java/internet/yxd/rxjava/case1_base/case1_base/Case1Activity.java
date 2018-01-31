package internet.yxd.rxjava.case1_base.case1_base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import internet.yxd.rxjava.R;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;

public class Case1Activity extends AppCompatActivity {

    //观察者（类形式）
    private Subscriber<String> subscriber;
    //观察者（接口形式）
    private Observer<String> observer;
    //被观察者
    private Observable<String> observable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case1);

        //创建观察者(类形式)
        createSubscriber();

        //创建观察者(接口形式)
        createObserer();

        //创建被观察者(完整形式) create
        createObserable1();

        //创建被观察者(简化形式) just
        createObserable2();

        //订阅
        observable.subscribe(subscriber);

    }

    private void createObserable2() {
        observable = Observable.just("杨影枫", "月眉儿");
    }

    private void createObserable1() {
        observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("杨影枫");
                subscriber.onNext("月眉儿");
                subscriber.onCompleted();

            }
        });
    }

    private void createObserer() {

        observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.i("Test", "createObserer+onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("Test", "createObserer+onError");
            }

            @Override
            public void onNext(String s) {
                Log.i("Test", "createObserer+onNext" + s);
            }
        };
    }

    private void createSubscriber() {

        subscriber = new Subscriber<String>() {
            //onCompleted：事件队列完结，RxJava 不仅把每个事件单独处理，还会把它们看做一个队列。
            //当不会再有新的onNext发出时，需要触发 onCompleted() 方法作为完成标志。
            @Override
            public void onCompleted() {
                Log.i("Test","onCompleted");
            }

            //onError：事件队列异常，在事件处理过程中出异常时，onError() 会被触发，
            //同时队列自动终止，不允许再有事件发出。
            @Override
            public void onError(Throwable e) {
                Log.i("Test","onError");
            }

            //onNext：普通的事件，将要处理的事件添加到事件队列中。
            @Override
            public void onNext(String s) {
                Log.i("Test","onNext"+s);
            }

            //onStart：它会在事件还未发送之前被调用，可以用于做一些准备工作。
            //例如数据的清零或重置，这是一个可选方法，默认情况下它的实现为空。
            @Override
            public void onStart() {
                Log.i("Test","onStart");
            }

        };
    }
}
