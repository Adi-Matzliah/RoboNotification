package com.exercise.temi.repository;

import com.exercise.temi.db.database.ContactsDatabase;
import com.exercise.temi.db.entity.Contact;
import java.util.List;
import io.reactivex.Flowable;

/**
 * Created by adi.matzliah on 07/03/2018.
 */
public class StorageRepository {
    private final ContactsDatabase db;

    public StorageRepository(ContactsDatabase contactDB) {
        this.db = contactDB;
    }

    public void saveContact(Contact contact){
        db.contactDao().insert(contact);
    }

    public void updateContact(Contact contact){
        db.contactDao().insertOrUpdate(contact);
    }

    public Flowable<List<Contact>> loadAllMessagedContacts(){
        return db.contactDao().loadAllMessaged();
    }
}
