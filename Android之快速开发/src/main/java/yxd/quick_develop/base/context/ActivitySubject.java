package yxd.quick_develop.base.context;

import android.support.v4.app.FragmentActivity;


/**
 * Created by asus on 2016/7/27.
 */
public abstract class ActivitySubject extends FragmentActivity {
    protected Observer observer = Observer.getObserver();
    protected void update(){}
}
