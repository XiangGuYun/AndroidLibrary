package yxd.design_mode.behavior.state;

/**
 * Created by asus on 2017/12/17.
 */
/*
Context中定义了setMp3State方法，用来设定状态，
其中powerOn方法中会调用setMp3State方法将状态置为PowerOffState，
同理powerOff中将状态置为PowerOffState
 */
public class Context {

    private Mp3State mp3State;

    public void setMp3State(Mp3State mp3State){
        this.mp3State=mp3State;
    }

    public void powerOn(){
        mp3State.powerOn();
        setMp3State(new PowerOnState());
    }

    public void powerOff(){
        mp3State.powerOff();
        setMp3State(new PowerOffState());
    }

    public void preSong(){
        mp3State.preSong();
    }

    public void nextSong(){
        mp3State.nextSong();
    }
}
