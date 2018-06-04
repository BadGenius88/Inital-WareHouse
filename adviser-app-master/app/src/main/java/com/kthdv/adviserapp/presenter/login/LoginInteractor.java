package com.kthdv.adviserapp.presenter.login;

import com.kthdv.adviserapp.presenter.BaseInteractor;

/**
 * Created by TranThanhTung on 23/01/2018.
 */

public interface LoginInteractor extends BaseInteractor {
    void login(String username, String password, OnLoginCompleteListener listener);
}
