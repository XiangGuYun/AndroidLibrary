package yxd.service.db;

import java.util.List;

import yxd.service.bean.ThreadInfo;

/**
 * Created by asus on 2017/12/13.
 */
/*
数据访问接口
 */
public interface ThreadDAO {
    /*
    传入线程信息
     */
    void insertThread(ThreadInfo info);
    /*
    删除线程
     */
    void deleteThread(String url, int thread_id);
    /*
    更新线程
     */
    void updateThread(String url, int thread_id, int process_value);
    /*
    查询线程信息
     */
    List<ThreadInfo> getThread(String url);
    /*
    线程是否存在
     */
    boolean isExists(String url, int thread_id);
}
