package com.kthdv.adviserapp.presenter;

/**
 * Created by TranThanhTung on 18/03/2018.
 */

public interface BaseResponseListener {
    void onServerError(String message);
    void onNetworkConnectionError();
}
