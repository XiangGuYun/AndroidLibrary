package yxd.project1.presenter.news;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import yxd.project1.R;
import yxd.project1.adapter.FragAdapter;
import yxd.project1.base.common.L;
import yxd.project1.base.context.FragmentUtils;
import yxd.project1.base.storage.SPUtils;
import yxd.project1.constant.Constant;
import yxd.project1.context.MainActivity;
import yxd.project1.fragment.news.NewsFragment;
import yxd.project1.fragment.news.TabFragment;
import yxd.project1.listener.duowan.NewsListener;
import yxd.project1.utils.CacheUtils;
import yxd.project1.view.DeleteTextView;

import static yxd.project1.constant.Constant.KEYWORD;
import static yxd.project1.constant.Constant.KEYWORD_UNSELECT;

/**
 * Created by asus on 2018/1/2.
 */

public class NewsPresenter implements View.OnClickListener,ViewPager.OnPageChangeListener{

    private NewsListener newsListener;
    private NewsFragment frag;
    private List<DeleteTextView> tvChannelList = new ArrayList<>();
    private List<String> unselectKWs;
    private MainActivity ctx;
    public TabFragment tabFragment;
    public FragmentUtils fu_search;

    public NewsPresenter(NewsListener newsListener) {
        this.newsListener = newsListener;
        frag = newsListener.get();
        ctx = (MainActivity) frag.getActivity();
        unselectKWs = frag.gson.fromJson((String) SPUtils.getParam(ctx, KEYWORD_UNSELECT, ""),
                new TypeToken<List<String>>(){}.getType());
        if(fu_search==null){
            fu_search=new FragmentUtils(ctx, R.id.fl_container_search);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_edit://添加按钮->打开侧滑菜单
                if(frag.editChannelView.getChildAt(1).getVisibility()==View.VISIBLE){
                    frag.editChannelView.getChildAt(1).setVisibility(View.GONE);
                    frag.editChannelView.getChildAt(0).setVisibility(View.VISIBLE);
                }
                frag.drawerLayout.openDrawer(newsListener.get().editChannelView, true);
                MainActivity.BACK_FLAG= Constant.CLOSE_DRAWER_EDIT;
                break;
            case R.id.iv_search://搜寻按钮
                if(frag.editChannelView.getChildAt(0).getVisibility()==View.VISIBLE){
                    frag.editChannelView.getChildAt(0).setVisibility(View.GONE);
                    frag.editChannelView.getChildAt(1).setVisibility(View.VISIBLE);
                }
                frag.drawerLayout.openDrawer(newsListener.get().editChannelView, true);
                MainActivity.BACK_FLAG= Constant.CLOSE_DRAWER_SEARCH;
                break;
            case R.id.iv_back_edit://回退按钮->关闭编辑频道菜单
                frag.drawerLayout.closeDrawer(newsListener.get().editChannelView);
                MainActivity.BACK_FLAG=Constant.QUIT_ACTIVITY;
                break;
            case R.id.iv_back_search://回退按钮->关闭搜索栏菜单
                frag.drawerLayout.closeDrawer(newsListener.get().editChannelView);
                MainActivity.BACK_FLAG=Constant.QUIT_ACTIVITY;
                break;
            case R.id.tv_channel_edit://"编辑"文本
                TextView view = (TextView) v;
                if(!DeleteTextView.needBitmap){//判断之前频道文本是否需要显示删除图标
                    DeleteTextView.needBitmap=true;//点击事件改变了需要性，此时为需要
                    view.setText("取消编辑");
                    for (int i = 0; i < tvChannelList.size(); i++) {
                        tvChannelList.get(i).chonghui();//重绘并显示删除图标
                        tvChannelList.get(i).setClickable(true);//设置可点击
                        tvChannelList.get(i).setTag(i);//设置标记
                        tvChannelList.get(i).setOnClickListener(this);//设置点击事件
                    }
                }else {
                    DeleteTextView.needBitmap=false;//点击事件改变了需要性，此时为不需要
                    view.setText("编辑");
                    for (DeleteTextView tv:tvChannelList) {
                        tv.chonghui();//重绘并隐藏删除图标
                        tv.setClickable(false);//设置不可点击
                    }
                }
                break;
            case R.id.tv_channel://频道文本
                int pos = (int)v.getTag();//获取文本索引标记
                frag.keywords.remove((int)v.getTag());//将该索引位置的关键字从数据中删除
                frag.fragments.remove(pos);
                //更新列表
                frag.rvUtils.getAdapter().notifyItemRemoved(pos);
                //frag.rvUtils.getAdapter().notifyItemRangeChanged(0,frag.keywords.size());
                //保存新数据到本地
                SPUtils.setParam(ctx, KEYWORD, frag.gson.toJson(frag.keywords));
                //刷新ViewPager和TabLayout
                frag.fragmentAdapter.notifyDataSetChanged();
                //重新给TabLayout设置文本信息
                for (int i = 0; i < frag.keywords.size(); i++) {
                    frag.tabLayout.getTabAt(i).setText(frag.keywords.get(i));
                }
                //清除hasLoad中的那个元素
                Integer intObj = pos;
                frag.hasLoaded.remove(intObj);
                unselectKWs.add(((TextView)v).getText().toString());
                SPUtils.setParam(ctx, KEYWORD_UNSELECT, frag.gson.toJson(unselectKWs));
                frag.rvUtils1.getAdapter().notifyDataSetChanged();
                //更新缓存
                CacheUtils.getInstance().saveListToCache(Constant.LOADED_TAG, frag.hasLoaded);
                CacheUtils.getInstance().getDiskHelper().remove(((TextView)v).getText().toString());
                break;
            case R.id.iv_add_channel://添加频道
                String channelName = ((TextView)((ViewGroup)v.getParent()).getChildAt(0)).getText().toString();
                frag.keywords.add(channelName);
                frag.fragments.add(new TabFragment(channelName));
                frag.rvUtils.getAdapter().notifyDataSetChanged();
                SPUtils.setParam(ctx, KEYWORD, frag.gson.toJson(frag.keywords));
                frag.fragmentAdapter.notifyDataSetChanged();
                for (int i = 0; i < frag.keywords.size(); i++) {
                    L.e("空指针查找 "+(frag.tabLayout.getTabAt(i)==null));
                    frag.tabLayout.getTabAt(i).setText(frag.keywords.get(i));
                }
                unselectKWs.remove(channelName);
                SPUtils.setParam(ctx, KEYWORD_UNSELECT, frag.gson.toJson(unselectKWs));
                frag.rvUtils1.getAdapter().notifyDataSetChanged();
                break;
            case R.id.tv_search://点击“搜索”文本
                String kw = frag.searchBar.getText();
                tabFragment = new TabFragment(kw);
                fu_search.switchFragment(tabFragment);
                frag.searchBar.setText("");
                //MainActivity.BACK_FLAG=QUIT_SEARCH_FRAGMENT;
                break;
        }
    }

