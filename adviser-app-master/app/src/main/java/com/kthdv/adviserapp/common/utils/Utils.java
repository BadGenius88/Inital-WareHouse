package com.kthdv.adviserapp.common.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {
    public static boolean checkNetwork(Context context) {
        boolean available = false;
        ConnectivityManager cn = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cn.getActiveNetworkInfo();
        if (info != null && info.isAvailable() && info.isConnected()) {
            available = true;
        }
        return available;
    }

    public static String getDateFromMilliseconds(long milliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        return formatter.format(new Date(milliSeconds));
    }
}
