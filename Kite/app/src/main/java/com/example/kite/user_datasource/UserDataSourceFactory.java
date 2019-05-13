package com.example.kite.user_datasource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.example.kite.utils.Repository;
import com.example.kite.utils.UserModelClass;

import io.reactivex.disposables.CompositeDisposable;


public class UserDataSourceFactory extends DataSource.Factory<Integer, UserModelClass> {

    private MutableLiveData<UserDataSourceClass> liveData;
    private Repository repository;
    private CompositeDisposable compositeDisposable;

    public UserDataSourceFactory(Repository repository, CompositeDisposable compositeDisposable) {
        this.repository = repository;
        this.compositeDisposable = compositeDisposable;
        liveData = new MutableLiveData<>();
    }

    public MutableLiveData<UserDataSourceClass> getMutableLiveData() {
        return liveData;
    }

    @Override
    public DataSource<Integer, UserModelClass> create() {
        UserDataSourceClass dataSourceClass = new UserDataSourceClass(repository, compositeDisposable);
        liveData.postValue(dataSourceClass);
        return dataSourceClass;
    }
}
