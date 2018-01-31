package yxd.recyclerview.case6_drag_item;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

import yxd.recyclerview.R;
import yxd.recyclerview.case1_base.DividerGridItemDecoration;
import yxd.recyclerview.case1_base.RecyclerAdapter;

public class DragGridActivity extends AppCompatActivity {

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
        recyclerView.setLayoutManager(
                new StaggeredGridLayoutManager(
                        4,
                        StaggeredGridLayoutManager.VERTICAL));
         /*
        设置item增加和删除时的动画
         */
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        /*
        设置item分割线
         */
        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        /*
        设置适配器
         */
        recyclerView.setAdapter(adapter);

    }

    private void init() {
        for (int i = 0; i < 30; i++) {
            list.add((i+1)+"");
        }
        recyclerView = findViewById(R.id.rv);
        adapter = new RecyclerAdapter(list);
        itemTouchHelper=new ItemTouchHelper(new CommonCallback(list, adapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}
