package com.example.medicineshop_app.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit instance;
    private static Retrofit retrofit = null;
    public static Retrofit getInstance(String baseUrl){
        if(instance == null){

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            instance = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        }
        return instance;
    }
}
//public class RetrofitClient {
//
//    private static Retrofit retrofit = null;
//
//    public static Retrofit getClient(){
//
//        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .readTimeout(60, TimeUnit.SECONDS)
//                .connectTimeout(60, TimeUnit.SECONDS)
//                .build();
//        if(retrofit ==null){
//
//            Gson gson = new GsonBuilder()
//                    .setLenient()
//                    .create();
//
//            retrofit = new Retrofit.Builder()
//                    .baseUrl("http://192.168.100.71/")
//                    .addConverterFactory(GsonConverterFactory.create(gson))
//                    .client(okHttpClient)
//                    .build();
//        }
//        return  retrofit;
//    }
//}
