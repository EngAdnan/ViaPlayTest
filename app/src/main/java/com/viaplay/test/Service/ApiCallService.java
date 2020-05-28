package com.viaplay.test.Service;

import com.viaplay.test.Model.ViaplayData;
import com.viaplay.test.Model.ViaplaySections;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiCallService {

    private  static String API_BASE_URL = "https://content.viaplay.se/";

    private static <T> T builder(Class<T> endpoint) {
        return new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(endpoint);
    }

    public static Api fetchData() {
        return builder(Api.class);
    }



}
