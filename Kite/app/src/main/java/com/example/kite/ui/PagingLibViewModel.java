package com.example.kite.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.example.kite.user_datasource.UserDataSourceClass;
import com.example.kite.user_datasource.UserDataSourceFactory;
import com.example.kite.utils.Repository;
import com.example.kite.utils.UserModelClass;

import io.reactivex.disposables.CompositeDisposable;


public class PagingLibViewModel extends ViewModel {

    private UserDataSourceFactory userDataSourceFactory;
    private LiveData<PagedList<UserModelClass>> listLiveData;

    private LiveData<String> progressLoadStatus = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public PagingLibViewModel(Repository repository) {
        userDataSourceFactory = new UserDataSourceFactory(repository, compositeDisposable);
        initializePaging();
    }


    private void initializePaging() {

        PagedList.Config pagedListConfig =
                new PagedList.Config.Builder()
                        .setEnablePlaceholders(true)
                        .setInitialLoadSizeHint(10)
                        .setPageSize(10).build();


        listLiveData = new LivePagedListBuilder<>(userDataSourceFactory, pagedListConfig)
                .build();

        progressLoadStatus = Transformations.switchMap(userDataSourceFactory.getMutableLiveData(),
                UserDataSourceClass::getProgressLiveStatus);

    }

    public LiveData<String> getProgressLoadStatus() {
        return progressLoadStatus;
    }

    public LiveData<PagedList<UserModelClass>> getListLiveData() {
        return listLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}