package yxd.media_player.case1_activity_raw_music;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

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
public class Case1Activity extends AppCompatActivity {
    //歌曲名称，当前播放位置，歌曲长度
    public TextView tv_song_name,tv_curr_pos,tv_song_len;
    private MediaPlayer mediaPlayer;//播放器
    private double currentPosition = 0;//当前播放位置
    private double songLength = 0;//歌曲长度
    private SeekBar seekbar;//拖动条
    private Handler myHandler = new Handler();//用来更新拖动条
    private Button playButton, stopButton;//播放按钮，暂停按钮
    private boolean PLAYING_FLAG = false;//是否在播放

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_case1);
        getSupportActionBar().hide();
        init();
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    currentPosition=progress;
                    mediaPlayer.seekTo(progress);
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

    @Override
    protected void onDestroy() {
        if(mediaPlayer.isPlaying())
            mediaPlayer.stop();
        mediaPlayer.release();
        super.onDestroy();
    }

    /*
    播放
     */
    public void play(View view){
        if(!PLAYING_FLAG){
            mediaPlayer.start();//播放
            tv_song_len.setText(msToSongTime(mediaPlayer.getDuration()));
            songLength = mediaPlayer.getDuration();//获取总时长
            currentPosition = mediaPlayer.getCurrentPosition();//获取当前位置
            seekbar.setMax((int) songLength);//设置拖动条的最大值
            seekbar.setProgress((int)currentPosition);//设置拖动条当前进度
            //每隔100毫秒刷新一次进度
            myHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    currentPosition = mediaPlayer.getCurrentPosition();
                    seekbar.setProgress((int)currentPosition);
                    tv_curr_pos.setText(msToSongTime((long) currentPosition));
                    myHandler.postDelayed(this, 100);
                }
            }, 100);
            playButton.setText("暂停");
            PLAYING_FLAG=true;
        }else {
            mediaPlayer.pause();
            playButton.setText("播放");
            PLAYING_FLAG=false;
        }

    }

    /*
    停止
     */
    public void stop(View view){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            currentPosition=0;
            mediaPlayer.seekTo(0);
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
        mediaPlayer = MediaPlayer.create(this, R.raw.song);

    }

    /*
    将文件时长转换为歌曲时间
     */
    public String msToSongTime(long length){
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
        return sdf.format(new Date(length));
    }

}

/*
private int forwardTime = 5000;//快进一次时长
private int backwardTime = 5000;//快退一次时长

public void forward(View view){
    int temp = (int)currentPosition;
    if((temp+forwardTime)<=songLength){
        currentPosition = currentPosition + forwardTime;
        mediaPlayer.seekTo((int) currentPosition);
    }else{
        mediaPlayer.seekTo(mediaPlayer.getDuration());
    }
}
public void rewind(View view){
    int temp = (int)currentPosition;
    if((temp-backwardTime)>0){
        currentPosition = currentPosition - backwardTime;
        mediaPlayer.seekTo((int) currentPosition);
    }else{
        mediaPlayer.seekTo(0);
    }

}
 */

