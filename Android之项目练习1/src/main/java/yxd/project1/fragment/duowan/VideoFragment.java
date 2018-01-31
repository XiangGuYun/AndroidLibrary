package yxd.project1.fragment.duowan;

import android.content.res.Configuration;
import android.os.Handler;
import android.view.SurfaceView;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.MediaPlayer;

import yxd.project1.R;
import yxd.project1.base.context.BaseFragment;

/**
 * Created by asus on 2018/1/12.
 */

public class VideoFragment extends BaseFragment {

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
    protected void onCreateView(View view) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }


}
