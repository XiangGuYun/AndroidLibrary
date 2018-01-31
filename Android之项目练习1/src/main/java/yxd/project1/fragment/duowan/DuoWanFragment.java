package yxd.project1.fragment.duowan;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.scu.miomin.shswiperefresh.core.SHSwipeRefreshLayout;
import com.victor.loading.rotate.RotateLoading;

import org.videolan.libvlc.Dialog;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import yxd.project1.R;
import yxd.project1.base.context.BaseFragment;
import yxd.project1.bean.Videos;
import yxd.project1.constant.URL;
import yxd.project1.listener.news.DuoWanListener;
import yxd.project1.network.MyOkHttpUtils;
import yxd.project1.presenter.duowan.DuoWanPresenter;

/**
 * Created by asus on 2017/12/31.
 */

@SuppressLint("ValidFragment")
public class DuoWanFragment extends BaseFragment implements DuoWanListener,
        SHSwipeRefreshLayout.SHSOnRefreshListener{

    public RecyclerView rv_duwan;
    public DuoWanPresenter presenter;
    public SurfaceView surfaceView;
    public SHSwipeRefreshLayout refreshLayout;
    public RotateLoading rotateLoading;
    public String fragTag;
    public boolean needSetData;

    @SuppressLint("ValidFragment")
    public DuoWanFragment(String fragTag, boolean needSetData){
        this.fragTag=fragTag;
        this.needSetData=needSetData;
    }

    @Override
    protected void onCreateView(View view) {
        initView();
        presenter = new DuoWanPresenter(this, this);
        if(needSetData)
            presenter.handleRV();
    }

    private void initView() {
        rv_duwan=id(R.id.rv_duwan);
        surfaceView=id(R.id.surface);
        refreshLayout=id(R.id.swipeRefreshLayout);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setLoadmoreEnable(false);
        rotateLoading=id(R.id.rotateloading);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_read;
    }

    @Override
    public void onRefresh() {
        refreshLayout.postDelayed(() -> presenter.refreshList(), 1000);//刷新延迟
    }

    @Override
    public void onLoading() {
        refreshLayout.postDelayed(() -> presenter.addMoreItems(), 1000);//加载延迟
    }

    @Override
    public void onRefreshPulStateChange(float v, int i) {
        switch (i) {
            case SHSwipeRefreshLayout.NOT_OVER_TRIGGER_POINT:
                refreshLayout.setLoaderViewText("下拉刷新");
                break;
            case SHSwipeRefreshLayout.OVER_TRIGGER_POINT:
                refreshLayout.setLoaderViewText("松开刷新");
                break;
            case SHSwipeRefreshLayout.START:
                refreshLayout.setLoaderViewText("正在刷新");
                break;
        }
    }

    @Override
    public void onLoadmorePullStateChange(float v, int i) {
        switch (i) {
            case SHSwipeRefreshLayout.NOT_OVER_TRIGGER_POINT:
                refreshLayout.setLoaderViewText("上拉加载");
                break;
            case SHSwipeRefreshLayout.OVER_TRIGGER_POINT:
                refreshLayout.setLoaderViewText("松开加载");
                break;
            case SHSwipeRefreshLayout.START:
                refreshLayout.setLoaderViewText("正在加载");
                break;
        }
    }
}
