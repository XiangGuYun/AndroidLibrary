package yxd.quick_develop;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yuyh.easyadapter.recyclerview.EasyRVHolder;

import java.util.Arrays;

import butterknife.BindView;
import yxd.quick_develop.base.context.BaseFragment;
import yxd.quick_develop.base.view.recyclerview.RVUtils;

/**
 * Created by asus on 2018/1/31.
 */

public class MainFragment extends BaseFragment {


    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.tab_btm)
    TabLayout tabBtm;

    @Override
    protected void onCreateView(View view) {
        new RVUtils(rv).manager(null).adapter(Arrays.asList(1, 2, 3, 4, 5),
                (holder, pos) -> holder.setText(R.id.tv_item, String.valueOf(pos+1)),
                position -> 0, R.layout.item);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }



}
