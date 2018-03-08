package com.exercise.temi.util.di.component;

import com.exercise.temi.util.di.module.ContactsScreenModule;
import com.exercise.temi.util.di.scope.ContactFragmentScope;
import com.exercise.temi.view.fragment.ContactsFragment;
import com.exercise.temi.view.fragment.RecentsFragment;
import dagger.Component;

/**
 * Created by adi.matzliah on 07/03/2018.
 */
@Component(modules = ContactsScreenModule.class, dependencies = AppComponent.class)
@ContactFragmentScope
public interface ContactsScreenComponent {
    void inject(ContactsFragment fragment);
    void inject(RecentsFragment fragment);
}
