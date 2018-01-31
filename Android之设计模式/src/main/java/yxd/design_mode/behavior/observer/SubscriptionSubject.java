package yxd.design_mode.behavior.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2017/12/17.
 */
/*
具体被观察者
 */
public class SubscriptionSubject implements Subject{

    private List<Observer> weixinUserlist = new ArrayList<Observer>();

    @Override
    public void attach(Observer observer) {
        weixinUserlist.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        weixinUserlist.remove(observer);
    }

    @Override
    public void notify(String message) {
        for (Observer observer : weixinUserlist) {
            observer.update(message);
        }
    }

}
