package yxd.service.bean;

/**
 * Created by asus on 2017/12/13.
 */

public class ThreadInfo {
    private int id;
    private String url;
    private int start;
    private int end;
    private int processValue;

    public ThreadInfo() {
    }

    public ThreadInfo(int id, String url, int start, int end, int processValue) {
        this.id = id;
        this.url = url;
        this.start = start;
        this.end = end;
        this.processValue = processValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getProcessValue() {
        return processValue;
    }

    public void setProcessValue(int processValue) {
        this.processValue = processValue;
    }

    @Override
    public String toString() {
        return "ThreadInfo{" + "id=" + id + ", url='" + url + '\'' + ", start=" + start + ", end=" + end + ", processValue=" + processValue + '}';
    }
}
