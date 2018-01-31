package yxd.media_player.case5_vlc_sdk;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import yxd.media_player.R;

public class Case5Activity extends AppCompatActivity implements  SeekBar.OnSeekBarChangeListener{
    private MediaPlayer mediaPlayer;//媒体播放器
    private LibVLC libVLC = null;//VLC库
    private TextView tv_current_time, tv_total_time;//显示当前播放时间，显示总时长
    private View play, pause, stop;//播放、暂停、停止
    private SurfaceView surfaceView;//视频界面
    private SeekBar seekBar;//拖动条
    private int currentPosition;//当前播放的位置
    private long timeLength;//视频时长
    private Handler handler;
    private boolean isLaunched;
    public String live_url="http://vodhls1.douyucdn.cn/live/normal_live-1881699reLI7locR--20170517204912/playlist.m3u8?k=472b40f974fdb368d77512615a162f40&t=5a57898a&u=0&ct=h5&vid=664325&d=2eb94a746278bf54c30dc7483659b771";
    public String song_url="http://dl.stream.qqmusic.qq.com/C400004TXEXY2G2c7C.m4a?vkey=BC3B5D250EB9B09C5ACA75F8F57C37232D6F8143BE713DBEFFE90938050266E5BE5F3EEF47B894DB67295C48A75EC008A2A8E9AB022A0B84&guid=5444262974&uin=0&fromtag=66";
    public String testUrl = "http://funbox-w6.dwstatic.com/51/4/1631/2871536-156-1471002688.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_case5);
        getSupportActionBar().hide();
        requestPerm();//申请权限
        initView();
        handler=new Handler();
        seekBar.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(fromUser){//如果进度的改变来自用户的拖动
            currentPosition=progress;//获取当前的位置
            mediaPlayer.setTime(currentPosition);//设置当前的播放时间
            seekBar.setProgress(currentPosition);//设置拖动条的进度
        }
    }

    private void launcher() {
        //在Activity中可以为按钮增加事件
        ArrayList<String> options = new ArrayList<>();
        //创建VLC库
        libVLC = new LibVLC(getApplication(), options);
        try {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
            //创建媒体播放器
            mediaPlayer = new MediaPlayer(libVLC);
            //设置视频界面
            mediaPlayer.getVLCVout().setVideoSurface(surfaceView.getHolder().getSurface(), surfaceView.getHolder());
            //将SurfaceView贴到MediaPlayer上
            mediaPlayer.getVLCVout().attachViews();
            //设置播放窗口的尺寸
            mediaPlayer.getVLCVout().setWindowSize(surfaceView.getWidth(), surfaceView.getHeight());
            //创建播放的媒体
//            Media media = new Media(libVLC, Environment.getExternalStorageDirectory().toString()+"/canon.flv");
//            Media media = new Media(libVLC, Uri.parse("http://funbox-w6.dwstatic.com/8/4/1546/186122-98-1447157302.mp4"));
            Media media = new Media(libVLC, Uri.parse(testUrl));
            //Media media = new Media(libVLC, Uri.parse(song_url));
            //设置媒体
            mediaPlayer.setMedia(media);
            timeLength=mediaPlayer.getLength();
            seekBar.setMax((int) timeLength);//设置拖动条的最大值
            seekBar.setProgress(currentPosition);//设置拖动条当前进度
        } catch (Exception e) {
            e.printStackTrace();
        }
        mediaPlayer.play();//播放
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentPosition = (int) mediaPlayer.getTime();
                Log.d("Test", "播放时间 "+mediaPlayer.getTime());
                if(mediaPlayer.getMedia().getDuration()>0){
                    seekBar.setMax((int) mediaPlayer.getMedia().getDuration());
                    tv_total_time.setText(msToSongTime(mediaPlayer.getMedia().getDuration()));
                }
                seekBar.setProgress(currentPosition);
                tv_current_time.setText(msToSongTime(mediaPlayer.getTime()));
                //获取视频当前播放的时间位置（毫秒）
                if((currentPosition>=seekBar.getMax()-1000)&&currentPosition>0){
                    isLaunched = false;
                    pause.setBackground(getResources().getDrawable(R.mipmap.play));
                    //Toast.makeText(Case5Activity.this, "播放完毕", Toast.LENGTH_SHORT).show();
                }
                handler.postDelayed(this, 100);
            }
        }, 100);
    }

    /*
    暂停按钮
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void pause(View view) {
        if(!isLaunched){
            launcher();
            isLaunched=true;
            pause.setBackground(getResources().getDrawable(R.mipmap.pause));
        }else {
            if(mediaPlayer.isPlaying()){
                mediaPlayer.pause();
                pause.setBackground(getResources().getDrawable(R.mipmap.play));
            }else {
                mediaPlayer.play();
                pause.setBackground(getResources().getDrawable(R.mipmap.pause));
            }
        }
    }

    /*
    设置全屏或取消全屏
     */
    public void full(View view) {
        if(getRequestedOrientation()==ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

    }

    /*
    根据屏幕方向的变化做出调整
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
          super.onConfigurationChanged(newConfig);//不能删，否则会报异常
        if (newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
            surfaceView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            mediaPlayer.getVLCVout().setWindowSize(MetricsUtils.getScreenWidth(this),
                    MetricsUtils.getScreenHeight(this));
        } else {
            surfaceView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    DensityUtil.dp2px(this, 210)));
            mediaPlayer.getVLCVout().setWindowSize(MetricsUtils.getScreenWidth(this),
                    DensityUtil.dp2px(this, 210));
        }

    }

    @Override
    public void onBackPressed() {
        if(getRequestedOrientation()==ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            surfaceView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    DensityUtil.dp2px(this, 210)));
            mediaPlayer.getVLCVout().setWindowSize(MetricsUtils.getScreenWidth(this),
                    DensityUtil.dp2px(this, 210));
        }else{
            finish();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            handler.getLooper().quit();
            handler.removeCallbacksAndMessages(null);
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }

    //----------------------------------------------------------------------------------------------

    private void requestPerm() {
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
    }

    /*
   将文件时长转换为歌曲时间
    */
    public String msToSongTime(long length){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
        return sdf.format(new Date(length));
    }

    private void initView() {
        surfaceView=findViewById(R.id.surface);
        pause=findViewById(R.id.btn_pause);
        seekBar=findViewById(R.id.seekbar);
        tv_current_time=findViewById(R.id.tv_current_time);
        tv_total_time=findViewById(R.id.tv_total_time);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}
