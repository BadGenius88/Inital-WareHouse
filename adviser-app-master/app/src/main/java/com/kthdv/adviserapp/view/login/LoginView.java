package com.kthdv.adviserapp.view.login;

/**
 * Created by TranThanhTung on 23/01/2018.
 */

public interface LoginView {
    void showProgress();
    void hideProgress();
    void showUsernameInputError(String message);
    void showPasswordInputError(String message);
    void navigateToHomeScreen(int resultCode);
}
