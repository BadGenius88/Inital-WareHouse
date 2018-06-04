package com.kthdv.adviserapp.presenter.register;

import android.content.Context;

import com.kthdv.adviserapp.R;
import com.kthdv.adviserapp.common.utils.ToastUtils;
import com.kthdv.adviserapp.models.AdviserDto;
import com.kthdv.adviserapp.view.register.RegisterView;

/**
 * Created by TranThanhTung on 24/01/2018.
 */

public class RegisterPresenterImpl implements RegisterPresenter,
        OnRegisterCompleteListener {
    private static final String TAG = RegisterPresenter.class.getSimpleName();
    private Context context;
    private RegisterView registerView;
    private RegisterInteractor registerInteractor;

    public RegisterPresenterImpl(Context context, RegisterView registerView) {
        this.context = context;
        this.registerView = registerView;
        this.registerInteractor = new RegisterInteractorImpl(context);
    }

    @Override
    public void onViewDestroy() {
        registerInteractor.onViewDestroy();
    }

    @Override
    public void validateRegisterForm(String fullName,
                                     String username,
                                     String password,
                                     String confirmPassword) {
        if (fullName.isEmpty()) {
            registerView.showFullNameInputError(context.getString(R.string.enter_full_name));
            return;
        }

        if (username.isEmpty()) {
            registerView.showUsernameInputError(context.getString(R.string.enter_username));
            return;
        }

        if (password.isEmpty()) {
            registerView.showPasswordInputError(context.getString(R.string.enter_password));
            return;
        }
        if (confirmPassword.isEmpty()) {
            registerView.showConfirmPasswordInputError(context.getString(R.string.enter_confirm_password));
            return;
        }
        if (!confirmPassword.equals(password)) {
            registerView.showConfirmPasswordInputError(context.getString(R.string.confirm_password_mismatch));
            return;
        }

        registerView.showProgress();
        registerInteractor.register(new AdviserDto(fullName, username, password), this);
    }

    @Override
    public void onRegisterSuccess() {
        registerView.hideProgress();
        registerView.backToLoginScreen();
    }

    @Override
    public void onUsernameExist() {
        registerView.hideProgress();
        registerView.showUsernameInputError(context.getString(R.string.username_exist));
    }

    @Override
    public void onServerError(String message) {
        registerView.hideProgress();
        ToastUtils.quickToast(context, R.string.error_message);
    }

    @Override
    public void onNetworkConnectionError() {
        registerView.hideProgress();
        ToastUtils.quickToast(context, R.string.no_internet_connection);
    }
}
