package com.example.kite.di;


import com.example.kite.ui.UserListActivity;
import com.example.kite.ui.UserProfileActivity;

import javax.inject.Singleton;

import dagger.Component;


@Component(modules = {UtilsModule.class})
@Singleton
public interface AppComponent {

    void doInjection(UserListActivity userListActivity);
    void doInjection(UserProfileActivity userProfileActivity);
}
