package com.exercise.temi.view.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.exercise.temi.App;
import com.exercise.temi.R;
import com.exercise.temi.network.model.Contact;
import com.exercise.temi.presenter.RecentsScreenPresenter;
import com.exercise.temi.util.di.component.DaggerContactsScreenComponent;
import com.exercise.temi.util.di.module.ContactsScreenModule;
import com.exercise.temi.view.ContactsView;
import com.exercise.temi.view.adapter.ContactsRecyclerViewAdapter;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecentsFragment extends BaseFragment implements ContactsView{

    @BindView
            (R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView
            (R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView
            (R.id.contentLoadingProgressBar)
    ProgressBar progressBar;

    private Unbinder unbinder;

    @Inject
    ContactsRecyclerViewAdapter adapter;

    @Inject
    RecentsScreenPresenter presenter;

    private View view;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RecentsFragment() {
    }

    public static RecentsFragment newInstance() {
        RecentsFragment fragment = new RecentsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        // Set the adapter

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        swipeRefreshLayout.setEnabled(false);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerContactsScreenComponent.builder()
                .appComponent(((App) getActivity().getApplication()).getAppComponent())
                .contactsScreenModule(new ContactsScreenModule(this, Collections.EMPTY_LIST, null))
                .build().inject(this);

        presenter.loadContacts();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public void displayContacts(List<Contact> contacts) {
        adapter = new ContactsRecyclerViewAdapter(contacts, null);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void displayNoContacts() {
        Toast.makeText(getActivity(), "No contacts", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showSwipeRefresh() {
    }

    @Override
    public void hideSWipeRefresh() {
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}