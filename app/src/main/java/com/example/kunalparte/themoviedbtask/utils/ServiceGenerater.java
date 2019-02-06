package com.example.kunalparte.themoviedbtask.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerater {

    private static Retrofit retrofit;

    private static Retrofit getRetrofitInst(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Consts.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public static <S> S createProductApiService(Class<S> sClass){
        return getRetrofitInst().create(sClass);
    }


}
