package com.elgohry.ibrahimhany.gittest.ApiService;

import com.elgohry.ibrahimhany.gittest.Model.Response;
import com.elgohry.ibrahimhany.gittest.Model.SubInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiService {
    @GET ("search/repositories")
    Call<Response> getData(@Query("q") String word);
    @GET
    Call<List<SubInfo>> getSubscribers (@Url String url);
}

