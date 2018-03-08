package com.exercise.temi.view;

import com.exercise.temi.network.model.Contact;
import java.util.List;

/**
 * Created by adi.matzliah on 07/03/2018.
 */
public interface ContactsView {
    void displayContacts(List<Contact> contacts);
    void displayNoContacts();
    void showError(String message);
    void showProgressBar();
    void hideProgressBar();
    void showSwipeRefresh();
    void hideSWipeRefresh();
    void showMessage(String message);
}
