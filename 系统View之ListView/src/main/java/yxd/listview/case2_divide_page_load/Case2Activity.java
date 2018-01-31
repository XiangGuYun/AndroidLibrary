package yxd.listview.case2_divide_page_load;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import yxd.listview.R;

public class Case2Activity extends AppCompatActivity implements AbsListView.OnScrollListener {

    List<String> items = new ArrayList<String>();
    private ListView listView;
    private int visibleLastIndex = 0;   //最后的可视项索引
    private int visibleItemCount;       // 当前窗口可见项总数
    private ListViewAdapter adapter;
    private View loadMoreView;
    private Button loadMoreButton;
    private Handler handler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case2);

        loadMoreView = getLayoutInflater().inflate(R.layout.btn_addmore, null);
        loadMoreButton = (Button) loadMoreView.findViewById(R.id.loadMoreButton);
        loadMoreButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                loadMoreButton.setText("正在加载...");   //设置按钮文字loading
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        loadData();

                        adapter.notifyDataSetChanged(); //数据集变化后,通知adapter
                        listView.setSelection(visibleLastIndex - visibleItemCount + 1); //设置选中项

                        loadMoreButton.setText("加载更多");    //恢复按钮文字
                    }
                }, 1000);
            }
        });
        listView = (ListView) this.findViewById(R.id.listView1);

        listView.addFooterView(loadMoreView);   //设置列表底部视图
        // listView.addHeaderView(v)    //设置列表顶部视图

        initAdapter();

        listView.setAdapter(adapter);                //自动为id是list的ListView设置适配器

        listView.setOnScrollListener(this);     //添加滑动监听
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view,
                                    int position, long arg3) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), items.get(position),Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {

        for (int i = 0; i < 20; i++) {
            items.add(String.valueOf(i + 1));
        }
        adapter = new ListViewAdapter(items,this);
    }

    /**
     * 滑动时被调用
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.visibleItemCount = visibleItemCount;
        visibleLastIndex = firstVisibleItem + visibleItemCount - 1;
    }

    /**
     * 滑动状态改变时被调用
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        int itemsLastIndex = adapter.getCount() - 1;    //数据集最后一项的索引
        int lastIndex = itemsLastIndex + 1;             //加上底部的loadMoreView项
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && visibleLastIndex == lastIndex) {
            //如果是自动加载,可以在这里放置异步加载数据的代码
            Log.i("LOADMORE", "loading...");
        }
    }


    /**
     * 模拟加载数据
     */
    private void loadData() {
        int count = adapter.getCount();
        for (int i = count; i < count + 20; i++) {
            adapter.addItem(String.valueOf(i + 1));
        }
    }

}
