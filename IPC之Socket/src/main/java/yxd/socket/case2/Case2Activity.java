package yxd.socket.case2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import yxd.socket.R;

public class Case2Activity extends AppCompatActivity implements Runnable{

    private Socket socket;
    private ServerSocket serverSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case2);
        Log.d("Test", "ip "+IpUtils.getIPAddress(this));
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            while(socket==null){
                socket = new Socket("localhost", 5555);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Test", e.getMessage());
        }
    }

    public void conn(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Socket connSocket = null;
                try {
                    serverSocket = new ServerSocket(5555);
                    connSocket = serverSocket.accept();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    writer.write("我是客户端");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connSocket.getInputStream()));
                    Log.d("Test", "服务端收到了消息"+reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("Test", e.getMessage()+"---------");
                }


            }
        });
    }
}
