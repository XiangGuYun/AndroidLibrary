package yxd.design_mode.creation.prototype;

import android.util.Log;

/**
 * Created by asus on 2017/12/17.
 */

public class DeepBusinessCard implements Cloneable {
    private String name;
    private Company company = new Company();

    public void setName(String name) {
        this.name = name;
    }

    public void setCompany(String name, String address) {
        this.company.setName(name);
        this.company.setAddress(address);
    }

    @Override
    public DeepBusinessCard clone() {
        DeepBusinessCard businessCard = null;
        try {
            businessCard = (DeepBusinessCard) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return businessCard;
    }

    public void show() {
        Log.d("Test", toString());
    }

    @Override
    public String toString() {
        return "DeepBusinessCard{" + "name='" + name + '\'' + ", company=" + company + '}';
    }
}
