package yxd.design_mode.creation.prototype;

/**
 * Created by asus on 2017/12/17.
 */
/*
为了实现Company类能被拷贝，Company类也需要实现Cloneable接口并且覆写clone方法
 */
public class Company implements Cloneable{
    private String name;
    private String address;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Company company=null;
        try {
            company= (Company) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
