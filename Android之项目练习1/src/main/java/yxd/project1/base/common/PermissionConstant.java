package yxd.project1.base.common;

/**
 * Created by asus on 2017/12/12.
 */
/*
普通权限
ACCESS_LOCATION_EXTRA_COMMANDS
ACCESS_NETWORK_STATE
ACCESS_NOTIFICATION_POLICY
ACCESS_WIFI_STATE
BLUETOOTH
BLUETOOTH_ADMIN
BROADCAST_STICKY
CHANGE_NETWORK_STATE
CHANGE_WIFI_MULTICAST_STATE
CHANGE_WIFI_STATE
DISABLE_KEYGUARD
EXPAND_STATUS_BAR
GET_PACKAGE_SIZE
INSTALL_SHORTCUT
INTERNET
KILL_BACKGROUND_PROCESSES
MODIFY_AUDIO_SETTINGS
NFC
READ_SYNC_SETTINGS
READ_SYNC_STATS
RECEIVE_BOOT_COMPLETED
REORDER_TASKS
REQUEST_INSTALL_PACKAGES
SET_ALARM
SET_TIME_ZONE
SET_WALLPAPER
SET_WALLPAPER_HINTS
TRANSMIT_IR
UNINSTALL_SHORTCUT
USE_FINGERPRINT
VIBRATE
WAKE_LOCK
WRITE_SYNC_SETTINGS
 */
/*
if(!activity.checkPM(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            activity.requestPM(PermissionConstant.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }else{
            toast("支持了写入操作");
        }
 */
public class PermissionConstant {
    //联系人组
    public static final int WRITE_CONTACTS = 0;
    public static final int GET_ACCOUNTS = 1;
    public static final int READ_CONTACTS = 2;
    //电话组
    public static final int READ_CALL_LOG = 3;
    public static final int READ_PHONE_STATE = 4;
    public static final int CALL_PHONE = 5;
    public static final int WRITE_CALL_LOG = 6;
    public static final int USE_SIP = 7;
    public static final int PROCESS_OUTGOING_CALLS = 8;
    public static final int ADD_VOICEMAIL = 9;
    //日历组
    public static final int READ_CALENDAR = 10;
    public static final int WRITE_CALENDAR = 11;
    //相机组
    public static final int CAMERA = 12;
    //传感器组
    public static final int BODY_SENSORS = 13;
    //定位组
    public static final int ACCESS_FINE_LOCATION = 14;
    public static final int ACCESS_COARSE_LOCATION = 15;
    //SD卡组
    public static final int READ_EXTERNAL_STORAGE = 16;
    public static final int WRITE_EXTERNAL_STORAGE = 17;
    //麦克风组
    public static final int RECORD_AUDIO = 18;
    //短信组
    public static final int READ_SMS = 19;
    public static final int RECEIVE_WAP_PUSH = 20;
    public static final int RECEIVE_MMS = 21;
    public static final int RECEIVE_SMS = 22;
    public static final int SEND_SMS = 23;
    public static final int READ_CELL_BROADCASTS = 24;

}
