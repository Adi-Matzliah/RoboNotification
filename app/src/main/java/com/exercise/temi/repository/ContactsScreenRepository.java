package com.exercise.temi.repository;

import com.exercise.temi.db.mapper.ContactEntityDataMapper;
import com.exercise.temi.network.model.Contact;
import java.sql.Timestamp;
import java.util.List;
import io.reactivex.Observable;

/**
 * Created by adi.matzliah on 07/03/2018.
 */
public class ContactsScreenRepository implements ContactsRepository {


    private final RemoteRepository remoteRepository;
    private final StorageRepository localRepository;

    public ContactsScreenRepository(RemoteRepository remoteRepository, StorageRepository localRepository) {
        this.remoteRepository = remoteRepository;
        this.localRepository = localRepository;
    }

    @Override
    public Observable<List<Contact>> getContacts() {
        return remoteRepository.getContacts();
    }

    public void storeContacts(List<Contact> contacts){
        com.exercise.temi.db.entity.Contact newContact;
        for (Contact contact: contacts) {
            newContact = ContactEntityDataMapper.transform(contact);
            localRepository.saveContact(newContact);
        }
    }
    public void updateContact(Contact contact) {
        com.exercise.temi.db.entity.Contact newContact = ContactEntityDataMapper.transform(contact);
        newContact.setLastMsgDate(new Timestamp(System.currentTimeMillis()).toString());
        localRepository.updateContact(newContact);
    }
}
