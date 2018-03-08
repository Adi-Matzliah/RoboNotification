package com.exercise.temi.util.di.module;

import android.app.Application;
import com.exercise.temi.util.di.scope.AppScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by adi.matzliah on 07/03/2018.
 */
@Module
public class AppModule {
    Application mApplication;

    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @AppScope
    Application provideApplication() {
        return mApplication;
    }
}
