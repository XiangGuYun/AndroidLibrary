package yxd.design_mode.exercises.zsq.zhuang_shi;

import android.util.Log;

/**
 * Created by asus on 2017/12/21.
 */

public class Car {

    private Person person;

    public Car(Person person){
        this.person = person;
    }

    public void driveCar(){
        Log.d("Test", "开车");
    }

}
