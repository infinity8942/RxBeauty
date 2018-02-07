package bj.codesolution.rxbeauty.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rylynn on 2016/5/18 0018.
 */
public class BeautyClient {

    private static BeautyRetrofit mBeautyRetrofit;
    private static final Object monitor = new Object();
    private static Retrofit retrofit;

    private static final String HOST = "http://api.huceo.com/";
    private static final int DEFAULT_TIMEOUT = 10;//second

    private BeautyClient() {
    }

    static {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(HOST)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static BeautyRetrofit getBeautyRetrofitInstance() {
        synchronized (monitor) {
            if (mBeautyRetrofit == null) {
                mBeautyRetrofit = retrofit.create(BeautyRetrofit.class);
            }
            return mBeautyRetrofit;
        }
    }

}