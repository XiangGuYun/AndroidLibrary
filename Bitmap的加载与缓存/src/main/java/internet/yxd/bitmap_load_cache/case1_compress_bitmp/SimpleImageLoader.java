package internet.yxd.bitmap_load_cache.case1_compress_bitmp;

import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;
import android.util.LruCache;
import android.widget.ImageView;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import internet.yxd.bitmap_load_cache.MyApp;
import internet.yxd.bitmap_load_cache.case1_compress_bitmp.disk.DiskLruCacheHelper;
import okhttp3.Call;

/**
 * Created by asus on 2017/12/12.
 */
/*
加载网络图片，并缓存到本地和内存
https://github.com/hongyangAndroid/base-diskcache
 */
public class SimpleImageLoader {

    private static SimpleImageLoader loader;

    private SimpleImageLoader(){}

    public static SimpleImageLoader.Instance getInstance(){
        return Instance.instance;
    }

    protected static class Instance{
        private static Instance instance = new Instance();
        private LruCache<String, Bitmap> lruCache;
        private DiskLruCacheHelper helper;
        private List<String> keys = new ArrayList<>();
        /*
        初始化缓存对象
         */
        private Instance(){
            //设置缓存大小
            int maxMemSize = (int) (Runtime.getRuntime().maxMemory()/8);
            lruCache = new LruCache<String, Bitmap>(maxMemSize){
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getByteCount();
                }
            };
            try {
                helper = new DiskLruCacheHelper(MyApp.getContext());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /*
        加载网络图片
         */
        public void displayImg(ImageView iv, String url){
            Bitmap bitmap = getBmpFromCache(url);
            if(bitmap != null){
                iv.setImageBitmap(bitmap);
                Toast.makeText(iv.getContext(), "从缓存中加载了图片",
                        Toast.LENGTH_SHORT).show();
            }else {
                downloadImg(iv, url);
                Toast.makeText(iv.getContext(), "从网络上下载了图片",
                        Toast.LENGTH_SHORT).show();
            }
        }

        private Bitmap getBmpFromCache(String url) {

            if(!TextUtils.isEmpty(url)){
                if(lruCache.get(url) != null){//先从内存缓存中去获取
                    return lruCache.get(url);
                }else if(helper.get(url) != null){
                    return helper.getAsBitmap(url);
                }
            }
            return null;
        }

        /*
        清空缓存
         */
        public void clear(){
            for (String key:keys
                 ) {
                lruCache.remove(key);
                keys.remove(key);
            }

        }
        /*
        下载图片，并添加到缓存中
         */
        public void downloadImg(final ImageView iv, final String url){
            OkHttpUtils//
                    .get()//
                    .url(url)//
                    .build()//
                    .execute(new FileCallBack(Environment.
                            getExternalStorageDirectory().getAbsolutePath(),
                            "cache.jpg")//
                    {

                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Toast.makeText(iv.getContext(), e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onResponse(File response, int id) {
                            Bitmap bmp = BitmapUtils.decodeBitmap(response.getAbsolutePath(),
                                    iv.getWidth(), iv.getHeight());
                            lruCache.put(url, bmp);
                            helper.put(url, bmp);
                            iv.setImageBitmap(bmp);
                        }
                    });
//            OkHttpUtils
//                    .get()//
//                    .url(url)//
//                    .build()//
//                    .execute(new BitmapCallback()
//                    {
//                        @Override
//                        public void onError(Call call, Exception e, int id) {
//                            Toast.makeText(iv.getContext(), e.getMessage(),
//                                    Toast.LENGTH_SHORT).show();
//                        }
//
//                        @Override
//                        public void onResponse(Bitmap bmp, int id) {
//                            if(!keys.contains(url)){
//                                keys.add(url);
//                            }
//                            lruCache.put(url, BitmapUtils.compressQuality(bmp, 10));
//                            helper.put(url, BitmapUtils.compressQuality(bmp, 10));
//                            iv.setImageBitmap(bmp);
//                        }
//                    });

        }
    }

}
