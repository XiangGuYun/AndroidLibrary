package yxd.service.bean;

import java.io.Serializable;

/**
 * Created by asus on 2017/12/13.
 */

public class FileInfo implements Serializable{
    private int id;
    private String url;
    private String name;
    private int length;
    private int processValue;

    public FileInfo() {
    }

    public FileInfo(int id, String url, String name, int length, int processValue) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.length = length;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getProcessValue() {
        return processValue;
    }

    public void setProcessValue(int processValue) {
        this.processValue = processValue;
    }

    @Override
    public String toString() {
        return "FileInfo{" + "id=" + id + ", url='" + url + '\'' + ", name='" + name + '\'' + ", length=" + length + ", processValue=" + processValue + '}';
    }
}
