package yxd.project1.fragment.duowan;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import yxd.project1.R;
import yxd.project1.base.context.BaseFragment;
import yxd.project1.fragment.news.TabFragment;
import yxd.project1.utils.FragPagerUtils;

/**
 * Created by asus on 2018/1/13.
 */

public class VideosFragment extends BaseFragment {

    private ViewPager vp_videos;
    private TabLayout tl_videos;
    private FragPagerUtils fragPagerUtils;
    private List<DuoWanFragment> fragments;
    private List<String> items = new ArrayList<>();
    private String[] itemArr=new String[]{"英雄联盟", "地下城与勇士", "绝地求生","龙之谷","天涯明月刀","魔兽世界",
    "诛仙3","坦克世界","部落冲突","新寻仙","战舰世界","守望先锋","皇室战争","炉石传说","王者荣耀"};
    @Override
    protected void onCreateView(View view) {
        vp_videos=id(R.id.vp_videos);
        tl_videos=id(R.id.tl_videos);
        fragments = new ArrayList<>();
        items.addAll(Arrays.asList(itemArr));
        for (int i = 0; i < items.size(); i++) {
            fragments.add(new DuoWanFragment(items.get(i), i == 0));
        }
        fragPagerUtils=new FragPagerUtils(getActivity(), vp_videos, fragments);
        fragPagerUtils.addTabLayout(tl_videos, true, items.size(), (tab, index) -> tab.setText(items.get(index)));
        fragPagerUtils.setPagerListener(pos -> {
            fragments.get(pos).presenter.handleRV();
            if(!fragments.get(pos).needSetData)
                fragments.get(pos).needSetData=true;
//            if(!hasLoaded.contains(pos)){
//                fragments.get(pos).presenter.handleRV();
//                hasLoaded.add(pos);
//                fragments.get(pos).needSetData=true;
//            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_videos;
    }
}
