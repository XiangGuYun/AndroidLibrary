package yxd.viewpager.case4_infinite;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import yxd.viewpager.R;

public class Case4Activity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<View> views = new ArrayList<>();
    private View view1, view2, view3;

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


    }
}
