package com.example.kite.user_datasource;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModel;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.example.kite.utils.ProfileModelClass;
import com.example.kite.utils.Repository;
import com.example.kite.utils.UserInfoHelper;

import org.json.JSONObject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ProfileDataClass extends ViewModel {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Gson gson;
    private Repository repository;
    public ProfileDataClass(Repository repository) {
        this.repository = repository;
    }


    @SuppressLint("CheckResult")
    public void callProfileService(String loginName, UserInfoHelper userInfoHelper){
        GsonBuilder builder =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        gson = builder.setLenient().create();
        repository.executeSingleUserApi(loginName)
                .doOnSubscribe(disposable ->
                {
                    compositeDisposable.add(disposable);
                })
                .subscribeOn(Schedulers.io())
                .subscribe(
                        (JsonElement result) ->
                        {
                            JSONObject jsonObject = new JSONObject(gson.toJson(result));
                            userInfoHelper.getUSerModel(
                                    new ProfileModelClass(jsonObject.optString("name"),
                                            jsonObject.optString("company"),
                                            jsonObject.optString("location"),
                                            jsonObject.optString("url"),
                                            jsonObject.optString("type"),
                                            jsonObject.optString("followers"),
                                            jsonObject.optString("following"),
                                            jsonObject.optString("repos_url")
                                    ));

                        }, Throwable::printStackTrace
                );

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }


}
