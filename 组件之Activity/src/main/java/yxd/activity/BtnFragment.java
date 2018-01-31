package yxd.activity;

import android.Manifest;
import android.view.View;
import android.widget.Button;

import yxd.activity.base.common.PermissionConstant;
import yxd.activity.base.context.BaseFragment;
import yxd.activity.base.thread.HandlerUtils;

/**
 * Created by asus on 2017/12/20.
 */

public class BtnFragment extends BaseFragment {
    private Button btn1, btn2, btn3;
    private HandlerUtils handlerUtils;
    @Override
    protected void onCreateView(View view) {
        MainActivity activity = (MainActivity) getActivity();
        btn1 = id(R.id.btn1);
        btn2 = id(R.id.btn2);
        btn3 = id(R.id.btn3);
        click(btn1, v -> {
            activity.fragmentUtils.switchFragment(activity.fragments.get(0));
            if(!activity.checkPM(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                activity.requestPM(PermissionConstant.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }else{
                toast("支持了写入操作");
            }
        });
//        click(btn2, v -> activity.fragmentUtils.switchFragment(activity.fragments.get(1)));
//        click(btn3, v -> activity.fragmentUtils.switchFragment(activity.fragments.get(2)));

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_btn;
    }

    @Override
    public void onDestroy() {
        if(handlerUtils != null)
            handlerUtils.clear();
        super.onDestroy();
    }
}
