package yxd.project1.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.annotation.Target;

import yxd.project1.R;

/**
 * Created by asus on 2017/12/12.
 */

public class BitmapUtils {

    /**
     * 压缩文件路径下的图片
     * @param filePath 文件路径
     * @param targetW 想要的宽度值
     * @param targetH 想要的高度值
     * @return
     */
    public static Bitmap decodeBitmap(String filePath, int targetW, int targetH){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//设置只解码图片的边框（宽高）数据，只为测出采样率
        options.inPreferredConfig = Bitmap.Config.RGB_565;//设置图片像素格式的首选配置
        BitmapFactory.decodeFile(filePath, options);//预加载
        //获取图片的原始宽高
        int originalW = options.outWidth;
        int originalH = options.outHeight;
        //设置采样大小
        options.inSampleSize = getSimpleSize(originalW, originalH, targetW, targetH);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    public static Bitmap decodeBitmap(Context ctx, int id, int targetW, int targetH){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//设置只解码图片的边框（宽高）数据，只为测出采样率
        options.inPreferredConfig = Bitmap.Config.RGB_565;//设置图片像素格式的首选配置
        BitmapFactory.decodeResource(ctx.getResources(), id, options);//预加载
        //获取图片的原始宽高
        int originalW = options.outWidth;
        int originalH = options.outHeight;
        //设置采样大小
        options.inSampleSize = getSimpleSize(originalW, originalH, targetW, targetH);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(ctx.getResources(), id, options);
    }

    public static Bitmap compressQuality(Bitmap bitmap, int quality){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        byte[] bytes = baos.toByteArray();
        try {
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    public static Bitmap compressSize(Bitmap bitmap, int targetW, int targetH){
        return Bitmap.createScaledBitmap(bitmap, targetW, targetH, true);
    }

    /*
    计算采样率
     */
    private static int getSimpleSize(int originalW, int originalH, int targetW, int targetH) {
        int sampleSize = 1;
        if(originalW > originalH && originalW > targetW){//以宽度来计算采样值
            sampleSize = originalW/targetW;
        }else if(originalW < originalH && originalH > targetH){
            sampleSize = originalH/targetH;
        }
        if(sampleSize <= 0){
            sampleSize = 1;
        }
        return sampleSize;
    }

    public static void showBitmap1(Context ctx, ImageView iv, String imgUrl){

        Glide.with(ctx).load(imgUrl)
                .crossFade()
                .placeholder(R.mipmap.ic_launcher).into(iv);

    }

    /*
    显示网络图片
     */
    public static void showBitmap(Context ctx, ImageView iv, String imgUrl){

//        String img = (imgUrl.split(".jpg"))[0];
//        Log.d("Test", img);
//        String width = (imgUrl.split(".jpg"))[1].split("=")[1].split("x")[0];
//        int intW = Integer.valueOf(width);
//        String height = (imgUrl.split(".jpg"))[1].split("=")[1].split("x")[1];
//        int intH = Integer.valueOf(height);
        //

        double sample = 1;
        Double width = Double.valueOf(MetricsUtils.getScreenWidth((FragmentActivity) ctx));
        double imgWidth = Double.valueOf((imgUrl.split(".jpg"))[1].split("=")[1].split("x")[0]);
        double imgHeight = Double.valueOf((imgUrl.split(".jpg"))[1].split("=")[1].split("x")[1]);

        sample=width/(imgWidth*34/10);

        Glide.with(ctx).load((imgUrl.split(".jpg"))[0]+".jpg")
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .override(
                        (int)(imgWidth*sample),
                        (int)(imgHeight*sample)
                ).into(iv);



    }

}
