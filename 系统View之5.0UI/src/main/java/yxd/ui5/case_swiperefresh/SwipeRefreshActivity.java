package yxd.ui5.case_swiperefresh;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import yxd.ui5.R;

/*
下面是SwipeRefreshLayout的常用方法说明：

setColorScheme : 设置进度条/圆圈的颜色。（该方法在新版中已被废弃）
setOnRefreshListener : 设置刷新监听器。在下拉松开时触发该监听器，
需要重写该监听器的onRefresh方法。
setRefreshing : 设置刷新的状态。true表示正在刷新，false表示结束刷新。
isRefreshing : 判断是否正在刷新。

下面是新版增加的方法说明：
setColorSchemeColors : 设置进度圆圈的圆环颜色。
setProgressBackgroundColorSchemeColor : 设置进度圆圈的背景颜色。
setProgressViewOffset : 设置进度圆圈的偏移量。第一个参数表示进度圈是否缩放，
第二个参数表示进度圈开始出现时距顶端的偏移，第三个参数表示进度圈拉到最大时距顶端的偏移。
setDistanceToTriggerSync : 设置手势向下滑动多少距离才会触发刷新操作。


 */
public class SwipeRefreshActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private TextView tv_listview;
    private SwipeRefreshLayout srl_listview;

    private Handler mHandler = new Handler();

    private ListView lv_content;
    private String[] yearArray = {"鼠年", "牛年", "虎年", "兔年", "龙年", "蛇年",
            "马年", "羊年", "猴年", "鸡年", "狗年", "猪年"};
    private void refreshView() {
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        adapter.addAll(yearArray);
        lv_content.setAdapter(adapter);

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh);

        tv_listview = findViewById(R.id.tv_listview);
        srl_listview = findViewById(R.id.srl_listview);
        srl_listview.setOnRefreshListener(this);
        //新版用下面的setColorSchemeResources设置进度圆圈颜色
        srl_listview.setColorSchemeResources(
              R.color.red, R.color.orange, R.color.green, R.color.blue );

        lv_content = findViewById(R.id.lv_content);
        refreshView();
    }

    @Override
    public void onRefresh() {
        tv_listview.setVisibility(View.VISIBLE);//刷新时显示该文本
        tv_listview.setText("正在刷新");
        mHandler.postDelayed(mRefresh, 3000);//模拟耗时
    }

    private Runnable mRefresh = new Runnable() {

        @Override
        public void run() {
            refreshView();
            tv_listview.setText("刷新完成");
            srl_listview.setRefreshing(false);//停止刷新
            mHandler.postDelayed(mComplete, 500);
        }

    };

    private Runnable mComplete = new Runnable() {

        @Override
        public void run() {
            tv_listview.setVisibility(View.GONE);//隐藏布局
        }

    };

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
