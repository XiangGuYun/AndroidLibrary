package yxd.project1.presenter.news;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.yuyh.easyadapter.recyclerview.EasyRVHolder;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import yxd.project1.R;
import yxd.project1.base.common.L;
import yxd.project1.base.context.FragmentUtils;
import yxd.project1.base.view.recyclerview.RVUtils;
import yxd.project1.bean.News;
import yxd.project1.constant.Constant;
import yxd.project1.context.MainActivity;
import yxd.project1.fragment.news.DetailFragment;
import yxd.project1.listener.duowan.TabLisener;
import yxd.project1.network.MyOkHttpUtils;
import yxd.project1.utils.BitmapUtils;
import yxd.project1.utils.CacheUtils;
import yxd.project1.utils.StringUtils;

import static yxd.project1.constant.Constant.KEYWORD;
import static yxd.project1.constant.Constant.NO_IMG;
import static yxd.project1.constant.Constant.ONE_IMG;
import static yxd.project1.constant.Constant.ON_GET_DATA;
import static yxd.project1.constant.Constant.THREE_IMG;

/**
 * Created by asus on 2018/1/1.
 */

public class TabPresenter {

    private Gson gson;
    private TabLisener tabLisener;//回调给View层的Fragment来获取帮助
    private RecyclerView rv;
    private FragmentActivity context;
    private RVUtils rvUtils;//RecyclerView工具类
    private TabHandler tabHandler;//自定义Handler
    /*
    新闻数据相关
     */
    private News news;//新闻
    private int pageToken = 1;//页码令牌
    private boolean hasNext = true;//是否有下一页
    private List<News.Data> dataList;//新闻数据
    private boolean isNeedNet=true;//是否需要用网络下载数据

    public TabPresenter(FragmentActivity ctx, TabLisener tabLisener) {
        context = ctx;
        this.tabLisener = tabLisener;
        KEYWORD = tabLisener.getKeyWord();
        gson = new Gson();
        if(CacheUtils.getInstance().getDataFromCache(KEYWORD)==null){
            //缓存中无数据时，初始化空集合并设置需要网络下载
            dataList = new ArrayList<>();
            isNeedNet=true;
        }else {
            //缓存中有数据时，判断新闻数据集合是否为空
            news = CacheUtils.getInstance().getDataFromCache(KEYWORD);
            dataList = news.getData();
            if(dataList==null){
                dataList = new ArrayList<>();
                isNeedNet=true;
            }else {
                isNeedNet=false;
            }
        }

    }

    /*
   处理RecyclerView的显示
    */
    public void handleRecyclerView()
    {
        if(rv==null){
            rv = tabLisener.getRecyclerView();
            rvUtils = new RVUtils(rv);
            rvUtils.manager(null)
                    .decorate(null)
                    .animator(null);
            tabHandler = new TabHandler(context, rvUtils, dataList, tabLisener);
            getNewsByOkHttpOrCache();
        }
    }


    /*
    下拉刷新列表
     */
    public void refreshList() {
        if(hasNext){
            pageToken++;
        }else {
            pageToken=1;
        }
        MyOkHttpUtils.get(StringUtils.appendURL(KEYWORD, "" + pageToken), new MyOkHttpUtils.Result() {
            @Override
            public void success(String response) {
                context.runOnUiThread(() -> {
                    News news = gson.fromJson(response, News.class);
                    dataList.addAll(0, news.getData());//将新的数据合并到dataList中
                    hasNext = news.isHasNext();
                    saveToCache();//将刷新后的数据保存到缓存中
                    rvUtils.getAdapter().notifyDataSetChanged();
                    tabLisener.getShSwipeRefreshLayout().finishRefresh();
                });

            }

            @Override
            public void failure(IOException e) {

            }
        });
    }

    /*
    上拉加载更多
     */
    public void addMoreItems() {
        if(hasNext){
            pageToken++;
        }else {
            pageToken=1;
        }
        MyOkHttpUtils.get(StringUtils.appendURL(KEYWORD, "" + pageToken), new MyOkHttpUtils.Result() {
            @Override
            public void success(String response) {
               context.runOnUiThread(() -> {
                   News news = gson.fromJson(response, News.class);
                   dataList.addAll(news.getData());
                   hasNext = news.isHasNext();
                   saveToCache();//将加载后的数据保存到缓存中
                   rvUtils.getAdapter().notifyDataSetChanged();
                   tabLisener.getShSwipeRefreshLayout().finishLoadmore();
               });
            }

            @Override
            public void failure(IOException e) {

            }
        });
    }
    
