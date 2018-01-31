package yxd.project1.fragment.news;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

import yxd.project1.R;
import yxd.project1.adapter.FragAdapter;
import yxd.project1.base.context.BaseFragment;
import yxd.project1.base.storage.SPUtils;
import yxd.project1.base.view.recyclerview.RVUtils;
import yxd.project1.constant.Constant;
import yxd.project1.listener.duowan.NewsListener;
import yxd.project1.presenter.news.NewsPresenter;
import yxd.project1.utils.CacheUtils;

import static yxd.project1.constant.Constant.LOADED_TAG;

/**
 * Created by asus on 2017/12/31.
 */
/*
新闻Fragment
职责
1.使用一个ViewPager显示多个Fragment页面，并和TabLayout绑定。
 */
public class NewsFragment extends BaseFragment implements NewsListener{
    /*
    UI
     */
    public ViewPager viewPager;//新闻翻页器
    public RecyclerView rv_channel, rv_add_channel;//频道列表
    public ImageView iv_search, iv_edit, iv_back_edit, iv_back_search;//搜寻图标、添加图标、回退图标
    public TabLayout tabLayout;//顶部标签栏
    public ActionBarDrawerToggle toggle;//侧滑菜单开关
    public DrawerLayout drawerLayout;//侧滑菜单
    public LinearLayout editChannelView;//侧滑菜单页面
    public TextView tv_edit_channel, tv_search;//频道编辑文本
    public MaterialSearchBar searchBar;
    /*
    集合
     */
    public List<Fragment> fragments = new ArrayList<>();//新闻页面集合
    public List<String> keywords = new ArrayList<>();//新闻关键字集合
    public List<Integer> hasLoaded;//已经加载的页面序号
    public FragAdapter fragmentAdapter;//翻页适配器
    /*
    其它
     */
    public Gson gson;
    public RVUtils rvUtils, rvUtils1;//列表工具
    public NewsPresenter newsPresenter;//新闻业务逻辑处理者

    @Override
    protected void onCreateView(View view) {
        initView();//初始化UI
        initData();//初始化数据
        newsPresenter.handleChannelRV();//处理频道列表
        newsPresenter.handleFragmentViewPager();//处理ViewPager
        newsPresenter.handleTopTabLayout();//处理TabLayout
        newsPresenter.handleEditChannelRV();//处理编辑频道列表
        newsPresenter.handleSearchBar();//处理搜索栏
    }

    private void initData() {
        //初始化列表工具
        rvUtils = new RVUtils(rv_channel);
        rvUtils1 = new RVUtils(rv_add_channel);
        //从本地获取新闻关键字的JSON字符串
        String channels = (String) SPUtils.getParam(getActivity(), Constant.KEYWORD, "");
        //将JSON字符串转换为关键字集合
        keywords = gson.fromJson(channels, new TypeToken<List<String>>(){}.getType());
        //判断本地缓存中是否有hasLoaded集合并进行初始化
        if(CacheUtils.getInstance().getListFromCache(LOADED_TAG)==null){
            hasLoaded = new ArrayList<>();
        }else {
            hasLoaded = CacheUtils.getInstance().getListFromCache(LOADED_TAG);
        }
    }

    private void initView() {
        gson = new Gson();
        iv_back_search=id(R.id.iv_back_search);
        tv_search=id(R.id.tv_search);
        searchBar=id(R.id.searchBar);
        rv_add_channel=id(R.id.rv_add_channel);
        newsPresenter = new NewsPresenter(this);
        viewPager=id(R.id.viewpager);
        viewPager.setOffscreenPageLimit(keywords.size());
        tabLayout=id(R.id.tabs);
        iv_edit = id(R.id.iv_edit);
        newsPresenter.setOnClickListener(iv_edit);
        iv_search = id(R.id.iv_search);
        newsPresenter.setOnClickListener(iv_search);
        iv_back_edit=id(R.id.iv_back_edit);
        newsPresenter.setOnClickListener(iv_back_edit);
        rv_channel=id(R.id.rv_channel);
        tv_edit_channel=id(R.id.tv_channel_edit);
        newsPresenter.setOnClickListener(tv_edit_channel);
        drawerLayout = id(R.id.main_drawer_layout);
        //设置菜单内容之外其他区域的背景色
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        //左边菜单
        //右边菜单
        editChannelView = id(R.id.editChannelView);
//        searchChannelView = id(R.id.searchChannelView);
        toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, R.mipmap.ic_launcher,
                R.string.drawer_open, R.string.drawer_close) {
            //菜单打开
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            // 菜单关闭
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.setDrawerListener(toggle);
        //关闭手势滑动
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
        drawerLayout.closeDrawer(editChannelView);
    }

    @Override//返回NewsFragment的布局ID
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    public NewsFragment get() {
        return this;
    }

    @Override
    public void onDestroy() {
        CacheUtils.getInstance().saveListToCache(LOADED_TAG, hasLoaded);
        super.onDestroy();
    }
}
