package com.example.kite.utils;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.kite.ui.PagingLibViewModel;
import com.example.kite.user_datasource.ProfileDataClass;

import javax.inject.Inject;


public class ViewModelFactory implements ViewModelProvider.Factory {

    private Repository repository;

    @Inject
    public ViewModelFactory(Repository repository) {
        this.repository = repository;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PagingLibViewModel.class)) {
            return (T) new PagingLibViewModel(repository);
        }else if(modelClass.isAssignableFrom(ProfileDataClass.class))
            return (T) new ProfileDataClass(repository);

        throw new IllegalArgumentException("Unknown class name");
    }
}
