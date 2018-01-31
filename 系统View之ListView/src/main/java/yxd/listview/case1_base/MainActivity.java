package yxd.listview.case1_base;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import yxd.listview.R;

public class MainActivity extends AppCompatActivity {

    private MyListView listView;
    List<String> list = new ArrayList<>();
    EditText editText;
    private ViewHolderAdapter adapter;
    int itemNum = 0;
    private int lastVisibelItemPosition= 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.et);

        for (int i = 0; i < 30; i++) {
            list.add("Item"+(i+1));
        }
        itemNum = list.size();

        listView = findViewById(R.id.lv);
        listView.setEmptyView(findViewById(R.id.iv_empty));
        //获取可视区域内最后一个Item的id
        listView.getLastVisiblePosition();
        //获取可视区域内第一个Item的id
        listView.getFirstVisiblePosition();
        /*
        滑动监听
         */
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState){
                    case SCROLL_STATE_IDLE:
                        Log.d("Test", "处于空闲状态");
                        break;
                    case SCROLL_STATE_TOUCH_SCROLL:
                        Log.d("Test", "处于手指滑动状态");
                        break;
                    case SCROLL_STATE_FLING:
                        Log.d("Test", "处于惯性滑动状态");
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem+visibleItemCount==totalItemCount
                        &&totalItemCount>0){
                    Toast.makeText(MainActivity.this, "滚到了最后一行",
                            Toast.LENGTH_SHORT).show();
                }
                if(firstVisibleItem > lastVisibelItemPosition){
                    Log.d("Test", "正在上滑");
                }else if(firstVisibleItem < lastVisibelItemPosition){
                    Log.d("Test", "正在下滑");
                }
                lastVisibelItemPosition = firstVisibleItem;
            }
        });

        /*
        触摸监听
         */
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:

                        break;
                    case MotionEvent.ACTION_MOVE:

                        break;
                    case MotionEvent.ACTION_UP:

                        break;
                }
                return false;
            }
        });
    }

    /*
    选择ListView的第N项
     */
    public void btn1(View view) {
        String str = editText.getText().toString();
        if(!TextUtils.isEmpty(str)){
            listView.setSelection(Integer.parseInt(str)-1);
        }
    }

    /*
    ListView的移动
     */
    public void btn2(View view) {
        String str = editText.getText().toString();
        if(!TextUtils.isEmpty(str)){
            int item = Integer.parseInt(str)-1;
            //移动到第item项，该项出现在最下方
            //listView.smoothScrollToPosition();
            //移动到第item项，该项出现在最上方
            listView.smoothScrollByOffset(item);
            //在固定时间内移动固定的距离，单位是像素
            //listView.smoothScrollBy(Integer.parseInt(str)-1, 2000);
        }

    }

    /*
    动态修改ListView
     */
    public void btn3(View view) {
        list.add("New Item");
        adapter.notifyDataSetChanged();
        listView.smoothScrollToPosition(itemNum++);
        /*
        遍历ListView当前可见的所有的Item
         */
        for (int i = 0; i < listView.getChildCount(); i++) {
            ViewGroup viewGroup = (ViewGroup) listView.getChildAt(i);
            TextView textView = (TextView) viewGroup.getChildAt(0);
            Log.d("Test", "text is "+textView.getText().toString());
        }
    }

    public void btn4(View view) {
        adapter = new ViewHolderAdapter(this, list);
        listView.setAdapter(adapter);
    }
}
