package com.exercise.temi;

import android.app.Application;
import com.exercise.temi.util.di.component.AppComponent;
import com.exercise.temi.util.di.component.DaggerAppComponent;
import com.exercise.temi.util.di.module.AppModule;
import com.exercise.temi.util.di.module.ContextModule;
import com.exercise.temi.util.di.module.NetworkModule;
import com.exercise.temi.util.di.module.StorageModule;

public class App extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .contextModule(new ContextModule(getApplicationContext()))
                .storageModule(new StorageModule(getApplicationContext(), "db_contacts"))
                .networkModule(new NetworkModule(getString(R.string.base_url)))
                .build();
        mAppComponent.inject(this);
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

}
