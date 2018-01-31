package yxd.media_player.case2_service_sdcard_music;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;

import yxd.media_player.R;

/*
常用方法
start()：播放
pause()：暂停
reset()：重置
isPlaying()：判断是否在播放
seekTo(pos)：移动进度条，单位是秒
getCurrentDuration()：获取当前播放的位置，单位是毫秒
getDuration()：获取文件的总时长
release()：释放播放器资源
setVolume(int left,int right)：设置左右声道的音量
setDataSource(FileDescriptor fd)：设置文件数据源
selectTrack(int index)：选择轨道
getTrackInfo：获取轨道数组信息
 */
public class Case2Activity extends AppCompatActivity {
    //歌曲名称，当前播放位置，歌曲长度
    public TextView tv_song_name,tv_curr_pos,tv_song_len;
    private double currentPosition = 0;//当前播放位置
    private double songLength = 0;//歌曲长度
    private SeekBar seekbar;//拖动条
    private Handler myHandler = new Handler();//用来更新拖动条
    private Button playButton, stopButton;//播放按钮，暂停按钮
    private boolean PLAYING_FLAG = false;//是否在播放

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_case1);
        getSupportActionBar().hide();
        init();
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    currentPosition=progress;
                    MusicService.mediaPlayer.seekTo(progress);
                    seekbar.setProgress((int)currentPosition);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

   

    /*
    播放
     */
    public void play(View view){
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }else {
            if(!PLAYING_FLAG){
                MusicService.mediaPlayer.start();//播放
                tv_song_len.setText(msToSongTime(MusicService.mediaPlayer.getDuration()));
                songLength = MusicService.mediaPlayer.getDuration();//获取总时长
                currentPosition = MusicService.mediaPlayer.getCurrentPosition();//获取当前位置
                seekbar.setMax((int) songLength);//设置拖动条的最大值
                seekbar.setProgress((int)currentPosition);//设置拖动条当前进度
                //每隔100毫秒刷新一次进度
                myHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        currentPosition = MusicService.mediaPlayer.getCurrentPosition();
                        seekbar.setProgress((int)currentPosition);
                        tv_curr_pos.setText(msToSongTime((long) currentPosition));
                        myHandler.postDelayed(this, 100);
                    }
                }, 100);
                playButton.setText("暂停");
                PLAYING_FLAG=true;
            }else {
                MusicService.mediaPlayer.pause();
                playButton.setText("播放");
                PLAYING_FLAG=false;
            }
        }
    }

    /*
    停止
     */
    public void stop(View view){
        if(MusicService.mediaPlayer.isPlaying()){
            MusicService.mediaPlayer.pause();
            currentPosition=0;
            MusicService.mediaPlayer.seekTo(0);
            seekbar.setProgress((int)currentPosition);
            playButton.setText("播放");
            PLAYING_FLAG=false;
        }
    }

    private void init() {
        tv_song_name = findViewById(R.id.tv_song_name);
        tv_curr_pos = findViewById(R.id.tv_curr_pos);
        tv_song_len = findViewById(R.id.tv_song_len);
        seekbar = findViewById(R.id.seekbar);
        playButton = findViewById(R.id.play_btn);
        stopButton = findViewById(R.id.stop_btn);
        tv_song_name.setText("song.mp3");
    }

    /*
    将文件时长转换为歌曲时间
     */
    public String msToSongTime(long length){
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
        return sdf.format(new Date(length));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 0:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Log.d("Test", "执行了");
                    startService(new Intent(this, MusicService.class));
                }else {
                    Toast.makeText(this, "权限授予未成功", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }


}


