package yxd.viewpager.case5_auto_infinite;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import yxd.viewpager.R;

public class Case5Activity extends AppCompatActivity implements Runnable{

    private ViewPager viewPager;
    private List<View> views = new ArrayList<>();
    private View view1, view2, view3;

    @SuppressLint("HandlerLeak")
    Handler handler;
    private HandlerThread handlerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case4);
        viewPager=findViewById(R.id.viewpager);
        view1 = getLayoutInflater().inflate(R.layout.view1, null);
        view2 = getLayoutInflater().inflate(R.layout.view2, null);
        view3 = getLayoutInflater().inflate(R.layout.view3, null);
        views.add(view1);
        views.add(view2);
        views.add(view3);

        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return Integer.MAX_VALUE;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
            /*
            从当前container中删除指定位置（position）的View;
             */
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                //container.removeView(views.get(position%3));
            }
            /*
            做了两件事，第一：将当前视图添加到container中，第二：返回当前View
             */
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                if(views.get(position%3).getParent()!=null){
                    container.removeView(views.get(position%views.size()));
                }
                container.addView(views.get(position%views.size()));
                return views.get(position%views.size());
            }
        });

        viewPager.setCurrentItem(1000);
        viewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Case5Activity.this, "点击了ViewPager", Toast.LENGTH_SHORT).show();
            }
        });


        ViewPagerScroller scroller = new ViewPagerScroller(this);
        scroller.setScrollDuration(2000);
        scroller.initViewPagerScroll(viewPager);//这个是设置切换过渡时间为2秒


        handlerThread = new HandlerThread("test", 0);
        handlerThread.start();

        handler = new Handler(handlerThread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                while (true){
                    Log.d("Test", "中了吗");
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(Case5Activity.this);
                }
            }
        };

        handler.sendEmptyMessage(0x123);

    }


    @Override
    public void run() {
        viewPager.setCurrentItem(viewPager.getCurrentItem()+1, true);
    }
}
