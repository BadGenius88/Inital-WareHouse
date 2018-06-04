package com.kthdv.adviserapp.common.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Thaihn on 11/24/2017.
 */

public class ToastUtils {
    public static void quickToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void quickToast(Context context, int stringID) {
        Toast.makeText(context, stringID, Toast.LENGTH_SHORT).show();
    }
}
