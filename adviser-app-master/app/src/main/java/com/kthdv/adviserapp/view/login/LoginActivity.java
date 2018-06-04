package com.kthdv.adviserapp.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kthdv.adviserapp.R;
import com.kthdv.adviserapp.common.ClearableEditText;
import com.kthdv.adviserapp.common.Constants;
import com.kthdv.adviserapp.common.LoadingDialog;
import com.kthdv.adviserapp.common.PasswordEditText;
import com.kthdv.adviserapp.presenter.login.LoginPresenter;
import com.kthdv.adviserapp.presenter.login.LoginPresenterImpl;
import com.kthdv.adviserapp.view.BaseActivity;
import com.kthdv.adviserapp.view.main.MainActivity;
import com.kthdv.adviserapp.view.register.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity<LoginPresenter> implements View.OnClickListener, LoginView {
    public static final String TAG = LoginActivity.class.getSimpleName();

    @BindView(R.id.edt_username)
    ClearableEditText editUsername;
    @BindView(R.id.edt_password)
    PasswordEditText edtPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.txt_register)
    TextView txtRegister;

    private LoadingDialog loadingDialog;

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenterImpl(this, this);
    }

    @Override
    protected int getLayoutResources() {
        return R.layout.activity_login;
    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        loadingDialog = new LoadingDialog(this);
        btnLogin.setOnClickListener(this);
        txtRegister.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        String email = getIntent().getStringExtra(Constants.KEY_EMAIL);
        if (email != null) {
            editUsername.setText(email);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login: {
                getPresenter().validateUsernameAndPassword(editUsername.getText(), edtPassword.getText());
            }
            break;

            case R.id.txt_register: {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
            break;

            default: {
                break;
            }
        }
    }

    @Override
    public void showProgress() {
        loadingDialog.show();
    }

    @Override
    public void hideProgress() {
        loadingDialog.dismiss();
    }

    @Override
    public void showUsernameInputError(String message) {
        editUsername.setError(message);
    }

    @Override
    public void showPasswordInputError(String message) {
        edtPassword.setError(message);
    }

    @Override
    public void navigateToHomeScreen(int resultCode) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
