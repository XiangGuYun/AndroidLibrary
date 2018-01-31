package yxd.pool.case1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import yxd.pool.R;

/*
参考文档：http://blog.csdn.net/u012702547/article/details/52259529

线程池其他常用功能
1.shutDown()  关闭线程池，不影响已经提交的任务
2.shutDownNow() 关闭线程池，并尝试去终止正在执行的线程
3.allowCoreThreadTimeOut(boolean value) 允许核心线程闲置超时时被回收
4.submit 一般情况下我们使用execute来提交任务，但是有时候可能也会用到submit，使用submit的好处是submit有返回值
public void submit(View view) {
    List<Future<String>> futures = new ArrayList<>();
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5, 1,
            TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
    for (int i = 0; i < 10; i++) {
        Future<String> taskFuture = threadPoolExecutor.submit(new MyTask(i));
        //将每一个任务的执行结果保存起来
        futures.add(taskFuture);
    }
    try {
        //遍历所有任务的执行结果
        for (Future<String> future : futures) {
            Log.d("google_lenve_fb", "submit: " + future.get());
        }
    } catch (InterruptedException e) {
        e.printStackTrace();
    } catch (ExecutionException e) {
        e.printStackTrace();
    }
}

class MyTask implements Callable<String> {

    private int taskId;

    public MyTask(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public String call() throws Exception {
        SystemClock.sleep(1000);
        //返回每一个任务的执行结果
        return "call()方法被调用----" + Thread.currentThread().getName() + "-------" + taskId;
    }
}
使用submit时我们可以通过实现Callable接口来实现异步任务。在call方法中执行异步任务，返回值即为该任务的返回值。
Future是返回结果，返回它的isDone属性表示异步任务执行成功！
 */
public class MainActivity extends AppCompatActivity {
    //普通线程池
    private ThreadPoolExecutor poolExecutor;
    //固定线程池
    private ExecutorService fixedThreadPool;
    //单一线程执行器
    private ExecutorService singleThreadExecutor;
    //缓存线程池
    private ExecutorService cachedThreadPool;
    //调度执行器
    private ScheduledExecutorService scheduledExecutorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        poolExecutor = new ThreadPoolExecutor(3, //核心线程数
                5,//最大线长数
                1, //非核心线程处于空闲状态的存活时间
                TimeUnit.SECONDS, //存活时间单位
                new LinkedBlockingDeque<Runnable>(128));//线程队列

