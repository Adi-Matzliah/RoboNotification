package com.exercise.temi.repository;

import com.exercise.temi.network.model.Contact;
import java.util.List;
import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.http.GET;

/**
 * Created by adi.matzliah on 07/03/2018.
 */
public class RemoteRepository {
    private final Retrofit retrofit;

    public RemoteRepository(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public Observable<List<Contact>> getContacts() {
        return retrofit.create(ContactAPI.class).getContactList();
    }

    public interface ContactAPI {
        @GET("roboteam/contacts_mock_short.json")
        Observable<List<Contact>> getContactList();
    }
}
