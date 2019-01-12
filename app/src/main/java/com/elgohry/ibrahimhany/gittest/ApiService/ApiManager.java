package com.elgohry.ibrahimhany.gittest.ApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    ApiManager instance=null;

    public ApiManager() {
        CreateRetrofit();
    }

    public ApiManager getinstannce() {
        if (instance == null) {
            instance = new ApiManager();
        }
        return instance;
    }
    public Retrofit CreateRetrofit(){

       Retrofit retrofit=new Retrofit.Builder().baseUrl("https://api.github.com/").addConverterFactory(GsonConverterFactory.create())
               .client(createCLient()).build();
       return retrofit;

    }

    private OkHttpClient createCLient() {
        OkHttpClient okHttpClient=new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }

}
