package com.viaplay.test.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiCallService {

    private static <T> T builder(Class<T> endpoint) {
        String API_BASE_URL = "https://content.viaplay.se/";
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
