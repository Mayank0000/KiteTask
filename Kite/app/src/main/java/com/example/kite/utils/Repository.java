package com.example.kite.utils;

import com.google.gson.JsonElement;

import io.reactivex.Observable;


public class Repository {

    private ApiCallInterface apiCallInterface;

    public Repository(ApiCallInterface apiCallInterface) {
        this.apiCallInterface = apiCallInterface;
    }

    /*
     * method to call news api
     * */
    public Observable<JsonElement> executeUserListApi(int index) {
        return apiCallInterface.fetchListUser(index);
    }

    public Observable<JsonElement> executeSingleUserApi(String login_name) {
        return apiCallInterface.fetchSingleUser(login_name);
    }

}
