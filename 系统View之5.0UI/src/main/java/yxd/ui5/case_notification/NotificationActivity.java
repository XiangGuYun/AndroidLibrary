package yxd.ui5.case_notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RemoteViews;

import yxd.ui5.MainActivity;
import yxd.ui5.R;

public class NotificationActivity extends AppCompatActivity {

    private static final int NOTIFICATION_ID_BASIC = 1;
    private static final int NOTIFICATION_ID_COLLAPSE = 2;
    private static final int NOTIFICATION_ID_HEADSUP = 3;
    private static final int NOTIFICATION_ID_VISIBILITY = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
    }

    /*
    基本的Notification

     */
    public void base(View view) {
        Notification.Builder builder = new Notification.Builder(this);
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.baidu.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity
                (this, 0,intent, 0);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentTitle("this is content title");
        builder.setContentText("this is content text");
        builder.setSubText("this is sub text");

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //第一个参数是一个ID，用来区分不同App的Notification
        nm.notify(NOTIFICATION_ID_BASIC, builder.build());

    }
    
    /*
    折叠式Notification
    自定义视图、RemoteViews、显示长文本
    普通视图&展开视图
     */
    public void collapse(View view) {
        Notification.Builder builder = new Notification.Builder(this);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sina.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity
                (this, 0, intent, 0);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        //通过RemoteViews来创建自定义的Notification视图
        RemoteViews contentView =
                new RemoteViews(getPackageName(), R.layout.notification);
        contentView.setTextViewText(R.id.textView, "当折叠时显示我");
        Notification notification = builder.build();
        //指定普通视图
        notification.contentView = contentView;
        //指定展开视图
        RemoteViews expandedView = new RemoteViews(getPackageName(),
                R.layout.notification_expanded);
        notification.bigContentView = expandedView;
        
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(NOTIFICATION_ID_COLLAPSE, notification);
    }

    /*
    悬挂式Notification
    在屏幕上方产生Notification且不会打断用户操作
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void suspend(View view) {
        Notification.Builder builder =
                new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setCategory(Notification.CATEGORY_MESSAGE)
                .setContentTitle("This is content title")
                .setContentText("This is content text");

        Intent push = new Intent();
        push.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        push.setClass(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0,push,PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentText("Heads-Up Notification on Android 5.0")
                .setFullScreenIntent(pendingIntent, true);//*****

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(NOTIFICATION_ID_HEADSUP, builder.build());
    }

    /*
    显示等级的Notification
    Android5.X将Notification分成了3个等级
    VISIBILITY_PRIVATE--表明只有没有锁屏的时候回显示
    VISIBILITY_PUBLIC--表明在任何情况下都会显示
    VISIBILITY_SELECT--表明在pin、password等安全锁和没有锁屏的情况下才能够显示
    builder.setVisibility(Notification.VISIBILITY_PUBLIC);
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void rank(View view) {
        RadioGroup radioGroup = findViewById(R.id.rg);
        Notification.Builder builder = new Notification.Builder(this)
                .setContentText("通知可见性测试");
        switch (radioGroup.getCheckedRadioButtonId()){
            case R.id.radio_button_public:
                builder.setVisibility(Notification.VISIBILITY_PUBLIC);
                builder.setContentText("Public");
                break;
            case R.id.radio_button_private:
                builder.setVisibility(Notification.VISIBILITY_PRIVATE);
                builder.setContentText("Private");
                break;
            case R.id.radio_button_secret:
                builder.setVisibility(Notification.VISIBILITY_SECRET);
                builder.setContentText("Secret");
                break;
            default:
                builder.setSmallIcon(R.mipmap.ic_launcher);
                NotificationManager nm = (NotificationManager)
                        getSystemService(NOTIFICATION_SERVICE);
                nm.notify(NOTIFICATION_ID_VISIBILITY, builder.build());
        }
    }
}
