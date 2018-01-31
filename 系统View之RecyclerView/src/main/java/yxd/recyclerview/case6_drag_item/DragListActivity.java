package yxd.recyclerview.case6_drag_item;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import yxd.recyclerview.R;
import yxd.recyclerview.case1_base.DividerItemDecoration;
import yxd.recyclerview.case1_base.RecyclerAdapter;
/*
https://www.cnblogs.com/wjtaigwh/p/6543354.html
 */
public class DragListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private List<String> list = new ArrayList<>();
    private ItemTouchHelper itemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
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
        recyclerView.setAdapter(adapter);
        /*
        设置点击监听事件
         */
        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(DragListActivity.this,
                        "点击了第"+(position+1)+"项", 0).show();
            }

            @Override
            public void onItemLongClick(View view, final int position, RecyclerAdapter.ViewHolder vh) {
                //判断被拖拽的是否是前两个，如果不是则执行拖拽
                if (vh.getLayoutPosition() != 0 && vh.getLayoutPosition() != 1) {
                    itemTouchHelper.startDrag(vh);

                }
            }
        });

    }

    private void init() {
        for (int i = 0; i < 30; i++) {
            list.add((i+1)+"");
        }
        recyclerView = findViewById(R.id.rv);
        adapter = new RecyclerAdapter(list);
//        itemTouchHelper=new ItemTouchHelper(new CommonCallback(list, adapter));
        itemTouchHelper=new ItemTouchHelper(new NotAllDragableCallback(list, adapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}
