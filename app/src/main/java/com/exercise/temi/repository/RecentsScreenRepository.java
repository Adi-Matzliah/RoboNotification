package com.exercise.temi.repository;

import com.exercise.temi.network.model.Contact;
import java.util.List;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * Created by adi.matzliah on 07/03/2018.
 */
public class RecentsScreenRepository implements ContactsRepository {


    private final StorageRepository localRepository;

    public RecentsScreenRepository(StorageRepository localRepository) {
        this.localRepository = localRepository;
    }

    @Override
    public Observable<List<Contact>> getContacts() {
        return new Observable<List<Contact>>() {
            @Override
            protected void subscribeActual(Observer<? super List<Contact>> observer) {
            }
        };
    }

    public Flowable<List<com.exercise.temi.db.entity.Contact>> getContactsDB() {
        return localRepository.loadAllMessagedContacts();
    }
}