        fixedThreadPool = Executors.newFixedThreadPool(3);

    }

    /*
    默认线程池
    点击按钮
    每隔两秒产生一个线程并打印输出
     */
    public void btnClick(View view) {
        for (int i = 0; i < 30; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(2000);
                    Log.d("Test", "run: " + Thread.currentThread().getId()
                            +" "+Thread.currentThread().getName());
                }
            };
            poolExecutor.execute(runnable);
        }
    }

    /*
    对比一下不使用线程池的效果
     */
    public void btnClick1(View view) {
        for (int i = 0; i < 30; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(2000);
                    Log.d("Test", "run: " + Thread.currentThread().getId()
                            +" "+Thread.currentThread().getName());
                }
            };
            new Thread(runnable).start();
        }
    }

    /*
    FixedThreadPool
    public static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                                      0L, TimeUnit.MILLISECONDS,
                                      new LinkedBlockingQueue<Runnable>());
    }
    FixedThreadPool中所有线程都是核心线程，核心线程即使在没有任务可执行时也不会被销毁（这样可让FixedThreadPool更快速的响应请求），
    最后的线程队列是一个没有参数的LinkedBlockingQueue,说明线程队列的大小为Integer.MAX_VALUE（2的31次方减1），
    思考：类似于公共厕所，排队的人可以有无限多，但是固定的坑位就那么几个。
     */
    public void btnClick2(View view) {
        for (int i = 0; i < 30; i++) {
            final int finalI = i;
            Runnable runnable = new Runnable(){
                @Override
                public void run() {
                    SystemClock.sleep(3000);
                    Log.d("Test", "run: "+ finalI+ Thread.currentThread().getId()
                            +" "+Thread.currentThread().getName());
                }
            };
            fixedThreadPool.execute(runnable);
            /*
            先往核心线程中添加三个任务，剩余任务进入到workQueue中等待，
            当有空闲的核心线程时就执行任务队列中的任务。
             */
        }
    }

    /*
    SingleThreadExecutor
    singleThreadExecutor和FixedThreadPool很像，不同的就是SingleThreadExecutor的核心线程数只有1，如下：
    public static ExecutorService newSingleThreadExecutor() {
        return new FinalizableDelegatedExecutorService
            (new ThreadPoolExecutor(1, 1,
                                    0L, TimeUnit.MILLISECONDS,
                                    new LinkedBlockingQueue<Runnable>()));
    }
    只有一个核心线程，可以理解为只有一个固定坑位的公共厕所
     */
    public void btnClick3(View view) {
        if(singleThreadExecutor == null){
            singleThreadExecutor = Executors.newSingleThreadExecutor();
        }
        for (int i = 0; i < 30; i++) {
            final int finalI = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    Log.d("Test", "run: " + Thread.currentThread().getName()
                            + "-----" + finalI);
                    SystemClock.sleep(1000);
                }
            };
            singleThreadExecutor.execute(runnable);
        }
    }

    /*
    CachedThreadPool
    CachedTreadPool一个最大的优势是它可以根据程序的运行情况自动来调整线程池中的线程数量，CachedThreadPool源码如下：
    public static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                      60L, TimeUnit.SECONDS,
                                      new SynchronousQueue<Runnable>());
    }
    ①：没有核心线程，全是非核心线程
    ②：非核心线程的数量不受限制，但空闲存活时间是60秒
    ③：使用SynchronousQueue作为线程队列，队列长度也不受限制
    ④：适用于有大量任务请求的场景
     */
    public void btnClick4(View view) {
        if(cachedThreadPool == null)
            cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 30; i++) {
            final int finalI = i;
            Runnable runnable = new Runnable(){
                @Override
                public void run() {
                    Log.d("Test", "run: " +
                            Thread.currentThread().getName() + "----" + finalI);
                }
            };
            cachedThreadPool.execute(runnable);
            SystemClock.sleep(2000);
            /*
            每次添加完任务之后我都停两秒在添加新任务，由于这里的任务执行不费时，
            我们可以猜测这里所有的任务都使用同一个线程来执行（因为每次添加新任务的时候都有空闲的线程）
             */
        }
    }


    /*
    ScheduledThreadPool
    ScheduledThreadPool是一个具有定时定期执行任务功能的线程池，源码如下：
    public ScheduledThreadPoolExecutor(int corePoolSize) {
        super(corePoolSize, Integer.MAX_VALUE,
              DEFAULT_KEEPALIVE_MILLIS, MILLISECONDS,
              new DelayedWorkQueue());
    }

    它的核心线程数量是固定的（我们在构造的时候传入的），但是非核心线程是无穷大，当非核心线程闲置时，则会被立即回收。
     */
    /*
    延迟启动任务
    public ScheduledFuture<?> schedule(Runnable command,
                                   long delay, TimeUnit unit);
     */
    public void btnClick5(View view) {
        scheduledExecutorService =
                Executors.newScheduledThreadPool(3);
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                Log.d("Test", "run: ----");
            }
        };
        scheduledExecutorService.schedule(runnable, 1, TimeUnit.SECONDS);
    }

    /*
    延迟定时执行任务
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command,
                                              long initialDelay,
                                              long period,
                                              TimeUnit unit);
    延迟initialDelay秒后每隔period秒执行一次任务
     */
    public void btnClick6(View view) {
        ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(3);
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                Log.d("Test", "run: ----");
            }
        };
        scheduledExecutorService.scheduleAtFixedRate(runnable,
                1, 1, TimeUnit.SECONDS);

    }

    /*
    延迟执行任务
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command,
                                                 long initialDelay,
                                                 long delay,
                                                 TimeUnit unit);
    第一次延迟initialDelay秒，以后每次延迟delay秒执行一个任务
     */
    public void btnClick7(View view) {
        ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(3);
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                Log.d("Test", "run: ----");
            }
        };
        scheduledExecutorService.scheduleWithFixedDelay(runnable,
                1, 1, TimeUnit.SECONDS);
    }





}