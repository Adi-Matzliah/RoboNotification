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
import com.exercise.temi.presenter.ContactsScreenPresenter;
import com.exercise.temi.util.di.component.DaggerContactsScreenComponent;
import com.exercise.temi.util.di.module.ContactsScreenModule;
import com.exercise.temi.view.ContactsView;
import com.exercise.temi.view.activity.MainActivity;
import com.exercise.temi.view.adapter.ContactsRecyclerViewAdapter;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnItemClickListener}
 * interface.
 */
public class ContactsFragment extends BaseFragment implements
    ContactsView,
    BaseFragment.OnItemClickListener,
    SwipeRefreshLayout.OnRefreshListener {

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
    ContactsScreenPresenter presenter;

/*    @Inject
    NetworkUtils network;*/

    private View view;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ContactsFragment() {
    }

    public static ContactsFragment newInstance() {
        ContactsFragment fragment = new ContactsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        // Set the adapter
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        onRefresh();

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerContactsScreenComponent.builder()
                .appComponent(((App)getActivity().getApplication()).getAppComponent())
                .contactsScreenModule(new ContactsScreenModule(this, Collections.EMPTY_LIST, this))
                .build().inject(this);
    }

    @Override
    public void onStart() {
        super.onStart();
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
    public void onRefresh() {
        presenter.loadContacts();
    }

    @Override
    public void displayContacts(List<Contact> contacts) {
        adapter = new ContactsRecyclerViewAdapter(contacts, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void displayNoContacts() {
        Toast.makeText(getActivity(), "No contacts", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
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
        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(true));
    }

    @Override
    public void hideSWipeRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(Contact item, int position) {
        ((MainActivity)getActivity()).sendMessageWithService("Hello " + item.getFirstName() + " " + item.getLastName());
        Toast.makeText(getActivity(), "Sending message...", Toast.LENGTH_LONG).show();
        presenter.messageContact(item);
    }

    @Override
    public void onLongItemClick(Contact item, int position) {
    }
}