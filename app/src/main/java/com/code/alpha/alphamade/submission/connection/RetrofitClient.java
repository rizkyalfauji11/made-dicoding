package com.code.alpha.alphamade.submission.connection;

import android.content.Context;

import com.code.alpha.alphamade.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

class RetrofitClient {

    private static final int REQUEST_TIME_OUT = 3;

    static Retrofit getClient(Context context) {
        final Gson gsonBuilder = new GsonBuilder().create();

        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout((long) REQUEST_TIME_OUT, TimeUnit.MINUTES)
                .readTimeout((long) REQUEST_TIME_OUT, TimeUnit.MINUTES)
                .addInterceptor(logging);

        final OkHttpClient okHttpClient = httpClient.build();
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
                .build();
    }
}
