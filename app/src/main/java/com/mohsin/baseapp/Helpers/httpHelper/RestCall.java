package com.mohsin.baseapp.Helpers.httpHelper;

import com.mohsin.baseapp.Helpers.Entities.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RestCall {

    private static Retrofit retrofitMain = null;


    public static Retrofit getMainClient() {

        if (retrofitMain==null) {


            final OkHttpClient okHttpClient = new OkHttpClient.Builder()

                    .readTimeout(360, TimeUnit.SECONDS)
                    .connectTimeout(360, TimeUnit.SECONDS)
                    .callTimeout(360,TimeUnit.SECONDS)
                    .build();

            retrofitMain = new Retrofit.Builder()
                    .baseUrl(Constants.getMainUrl())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofitMain;
    }

//    public static Retrofit getMainClient() {
//
//        if (retrofitMain == null) {
//
//
//            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//
//            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//            Strategy strategy = new AnnotationStrategy();
//
//            Serializer serializer = new Persister(strategy);
//
//            OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                    .addInterceptor(interceptor)
//                    .connectTimeout(2, TimeUnit.MINUTES)
//                    .writeTimeout(2, TimeUnit.MINUTES)
//                    .readTimeout(2, TimeUnit.MINUTES)
//                    .build();
//
//            retrofitMain = new Retrofit.Builder()
//                    .addConverterFactory(SimpleXmlConverterFactory.create(serializer))
//                    .baseUrl(Constants.getMainUrl())
//                    .client(okHttpClient)
//                    .build();
//        }
//        return retrofitMain;
//    }





}
