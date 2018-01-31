package internet.yxd.interview.jsbridge_undone.case2;

import android.util.Log;
import android.webkit.JavascriptInterface;

/**
 * Created by asus on 2017/12/29.
 */

// 继承自Object类
public class AndroidToJS{

    // 定义JS需要调用的方法
    // 被JS调用的方法必须加入@JavascriptInterface注解
    @JavascriptInterface
    public void hello(String msg) {
        Log.d("Test", "JS调用了Android的hello方法");
    }
}
