package com.example.kite.user_datasource;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.example.kite.utils.Constant;
import com.example.kite.utils.Repository;
import com.example.kite.utils.UserModelClass;

import org.json.JSONArray;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;

public class UserDataSourceClass extends PageKeyedDataSource<Integer, UserModelClass> {

    private Repository repository;
    private Gson gson;
    private int sourceIndex;
    private MutableLiveData<String> progressLiveStatus;
    private CompositeDisposable compositeDisposable;

    UserDataSourceClass(Repository repository, CompositeDisposable compositeDisposable) {
        this.repository = repository;
        this.compositeDisposable = compositeDisposable;
        progressLiveStatus = new MutableLiveData<>();
        GsonBuilder builder =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        gson = builder.setLenient().create();
    }


    public MutableLiveData<String> getProgressLiveStatus() {
        return progressLiveStatus;
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, UserModelClass> callback) {

        repository.executeUserListApi(sourceIndex)
                .doOnSubscribe(disposable ->
                {
                    compositeDisposable.add(disposable);
                    progressLiveStatus.postValue(Constant.LOADING);
                })
                .subscribe(
                        (JsonElement result) ->
                        {
                            progressLiveStatus.postValue(Constant.LOADED);
                            JSONArray jsonArray = new JSONArray(gson.toJson(result));
                            ArrayList<UserModelClass> arrayList = new ArrayList<>();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                arrayList.add(new UserModelClass(jsonArray.getJSONObject(i).optString("login")));
                                int id = jsonArray.getJSONObject(i).optInt("id");
                                if(jsonArray.length()-1==i){
                                    sourceIndex = id;
                                }
                            }
                            callback.onResult(arrayList, null, sourceIndex);
                        },
                        throwable ->
                                progressLiveStatus.postValue(Constant.LOADED)

                );

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, UserModelClass> callback) {

    }

    @SuppressLint("CheckResult")
    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, UserModelClass> callback) {
        repository.executeUserListApi(sourceIndex)
                .doOnSubscribe(disposable ->
                {
                    compositeDisposable.add(disposable);
                    progressLiveStatus.postValue(Constant.LOADING);
                })
                .subscribe(
                        (JsonElement result) ->
                        {
                            progressLiveStatus.postValue(Constant.LOADED);
                            JSONArray jsonArray = new JSONArray(gson.toJson(result));
                            ArrayList<UserModelClass> arrayList = new ArrayList<>();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                arrayList.add(new UserModelClass(jsonArray.getJSONObject(i).optString("login")));
                                int id = jsonArray.getJSONObject(i).optInt("id");
                                if (jsonArray.length() - 1 == i) {
                                    sourceIndex = id;
                                }
                            }
                            callback.onResult(arrayList, sourceIndex);
                        },
                        throwable ->
                                progressLiveStatus.postValue(Constant.LOADED)
                );
    }

}
