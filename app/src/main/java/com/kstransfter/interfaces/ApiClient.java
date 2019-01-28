package com.kstransfter.interfaces;

import com.kstransfter.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "http://apnaplaces.com/kstranfer/test_react/api/v1/";
    public static final String BASE_URL_LOGIN = "http://192.168.137.1/Test/";
    public static String BASE_URL_GOOGLE_API = "https://maps.googleapis.com/";
/*
             key=&input=delhi";
*/

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        //We can put logger here
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getClientForGoogleApi() {
        //We can put logger here
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL_GOOGLE_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
          }
        return retrofit;
    }

}
