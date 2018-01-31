package yxd.test.design_pattern;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2018/1/9.
 */

public class ObserverTest {

    public static void main(){
        Observable.getInstance().add(new Observer("小明"));
        Observable.getInstance().add(new Observer("小红"));
        Observable.getInstance().notifyUpdate();
    }

}

class Observable{

    private static Observable instance;
    private List<Observer> list = new ArrayList<>();

    private Observable(){

    }

    public static Observable getInstance(){
        if(instance==null)
            return instance = new Observable();
        return instance;
    }

    public void add(Observer observer){
        if(!list.contains(observer))
            list.add(observer);
    }

    public void remove(Observer observer){
        if(list.contains(observer))
            list.remove(observer);
    }

    public void notifyUpdate(){
        for (Observer observer:list
             ) {
            observer.update();
        }
    }

}

interface Subject{

    void update();

}

class Observer implements Subject{

    private String name;

    public Observer(String name) {
        this.name = name;
    }

    @Override
    public void update() {
        Log.d("Test", name+"收到了通知");
    }
}
