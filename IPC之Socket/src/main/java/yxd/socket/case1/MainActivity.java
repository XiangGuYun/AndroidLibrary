package yxd.socket.case1;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import yxd.socket.R;

/*
编程思想
客户端和服务端通过Socket和ServerSocket建立连接(各创建一条子线程)。
通过BufferWriter向对方发送消息。
通过BufferReader读取对方发送过来的消息。
 */
public class MainActivity extends AppCompatActivity {

    private Button bt_send;
    private EditText et_receive;
    private volatile PrintWriter writer;
    private TextView tv_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    1);
        }

        initView();
        Intent intent = new Intent(this, SocketService.class);
        startService(intent);//启动另一个进程的服务
        new Thread(new Runnable() {
            @Override
            public void run() {
                connServerSocket();//连接服务端Socket
            }
        }).start();

    }

    private void connServerSocket() {
        BufferedReader reader = null;
        PrintWriter printWriter = null;
        Socket socket = null;
        Log.d("Test", "line4");
        try {
            while (socket == null){
                Log.d("Test", "line3");
                socket = new Socket("localhost", 8088);
                Log.d("Test", "line3.1");
            }
            printWriter = new PrintWriter(new OutputStreamWriter
                    (socket.getOutputStream()), true);
            Log.d("Test", "line3.2");

            writer = printWriter;
            Log.d("Test", "line3.3");

            reader = new BufferedReader(new InputStreamReader
                    (socket.getInputStream()));
            String line = "";
            final StringBuilder builder = new StringBuilder("");
            while ((line=reader.readLine()) != null){
                builder.append(line);
            }
            runOnUiThread(new Runnable() {
                @SuppressLint("SetTextI18n")
                @Override
                public void run() {
                    tv_message.setText("读取到了服务端的消息 "+builder.toString());
                }
            });
        } catch (IOException e) {
            SystemClock.sleep(1000);
            e.printStackTrace();
            Log.d("Test", "异常 "+e.getMessage());
        }finally {
            try {
                if(reader != null)
                    reader.close();
                if(writer != null)
                    writer.close();
                if(socket != null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void initView() {
        et_receive= findViewById(R.id.et_receive);
        bt_send= findViewById(R.id.bt_send);
        tv_message= this.findViewById(R.id.tv_message);
        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String msg = et_receive.getText().toString();
                Log.d("Test", "line1"+(writer == null));
                //向服务器发送信息
                if(!TextUtils.isEmpty(msg)&&null!=writer) {
                    Log.d("Test", "line2");
                    writer.println(msg);
                    tv_message.setText(tv_message.getText() + "\n" + "客户端：" + msg);
                    et_receive.setText("");
                }
            }
        });
    }
}
