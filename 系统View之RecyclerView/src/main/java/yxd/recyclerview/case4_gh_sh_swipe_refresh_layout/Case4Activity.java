package yxd.recyclerview.case4_gh_sh_swipe_refresh_layout;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.scu.miomin.shswiperefresh.core.SHSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import yxd.recyclerview.R;
import yxd.recyclerview.case1_base.DividerItemDecoration;
import yxd.recyclerview.case1_base.RecyclerAdapter;
import yxd.recyclerview.case3_gh_pull_to_make_soup.Case3Activity;

/*
https://github.com/miomin/SHSwipeRefreshLayout
支持下拉刷新和上拉加载更多，
支持自定义HeaderView和FooterView，
支持RecyclerView、ScrollView嵌套滚动，支持所有Layout，支持自定义动画
 */
/*
提示
如使用其它控件时遇到滑动冲突，请参考源码中ShareScrollView、SHListView的实现自行解决，
只需让该控件实现NestedScrollingChild接口即可。
 */
/*
如何自定义HeaderView、FooterView

如果不设置，则使用默认的ProgressBar

可通过如下代码设置 ：
设置Resource ID
swipeRefreshLayout.setFooterView(R.layout.refresh_view);
设置View
swipeRefreshLayout.setFooterView(myview);
 */
public class Case4Activity extends AppCompatActivity implements SHSwipeRefreshLayout.SHSOnRefreshListener{

    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private List<String> list = new ArrayList<>();
    private SHSwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case4);
        init();
        initRecyclerView();
        //设置刷新监听器
        swipeRefreshLayout.setOnRefreshListener(this);
    }



    @Override
    public void onRefresh() {
        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.finishRefresh();//调用此方法结束刷新
                toast("刷新完成");
            }
        }, 1600);//刷新模拟耗时
    }

    @Override
    public void onLoading() {
        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.finishLoadmore();//调用此方法结束加载更多
                toast("加载完成");
            }
        }, 1600);//加载模拟耗时
    }

    /**
     * 监听下拉刷新过程中的状态改变
     * @param percent 当前下拉距离的百分比（0-1）
     * @param state NOT_OVER_TRIGGER_POINT：还未到触发下拉刷新的距离；
     *              OVER_TRIGGER_POINT：已经到触发下拉刷新的距离；
     *              START：正在下拉刷新
     */
    @Override
    public void onRefreshPulStateChange(float percent, int state) {
        switch (state) {
            case SHSwipeRefreshLayout.NOT_OVER_TRIGGER_POINT:
                swipeRefreshLayout.setLoaderViewText("下拉刷新");
                break;
            case SHSwipeRefreshLayout.OVER_TRIGGER_POINT:
                swipeRefreshLayout.setLoaderViewText("松开刷新");
                break;
            case SHSwipeRefreshLayout.START:
                swipeRefreshLayout.setLoaderViewText("正在刷新");
                break;
        }
    }

    @Override
    public void onLoadmorePullStateChange(float percent, int state) {
        switch (state) {
            case SHSwipeRefreshLayout.NOT_OVER_TRIGGER_POINT:
                swipeRefreshLayout.setLoaderViewText("上拉加载");
                Log.d("Test", "上拉加载");
                break;
            case SHSwipeRefreshLayout.OVER_TRIGGER_POINT:
                swipeRefreshLayout.setLoaderViewText("松开加载");
                Log.d("Test", "松开加载");
                break;
            case SHSwipeRefreshLayout.START:
                swipeRefreshLayout.setLoaderViewText("正在加载");
                Log.d("Test", "正在加载...");
                break;
        }
    }

    private void initRecyclerView() {
         /*
        设置布局
         */
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
         /*
        设置item增加和删除时的动画
         */
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        /*
        设置item分割线
         */
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
        /*
        设置适配器
         */
        adapter = new RecyclerAdapter(list);
        recyclerView.setAdapter(adapter);
        /*
        设置点击监听事件
         */
        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(Case4Activity.this,
                        "点击了第"+(position+1)+"项", 0).show();
            }

            @Override
            public void onItemLongClick(View view, final int position, RecyclerAdapter.ViewHolder vh) {
                new AlertDialog.Builder(Case4Activity.this)
                        .setTitle("确认删除嘛")
                        .setNegativeButton("取消",null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                adapter.removeData(position);
                            }
                        })
                        .show();
            }
        });
    }

    private void init() {
        for (int i = 0; i < 30; i++) {
            list.add((i+1)+"");
        }
        recyclerView = findViewById(R.id.recyclerView);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
    }

    public void toast(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
