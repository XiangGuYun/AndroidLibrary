package yxd.design_mode.creation.prototype;

import android.util.Log;

/**
 * Created by asus on 2017/12/17.
 */
/*
具体的原型类
Cloneable接口 表示这个对象是可拷贝的 必须重写clone()
Java中所有的对象都是保存在堆中，而堆是供全局共享的。也就是说，如果同一个Java程序的不同方法，
只要能拿到某个对象的引用，引用者就可以随意的修改对象的内部数据（前提是这个对象的内部数据通过get/set方法曝露出来）。
有的时候，我们编写的代码想让调用者只获得该对象的一个拷贝（也就是一个内容完全相同的对象，但是在内存中存在两个这样的对象）
 */
public class BusinessCard implements Cloneable{
    private String name;
    private String company;
    public BusinessCard(){
        Log.d("Test", "执行了构造函数");
    }

    @Override
    protected BusinessCard clone() throws CloneNotSupportedException {
        BusinessCard businessCard = null;
        try {
            businessCard = (BusinessCard) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return businessCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void show(){
        Log.d("Test", toString());
    }

    @Override
    public String toString() {
        return "BusinessCard{" + "name='" + name + '\'' + ", company='" + company + '\'' + '}';
    }
}
