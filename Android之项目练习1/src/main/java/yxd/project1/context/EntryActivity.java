package yxd.project1.context;


import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import yxd.project1.R;
import yxd.project1.base.common.L;
import yxd.project1.base.storage.SPUtils;
import yxd.project1.constant.Constant;
import yxd.project1.utils.BitmapUtils;
import yxd.project1.utils.MetricsUtils;

import static yxd.project1.constant.Constant.FIRST_LAUNCHER;
import static yxd.project1.constant.Constant.KEYWORD;
import static yxd.project1.constant.Constant.KEYWORD_UNSELECT;
import static yxd.project1.constant.Constant.defaultKWs;
import static yxd.project1.constant.Constant.defaultUnselectKWs;

public class EntryActivity extends FragmentActivity implements View.OnClickListener {

    private ViewPager vp_entry;
    private List<View> imgList = new ArrayList<>();
    private int[] lunboImgIds = new int[]{R.mipmap.lunbo1, R.mipmap.lunbo2};
    private int[] entryImgIds = new int[]{R.mipmap.crash_logo, R.mipmap.splash_bg};
    private int isFirstLauncher;
    private View entryView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_entry);

        if(SPUtils.getParam(this, KEYWORD, "").equals("")){
            SPUtils.setParam(this,KEYWORD, new Gson().toJson(Arrays.asList(defaultKWs)));
        }

        if(SPUtils.getParam(this, KEYWORD_UNSELECT, "").equals("")){
            SPUtils.setParam(this,KEYWORD_UNSELECT, new Gson().toJson(Arrays.asList(defaultUnselectKWs)));
        }

        isFirstLauncher = (int) SPUtils.getParam(this, FIRST_LAUNCHER, -1);
        if(isFirstLauncher==-1){
            SPUtils.setParam(this, FIRST_LAUNCHER, 0);
        }


        if(isFirstLauncher==-1){
            for (int i = 0; i < lunboImgIds.length; i++) {
                if(i!=lunboImgIds.length-1){
                    imageView = (ImageView) LayoutInflater.from(this).inflate(R.layout.entry_iv2, null);
                    imageView.setImageBitmap(BitmapUtils.decodeBitmap(this, lunboImgIds[i],
                            MetricsUtils.getScreenWidth(this), MetricsUtils.getScreenHeight(this)));
                    imgList.add(imageView);
                }else {
                    entryView = LayoutInflater.from(this).inflate(R.layout.entry_iv1, null);
                    imageView = entryView.findViewById(R.id.iv_entry);
                    imageView.setImageBitmap(BitmapUtils.decodeBitmap(this, lunboImgIds[i],
                            MetricsUtils.getScreenWidth(this), MetricsUtils.getScreenHeight(this)));
                    Button btn_entry = entryView.findViewById(R.id.btn_entry);
                    btn_entry.setOnClickListener(this);
                    imgList.add(entryView);
                }
            }
        }else {
            imageView = (ImageView) LayoutInflater.from(this).inflate(R.layout.entry_iv2, null);
            imageView.setImageBitmap(BitmapUtils.decodeBitmap(
                    this,
                    (((int)(Math.random()*100))%2==0)?entryImgIds[0]:entryImgIds[1],
                    MetricsUtils.getScreenWidth(this),
                    MetricsUtils.getScreenHeight(this)));
            imgList.add(imageView);
            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(EntryActivity.this, MainActivity.class));
                finish();
            }).start();
        }


        vp_entry=findViewById(R.id.vp_entry);
        vp_entry.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imgList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
            /*
            从当前container中删除指定位置（position）的View;
             */
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(imgList.get(position));
            }
            /*
            做了两件事，第一：将当前视图添加到container中，第二：返回当前View
             */
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(imgList.get(position));
                return imgList.get(position);
            }
        });

    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
