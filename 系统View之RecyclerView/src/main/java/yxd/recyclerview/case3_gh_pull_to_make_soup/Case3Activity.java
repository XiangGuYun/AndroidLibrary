package yxd.recyclerview.case3_gh_pull_to_make_soup;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.yalantis.pulltomakesoup.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

import yxd.recyclerview.R;
import yxd.recyclerview.case1_base.DividerItemDecoration;
import yxd.recyclerview.case1_base.ListActivity;
import yxd.recyclerview.case1_base.RecyclerAdapter;

/*
https://github.com/Yalantis/pull-to-make-soup
 */
public class Case3Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private List<String> list = new ArrayList<>();
    private PullToRefreshView pullToRefreshView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case3);
        init();
        initRecyclerView();
        pullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(Case3Activity.this, "正在刷新...",
                        Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pullToRefreshView.setRefreshing(false);
                            }
                        });
                    }
                }).start();
            }
        });
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
                Toast.makeText(Case3Activity.this,
                        "点击了第"+(position+1)+"项", 0).show();
            }

            @Override
            public void onItemLongClick(View view, final int position, RecyclerAdapter.ViewHolder vh) {
                new AlertDialog.Builder(Case3Activity.this)
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
        recyclerView = findViewById(R.id.rv);
        pullToRefreshView = findViewById(R.id.pull_to_refresh);
    }
}
