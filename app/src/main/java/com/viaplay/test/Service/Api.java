package com.viaplay.test.Service;

import com.viaplay.test.Model.ViaplayData;
import com.viaplay.test.Model.ViaplayResponse;
import com.viaplay.test.Model.ViaplaySections;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    @GET("androiddash-se")
    Call<ViaplayResponse> sections();



    @GET("{url}")
    Call<ViaplayData> sectionItems(
            @Path("url") String url
    );

}
