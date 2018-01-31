package yxd.activity.base.thread;

import android.os.Message;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by asus on 2016/7/21.
 *
 */
public class RxJava {

    private static Observable observable;
    private static RxJava rxJava;

    public static RxJava get(){
        if(rxJava == null){
            rxJava = new RxJava();
        }
        return rxJava;
    }

    public static <T extends Object> Message getMsg(T t){
        Message message = Message.obtain();
        message.obj = t;
        return message;
    }

    public static void thread(){
        RxJava.thread(msg -> {

        }, sub -> {

        });
    }


    public static <T extends Object> Message getMsg(T t, int what){
        Message message = Message.obtain();
        message.obj = t;
        message.what = what;
        return message;
    }

    public static Message getEmptyMsg(int what){
        Message message = Message.obtain();
        message.what = what;
        return message;
    }

    public static void sleep(long time){
        try {
            java.lang.Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void thread(final Thread thread){
        Observable.create(new Observable.OnSubscribe<Message>() {
            @Override
            public void call(Subscriber<? super Message> subscriber) {
                thread.inNewThread(subscriber);
            }
        }).subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()
        ).subscribe(new Action1<Message>() {

            @Override
            public void call(Message msg) {
                thread.inMainThread(msg);
            }
        });
    }

    public static void thread(final MainThread mainThread, final NewThread newThread){
        Observable.create(new Observable.OnSubscribe<Message>() {
            @Override
            public void call(Subscriber<? super Message> subscriber) {
                newThread.inNewThread(subscriber);
            }
        }).subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()
        ).subscribe(new Action1<Message>() {

            @Override
            public void call(Message msg) {
                mainThread.inMainThread(msg);
            }
        });
    }





    public static RxJava sub(NewThread newThread){
        observable = Observable.create(new Observable.OnSubscribe<Message>() {
            @Override
            public void call(Subscriber<? super Message> subscriber) {
                newThread.inNewThread(subscriber);
            }
        }).subscribeOn(Schedulers.io());
        return rxJava;
    }

    public RxJava main(MainThread mainThread){
        if(observable == null) return null;
        observable.observeOn(
                AndroidSchedulers.mainThread()
        ).subscribe(new Action1<Message>() {
            @Override
            public void call(Message msg) {
                mainThread.inMainThread(msg);
            }
        });
        return rxJava;
    }


    public  interface MainThread{
        void inMainThread(Message msg);
    }

    public interface NewThread{
        void inNewThread(Subscriber sub);
    }

    public interface Thread{
        void inMainThread(Message msg) ;
        void inNewThread(Subscriber sub);
    }

}


