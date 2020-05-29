package com.viaplay.test.service;

import com.viaplay.test.model.ViaplayData;
import com.viaplay.test.model.ViaplayResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface Api {

    @GET("https://content.viaplay.se/androiddash-se")
    Call<ViaplayResponse> sections();


    @GET
    Call<ViaplayData> sectionItems(
            @Url String url
    );

}
