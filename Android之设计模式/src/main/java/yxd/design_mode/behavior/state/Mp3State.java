package yxd.design_mode.behavior.state;

/**
 * Created by asus on 2017/12/17.
 */

public interface Mp3State {
    //开机
    void powerOn();
    //关机
    void powerOff();
    //上一首歌曲
    void preSong();
    //下一首歌曲
    void nextSong();
}
