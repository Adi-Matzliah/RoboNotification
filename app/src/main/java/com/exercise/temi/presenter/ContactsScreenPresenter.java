package com.exercise.temi.presenter;

import android.util.Log;

import com.exercise.temi.db.mapper.ContactEntityDataMapper;
import com.exercise.temi.network.model.Contact;
import com.exercise.temi.repository.ContactsScreenRepository;
import com.exercise.temi.util.NetworkUtils;
import com.exercise.temi.view.ContactsView;

import java.util.ArrayList;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by adi.matzliah on 07/03/2018.
 */
public class ContactsScreenPresenter implements BasePresenter {

    private final ContactsView view;
    private final ContactsScreenRepository repository;
    private final NetworkUtils network;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public ContactsScreenPresenter(ContactsView view, ContactsScreenRepository repository, NetworkUtils network) {
        this.view = view;
        this.repository = repository;
        this.network = network;
    }

    @Override
    public void loadContacts() {
        //if (network.isNetworkAvailable()) {
            compositeDisposable.add(repository.getContacts()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(contacts -> {
                                if (!contacts.isEmpty() && contacts.size() > 0) {
                                    view.displayContacts(contacts);
                                    storeContacts(contacts);
                                } else view.displayNoContacts();
                                view.hideProgressBar();
                                view.hideSWipeRefresh();
                            },
                            throwable -> {
                                view.showError(throwable.getMessage());
                                view.hideProgressBar();
                                view.hideSWipeRefresh();
                            }));
        //} // else read from the DB and make a transform to the data
    }

    public void storeContacts(List<Contact> contacts){
        Completable.fromAction(() -> repository.storeContacts(contacts))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.getMessage());
                    }
                });
    }

    public void messageContact(Contact contact){
        Completable.fromAction(() -> repository.updateContact(contact))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                view.showMessage("Contact is saved.");
            }

            @Override
            public void onError(Throwable e) {
                view.showError(e.getMessage());
            }
        });
    }

    @Override
    public void destroy() {
        compositeDisposable.clear();
    }
}
