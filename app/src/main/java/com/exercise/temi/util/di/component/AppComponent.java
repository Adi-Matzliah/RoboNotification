package com.exercise.temi.util.di.component;

import android.net.NetworkInfo;
import com.exercise.temi.App;
import com.exercise.temi.db.dao.ContactDao;
import com.exercise.temi.db.database.ContactsDatabase;
import com.exercise.temi.repository.RemoteRepository;
import com.exercise.temi.repository.StorageRepository;
import com.exercise.temi.util.NetworkUtils;
import com.exercise.temi.util.di.module.AppModule;
import com.exercise.temi.util.di.module.ImageLoaderModule;
import com.exercise.temi.util.di.module.NetworkModule;
import com.exercise.temi.util.di.module.StorageModule;
import com.exercise.temi.util.di.scope.AppScope;
import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by adi.matzliah on 07/03/2018.
 */
@AppScope
@Component(modules = {AppModule.class, NetworkModule.class, ImageLoaderModule.class, StorageModule.class})
public interface AppComponent {
    void inject(App app);

    RemoteRepository remoteRepository();

    StorageRepository storageRepository();

    Retrofit retrofit();

    ContactsDatabase db();

    ContactDao dao();

    NetworkUtils networkUtils();
}
