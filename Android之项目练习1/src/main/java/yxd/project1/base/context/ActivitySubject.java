package yxd.project1.base.context;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by asus on 2016/7/27.
 */
public abstract class ActivitySubject extends FragmentActivity {

    protected Observer observer = Observer.getObserver();

    protected void update(){

    }



}
