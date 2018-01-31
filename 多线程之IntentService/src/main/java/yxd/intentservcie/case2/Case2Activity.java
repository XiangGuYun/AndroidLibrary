package yxd.intentservcie.case2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import yxd.intentservcie.R;

public class Case2Activity extends AppCompatActivity {

    private Intent intent;
    private ImageView imageView;
    private static final String URL = "https://www.baidu.com/img/superlogo_c4d7df0a003d3db9b65e9ef0fe6da1ec.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case2);
        imageView = findViewById(R.id.iv);
        //注册EventBus
        EventBus.getDefault().register(this);
        intent = new Intent(this, Case2Service.class);
    }

    //注解中的线程模式表示该方法将执行在主线程
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(Bitmap bitmap){
        if(bitmap != null)//显示图片
            imageView.setImageBitmap(bitmap);
    }

    /*
    下载，启动IntentService，并传入图片URL
     */
    public void download(View view) {
        intent.putExtra("url", URL);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        //EvnetBus需要在销毁时取消注册
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
