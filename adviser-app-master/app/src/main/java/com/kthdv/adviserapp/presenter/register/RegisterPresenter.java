package com.kthdv.adviserapp.presenter.register;

import com.kthdv.adviserapp.presenter.BasePresenter;

/**
 * Created by TranThanhTung on 24/01/2018.
 */

public interface RegisterPresenter extends BasePresenter {
    void validateRegisterForm(String fullName,
                              String username,
                              String password,
                              String confirmPassword);
}
