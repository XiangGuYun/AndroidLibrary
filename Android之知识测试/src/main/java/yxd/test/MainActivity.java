package yxd.test;

import android.app.Activity;
import android.os.Bundle;

import yxd.test.arithmetic.ArithmeticTest;
import yxd.test.design_pattern.DecoratorTest;
import yxd.test.design_pattern.FactoryTest;
import yxd.test.design_pattern.ObserverTest;
import yxd.test.design_pattern.PrototypeTest;
import yxd.test.design_pattern.SingeltonTest;
import yxd.test.optimize.CodeOptimizeTest;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CodeOptimizeTest.main();

    }
}
