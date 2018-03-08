package com.exercise.temi.repository;


import com.exercise.temi.network.model.Contact;
import java.util.List;
import io.reactivex.Observable;

/**
 * Created by adi.matzliah on 07/03/2018.
 */
public interface ContactsRepository {
    Observable<List<Contact>> getContacts();
}
