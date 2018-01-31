package yxd.socket.case1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;

/*
实现Service
接下来我们在Service启动时，在线程中建立TCP服务，我们监听的是8688端口，等待客户端连接，
当客户端连接时就会生成Socket。通过每次创建的Socket就可以和不同的客户端通信了。
当客户端断开连接时，服务端也会关闭Socket并结束结束通话线程。

服务端首先会向客户端发送一条消息：“您好，我是服务端”，
并接收客户端发来的消息，将收到的消息进行加工再返回给客户端。
 */
public class SocketService extends Service {

    private boolean isServiceDestroyed = false;

    public SocketService() {
    }

    @Override
    public void onCreate() {
        //开启一条线程创建服务端Socket来阻塞式接收客户端请求，并与之信息交互。
        new Thread(new TCPServer()).start();
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private class TCPServer implements Runnable {
        @Override
        public void run() {
            ServerSocket serverSocket;
            try {
                //创建服务端Socket，监听8688端口
                serverSocket = new ServerSocket(8088);
                while (!isServiceDestroyed){//当服务未销毁
                    // 接受客户端请求，并且阻塞直到接收到消息
                    try {
                        Log.d("Test", "进行了阻塞监听");
                        final Socket socket = serverSocket.accept();
                        responseClient(socket);//响应客户端的请求
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    编程思想：使用Reader读取来自客户端的消息，用Writer回应客户端。
     */
    private void responseClient(Socket socket) {
        Log.d("Test", "服务器接收到了");
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(
                    new OutputStreamWriter(
                            socket.getOutputStream()), true);
            writer.println("这里是服务端");

            reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            String line = "";
            StringBuilder builder = new StringBuilder("");
            while((line = reader.readLine())!=null){
                builder.append(line);
            }
            if(!TextUtils.isEmpty(builder.toString())){
                Log.d("Test", "收到了客户端的信息："+builder.toString());
                writer.println("收到了客户端的信息："+builder.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(reader != null)
                    reader.close();
                if(socket != null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
