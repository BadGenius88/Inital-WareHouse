package com.kthdv.adviserapp.view.register;

/**
 * Created by TranThanhTung on 24/01/2018.
 */

public interface RegisterView {
    void showProgress();
    void hideProgress();
    void showFullNameInputError(String message);
    void showUsernameInputError(String message);
    void showPasswordInputError(String message);
    void showConfirmPasswordInputError(String message);
    void backToLoginScreen();
}
