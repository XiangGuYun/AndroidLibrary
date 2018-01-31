package internet.yxd.glide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
/*
多样化媒体加载
Glide 不仅是一个图片缓存，它支持 Gif、WebP、缩略图。甚至是 Video

生命周期集成
通过设置绑定生命周期，我们可以更加高效的使用Glide提供的方式进行绑定，这样可以更好的让加载图片的请求的生命周期动态管理起来

高效的缓存策略
A. 支持Memory和Disk图片缓存
B. Picasso 只会缓存原始尺寸的图片，而 Glide 缓存的是多种规格，也就意味着 Glide 会根据你 ImageView 的大小来缓存相应大小的图片尺寸
C. 内存开销小

Android关于图片内存计算，共有四种，分别是：

ALPHA_8：每个像素占用1byte内存
ARGB_4444:每个像素占用2byte内存
ARGB_8888:每个像素占用4byte内存（默认，色彩最细腻=显示质量最高=占用的内存也最大）
RGB_565:每个像素占用2byte内存（8bit = 1byte）
举例说明：一个32位的PNG=ARGB_8888=1204x1024,那么占用空间是:1024x1024x(32/8) = 4,194,304kb=4M左右
在解析图片的时候，为了避免oom和节省内存，最好使用ARGB_4444模式（节省一半的内存空间）

缺点

使用方法复杂
由于Glide其功能强大，所以使用的方法非常多，其源码也相对的复杂
包较大

对比Picasso
Glide 是在Picasso 基础之上进行的二次开发做了不少改进，不过这也导致包比 Picasso 大不少，不过也就不到 500k（Picasso 是100多k），
用法较为复杂，不过毕竟级别还是蛮小的，影响不是很大
对比Fresco
使用较Fresco简单，但性能（加载速度 & 缓存）却比不上Fresco

应用场景

根据Glide的特点和与其他图片加载库的对比，可以得出其使用场景：

需要更多的内容表现形式(如Gif)；
更高的性能要求（缓存 & 加载速度）；

作者：Carson_Ho
链接：http://www.jianshu.com/p/c3a5518b58b2
來源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class MainActivity extends AppCompatActivity {

    private static final String IMG_URL = "http://n.sinaimg.cn/news/transform/w1000h500/20171212/dOoP-fypnsip9130896.jpg";
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = findViewById(R.id.iv);

    }

    public void download(View view) {
        Glide.with(this)
                //.with(Context context)// 绑定Context
                //.with(Activity activity);// 绑定Activity
                //.with(FragmentActivity activity);// 绑定FragmentActivity
                //.with(Fragment fragment);// 绑定Fragment
                //传入的context类型影响到Glide加载图片的优化程度
                //Glide可以监视Activity的生命周期，在Activity销毁的时候自动取消等待中的请求。
                //但是如果你使用Application context，你就失去了这种优化效果。
                .load(IMG_URL)
                .placeholder(R.mipmap.ic_launcher)//占位图
                .error(R.mipmap.ic_launcher)//错图
                .animate(R.animator.scalex)//加载自定义动画
                .crossFade()//加载交叉淡化动画
                .override(320, 240)//设置加载尺寸
                .thumbnail(0.1f)//设置缩略图支持：先加载缩略图 然后在加载全图
                //传了一个 0.1f 作为参数，Glide 将会显示原始图像的10%的大小。
                //如果原始图像有 1000x1000 像素，那么缩略图将会有 100x100 像素。
                .diskCacheStrategy(DiskCacheStrategy.ALL)//磁盘缓存
                // DiskCacheStrategy.NONE：不缓存任何图片，即禁用磁盘缓存
                // DiskCacheStrategy.ALL ：缓存原始图片 & 转换后的图片（默认）
                // DiskCacheStrategy.SOURCE：只缓存原始图片（原来的全分辨率的图像，即不缓存转换后的图片）
                // DiskCacheStrategy.RESULT：只缓存转换后的图片（即最终的图像：降低分辨率后 / 或者转换后 ，不缓存原始图片
                //.into(iv);
                .skipMemoryCache(true)//设置跳过内存缓存
                //这意味着 Glide 将不会把这张图片放到内存缓存中去
                //这里需要明白的是，这只是会影响内存缓存！Glide 将会仍然利用磁盘缓存来避免重复的网络请求。
                .centerCrop()//设置动态转换
                .priority(Priority.NORMAL)//设置下载优先级
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource,
                                                GlideAnimation<? super GlideDrawable> glideAnimation) {

                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(MainActivity.this).clearDiskCache();//清理磁盘缓存 需要在子线程中执行
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(MainActivity.this).clearMemory();//清理内存缓存 可以在UI主线程中进行

                    }
                });
            };
        }).start();
    }
}
