package com.exercise.temi.util.di.module;

import android.content.Context;
import com.exercise.temi.util.di.scope.AppScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by adi.matzliah on 07/03/2018.
 */
@Module
public class ContextModule {
    public final Context mContext;

    public ContextModule(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    @AppScope
    Context provideContext() {
        return mContext;
    }
}
