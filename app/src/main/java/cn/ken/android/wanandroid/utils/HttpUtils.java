package cn.ken.android.wanandroid.utils;

import java.util.concurrent.TimeUnit;

import cn.ken.android.wanandroid.core.api.GeeksApis;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUtils {
    public static final int DEFAULT_TIME_OUT = 8000;

    public static Retrofit createRetrofit() {
        // 创建Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .client(createOkHttpBuilder().build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(GeeksApis.HOST)
                .build();
        return retrofit;
    }

    private static OkHttpClient.Builder createOkHttpBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//连接 超时时间
        builder.writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//写操作 超时时间
        builder.readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//读操作 超时时间
        builder.retryOnConnectionFailure(true);//错误重连

        // 添加公共参数拦截器
//        BasicParamsInterceptor basicParamsInterceptor = new BasicParamsInterceptor.Builder()
//                .addHeaderParam("userName","")//添加公共参数
//                .addHeaderParam("device","")
//                .build();
//        builder.addInterceptor(basicParamsInterceptor);

        return builder;
    }

}
