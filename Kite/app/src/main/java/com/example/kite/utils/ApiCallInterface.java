package com.example.kite.utils;

import com.google.gson.JsonElement;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiCallInterface {

    @GET(Urls.FetchUserList)
    Observable<JsonElement> fetchListUser(@Query("since") int source);

    @GET("users/{login_name}")
    Observable<JsonElement> fetchSingleUser(@Path("login_name") String login_name);
}
