package yxd.android.case_code;

import android.app.ActivityManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;

import java.util.Map;

import yxd.android.R;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        SparseArray
         */
        SparseArray<String> array = new SparseArray<>();
        for (int i = 0; i < 30; i++) {
            array.append(i, i+"");
        }
        for (int i = 0; i < array.size(); i++) {
            Log.d("Test", array.get(i));
        }

        /*
        ArrayMap
        使用场景
        对象个数的数量级最好是千以内
        数据组织形式包含Map结构
         */
        ArrayMap<Integer, String> map = new ArrayMap<>();
        for (int i = 0; i < 30; i++) {
            map.put(i, "map"+i);
        }
        for (int i = 0; i < map.size(); i++) {
            Log.d("Test", "key"+map.keyAt(i));
            Log.d("Test", "value"+map.valueAt(i));
        }

        /*
        SparseArray Family Ties
        为了避免HashMap的autoboxing行为，Android系统提供了SparseBoolMap，SparseIntMap，
        SparseLongMap，LongSparseMap等容器。关于这些容器的基本原理请参考前面的ArrayMap的介绍，
        另外这些容器的使用场景也和ArrayMap一致，需要满足数量级在千以内，数据组织形式需要包含Map结构。
         */

    }

    /*
    通常来说，当所有的background应用都被kill掉的时候，foreground应用会收到onLowMemory()的回调。
    在这种情况下，需要尽快释放当前应用的非必须内存资源，从而确保系统能够稳定继续运行。
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    /*
    削减内存占用
    当系统内存达到某些条件的时候，所有正在运行的应用都会收到这个回调，
    同时在这个回调里面会传递以下的参数，代表不同的内存使用情况
    https://www.jianshu.com/p/5b30bae0eb49
    Activity，Fragment，Service，ContentProvider都能受到这个回调
     */
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        switch (level){
            case TRIM_MEMORY_RUNNING_MODERATE:
            /*运行变弱时
            示应用程序正常运行，并且不会被杀掉。但是目前手机的内存已经有点低了，
            系统可能会开始根据LRU缓存规则来去杀死进程了。
             */
                break;
            case TRIM_MEMORY_RUNNING_CRITICAL:
            /*运行危急时
            表示应用程序仍然正常运行，但是系统已经根据LRU缓存规则杀掉了大部分缓存的进程了。
            这时应当尽可能地去释放任何不必要的资源，不然的话系统可能会继续杀掉所有缓存中的进程，
            并且开始杀掉一些本来应当保持运行的进程，比如说后台运行的服务
             */
                break;
            case TRIM_MEMORY_UI_HIDDEN:
             /*UI隐藏时
            表示应用程序的所有UI界面被隐藏了，
            即用户点击了Home键或者Back键导致应用的UI界面不可见．
            这时候应该释放一些资源
             */
                break;
            case TRIM_MEMORY_RUNNING_LOW:
            /*低内存运行时
            表示应用程序正常运行，并且不会被杀掉。但是目前手机的内存已经非常低了，
            应该去释放掉一些不必要的资源以提升系统的性能，同时这也会直接影响到应用程序的性能
             */
                break;

            //当应用程序是缓存的，则会收到以下几种类型的回调：

            case TRIM_MEMORY_BACKGROUND:
            /*后台时
            表示手机目前内存已经很低了，系统准备开始根据LRU缓存来清理进程。这时候程序在LRU缓存列表的最近位置，
            是不太可能被清理掉的，但这时去释放掉一些比较容易恢复的资源能够让手机的内存变得比较充足，
            从而让我们的程序更长时间地保留在缓存当中，这样当用户返回我们的程序时会感觉非常顺畅，而不是经历了一次重新启动的过程。
             */
                break;

            case TRIM_MEMORY_MODERATE:
            /*
            表示手机目前内存已经很低了，并且我们的程序处于LRU缓存列表的中间位置，
            如果手机内存还得不到进一步释放的话，那么我们的程序就有被系统杀掉的风险了
             */
                break;
            case TRIM_MEMORY_COMPLETE:
            /*
            表示手机目前内存已经很低了，并且我们的程序处于LRU缓存列表的最边缘位置，
            系统会最优先考虑杀掉我们的应用程序，在这个时候应当尽可能地把一切可以释放的东西都进行释放
             */
                break;
        }
    }

    /*
    计算内存
    Activity管理器的获取内存类
     */
    public void calculate(){
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        int memory = manager.getMemoryClass();//以m为单位
        int largeMemory = manager.getLargeMemoryClass();

    }
}
