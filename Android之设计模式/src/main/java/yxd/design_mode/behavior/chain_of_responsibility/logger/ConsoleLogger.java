package yxd.design_mode.behavior.chain_of_responsibility.logger;

import android.util.Log;

/**
 * Created by asus on 2017/12/19.
 */

public class ConsoleLogger extends AbLogger {
    public ConsoleLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        Log.d("Test","Standard Console::Logger: " + message);
    }
}
