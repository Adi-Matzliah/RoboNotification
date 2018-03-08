package com.exercise.temi.presenter;

import com.exercise.temi.db.mapper.ContactEntityDataMapper;
import com.exercise.temi.network.model.Contact;
import com.exercise.temi.repository.RecentsScreenRepository;
import com.exercise.temi.view.ContactsView;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by adi.matzliah on 07/03/2018.
 */
public class RecentsScreenPresenter implements BasePresenter{

    private final ContactsView view;
    private final RecentsScreenRepository repository;
    //private final LiveData<List<Contact>> contactsLiveData;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public RecentsScreenPresenter(ContactsView view, RecentsScreenRepository repository/*, LiveData<List<Contact>> contactsLiveData*/) {
        this.view = view;
        this.repository = repository;
        //this.contactsLiveData = contactsLiveData;
    }

    @Override
    public void loadContacts() {
        compositeDisposable.add(repository.getContactsDB()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(contacts -> {
                            if (!contacts.isEmpty() && contacts.size() > 0) {
                                List<Contact> contactsConverted = new ArrayList<>();
                                for (com.exercise.temi.db.entity.Contact contact : contacts) {
                                    contactsConverted.add(ContactEntityDataMapper.transform(contact));
                                }
                                view.displayContacts(contactsConverted);
                            }
                            view.hideProgressBar();
                        },
                        throwable -> {
                            view.showError(throwable.getMessage());
                            view.hideProgressBar();
                        }));
    }

    @Override
    public void destroy() {
        compositeDisposable.clear();
    }
}
