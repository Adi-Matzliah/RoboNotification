package com.exercise.temi.db.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.exercise.temi.db.dao.ContactDao;
import com.exercise.temi.db.entity.Contact;

/**
 * Created by adi.matzliah on 07/03/2018.
 */
@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class ContactsDatabase extends RoomDatabase {
    public abstract ContactDao contactDao();
}