    /*
    处理频道列表
     */
    public void handleChannelRV() {
        if(tvChannelList.size()!=0){
            tvChannelList.clear();
        }
        frag.rvUtils
                .manager(frag.rvUtils.getGridManager(4))//4列网格管理器
                .animator(null)//默认动画
                .adapter(frag.keywords, //数据源
                        (holder, pos) -> {
                    //holder.getView(R.id.tv_channel).setClickable(false);
                    //显示频道文本
                    holder.setText(R.id.tv_channel, frag.keywords.get(pos));
                    //将当前TextView加入到集合
                    tvChannelList.add(holder.getView(R.id.tv_channel));
                    L.e("观察关键数据："+tvChannelList.size());
                    //为TextView设置点击事件
                    holder.setOnClickListener(R.id.tv_channel, v -> {
                        DeleteTextView.needBitmap = true;//需要在右上角显示删除图标
                        ((DeleteTextView)v).chonghui();//重绘TV
                    });
                }, position -> 0, R.layout.item_channel);
    }

    /*
    处理新闻ViewPager
     */
    public void handleFragmentViewPager() {
        //根据关键字的数量来将对应数量的TabFragment添加到集合中
        for (int i = 0; i < frag.keywords.size(); i++)
            frag.fragments.add(new TabFragment(frag.keywords.get(i)));
        //创建ViewPager的适配器，并传入TabFragment集合
        frag.fragmentAdapter = new FragAdapter(ctx.getSupportFragmentManager(), frag.fragments);
        //设置适配器
        frag.viewPager.setAdapter(frag.fragmentAdapter);
        //设置页面翻转监听
        frag.viewPager.setOnPageChangeListener(this);
    }

    /*
    处理顶部标签栏
     */
    public void handleTopTabLayout() {
        frag.tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//设置滑动Tab模式
        for (String kw : frag.keywords) {//添加Tab选项卡
            frag.tabLayout.addTab(frag.tabLayout.newTab());
        }
        //将TabLayout和ViewPager关联起来
        frag.tabLayout.setupWithViewPager(frag.viewPager, true);
        //Tab属性必须在关联ViewPager之后设置
        for (int i = 0; i < frag.keywords.size(); i++) {
            frag.tabLayout.getTabAt(i).setText(frag.keywords.get(i));//设置Tab文本
        }
    }

    public void setOnClickListener(View view){
        view.setOnClickListener(this);
    }

    @Override//监听当翻转到某个页面
    public void onPageSelected(int position) {
        //如果当前的页面索引不为0并且这个位置已经加载过页面
        if(position!=0&!frag.hasLoaded.contains(position)){
            //调用TabFragment的getNewsByOkHttpOrCache来获取新闻数据
            ((TabFragment)frag.fragments.get(position)).tabPresentor.getNewsByOkHttpOrCache();
            frag.hasLoaded.add(position);//记录这个加载过页面的索引
        }
    }

    /*
    处理搜索栏
     */
    public void handleSearchBar() {
        frag.iv_back_search.setOnClickListener(this);
        frag.tv_search.setOnClickListener(this);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    public void handleEditChannelRV() {
        frag.rvUtils1.manager(null)
                .decorate(null)
                .animator(null)
                .adapter(unselectKWs, (holder, pos) -> {
                    holder.setText(R.id.tv_unselect_channel, unselectKWs.get(pos));
                    holder.getView(R.id.iv_add_channel).setOnClickListener(this);
                }, position -> 0, R.layout.rv_item_add_channel);

    }

}
