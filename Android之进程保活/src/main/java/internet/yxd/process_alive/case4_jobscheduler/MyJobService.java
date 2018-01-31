package internet.yxd.process_alive.case4_jobscheduler;

import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

/**
 * Created by asus on 2017/12/15.
 */
/*
①创建Service继承JobService类，重写onStartJob和onStopJob。
②在onCreate方法里通过JobScheduler来调度Service，并把参数设置为Persisted（持续）
注意：当进程被force-stop指令杀死后，JobService依旧无法拉活进程。
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)//JobService只适用于Android5.0以上的系统
public class MyJobService extends JobService {//继承自JobService

    @Override
    public void onCreate() {
        super.onCreate();
        //工作信息
        JobInfo.Builder builder = new JobInfo.Builder(
                1,//工作ID
                new ComponentName(this, MyJobService.class));//组件名
        //设置执行延迟
        builder.setOverrideDeadline(0);
        //设置持续运行
        builder.setPersisted(true);
        //通过系统服务获取工作调度器
        JobScheduler jobScheduler = (JobScheduler) getSystemService(Service.JOB_SCHEDULER_SERVICE);
        //调度MyService工作
        jobScheduler.schedule(builder.build());
    }

    /*
    开始工作
     */
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d("Test", "onStartJob");
        return false;
    }

    /*
    停止工作
     */
    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d("Test", "onStopJob");
        return false;
    }
}
