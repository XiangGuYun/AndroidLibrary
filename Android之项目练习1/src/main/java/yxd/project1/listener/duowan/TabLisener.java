package yxd.project1.listener.duowan;

import android.support.v7.widget.RecyclerView;

import com.scu.miomin.shswiperefresh.core.SHSwipeRefreshLayout;

import yxd.project1.base.context.FragmentUtils;

/**
 * Created by asus on 2018/1/1.
 */

public interface TabLisener {

    RecyclerView getRecyclerView();

    SHSwipeRefreshLayout getShSwipeRefreshLayout();

    String getKeyWord();

    void showProgressDialog();

    void dismissProgressDialog();

    FragmentUtils getFragmengUtils();
}
