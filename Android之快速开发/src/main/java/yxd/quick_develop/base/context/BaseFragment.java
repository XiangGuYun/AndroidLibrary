package yxd.quick_develop.base.context;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;


/**
 * Created by asus on 2016/7/20.
 */
public abstract class BaseFragment extends FragmentSubject {

    protected static BaseActivity activity;
    private View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        onCreateView(view);
        return view;
    }

    protected abstract void onCreateView(View view);

    protected abstract int getLayoutId();

    protected void click(View view, View.OnClickListener listener){
        view.setOnClickListener(listener);
    }

    protected <T extends View> T id(int id){
        return view.findViewById(id);
    }

    protected <T> List<T> list(T...ts){
        List<T> list = new ArrayList<>();
        list.addAll(Arrays.asList(ts));
        return list;
    }

    public void toast(String text){
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
    }

    protected void sleep(long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
