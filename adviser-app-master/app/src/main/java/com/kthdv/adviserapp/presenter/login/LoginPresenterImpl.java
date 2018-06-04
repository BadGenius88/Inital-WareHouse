package com.kthdv.adviserapp.presenter.login;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.Display;

import com.kthdv.adviserapp.R;
import com.kthdv.adviserapp.common.Constants;
import com.kthdv.adviserapp.common.utils.ToastUtils;
import com.kthdv.adviserapp.models.UserInfo;
import com.kthdv.adviserapp.view.login.LoginView;

/**
 * Created by TranThanhTung on 23/01/2018.
 */

public class LoginPresenterImpl implements LoginPresenter {
    private static final String TAG = LoginPresenterImpl.class.getSimpleName();
    private Context context;
    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView, Context context) {
        this.context = context;
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl(context);
    }

    @Override
    public void onViewDestroy() {
        context = null;
        loginInteractor.onViewDestroy();
    }

    @Override
    public void validateUsernameAndPassword(String username, String password) {
        if (username.isEmpty()) {
            loginView.showUsernameInputError(context.getString(R.string.enter_username));
            return;
        }
        if (password.isEmpty()) {
            loginView.showPasswordInputError(context.getString(R.string.enter_password));
            return;
        }
        loginView.showProgress();
        loginInteractor.login(username.trim(), password, new OnLoginCompleteListener() {
            @Override
            public void onLoginSuccess(UserInfo userInfo) {
                if(!userInfo.getRole().equals("adviser")) {
                    ToastUtils.quickToast(context, R.string.account_is_not_adviser);
                    return;
                }
                SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.USER_PREF, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Constants.USER_ID, userInfo.getUserID());
                editor.apply();
                loginView.navigateToHomeScreen(Activity.RESULT_OK);
            }

            @Override
            public void onWrongEmailOrPassword() {
                loginView.hideProgress();
                ToastUtils.quickToast(context, context.getString(R.string.wrong_email_or_password));
            }

            @Override
            public void onAccountNotRegistered() {
                loginView.hideProgress();
                ToastUtils.quickToast(context, context.getString(R.string.account_not_registered));
            }

            @Override
            public void onServerError(String message) {
                loginView.hideProgress();
                ToastUtils.quickToast(context, R.string.error_message);
            }

            @Override
            public void onNetworkConnectionError() {
                loginView.hideProgress();
                ToastUtils.quickToast(context, R.string.no_internet_connection);
            }
        });
    }
}
