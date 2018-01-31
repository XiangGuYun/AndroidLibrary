package yxd.project1.context;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;

import java.util.List;
import yxd.project1.R;
import yxd.project1.base.context.BaseActivity;
import yxd.project1.base.context.FragmentUtils;
import yxd.project1.base.storage.SPUtils;
import yxd.project1.constant.Constant;
import yxd.project1.fragment.live.LiveFragment;
import yxd.project1.fragment.news.NewsFragment;
import yxd.project1.fragment.user.UserFragment;
import yxd.project1.fragment.user.UserInfoFragment;
import yxd.project1.fragment.duowan.VideosFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    public NewsFragment newsFragmnet;
    private LiveFragment liveFragment;
    private VideosFragment videosFragment;
    private UserFragment userFragment;
    private RadioGroup bottomMenu;
    private FragmentUtils fragmentUtils;
    public static int BACK_FLAG = Constant.QUIT_ACTIVITY;
    public UserInfoFragment userInfoFragment;
    public Gson gson;

    public FragmentUtils getFragmentUtils(){
        return fragmentUtils;
    }

    public RadioGroup getBottomMenu(){
        return bottomMenu;
    }

    @Override
    public void onCreate(FragmentUtils fu) {
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET}, 0);
        }
        gson = new Gson();
        bottomMenu = id(R.id.bottom_menu);
        for (int i = 0; i < bottomMenu.getChildCount(); i++) {
            bottomMenu.getChildAt(i).setOnClickListener(this);
        }
        fragmentUtils = fu;
        mainActivity = this;
        if(!SPUtils.getParam(this, Constant.CURRENT_USERNAME, "").equals("")){
            Constant.IS_LOGIN=true;
        }
    }
    private MainActivity mainActivity;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public List<Fragment> getFragments() {
        if(newsFragmnet==null)
            newsFragmnet = new NewsFragment();
        if(liveFragment==null)
            liveFragment = new LiveFragment();
        if(videosFragment==null)
            videosFragment = new VideosFragment();
        if(userFragment==null)
            userFragment = new UserFragment();
        if(!fragments.contains(newsFragmnet))
            fragments.add(newsFragmnet);
        if(!fragments.contains(liveFragment))
            fragments.add(liveFragment);
        if(!fragments.contains(videosFragment))
            fragments.add(videosFragment);
        if(!fragments.contains(userFragment))
            fragments.add(userFragment);
        return fragments;
    }

    @Override
    public int getFragmentContainerId() {
        return R.id.fl_container;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rb_info:
                fragmentUtils.switchFragment(newsFragmnet);
                changeColor(0);
                break;
            case R.id.rb_live:
                fragmentUtils.switchFragment(liveFragment);
                for (int i = 0; i < bottomMenu.getChildCount(); i++) {
                    if(i!=1){
                        ((RadioButton)(bottomMenu.getChildAt(i))).setTextColor(Color.parseColor("#ff1231"));
                    }
                }
                changeColor(1);
                break;
            case R.id.rb_video:
                fragmentUtils.switchFragment(videosFragment);
                for (int i = 0; i < bottomMenu.getChildCount(); i++) {
                    if(i!=2){
                        ((RadioButton)(bottomMenu.getChildAt(i))).setTextColor(Color.parseColor("#ff1231"));
                    }
                }
                changeColor(2);
                break;
            case R.id.rb_user:
                if(Constant.IS_LOGIN){
                    if(userInfoFragment==null) {
                        userInfoFragment = new UserInfoFragment((String)
                                SPUtils.getParam(this, Constant.CURRENT_USERNAME, ""));
                    }
                    fragmentUtils.switchFragment(userInfoFragment);
                }else {
                    fragmentUtils.switchFragment(userFragment);
                }
                changeColor(3);
                break;
        }
    }

    public void changeColor(int pos){
        for (int i = 0; i < bottomMenu.getChildCount(); i++) {
            if(i!=pos){
                ((RadioButton)(bottomMenu.getChildAt(i))).setTextColor(Color.parseColor("#aa000000"));
            }else {
                ((RadioButton)(bottomMenu.getChildAt(i))).setTextColor(Color.parseColor("#ff1231"));
            }
        }
    }

    @Override
    public void onBackPressed() {
        switch (BACK_FLAG){
            case Constant.QUIT_WEB:
                super.onBackPressed();
                bottomMenu.setVisibility(View.VISIBLE);
                BACK_FLAG = Constant.QUIT_ACTIVITY;
                break;
            case Constant.CLOSE_DRAWER_EDIT:
                newsFragmnet.drawerLayout.closeDrawers();
                BACK_FLAG = Constant.QUIT_ACTIVITY;
                break;
            case Constant.CLOSE_DRAWER_SEARCH:
                newsFragmnet.drawerLayout.closeDrawers();
                BACK_FLAG = Constant.QUIT_ACTIVITY;
                break;
            case Constant.QUIT_SEARCH_FRAGMENT:
                newsFragmnet.newsPresenter.fu_search.remove(newsFragmnet.newsPresenter.tabFragment);
                fragmentUtils.switchFragment(newsFragmnet);
                BACK_FLAG = Constant.QUIT_ACTIVITY;
                break;
            case Constant.QUIT_ACTIVITY:
                new MaterialDialog.Builder(this)
                        .title("提示")
                        .content("确定要退出吗")
                        .positiveText("取消")
                        .negativeText("确定")
                        .onPositive((dialog1, which) ->
                                dialog1.dismiss())
                        .onNegative((dialog12, which) -> {
                            dialog12.dismiss();
                            mainActivity.finish();
                        })
                        .show();
                break;
        }



    }
}
