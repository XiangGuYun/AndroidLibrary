package yxd.media_player.case3_play_sdcard_video;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;

import java.io.IOException;

import yxd.media_player.R;

public class Case3Activity extends AppCompatActivity implements View.OnClickListener{

    private SurfaceView surface1;
    private Button start, pre, stop;
    private MediaPlayer mediaPlayer;

    private SeekBar seekbar;
    private int currentPosition;
    private int videoLength;
    private Handler myHandler;

    public String mp4 = "http://funbox-w6.dwstatic.com/84/11/1716/4389123-98-1492690663.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_case3);
        getSupportActionBar().hide();
        surface1 = findViewById(R.id.surface1);
        start = findViewById(R.id.start);
        pre = findViewById(R.id.pre);
        stop=findViewById(R.id.stop);
        seekbar = findViewById(R.id.seekbar);
        mediaPlayer = new MediaPlayer();
        myHandler =new Handler();
        //设置播放时打开屏幕
        surface1.getHolder().setKeepScreenOn(true);
        surface1.getHolder().addCallback(new SurfaceViewLis());
        start.setOnClickListener(this);
        pre.setOnClickListener(this);
        stop.setOnClickListener(this);
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                try {
                    play();
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SecurityException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            case R.id.pre:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    pre.setText("播放");
                } else {
                    mediaPlayer.start();
                    pre.setText("暂停");
                }
                break;
            case R.id.stop:
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.reset();
                    seekbar.setProgress(0);
                    currentPosition=0;
                }
                break;
            default:
                break;
        }

    }

    public void play() throws IllegalArgumentException, SecurityException,
            IllegalStateException, IOException {
        mediaPlayer.reset();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setDataSource(Environment.getExternalStorageDirectory()+"/movie.mp4");
        // 把视频输出到SurfaceView上
        mediaPlayer.setDisplay(surface1.getHolder());
        mediaPlayer.prepare();
        mediaPlayer.start();
        videoLength = mediaPlayer.getDuration();//获取总时长
        currentPosition = mediaPlayer.getCurrentPosition();//获取当前位置
        seekbar.setMax((int) videoLength);//设置拖动条的最大值
        seekbar.setProgress((int)currentPosition);//设置拖动条当前进度
        //每隔100毫秒刷新一次进度
        myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentPosition = mediaPlayer.getCurrentPosition();
                seekbar.setProgress((int)currentPosition);
                myHandler.postDelayed(this, 100);
            }
        }, 100);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    currentPosition=progress;
                    mediaPlayer.seekTo(progress);
                    seekbar.setProgress(currentPosition);
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

    private class SurfaceViewLis implements SurfaceHolder.Callback {

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {

        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            if (currentPosition == 0) {
                try {
                    play();
                    mediaPlayer.seekTo(currentPosition);
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SecurityException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }

    }

    @Override
    protected void onPause() {
        if (mediaPlayer.isPlaying()) {
            // 保存当前播放的位置
            currentPosition = mediaPlayer.getCurrentPosition();
            mediaPlayer.stop();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer.isPlaying())
            mediaPlayer.stop();
        mediaPlayer.release();
        super.onDestroy();
    }
}
