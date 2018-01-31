package internet.yxd.process_alive.case5_account;

import android.accounts.AccountManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import internet.yxd.process_alive.R;

public class Case5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case5);

        AccountManager mAccountManager = (AccountManager) getSystemService(ACCOUNT_SERVICE);

    }
}