    /*
    通过网络或从缓存中获得数据
     */
    public void getNewsByOkHttpOrCache(){
        if(!isNeedNet){//如果缓存中有数据，就使用缓存中的数据
            L.e("没下载------------------------");
            tabHandler.sendEmptyMessage(ON_GET_DATA);
            return;
        }
        Log.e("Test", "下载了---------------------");
        tabLisener.showProgressDialog();
        //如果缓存中没有数据就使用网络下载数据
        MyOkHttpUtils.get(StringUtils.appendURL(KEYWORD, pageToken + ""),
                new MyOkHttpUtils.Result() {

                    @Override
                    public void success(String response) {
                       context.runOnUiThread(() -> {
                           tabLisener.dismissProgressDialog();
                           L.d("执行下载");
                           news = gson.fromJson(response, News.class);
                           saveToCache();//将下载的数据缓存起来
                           if(news.getData()!= null){
                               dataList.addAll(news.getData());
                               hasNext = news.isHasNext();
                               tabHandler.sendEmptyMessage(ON_GET_DATA);
                           }
                       });
                    }

                    @Override
                    public void failure(IOException e) {

                    }
                });
    }

    /*
    将新闻保存到缓存中
     */
    public void saveToCache(){
        CacheUtils.Instance instance = CacheUtils.getInstance();
        instance.saveDataToCache(KEYWORD, news);
    }
    

    static class TabHandler extends Handler implements View.OnClickListener {
        private int itemIndex;
        private int[] imgArr = {R.id.img1, R.id.img2, R.id.img3};//三图列表项的ImageView的id
        private WeakReference<RVUtils> rvUtilsReference;//RecyclerView工具类的弱引用
        private WeakReference<Context> ctxReference;//Context的弱引用
        private WeakReference<List<News.Data>> dataReference;//新闻数据的弱引用
        private WeakReference<TabLisener> tabLisenerWeakReference;
        private FragmentUtils fragmentUtils;
        private DetailFragment detailFragment;

        public TabHandler(Context ctx, RVUtils rvUtils, List<News.Data> datas, TabLisener tabLisener){
            rvUtilsReference = new WeakReference<>(rvUtils);
            ctxReference = new WeakReference<>(ctx);
            dataReference = new WeakReference<>(datas);
            tabLisenerWeakReference = new WeakReference<>(tabLisener);
            fragmentUtils = tabLisenerWeakReference.get().getFragmengUtils();
            detailFragment = new DetailFragment();
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case ON_GET_DATA://当完成了数据获取
                    handleNewsData();
                    break;
            }
        }
        /*
        处理新闻数据
         */
        private void handleNewsData() {
            rvUtilsReference.get()
                    .adapter(//为RecyclerView设置适配器
                    dataReference.get(), //传入数据，由构造方法传入，来源1网络下载，来源2缓存
                    (holder, pos) -> {
                        detailFragment.setWebUrl(dataReference.get().get(pos).getUrl());
                        setText(holder, dataReference.get(), pos);//为每个列表项实现新闻发布时间
                        switch (itemIndex){
                            case NO_IMG:
                                holder.setOnClickListener(R.id.item1, this);
                                break;
                            case ONE_IMG://一图列表项
                                holder.setOnClickListener(R.id.item2, this);
                                for (String img:dataReference.get().get(pos).getImageUrls())
                                    BitmapUtils.showBitmap(ctxReference.get(), holder.getView(R.id.iv_1img), img);
                                break;
                            case THREE_IMG://三图列表项
                                holder.setOnClickListener(R.id.item3, this);
                                for (int i = 0; i < 3; i++) {
                                    BitmapUtils.showBitmap(ctxReference.get(),
                                            holder.getView(imgArr[i]),
                                            dataReference.get().get(pos).getImageUrls()[i]);
                                }
                                break;
                        }
                    },
                    (int pos) -> {
                        //根据图片数组的长度来返回不同的列表项并标记到itemIndex
                        if(null==dataReference.get().get(pos).getImageUrls()){
                            return itemIndex = 0;
                        }else if(dataReference.get().get(pos).getImageUrls().length==1){
                            return itemIndex=1;
                        }else {
                            return itemIndex=2;
                        }
                    },//传入需要使用的列表项布局id
                    R.layout.item_news1, R.layout.item_news2, R.layout.item_news3);
        }
        
        /*
        显示发布时间文本
         */
        private void setText(EasyRVHolder holder, List<News.Data> data, int pos){
            holder.setText(R.id.tv_title, data.get(pos).getTitle());
            holder.setText(R.id.tv_source, data.get(pos).getPosterScreenName());
            long publish = data.get(pos).getPublishDate();
            long current = System.currentTimeMillis()/1000;
            long minutes = (current-publish)/(60);
            if(minutes<60){
                holder.setText(R.id.tv_time, minutes+"分钟前");
            }else {
                holder.setText(R.id.tv_time, (int)(minutes/60)+"小时前");
            }
        }

        @Override
        public void onClick(View v) {
            MainActivity.BACK_FLAG= Constant.QUIT_WEB;
            fragmentUtils.switchFragmentWithStack(detailFragment);
        }
    }
}
