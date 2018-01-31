package yxd.design_mode.behavior.chain_of_responsibility.logger;

import android.util.Log;

/**
 * Created by asus on 2017/12/19.
 */

public class ErrorLogger extends AbLogger {

    public ErrorLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        Log.d("Test","Error Console::Logger: " + message);
    }
}
