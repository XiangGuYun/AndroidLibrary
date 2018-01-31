package yxd.paint;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Shader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
/*
Shader:渲染器、着色器。
BitmapShader 位图
LinearGradient 线性
RadialGradient 光束
SweepGradient 梯度
CompressShader 混合

填充模式
CLAMP：拉伸
REPEAT：重复
MIRROR：镜像
 */
public class ShaderActivity extends AppCompatActivity {

    private Bitmap bitmap;
    private BitmapShader bitmapShader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shader);

        bitmap = BitmapFactory.
                decodeResource(getResources(), R.mipmap.ic_launcher);
        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

    }
}
