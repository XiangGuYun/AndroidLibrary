package internet.yxd.rxjava.case1_base.case1_base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import internet.yxd.rxjava.R;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

public class Case2Activity extends AppCompatActivity {

    private Observable<String> observable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case2);

        observable = Observable.just("杨影枫", "月眉儿");

         /*
        不完整定义回调
        Action1:表示回调函数中有一个参数的动作
        Action0:表示回调函数中没有参数的动作
         */
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {Log.i("Test", "onNext" + s);}
        };

        Action1<Throwable> onErrorAction = throwable -> {};

        Action0 onCompletedAction = new Action0() {
            @Override
            public void call() {Log.d("Test", "onCompleted");}
        };

        observable.subscribe(onNextAction,onErrorAction,onCompletedAction);

        /*
        简写形式
        根据需要选择对应的subscribe方法
         */
        Observable.just("A1","B1").subscribe(onNextAction);
        Observable.just("A1","B1").subscribe(onNextAction, onErrorAction);
        Observable.just("A1","B1").subscribe(onNextAction, onErrorAction, onCompletedAction);
    }
}
