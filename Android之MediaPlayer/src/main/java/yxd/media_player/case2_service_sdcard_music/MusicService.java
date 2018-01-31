package yxd.media_player.case2_service_sdcard_music;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;
import java.net.URL;

public class MusicService extends Service {

    public static MediaPlayer mediaPlayer;//播放器

    public MusicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(Environment.getExternalStorageDirectory().toString()
            +"/Fanta.mp3");
            mediaPlayer.prepare();//设置数据源之后要prepare！
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Test", e.getMessage());
        }
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        if(mediaPlayer.isPlaying())
            mediaPlayer.stop();
        mediaPlayer.release();
        super.onDestroy();
    }
}
