package yxd.design_mode.behavior.state;

/**
 * Created by asus on 2017/12/17.
 */

public class PowerOffState implements Mp3State {
    @Override
    public void powerOn() {
        System.out.println("开机");
    }
    @Override
    public void powerOff() {
    }
    @Override
    public void preSong() {
    }
    @Override
    public void nextSong() {
    }
}
