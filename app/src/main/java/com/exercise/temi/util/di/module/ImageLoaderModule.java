package com.exercise.temi.util.di.module;

import android.content.Context;
import com.bumptech.glide.Glide;
import com.exercise.temi.util.di.scope.AppScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by adi.matzliah on 07/03/2018.
 */
@Module (includes = ContextModule.class)
public class ImageLoaderModule {

    @Provides
    @AppScope
    Glide provideGlide(Context context){
        return Glide.get(context);
    }

/*    @Provides
    @AppScope
    Picasso picasso(@ApplicationContext Context context, OkHttp3Downloader okHttp3Downloader) {
        return new Picasso.Builder(context)
                .downloader(okHttp3Downloader)
                .build();
    }

    @Provides
    @AppScope
    OkHttp3Downloader okHttp3Downloader(OkHttpClient okHttpClient) {
        return new OkHttp3Downloader(okHttpClient);
    }*/
}
