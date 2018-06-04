package com.kthdv.adviserapp.presenter.register;

import com.kthdv.adviserapp.presenter.BaseResponseListener;

/**
 * Created by TranThanhTung on 24/01/2018.
 */

public interface OnRegisterCompleteListener extends BaseResponseListener {
    void onRegisterSuccess();
    void onUsernameExist();
}
