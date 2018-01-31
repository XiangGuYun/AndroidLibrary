package yxd.service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.apache.http.HttpStatus;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import yxd.service.bean.FileInfo;
import yxd.service.bean.ThreadInfo;
import yxd.service.db.ThreadDAO;
import yxd.service.db.ThreadDAOImpl;

/**
 * Created by asus on 2017/12/13.
 */

public class DownloadTask {
    private Context context;
    private FileInfo fileInfo;
    private ThreadDAO threadDAO;
    private int process;
    public boolean isPause = false;

    public DownloadTask(Context context, FileInfo fileInfo) {
        this.context = context;
        this.fileInfo = fileInfo;
        threadDAO = new ThreadDAOImpl(context);
    }

    public void download(){
        //读取数据库的线程信息
        Log.d("Test", "line0.1");
        List<ThreadInfo> infos = threadDAO.getThread(fileInfo.getUrl());
        ThreadInfo info = null;
        if(infos.size()==0){
            //初始化线程信息
            info = new ThreadInfo(0, fileInfo.getUrl(),
                    0, fileInfo.getLength(), 0);
        }else {
            info = infos.get(0);
        }
        //创建子线程进行下载
        Log.d("Test", "line1");
        new DownloadThread(info).start();
        Log.d("Test", "line2");
    }

    class DownloadThread extends Thread{
        private ThreadInfo threadInfo;

        public DownloadThread(ThreadInfo info){
            threadInfo = info;
        }

        @Override
        public void run() {
            //向数据库插入线程信息
            if(!threadDAO.isExists(threadInfo.getUrl(), threadInfo.getId())){
                threadDAO.insertThread(threadInfo);
            }
            //设置下载位置
            HttpURLConnection conn = null;
            RandomAccessFile raf = null;
            InputStream is = null;
            try {
                URL url = new URL(threadInfo.getUrl());
                conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(3000);
                conn.setRequestMethod("GET");
                //设置下载位置
                int start = threadInfo.getStart()+threadInfo.getProcessValue();
                conn.setRequestProperty("Range", "bytes="+start+"-"+threadInfo.getEnd());
                //设置文件写入位置
                File file = new File(DownloadService.DOWMLOAD_PATH, fileInfo.getName());
                raf = new RandomAccessFile(file, "rwd");
                raf.seek(start);
                Intent intent = new Intent(DownloadService.ACTION_UPDATE);
                process += threadInfo.getProcessValue();
                //开始下载
                if(conn.getResponseCode()== HttpStatus.SC_PARTIAL_CONTENT){
                    is = conn.getInputStream();
                    byte[] buf = new byte[1024*4];
                    int len = -1;
                    long time = System.currentTimeMillis();
                    while((len=is.read(buf))!=-1){
                        //写入文件
                        raf.write(buf, 0, len);
                        //把下载进度发送给Activity更新UI
                        process += len;
                        if(System.currentTimeMillis()-time>500){//每个0.5秒发送一次广播
                            intent.putExtra("process", process*100/fileInfo.getLength());
                            context.sendBroadcast(intent);
                        }
                        //下载暂停时保存进度到数据库
                        if(isPause){
                            threadDAO.updateThread(fileInfo.getUrl(), fileInfo.getId(),
                                    fileInfo.getProcessValue());
                            return;
                        }
                    }
                    //删除线程信息
                    threadDAO.deleteThread(fileInfo.getUrl(), fileInfo.getId());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                conn.disconnect();
                try {
                    assert raf != null;
                    raf.close();
                    assert is != null;
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
