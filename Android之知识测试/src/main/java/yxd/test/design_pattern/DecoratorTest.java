package yxd.test.design_pattern;

import android.graphics.Shader;
import android.util.Log;

/**
 * Created by asus on 2018/1/9.
 */

public class DecoratorTest {

    public static void main(){
        Person person = new Person();
        person.walk();
        person.sleep();
        CarDriver carDriver = new CarDriver(person);
        carDriver.drive();
        PlaneDriver planeDriver = new PlaneDriver(person);
        planeDriver.drive();
    }

}

class Person{
    public void walk(){
        Log.d("Test", "走路");
    }
    public void sleep(){
        Log.d("Test", "睡觉");
    }
}

class PlaneDriver{

    private Person person;

    public PlaneDriver(Person person) {
        this.person = person;
    }

    public void drive(){
        Log.d("Test", "开飞机");
    }
}

class CarDriver{

    private Person person;

    public CarDriver(Person person) {
        this.person = person;
    }

    public void drive(){
        Log.d("Test", "开车");
    }
}
