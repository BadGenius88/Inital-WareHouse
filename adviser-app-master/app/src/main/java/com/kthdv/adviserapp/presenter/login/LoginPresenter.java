package com.kthdv.adviserapp.presenter.login;

import com.kthdv.adviserapp.presenter.BasePresenter;

/**
 * Created by TranThanhTung on 23/01/2018.
 */

public interface LoginPresenter extends BasePresenter {
    void validateUsernameAndPassword(String username, String password);
}
