package yxd.design_mode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import yxd.design_mode.architecture.mvp.view.UserLoginActivity;
import yxd.design_mode.behavior.chain_of_responsibility.LoggerActivity;
import yxd.design_mode.behavior.observer.ObserverActivity;
import yxd.design_mode.creation.prototype.ProtoActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void observer(View view) {
        startActivity(new Intent(this, ObserverActivity.class));
    }

    public void proto(View view) {
        startActivity(new Intent(this, ProtoActivity.class));
    }

    public void chain_of_responsibility(View view) {
        startActivity(new Intent(this, LoggerActivity.class));
    }

    public void mvp(View view) {
        startActivity(new Intent(this, UserLoginActivity.class));
    }
}
