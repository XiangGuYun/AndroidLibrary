package yxd.recyclerview.case5_scrollview_nest_rv;

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

import java.util.ArrayList;
import java.util.List;

import yxd.recyclerview.R;
import yxd.recyclerview.case1_base.DividerItemDecoration;
import yxd.recyclerview.case1_base.RecyclerAdapter;
/*
https://www.cnblogs.com/tianzhijiexian/p/4469516.html
 */
public class ListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        /*
        设置布局
         */
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(this));
         /*
        设置item增加和删除时的动画
         */
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        /*
        设置item分割线
         */
        recyclerView.addItemDecoration(new DividerItemDecoration(ListActivity.this,
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
                Toast.makeText(ListActivity.this,
                        "点击了第"+(position+1)+"项", 0).show();
            }

            @Override
            public void onItemLongClick(View view, final int position, RecyclerAdapter.ViewHolder vh) {
                new AlertDialog.Builder(ListActivity.this)
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
    }
    

}
