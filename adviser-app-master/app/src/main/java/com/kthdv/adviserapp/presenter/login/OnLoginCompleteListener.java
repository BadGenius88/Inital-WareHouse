package com.kthdv.adviserapp.presenter.login;

import com.kthdv.adviserapp.models.UserInfo;
import com.kthdv.adviserapp.presenter.BaseResponseListener;

/**
 * Created by TranThanhTung on 23/01/2018.
 */

public interface OnLoginCompleteListener extends BaseResponseListener {
    void onLoginSuccess(UserInfo userInfo);
    void onWrongEmailOrPassword();
    void onAccountNotRegistered();
}
