package yxd.design_mode.exercises.zsq;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import yxd.design_mode.R;
import yxd.design_mode.exercises.zsq.zhuang_shi.Car;
import yxd.design_mode.exercises.zsq.zhuang_shi.Person;

public class ExActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex);
        /*
        装饰模式
         */
        Person person = new Person();
        person.walk();
        person.sleep();
        Car car = new Car(person);
        car.driveCar();
    }
}
