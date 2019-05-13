package com.example.kite;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.example.kite.di.AppComponent;
import com.example.kite.di.DaggerAppComponent;
import com.example.kite.di.UtilsModule;


public class MyApplication extends Application {
    AppComponent appComponent;
    Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        appComponent = DaggerAppComponent.builder().utilsModule(new UtilsModule()).build();
        Fresco.initialize(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }
}
