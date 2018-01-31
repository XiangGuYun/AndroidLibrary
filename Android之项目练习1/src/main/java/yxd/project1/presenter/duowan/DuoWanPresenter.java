package yxd.project1.presenter.duowan;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yuyh.easyadapter.recyclerview.EasyRVHolder;

import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yxd.project1.R;
import yxd.project1.base.common.L;
import yxd.project1.base.view.recyclerview.RVUtils;
import yxd.project1.bean.Videos;
import yxd.project1.constant.Constant;
import yxd.project1.constant.URL;
import yxd.project1.context.MainActivity;
import yxd.project1.fragment.duowan.DuoWanFragment;
import yxd.project1.fragment.duowan.VideoActivity;
import yxd.project1.listener.news.DuoWanListener;
import yxd.project1.network.MyOkHttpUtils;
import yxd.project1.utils.BitmapUtils;
import yxd.project1.utils.CacheUtils;
import yxd.project1.utils.MetricsUtils;
import yxd.project1.utils.StringUtils;

import static yxd.project1.constant.Constant.KEYWORD;

/**
 * Created by asus on 2018/1/12.
 */

public class DuoWanPresenter{

    private DuoWanListener listener;
    private MainActivity act;
    private DuoWanFragment frag;
    private RVUtils rvUtils;
    private Videos videos;
    private int pageToken;
    private List<Videos.VideoData> data;

    public DuoWanPresenter(DuoWanListener listener, DuoWanFragment frag) {
        this.listener = listener;
        this.frag = frag;
        act= (MainActivity) frag.getActivity();
    }

    /*
    处理列表
     */
    public void handleRV() {
        if(videos==null){//判断数据是否为空
            //如果为空则先从本地缓存中去取
            videos = (Videos) CacheUtils.getInstance().getDataFromDiskCache(frag.fragTag, Videos.class);
            if(videos==null){
                //如果本地没有缓存就使用网络下载
                frag.rotateLoading.start();
                MyOkHttpUtils.get1(act, getFirstURL(), response -> {
                   act.runOnUiThread(() -> {
                       videos=act.gson.fromJson(response,Videos.class);
                       pageToken=Integer.parseInt(videos.getPageToken());
                       CacheUtils.getInstance().saveDataToDiskCache(frag.fragTag, videos);
                       setData();
                       frag.rotateLoading.stop();
                   });
                }, e -> frag.toast("请求网络失败！"));
            }else {
                //如果有则使用本地的缓存数据
                setData();
            }
        }
    }

    /*
    设置列表数据
     */
    private void setData() {
        videos = (Videos) CacheUtils.getInstance().getDataFromDiskCache(frag.fragTag, Videos.class);
        if(rvUtils==null)
            rvUtils = new RVUtils(frag.rv_duwan);
        data = videos.getData();
        rvUtils.manager(null).decorate(null).animator(null)
                .adapter(data, (holder, pos) -> {
                    holder.setText(R.id.tv_title, data.get(pos).getTitle());
                    holder.setText(R.id.tv_viewCount, data.get(pos).getViewCount()+"");
                    holder.setText(R.id.tv_publishDateStr, data.get(pos).getPublishDateStr());
                    holder.setText(R.id.tv_posterScreenName, data.get(pos).getPosterScreenName());
                    BitmapUtils.showBitmap1(act,holder.getView(R.id.iv_coverUrl),data.get(pos).getCoverUrl());
                    //holder.getView(R.id.iv_coverUrl).set
                    holder.getView(R.id.iv_coverUrl).setOnClickListener(v -> {
                        if (data.get(pos).getVideoUrls()!=null&&data.get(pos).getVideoUrls().length>0){
                            Intent intent = new Intent(act, VideoActivity.class);
                            intent.putExtra(Constant.VIDEO_URL, data.get(pos).getVideoUrls()[0]);
                            act.startActivity(intent);
                        }else {
                            Toast.makeText(act, "没有视频", Toast.LENGTH_SHORT).show();
                        }
                    });}, position -> 0, R.layout.item_duowan_videos);
    }

    /*
    下拉刷新
     */
    public void refreshList() {
        if(videos.isHasNext()){
            pageToken++;
        }else {
            pageToken=0;
        }
        MyOkHttpUtils.get1(act, getURL(), response -> {
           act.runOnUiThread(() -> {
               Videos newVideos = act.gson.fromJson(response, Videos.class);
               if(null!=newVideos.getData()){
                   data.addAll(0, newVideos.getData());//将新的数据合并到dataList中
                   CacheUtils.getInstance().saveDataToDiskCache(frag.fragTag, videos);
                   rvUtils.getAdapter().notifyDataSetChanged();
                   frag.refreshLayout.finishRefresh();
               }else {
                   Toast.makeText(act, "刷新失败", Toast.LENGTH_SHORT).show();
                   frag.refreshLayout.finishRefresh();
               }
           });
        }, e -> {
            Toast.makeText(act, "请求失败", Toast.LENGTH_SHORT).show();
            frag.refreshLayout.finishRefresh();
        });
    }

    /*
    上拉加载
     */
    public void addMoreItems() {

    }

    //非核心函数-------------------------------------------------------------------------------------

    public String getURL() {
        return StringUtils.appendStr(URL.DUOWAN_BASE_URL, "?kw=", frag.fragTag,
                "&pageToken=", pageToken + "", URL.DUOWAN_APIKEY);
    }

    public String getFirstURL() {
        return  StringUtils.appendStr(URL.DUOWAN_BASE_URL, "?kw=", frag.fragTag, "&pageToken=0", URL.DUOWAN_APIKEY);
    }
}

