package yxd.ui5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import yxd.ui5.case_cardview.CardViewActivity;
import yxd.ui5.case_notification.NotificationActivity;
import yxd.ui5.case_snackbar.SnackBarActivity;
import yxd.ui5.case_swiperefresh.SwipeRefreshActivity;
import yxd.ui5.case_textinput.TextInputActivity;
import yxd.ui5.case_toolbar.ToolbarActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void cardview(View view) {
        startActivity(new Intent(this, CardViewActivity.class));
    }

    public void toolbar(View view) {
        startActivity(new Intent(this, ToolbarActivity.class));
    }

    public void notification(View view) {
        startActivity(new Intent(this, NotificationActivity.class));
    }

    public void refresh(View view) {
        startActivity(new Intent(this, SwipeRefreshActivity.class));
    }

    public void appbar(View view) {}

    public void snackbar(View view) {
        startActivity(new Intent(this, SnackBarActivity.class));
    }

    public void textinput(View view) {
        startActivity(new Intent(this, TextInputActivity.class));
    }
}
