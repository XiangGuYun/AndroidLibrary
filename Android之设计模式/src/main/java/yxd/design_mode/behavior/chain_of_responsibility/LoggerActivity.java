package yxd.design_mode.behavior.chain_of_responsibility;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import yxd.design_mode.R;
import yxd.design_mode.behavior.chain_of_responsibility.logger.AbLogger;
import yxd.design_mode.behavior.chain_of_responsibility.logger.ConsoleLogger;
import yxd.design_mode.behavior.chain_of_responsibility.logger.ErrorLogger;
import yxd.design_mode.behavior.chain_of_responsibility.logger.FileLogger;

public class LoggerActivity extends AppCompatActivity {

    private AbLogger loggerChain;

    private static AbLogger getChainOfLoggers(){

        AbLogger errorLogger = new ErrorLogger(AbLogger.ERROR);
        AbLogger fileLogger = new FileLogger(AbLogger.DEBUG);
        AbLogger consoleLogger = new ConsoleLogger(AbLogger.INFO);

        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);

        return errorLogger;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logger);

        loggerChain = getChainOfLoggers();

    }

    public void info(View view) {
        loggerChain.logMessage(AbLogger.INFO,
                "This is an information.");
    }

    public void debug(View view) {
        loggerChain.logMessage(AbLogger.DEBUG,
                "This is an debug level information.");
    }

    public void error(View view) {
        loggerChain.logMessage(AbLogger.ERROR,
                "This is an error information.");
    }

     /*
    D: Standard Console::Logger: This is an information.
    D: File::Logger: This is an debug level information.
    D: Standard Console::Logger: This is an debug level information.
    D: Error Console::Logger: This is an error information.
    D: File::Logger: This is an error information.
    D: Standard Console::Logger: This is an error information.
     */

}
