package yxd.project1.fragment.news;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.scu.miomin.shswiperefresh.core.SHSwipeRefreshLayout;

import yxd.project1.R;
import yxd.project1.base.context.BaseFragment;
import yxd.project1.base.context.FragmentUtils;
import yxd.project1.context.MainActivity;
import yxd.project1.listener.duowan.TabLisener;
import yxd.project1.presenter.news.TabPresenter;

/**
 * Created by asus on 2018/1/1.
 */

public class TabFragment extends BaseFragment implements TabLisener,
SHSwipeRefreshLayout.SHSOnRefreshListener{

    private RecyclerView rv;
    public TabPresenter tabPresentor;
    private SHSwipeRefreshLayout swipeRefreshLayout;
    private ProgressDialog progressDialog;

    private String keyword;

    public TabFragment(){
        keyword = "游戏";
    }

    @SuppressLint("ValidFragment")
    public TabFragment(String keyword){
        this.keyword=keyword;

    }

    @Override
    protected void onCreateView(View view) {
        initViews();

        swipeRefreshLayout.setOnRefreshListener(this);
        tabPresentor = new TabPresenter(getActivity(), this);
        tabPresentor.handleRecyclerView();
        Log.d("Test", "执行了oCV");
    }

    private void initViews() {
        rv=id(R.id.rv);
        swipeRefreshLayout=id(R.id.swipeRefreshLayout);
        progressDialog = new ProgressDialog(getActivity());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tab;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return rv;
    }

    @Override
    public SHSwipeRefreshLayout getShSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }

    @Override
    public String getKeyWord() {
        return keyword;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public FragmentUtils getFragmengUtils() {
        return ((MainActivity)getActivity()).getFragmentUtils();
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.postDelayed(() -> tabPresentor.refreshList(), 1000);//刷新延迟
    }

    @Override
    public void onLoading() {
        swipeRefreshLayout.postDelayed(() -> tabPresentor.addMoreItems(), 1000);//加载延迟
    }

    @Override
    public void onRefreshPulStateChange(float percent, int state) {
        switch (state) {
            case SHSwipeRefreshLayout.NOT_OVER_TRIGGER_POINT:
                swipeRefreshLayout.setLoaderViewText("下拉刷新");
                break;
            case SHSwipeRefreshLayout.OVER_TRIGGER_POINT:
                swipeRefreshLayout.setLoaderViewText("松开刷新");
                break;
            case SHSwipeRefreshLayout.START:
                swipeRefreshLayout.setLoaderViewText("正在刷新");
                break;
        }
    }

    @Override
    public void onLoadmorePullStateChange(float percent, int state) {
        switch (state) {
            case SHSwipeRefreshLayout.NOT_OVER_TRIGGER_POINT:
                swipeRefreshLayout.setLoaderViewText("上拉加载");
                break;
            case SHSwipeRefreshLayout.OVER_TRIGGER_POINT:
                swipeRefreshLayout.setLoaderViewText("松开加载");
                break;
            case SHSwipeRefreshLayout.START:
                swipeRefreshLayout.setLoaderViewText("正在加载");
                break;
        }
    }

    @Override
    public void onDestroy() {
//        L.d("保存了");
//        tabPresentor.saveToCache();
        super.onDestroy();
    }
}
