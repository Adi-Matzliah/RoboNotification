package com.exercise.temi.util.di.module;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.exercise.temi.db.dao.ContactDao;
import com.exercise.temi.db.database.ContactsDatabase;
import com.exercise.temi.repository.StorageRepository;
import com.exercise.temi.util.di.scope.AppScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by adi.matzliah on 07/03/2018.
 */
@Module
public class StorageModule {
    private String mFileName;
    private RoomDatabase mRoomDB;
    public StorageModule(Context context, String fileName) {
        this.mFileName = fileName;
        this.mRoomDB = Room.databaseBuilder(context, ContactsDatabase.class
                , mFileName).build();
    }

/*    @Provides
    @AppScope
    File provideFileInternalStorage(Context context) {
        return context.getFileStreamPath(mFileName);
    }

    @Provides
    @AppScope
    SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }*/

    @Provides
    @AppScope
    ContactsDatabase provideDB(Context context) {
        return Room.databaseBuilder(context,
                ContactsDatabase.class, mFileName).build();
    }

    @Provides
    @AppScope
    ContactDao provideDao(ContactsDatabase db) {
        return db.contactDao();
    }

    @Provides
    @AppScope
    StorageRepository storageRepository(ContactsDatabase roomDB){
        return new StorageRepository(roomDB);
    }
}
