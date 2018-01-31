package yxd.activity.base.context;

import android.support.v7.app.AppCompatActivity;

import yxd.activity.base.common.MsgUtils;


/**
 * Created by asus on 2016/7/27.
 */
public abstract class ActivitySubject extends AppCompatActivity {

    protected Observer observer = Observer.getObserver();

    protected void update(){

    }


    public MsgUtils msg(){
        return new MsgUtils();
    }

}
