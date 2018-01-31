package yxd.sqlite;

/**
 * Created by asus on 2017/12/13.
 */

public class Person {

    private int _id;
    private String name;
    private int age;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "_id is "+_id+" name is "+name+" age is "+age;
    }
}
