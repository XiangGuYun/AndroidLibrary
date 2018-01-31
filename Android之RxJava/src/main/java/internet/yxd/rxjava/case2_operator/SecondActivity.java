package internet.yxd.rxjava.case2_operator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import internet.yxd.rxjava.R;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.exceptions.Exceptions;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.GroupedObservable;
import rx.schedulers.Schedulers;

/*
http://blog.csdn.net/maplejaw_/article/details/52396175
Observable:可观察的，即被观察者，发布者
Subscriber：订阅者
 */
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

    }

    /*
    HelloWorld示例，阐述原理
     */
    public void onHelloWorld(View view) {
        Observable<String> myObservable = Observable.create(//创建发布者
                new Observable.OnSubscribe<String>() {//订阅事件
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        sub.onNext("Hello, world!");//发布信息
                        sub.onCompleted();//发布完成
                    }
                }
        );

        Subscriber<String> mySubscriber = new Subscriber<String>() {//创建订阅者
            @Override
            public void onNext(String s) { Log.d("Test", s); }//处理发布的信息

            @Override
            public void onCompleted() { }//处理完成

            @Override
            public void onError(Throwable e) { }//处理错误
        };

        myObservable.subscribe(mySubscriber);//产生订阅关系
    }

    /*
    简化版的HelloWorld
     */
    public void onSimpleHelloWorld(View view) {
        //just:只发布一条信息就完成
        Observable<String> myObservable = Observable.just("Hello, world!");
        //Action1：只有onNext这么一个动作
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d("Test", s);
            }
        };
        myObservable.subscribe(onNextAction);
        //myObservable.subscribe(onNextAction, onErrorAction, onCompleteAction);
    }

    /*
    使用了Lambda表达式
     */
    public void onSimpleLambdaHelloWorld(View view) {
        Observable.just("Hello, world!").subscribe(s -> Log.d("Test", s));
    }

    /*
    map操作符：
    1.返回的对象仍然是发布者
    2.对发布的信息进行修改，不仅可以修改内容，也可以修改类型
     */
    public void map(View view) {
        /*
        修改内容
         */
//        Observable.just("Hello, world!")
//                .map(new Func1<String, String>() {
//                    @Override
//                    public String call(String s) {
//                        return s + " -Dan";
//                    }
//                })
//                .subscribe(s -> Log.d("Test", s));
        Observable.just("Hello, world!")
                .map(s -> s + " -Dan")
                .subscribe(s -> Log.d("Test", s));
        /*
        修改类型
         */
//        Observable.just("Hello, world!")
//                .map(new Func1<String, Integer>() {
//                    @Override
//                    public Integer call(String s) {
//                        return s.hashCode();
//                    }
//                })
//                .subscribe(i -> System.out.println(Integer.toString(i)));
        Observable.just("Hello, world!")
                .map(s -> s.hashCode())
                .subscribe(i -> System.out.println(Integer.toString(i)));
    }

    //----------------------------------------------------------------------------------------------
    //创建操作符
    //----------------------------------------------------------------------------------------------

    /*
    将一个Iterable, 一个Future, 或者一个数组，内部通过代理的方式转换成一个Observable。
    Future转换为OnSubscribe是通过OnSubscribeToObservableFuture进行的，
    Iterable转换通过OnSubscribeFromIterable进行。数组通过OnSubscribeFromArray转换
     */
    public void from(View v){
        //Iterable
        List<String> list=new ArrayList<>();

        Observable.from(list)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {

                    }
                });

        //Future
        Future<String> futrue= Executors.newSingleThreadExecutor().submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return "maplejaw";
            }
        });

        Observable.from(futrue)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {

                    }
                });
    }

    /*
    timer操作符 执行定时（延时）任务
    创建一个在给定的延时之后发射数据项为0的Observable<Long>,内部通过OnSubscribeTimerOnce工作
     */
    public void timer(View view) {
//        Observable.timer(1000, TimeUnit.MILLISECONDS)
//                .subscribe(new Action1<Long>() {
//                    @Override
//                    public void call(Long aLong) {
//                        Log.d("Test",aLong.toString()); // 0
//                    }
//                });
        Observable.timer(1000, TimeUnit.MILLISECONDS)
                .subscribe(aLong -> {
                    Log.d("Test",aLong.toString()); // 0
                });
    }

    /*
    使用Rxjava来完成按钮监听
     */
    public void test(View view) {
//        Observable.just(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        }).subscribe(new Action1<View.OnClickListener>() {
//            @Override
//            public void call(View.OnClickListener onClickListener) {
//                view.setOnClickListener(onClickListener);
//            }
//        });
        Observable.just((View.OnClickListener) v -> {
            Toast.makeText(this, "点击了测试按钮", Toast.LENGTH_SHORT).show();
        }).subscribe(view::setOnClickListener);

//        view.setOnClickListener(v ->
//                Toast.makeText(v.getContext(), "点击了测试按钮", Toast.LENGTH_SHORT).show());

    }

    /*
    interval操作符 执行周期（循环）任务
    创建一个按照给定的时间间隔发射从0开始的整数序列的Observable<Long>，内部通过OnSubscribeTimerPeriodically工作
     */
    public void interval(View view) {
//        Observable.interval(1, TimeUnit.SECONDS)
//                .subscribe(new Action1<Long>() {
//                    @Override
//                    public void call(Long aLong) {
//                        //每隔1秒发送数据项，从0开始计数
//                        //0,1,2,3....
//                        Log.d("Test", "message"+aLong);
//                    }
//                });
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(aLong -> {
                    //每隔1秒发送数据项，从0开始计数
                    //0,1,2,3....
                    Log.d("Test", "message"+aLong);
                });
    }

    /*
    range操作符 类似于fori循环
    创建一个发射指定范围的整数序列的Observable<Integer>
     */
    public void range(View view) {
//        Observable.range(2,5).subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                Log.d("Test",integer.toString());// 2,3,4,5,6 从2开始发射5个数据
//            }
//        });
        Observable.range(2,5).subscribe(integer -> {//call会调用5次
            Log.d("Test",integer.toString());// 2,3,4,5,6 从2开始发射5个数据
        });
    }

    //----------------------------------------------------------------------------------------------
    //合并操作符
    //----------------------------------------------------------------------------------------------

    /*
    concat操作符
    按顺序连接多个Observables。需要注意的是Observable.concat(a,b)等价于a.concatWith(b)
     */
    public void concat(View view) {
        Observable<Integer> observable1=Observable.just(1,2,3,4);
        Observable<Integer>  observable2=Observable.just(4,5,6);

        Observable.concat(observable1,observable2)
                .subscribe(item->Log.d("Test",item.toString()));//1,2,3,4,4,5,6
    }

    /*
    startWith操作符
     在数据序列的开头增加一项数据。startWith的内部也是调用了concat
     */
    public void startWith(View view) {
        Observable.just(1,2,3,4,5)
                .startWith(6,7,8)
                .subscribe(item->Log.d("JG",item.toString()));//6,7,8,1,2,3,4,5
    }

    /*
     将多个Observable合并为一个。不同于concat，merge不是按照添加顺序连接，而是按照时间线来连接。
     其中mergeDelayError将异常延迟到其它没有错误的Observable发送完毕后才发射。
     而merge则是一遇到异常将停止发射数据，发送onError通知。
     */
    public void merge(View view) {

    }

    /*
    zip操作符
    使用一个函数组合多个Observable发射的数据集合，然后再发射这个结果。如果多个Observable发射的数据量不一样，
    则以最少的Observable为标准进行压合。内部通过OperatorZip进行压合。
     */
    public void zip(View view) {
        Observable<Integer>  observable1=Observable.just(1,2,3,4);
        Observable<Integer>  observable2=Observable.just(4,5,6);

        Observable.zip(observable1, observable2, new Func2<Integer, Integer, String>() {
            @Override
            public String call(Integer item1, Integer item2) {
                return item1+"and"+item2;
            }
        }).subscribe(item->Log.d("Test",item)); //1and4,2and5,3and6
    }

    /*
    combineLatest操作符
    结合最后的数
     */
    public void combineLatest(View view) {
        Observable<Integer>  observable1=Observable.just(1,2,3,4);
        Observable<Integer>  observable2=Observable.just(4,5,6);

        Observable.combineLatest(observable1, observable2, new Func2<Integer, Integer, String>() {
            @Override
            public String call(Integer item1, Integer item2) {
                return item1+"and"+item2;
            }
        }).subscribe(item->Log.d("Test",item)); //4and4,4and5,4and6
    }

    //----------------------------------------------------------------------------------------------
    //过滤操作符
    //----------------------------------------------------------------------------------------------

    /*
    filter操作符
    类似于if...else
    */
    public void filter(View view) {
//        Observable.just(3,4,5,6)
//                .filter(new Func1<Integer, Boolean>() {
//                    @Override
//                    public Boolean call(Integer integer) {
//                        return integer>4;
//                    }
//                })
//                .subscribe(item->Log.d("Test",item.toString())); //5,6
        Observable.just(3,4,5,6)
                .filter(integer -> integer>4)
                .subscribe(item->Log.d("Test",item.toString())); //5,6
    }

    /*
    ofType操作符
    过滤指定类型的数据
     */
    public void ofType(View view) {
        Observable.just(1,2,"3")
                .ofType(Integer.class)//过滤掉除Integer类型以外的数据
                .subscribe(item -> Log.d("Test",item.toString()));
    }

    /*
    take操作符
    只发射开始的N项数据或者一定时间内的数据。内部通过OperatorTake和OperatorTakeTimed过滤数据。
     */
    public void take(View view) {
        Observable.just(3,4,5,6)
                .take(3)//发射前三个数据项
                .take(100, TimeUnit.MILLISECONDS)
                .subscribe(integer -> Log.d("Test", integer.toString()));//发射100ms内的数据
    }

    /*
    takeLast操作符
    只发射最后的N项数据或者一定时间内的数据。内部通过OperatorTakeLast和OperatorTakeLastTimed过滤数据。          takeLastBuffer和takeLast类似，不同点在于takeLastBuffer会收集成List后发射
     */
    public void takeLast(View view) {
        Observable.just(3,4,5,6)
                .takeLast(3)
                .subscribe(integer -> Log.d("Test",integer.toString()));//4,5,6
    }

    /*
    takeFirst操作符
    提取满足条件的第一项
     */
    public void takeFirst(View view) {
        Observable.just(3,4,5,6)
                .takeFirst(integer -> integer>4)
                .subscribe(integer -> Log.d("Test",integer.toString()));//5
    }

    /*
    first操作符
    只发射第一项（或者满足某个条件的第一项）数据，可以指定默认值
     */
    public void first(View view) {
        Observable.just(3,4,5,6)
                .first()
                .subscribe(integer -> Log.d("Test",integer.toString()));//3

        Observable.just(3,4,5,6)
                .first(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer>3;
                    }
                }) .subscribe(integer -> Log.d("Test",integer.toString()));//4
    }

    /*
    last操作符
    只发射最后一项（或者满足某个条件的最后一项）数据，可以指定默认值
     */
    public void last(View view) {

    }

    /*
    skip操作符
    跳过开始的N项数据或者一定时间内的数据。内部通过OperatorSkip和OperatorSkipTimed实现过滤
     */
    public void skip(View view) {
        Observable.just(3,4,5,6)
                .skip(1)
                .subscribe(integer -> Log.d("JG",integer.toString()));//4,5,6
    }

    /*
    skipLast操作符
    跳过最后的N项数据或者一定时间内的数据。内部通过OperatorSkipLast和OperatorSkipLastTimed实现过滤。
     */
    public void skipLast(View view) {

    }

    /*
    elementAt操作符 类似于childAt() 和 get()
    发射某一项数据，如果超过了范围可以的指定默认值。内部通过OperatorElementAt过滤。
     */
    public void elementAt(View view) {
        Observable.just(3,4,5,6)
                .elementAt(2)
                .subscribe(item->Log.d("Test",item.toString())); //5
    }

    /*
    ignoreElements操作符
    丢弃所有数据，只发射错误或正常终止的通知。内部通过OperatorIgnoreElements实现。
     */

    /*
    distinct操作符
    过滤重复数据，内部通过OperatorDistinct实现。
     */
    public void distinct(View view) {
        Observable.just(3,4,5,6,3,3,4,9)
                .distinct()
                .subscribe(item->Log.d("Test",item.toString())); //3,4,5,6,9
    }

    /*
    过滤掉连续重复的数据
     */
    public void distinctUntilChanged(View view) {
        Observable.just(3,4,5,6,3,3,4,9)
                .distinctUntilChanged()
                .subscribe(item->Log.d("Test",item.toString())); //3,4,5,6,3,4,9
    }

    /*
    定期发射Observable发射的第一项数据
     */
    public void throttleFirst(View view) {
        Observable.create(subscriber -> {
            subscriber.onNext(1);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw Exceptions.propagate(e);
            }
            subscriber.onNext(2);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw Exceptions.propagate(e);
            }

            subscriber.onNext(3);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw Exceptions.propagate(e);
            }
            subscriber.onNext(4);
            subscriber.onNext(5);
            subscriber.onCompleted();

        }).throttleFirst(999, TimeUnit.MILLISECONDS)
                .subscribe(item-> Log.d("Test",item.toString())); //结果为1,3,4
    }

    /*
    发射数据时，如果两次数据的发射间隔小于指定时间，就会丢弃前一次的数据,
    直到指定时间内都没有新数据发射时才进行发射
     */
    public void debounce(View view) {
        Observable.create(subscriber -> {
            subscriber.onNext(1);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw Exceptions.propagate(e);
            }
            subscriber.onNext(2);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw Exceptions.propagate(e);
            }

            subscriber.onNext(3);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw Exceptions.propagate(e);
            }
            subscriber.onNext(4);
            subscriber.onNext(5);
            subscriber.onCompleted();

        }).debounce(999, TimeUnit.MILLISECONDS)//或者为throttleWithTimeout(1000, TimeUnit.MILLISECONDS)
                .subscribe(item-> Log.d("Test",item.toString())); //结果为3,5
    }

    /*
    定期发射Observable最近的数据
     */
    public void sample(View view) {
        Observable.create(subscriber -> {
            subscriber.onNext(1);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw Exceptions.propagate(e);
            }
            subscriber.onNext(2);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw Exceptions.propagate(e);
            }

            subscriber.onNext(3);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw Exceptions.propagate(e);
            }
            subscriber.onNext(4);
            subscriber.onNext(5);
            subscriber.onCompleted();

        }).sample(999, TimeUnit.MILLISECONDS)//或者为throttleLast(1000, TimeUnit.MILLISECONDS)
                .subscribe(item-> Log.d("Test",item.toString())); //结果为2,3,5
    }

    /*
     如果原始Observable过了指定的一段时长没有发射任何数据，就发射一个异常或者使用备用的Observable
     */
    public void timeout(View view) {
        Observable.create(( subscriber) -> {
            subscriber.onNext(1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw Exceptions.propagate(e);
            }
            subscriber.onNext(2);

            subscriber.onCompleted();

        }).timeout(999, TimeUnit.MILLISECONDS,Observable.just(99,100))//如果不指定备用Observable将会抛出异常
                .subscribe(item-> Log.d("Test",item.toString()),error->Log.d("Test","onError"));
        //结果为1,99,100  如果不指定备用Observable结果为1,onError
    }

    //----------------------------------------------------------------------------------------------
    //布尔操作符
    //----------------------------------------------------------------------------------------------

    /*
    判断所有的数据项是否满足某个条件
     */
    public void all(View view) {
        Observable.just(2,3,4,5)
                .all(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer>3;
                    }
                })
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        Log.d("Test",aBoolean.toString()); //false
                    }
                });
    }

    /*
    判断是否存在数据项满足某个条件
     */
    public void exists(View view) {
        Observable.just(2,3,4,5)
                .exists(integer -> integer>3)
                .subscribe(aBoolean -> Log.d("Test",aBoolean.toString())); //true
    }

    /*
    判断在发射的所有数据项中是否包含指定的数据，内部调用的其实是exists
     */
    public void contains(View view) {
        Observable.just(2,3,4,5)
                .contains(3)
                .subscribe(aBoolean -> Log.d("Test",aBoolean.toString())); //true
    }

    /*
    用于判断两个Observable发射的数据是否相同（数据，发射顺序，终止状态）
     */
    public void sequenceEqual(View view) {
        Observable.sequenceEqual(Observable.just(2,3,4,5),Observable.just(2,3,4,5))
                .subscribe(aBoolean -> Log.d("JG",aBoolean.toString()));//true
    }

    /*
    用于判断Observable发射完毕时，有没有发射数据。有数据false，如果只收到了onComplete通知则为true。
     */
    public void isEmpty(View view) {
        Observable.just(3,4,5,6)
                .isEmpty()
                .subscribe(item -> Log.d("Test",item.toString()));//false
    }

    /*
    给定多个Observable，只让第一个发射数据的Observable发射全部数据，其他Observable将会被忽略
     */
    public void amb(View view) {
        Observable<Integer> observable1=Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    subscriber.onError(e);
                }
                subscriber.onNext(1);
                subscriber.onNext(2);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.computation());

        Observable<Integer> observable2=Observable.create(subscriber -> {
            subscriber.onNext(3);
            subscriber.onNext(4);
            subscriber.onCompleted();
        });

        Observable.amb(observable1,observable2)
                .subscribe(integer -> Log.d("Test",integer.toString())); //3,4
    }

    /*
    如果原始Observable正常终止后仍然没有发射任何数据，就使用备用的Observable
     */
    public void switchIfEmpty(View view) {
        Observable.empty()
                .switchIfEmpty(Observable.just(2,3,4))
                .subscribe(o -> Log.d("Test",o.toString())); //2,3,4
    }

    /*
    如果原始Observable正常终止后仍然没有发射任何数据，就发射一个默认值,内部调用的switchIfEmpty。
     */
    public void defaultIfEmpty(View view) {

    }

    /*
    当发射的数据满足某个条件后（包含该数据），或者第二个Observable发送完毕，终止第一个Observable发送数据
     */
    public void takeUntil(View view) {
        Observable.just(2,3,4,5)
                .takeUntil(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer==4;
                    }
                }).subscribe(integer -> Log.d("JG",integer.toString())); //2,3,4
    }

    /*
    当发射的数据满足某个条件时（不包含该数据），Observable终止发送数据
     */
    public void takeWhile(View view) {
        Observable.just(2,3,4,5)
                .takeWhile(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer==4;
                    }
                })
                .subscribe(integer -> Log.d("JG",integer.toString())); //2,3
    }

    /*
    丢弃Observable发射的数据，直到第二个Observable发送数据。（丢弃条件数据）
     */
    public void skipUntil(View view) {

    }

    /*
    丢弃Observable发射的数据，直到一个指定的条件不成立（不丢弃条件数据）
     */
    public void skipWhile(View view) {

    }

    //----------------------------------------------------------------------------------------------
    //聚合操作符
    //----------------------------------------------------------------------------------------------

    /*
    对序列使用reduce()函数并发射最终的结果,内部使用OnSubscribeReduce实现
     */
    public void reduce(View view) {
        Observable.just(2,3,4,5)
                .reduce(new Func2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer sum, Integer item) {
                        return sum+item;
                    }
                })
                .subscribe(integer -> Log.d("Test",integer.toString()));//14
    }

    /*
    使用collect收集数据到一个可变的数据结构
     */
    public void collect(View view) {
        Observable.just(3,4,5,6)
                .collect(new Func0<List<Integer>>() { //创建数据结构

                    @Override
                    public List<Integer> call() {
                        return new ArrayList<Integer>();
                    }
                }, new Action2<List<Integer>, Integer>() { //收集器
                    @Override
                    public void call(List<Integer> integers, Integer integer) {
                        integers.add(integer);
                    }
                })
                .subscribe(new Action1<List<Integer>>() {
                    @Override
                    public void call(List<Integer> integers) {
                        Log.d("Test", integers.size()+"");
                    }
                });
    }

    /*
    计算发射的数量，内部调用的是reduce
     */
    public void count(View view) {

    }

    //----------------------------------------------------------------------------------------------
    //转换操作符
    //----------------------------------------------------------------------------------------------

    /*
    收集原始Observable发射的所有数据到一个列表，然后返回这个列表
     */
    public void toList(View view) {
        Observable.just(2,3,4,5)
                .toList()
                .subscribe(new Action1<List<Integer>>() {
                    @Override
                    public void call(List<Integer> integers) {

                    }
                });
    }

    /*
    收集原始Observable发射的所有数据到一个有序列表，然后返回这个列表
     */
    public void toSortedList(View view) {
        Observable.just(6,2,3,4,5)
                .toSortedList(new Func2<Integer, Integer, Integer>() {//自定义排序
                    @Override
                    public Integer call(Integer integer, Integer integer2) {
                        return integer-integer2; //>0 升序 ，<0 降序
                    }
                })
                .subscribe(new Action1<List<Integer>>() {
                    @Override
                    public void call(List<Integer> integers) {
                        Log.d("Test",integers.toString()); // [2, 3, 4, 5, 6]
                    }
                });
    }

    /*
    将序列数据转换为一个Map。我们可以根据数据项生成key和生成value
     */
    public void toMap(View view) {
        Observable.just(6,2,3,4,5)
                .toMap(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return "key：" + integer; //根据数据项生成map的key
                    }
                }, new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return "value："+integer; //根据数据项生成map的kvalue
                    }
                }).subscribe(new Action1<Map<String, String>>() {
            @Override
            public void call(Map<String, String> stringStringMap) {
                Log.d("Test",stringStringMap.toString());
                // {key：6=value：6, key：5=value：5, key：4=value：4, key：2=value：2, key：3=value：3}
            }
        });
    }

    /*
    类似于toMap，不同的地方在于map的value是一个集合
     */
    public void toMultiMap(View view) {

    }

    //----------------------------------------------------------------------------------------------
    //变换操作符
    //----------------------------------------------------------------------------------------------

    /*
    在发射之前强制将Observable发射的所有数据转换为指定类型
     */
    public void cast(View view) {

    }

    /*
    将Observable发射的数据变换为Observables集合，然后将这些Observable发射的数据平坦化的放进一个单独的Observable，内部采用merge合并
     */
    public void flatMap(View view) {
        Observable.just(2,3,5)
                .flatMap(new Func1<Integer, Observable<String>>() {
                    @Override
                    public Observable<String> call(Integer integer) {
                        return Observable.create(subscriber -> {
                            subscriber.onNext(integer*10+"");
                            subscriber.onNext(integer*100+"");
                            subscriber.onCompleted();
                        });
                    }
                })
                .subscribe(o -> Log.d("Test",o)); //20,200,30,300,50,500
    }

    /*
    和flatMap的作用一样，只不过生成的是Iterable而不是Observable
     */
    public void flatMapIterable(View view) {
        Observable.just(2,3,5)
                .flatMapIterable(new Func1<Integer, Iterable<String>>() {
                    @Override
                    public Iterable<String> call(Integer integer) {
                        return Arrays.asList(integer*10+"",integer*100+"");
                    }
                }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d("Test", s);//20,200,30,300,50,500
            }
        });
    }

    /*
    类似于flatMap，由于内部使用concat合并，所以是按照顺序连接发射
     */
    public void concatMap(View view) {

    }

    /*
    和flatMap很像，将Observable发射的数据变换为Observables集合，当原始Observable发射一个新的数据（Observable）时，
    它将取消订阅前一个Observable
     */
    public void switchMap(View view) {
        Observable.create(new Observable.OnSubscribe<Integer>() {

            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for(int i=1;i<4;i++){
                    subscriber.onNext(i);
                    try {
                        Thread.sleep(500);//线程休眠500ms
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.newThread())
                .switchMap(new Func1<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Integer integer) {
                        //每当接收到新的数据，之前的Observable将会被取消订阅
                        return Observable.create(new Observable.OnSubscribe<Integer>() {
                            @Override
                            public void call(Subscriber<? super Integer> subscriber) {
                                subscriber.onNext(integer*10);
                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                subscriber.onNext(integer*100);
                                subscriber.onCompleted();
                            }
                        }).subscribeOn(Schedulers.newThread());
                    }
                })
                .subscribe(s -> Log.d("JG",s.toString()));//10,20,30,300
    }

    /*
    与reduce很像，对Observable发射的每一项数据应用一个函数，然后按顺序依次发射每一个值
     */
    public void scan(View view) {
        Observable.just(2,3,5)
                .scan(new Func2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer sum, Integer item) {
                        return sum+item;
                    }
                })
                .subscribe(integer -> Log.d("Test",integer.toString())); //2,5,10
    }

    /*
    将Observable分拆为Observable集合，将原始Observable发射的数据按Key分组，每一个Observable发射一组不同的数据
     */
    public void groupBy(View view) {
        Observable.just(2,3,5,6)
                .groupBy(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {//分组
                        return integer%2==0?"偶数":"奇数";
                    }
                })
                .subscribe(new Action1<GroupedObservable<String, Integer>>() {
                    @Override
                    public void call(GroupedObservable<String, Integer> o) {

                        o.subscribe(new Action1<Integer>() {
                            @Override
                            public void call(Integer integer) {
                                Log.d("Test",o.getKey()+":"+integer.toString()); //偶数：2，奇数：3，...
                            }
                        });
                    }
                });
    }

    /*
    它定期从Observable收集数据到一个集合，然后把这些数据集合打包发射，而不是一次发射一个
     */
    public void buffer(View view) {
        Observable.just(2,3,5,6)
                .buffer(3)
                .subscribe(new Action1<List<Integer>>() {
                    @Override
                    public void call(List<Integer> integers) {

                    }
                });
    }

    /*
    定期将来自Observable的数据分拆成一些Observable窗口，然后发射这些窗口，而不是每次发射一项
     */
    public void window(View view) {
        Observable.just(2,3,5,6)
                .window(3)
                .subscribe(new Action1<Observable<Integer>>() {
                    @Override
                    public void call(Observable<Integer> integerObservable) {
                        integerObservable.subscribe(new Action1<Integer>() {
                            @Override
                            public void call(Integer integer) {
                                Log.d("Test", integer+"");//2 3 5 6
                            }
                        });
                    }
                });
    }

    //----------------------------------------------------------------------------------------------
    //错误处理/重试机制
    //----------------------------------------------------------------------------------------------

    /*
    当原始Observable在遇到错误时，使用备用Observable
    */
    public void onErrorResumeNext(View view) {
        Observable.just(1,"2",3)
                .cast(Integer.class)
                .onErrorResumeNext(Observable.just(1,2,3))
                .subscribe(integer -> Log.d("JG",integer.toString())); //1,2,3
    }

    /*
    当原始Observable在遇到异常时，使用备用的Observable。与onErrorResumeNext类似，
    区别在于onErrorResumeNext可以处理所有的错误，onExceptionResumeNext只能处理异常
     */
    public void onExceptionResumeNext(View view) {

    }

    /*
    当原始Observable在遇到错误时发射一个特定的数据
     */
    public void onErrorReturn(View view) {
        Observable.just(1,"2",3)
                .cast(Integer.class)
                .onErrorReturn(new Func1<Throwable, Integer>() {
                    @Override
                    public Integer call(Throwable throwable) {
                        return 4;
                    }
                }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d("Test",integer.toString());//1,4
            }
        });
    }

    /*
    当原始Observable在遇到错误时进行重试
     */
    public void retry(View view) {
        Observable.just(1,"2",3)
                .cast(Integer.class)
                .retry(3)
                .subscribe(integer -> Log.d("JG",integer.toString()),
                        throwable -> Log.d("JG","onError"));//1,1,1,1,onError
    }

    /*
    原始Observable在遇到错误，将错误传递给另一个Observable来决定是否要重新订阅这个Observable,内部调用的是retry
     */
    public void retryWhen(View view) {
        Observable.just(1,"2",3)
                .cast(Integer.class)
                .retryWhen(new Func1<Observable<? extends Throwable>, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(Observable<? extends Throwable> observable) {
                        return Observable.timer(1, TimeUnit.SECONDS);
                    }
                })
                .subscribe(integer -> Log.d("JG",integer.toString()),
                        throwable -> Log.d("Test","onError"));//1,1
    }


    public void thread(View view) {
       Observable.create((Observable.OnSubscribe<String>) subscriber -> {
           for (int i = 0; i < 10; i++) {
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               subscriber.onNext(i+"");
           }
       }).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(s -> Log.d("Test", "线程"+s));
    }
}
