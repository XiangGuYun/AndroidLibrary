package yxd.retrofit2.case1_base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import yxd.retrofit2.R;

/*
GET的URL
www.BaseURL.com/扩展URL        ? 参数名=参数值&参数名=参数值
  BaseURl()    @GET({扩展URL})   (@Query(参数名)参数类型 参数值)
  
POST的URL
 @POST("/aaa")
 Call<MBean> send( @Body UserInfo body);

 */
public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRetrofit();//初始化Retrofit，基本URL，GSON转换工厂

        createRepoEnqueue1();//链式请求

        createRepoEnqueue2();//分步请求

    }

    private void createRepoEnqueue2() {
          /*
        创建通信接口
         */
        APIInterface service = retrofit.create(APIInterface.class);
        //这样的方式有利于我们使用不同参数访问同一个 Web API 接口
        Call<TestModel> model = service.repo("Guolei1130");
        model.enqueue(new Callback<TestModel>() {
            @Override
            public void onResponse(Call<TestModel> call, Response<TestModel> response) {
                Log.d("Test", "请求成功了！！！");
            }

            @Override
            public void onFailure(Call<TestModel> call, Throwable t) {
                Log.d("Test", "请求败了！！！");
            }
        });
    }

    private void createRepoEnqueue1() {
        retrofit.create(APIInterface.class)
                .repo("Guolei1130")
                .enqueue(new Callback<TestModel>() {
                    @Override
                    public void onResponse(Call<TestModel> call, Response<TestModel> response) {
                        Log.d("Test", "请求成功了！！！");
                    }

                    @Override
                    public void onFailure(Call<TestModel> call, Throwable t) {
                        Log.d("Test", "请求失败了！！！");
                    }
                });
    }

    private void initRetrofit() {
         retrofit= new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                //retrofit将URL分为了两部分，一部分是baseURL，一个是@GET所注解的扩展URL
                .addConverterFactory(GsonConverterFactory.create())
                //添加GSON转换工厂，将Gson数据转换实体类
                .build();
    }
}
