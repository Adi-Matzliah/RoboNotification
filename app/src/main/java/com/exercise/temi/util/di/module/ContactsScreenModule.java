package com.exercise.temi.util.di.module;

import com.exercise.temi.network.model.Contact;
import com.exercise.temi.presenter.ContactsScreenPresenter;
import com.exercise.temi.presenter.RecentsScreenPresenter;
import com.exercise.temi.repository.ContactsScreenRepository;
import com.exercise.temi.repository.RecentsScreenRepository;
import com.exercise.temi.repository.RemoteRepository;
import com.exercise.temi.repository.StorageRepository;
import com.exercise.temi.util.NetworkUtils;
import com.exercise.temi.util.di.scope.ContactFragmentScope;
import com.exercise.temi.view.ContactsView;
import com.exercise.temi.view.adapter.ContactsRecyclerViewAdapter;
import com.exercise.temi.view.fragment.BaseFragment;
import java.util.List;
import dagger.Module;
import dagger.Provides;

/**
 * Created by adi.matzliah on 07/03/2018.
 */
@Module
public class ContactsScreenModule {
    //private final ContactsView mView;
    private final BaseFragment mFragment;
    private final BaseFragment.OnItemClickListener mListener;
    private final List<Contact> mContacts;

    public ContactsScreenModule(BaseFragment fragment, List<Contact> contacts, BaseFragment.OnItemClickListener listener/*ContactsView mView*/) {
        //this.mView = mView;
        this.mFragment = fragment;
        this.mListener = listener;
        this.mContacts = contacts;
    }

/*    @Provides
    @AppScope
    ContactsView providesContactsScreenView() {
        return mView;
    }*/

    @Provides
    @ContactFragmentScope
    ContactsRecyclerViewAdapter provideContactAdapter(){
        return new ContactsRecyclerViewAdapter(mContacts, mListener);
    }

    @Provides
    @ContactFragmentScope
    ContactsScreenPresenter provideContactsScreenPresenter(ContactsScreenRepository repository, NetworkUtils networkUtils){
        return new ContactsScreenPresenter((ContactsView)mFragment, repository, networkUtils);
    }

    @Provides
    @ContactFragmentScope
    ContactsScreenRepository provideContactsScreenRepository(RemoteRepository remoteRepository, StorageRepository storageRepository){
        return new ContactsScreenRepository(remoteRepository, storageRepository);
    }

    @Provides
    @ContactFragmentScope
    RecentsScreenPresenter provideRecentsScreenPresenter(RecentsScreenRepository repository){
        return new RecentsScreenPresenter((ContactsView)mFragment, repository);
    }

    @Provides
    @ContactFragmentScope
    RecentsScreenRepository provideRecentsScreenRepository(StorageRepository storageRepository){
        return new RecentsScreenRepository(storageRepository);
    }

}
