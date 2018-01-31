package yxd.design_mode.behavior.state;

/**
 * Created by asus on 2017/12/17.
 */
/*
我们先来实现开机状态
 */
public class PowerOnState implements Mp3State {
    @Override
    public void powerOn() {
        System.out.println("已开机");
    }
    @Override
    public void powerOff() {
        System.out.println("关机");
    }
    @Override
    public void preSong() {
        System.out.println("上一首歌");
    }
    @Override
    public void nextSong() {
        System.out.println("下一首歌");
    }
}
