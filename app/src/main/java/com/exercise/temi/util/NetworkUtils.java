package com.exercise.temi.util;

import android.net.NetworkInfo;
import javax.inject.Inject;

/**
 * Created by adi.matzliah on 07/03/2018.
 */
public class NetworkUtils {
    @Inject
    NetworkInfo networkInfo;

    public boolean isNetworkAvailable() {
        return networkInfo != null && networkInfo.isConnected();
    }
}
