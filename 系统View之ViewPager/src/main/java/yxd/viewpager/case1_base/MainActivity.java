package yxd.viewpager.case1_base;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

import yxd.viewpager.R;

public class MainActivity extends AppCompatActivity {

    private View view1, view2, view3;
    private ViewPager viewPager;
    private List<View> views = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.vp);
        view1 = getLayoutInflater().inflate(R.layout.view1, null);
        view2 = getLayoutInflater().inflate(R.layout.view2, null);
        view3 = getLayoutInflater().inflate(R.layout.view3, null);
        views.add(view1);
        views.add(view2);
        views.add(view3);

        /*
        PageAdapter 必须重写的四个函数：
        boolean isViewFromObject(View arg0, Object arg1)
        int getCount()
        void destroyItem(ViewGroup container, int position,Object object)
        Object instantiateItem(ViewGroup container, int position)
         */
        PagerAdapter adapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return views.size();
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
                container.removeView(views.get(position));
            }
            /*
            做了两件事，第一：将当前视图添加到container中，第二：返回当前View
             */
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(views.get(position));
                return views.get(position);
            }
        };

        viewPager.setAdapter(adapter);
    }
}
