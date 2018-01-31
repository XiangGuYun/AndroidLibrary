package yxd.fragment.case2_viewpager_fragment_tablayout;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;

/**
 * Created by asus on 2018/1/13.
 */

public class FragPagerUtils {

    private ViewPager viewPager;
    private List<Fragment> fragments;
    private FragAdapter adapter;

    public FragPagerUtils(FragmentActivity act, ViewPager viewPager, List<Fragment> fragments) {
        this.viewPager = viewPager;
        this.fragments = fragments;
        adapter = new FragAdapter(act.getSupportFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
    }

    public void setPagerListener(final PagerSelectListener listener){
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                listener.select(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void addTabLayout(TabLayout tabLayout, boolean isScroll, int tabNum, TabListener listener){
        if(isScroll){
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//设置滑动Tab模式
        }else {
            tabLayout.setTabMode(TabLayout.MODE_FIXED);//设置固定Tab模式
        }

        for (int i = 0; i < tabNum; i++) {
            tabLayout.addTab(tabLayout.newTab());
        }
        //将TabLayout和ViewPager关联起来
        tabLayout.setupWithViewPager(viewPager, true);
        //Tab属性必须在关联ViewPager之后设置
        for (int i = 0; i < tabNum; i++) {
            listener.setTabContent(tabLayout.getTabAt(i), i);
        }
    }

    public interface TabListener{
        void setTabContent(TabLayout.Tab tab, int index);
    }

    public interface PagerSelectListener{
        void select(int pos);
    }


    public ViewPager getViewPager() {
        return viewPager;
    }

    public List<Fragment> getFragments() {
        return fragments;
    }

    public FragAdapter getAdapter() {
        return adapter;
    }

    public class FragAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> fragments;

        public FragAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments=fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public int getItemPosition(Object object) {
            // TODO Auto-generated method stub
            return PagerAdapter.POSITION_NONE;
        }

    }
}
