package com.exercise.temi.view.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.exercise.temi.R;
import com.exercise.temi.service.PubNubService;
import com.exercise.temi.view.adapter.ViewPagerAdapter;
import com.exercise.temi.view.fragment.ContactsFragment;
import com.exercise.temi.view.fragment.RecentsFragment;

/**
 * Created by adi.matzliah on 07/03/2018.
 */
public class MainActivity extends AppCompatActivity implements
        ViewPager.OnPageChangeListener,
        ServiceConnection {

    @BindView(R.id.viewpager)
    ViewPager viewPager;
    ViewPagerAdapter adapter;

    PubNubService.LocalBinder binder;

    @BindView(R.id.collapse_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tabs) TabLayout tabLayout;
    private boolean mBound;
    //private MainActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

        setupViewPager();
        setupCollapsingToolbar();
         //binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to LocalService
        Intent intent = new Intent(this, PubNubService.class);
        bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(this);
        mBound = false;
    }

    private void setupViewPager() {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new RecentsFragment().newInstance(), getString(R.string.recents_tab_text));
        adapter.addFrag(new ContactsFragment().newInstance(), getString(R.string.contacts_tab_text));

        viewPager.addOnPageChangeListener(this);
        //viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
    }


    private void setupCollapsingToolbar() {
        collapsingToolbarLayout.setTitleEnabled(false);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        // We've bound to LocalService, cast the IBinder and get LocalService instance
        binder = (PubNubService.LocalBinder) iBinder;
        mBound = true;
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        mBound = false;
    }

    public void sendMessageWithService(String... words){
        if (mBound)
            binder.getService().publishMessageToChannel(words);
    }

}
