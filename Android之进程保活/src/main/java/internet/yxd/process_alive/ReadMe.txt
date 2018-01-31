查看android进程 及进程优先级的指令
adb shell
ps
cat /proc/${pid}/oom_adj
dumpsys activity

Android进程保活总结
http://blog.csdn.net/superxlcr/article/details/70244803?ref=myread
case1：在Service中使用广播监听锁屏开屏事件，锁屏时开启一个1像素的Activity，开屏时关闭。
case2：创建1个临时的Servcie和1个工作的Service，在临时Service中绑定Notification开启
前台服务并启动工作的Servcie，在工作的Servcie中绑定相同Id的Notification并开启前台服务，
最后关闭临时的Service。
case3:修改Servcie中onStartCommand方法中的返回值来进行拉活，缺点是如果短时间内被连续杀死
或者被强制停止将无法拉活。
case4:使用jobscheduler机制拉活进程。