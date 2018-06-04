package com.kthdv.adviserapp.view.register;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kthdv.adviserapp.R;
import com.kthdv.adviserapp.common.ClearableEditText;
import com.kthdv.adviserapp.common.LoadingDialog;
import com.kthdv.adviserapp.common.PasswordEditText;
import com.kthdv.adviserapp.presenter.register.RegisterPresenter;
import com.kthdv.adviserapp.presenter.register.RegisterPresenterImpl;
import com.kthdv.adviserapp.view.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterView, View.OnClickListener {
    @BindView(R.id.edt_name)
    ClearableEditText editName;
    @BindView(R.id.edt_username)
    ClearableEditText editUsername;
    @BindView(R.id.edt_password)
    PasswordEditText editPassword;
    @BindView(R.id.edt_confirm_password)
    PasswordEditText editConfirmPassword;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.txt_login)
    TextView txtLogin;
    @BindView(R.id.tool_bar)
    Toolbar toolbar;

    private LoadingDialog loadingDialog;

    @Override
    protected RegisterPresenter initPresenter() {
        return new RegisterPresenterImpl(this, this);
    }

    @Override
    protected int getLayoutResources() {
        return R.layout.activity_register;
    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {
        ButterKnife.bind(this);

        btnRegister.setOnClickListener(this);
        txtLogin.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        loadingDialog = new LoadingDialog(this);

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
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
            case R.id.btn_register: {
                getPresenter().validateRegisterForm(editName.getText(), editUsername.getText(),
                        editPassword.getText(), editConfirmPassword.getText());
            }
            break;

            case R.id.txt_login: {
                finish();
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
        loadingDialog.hide();
    }

    @Override
    public void showFullNameInputError(String message) {
        editName.setError(message);
    }

    @Override
    public void showUsernameInputError(String message) {
        editUsername.setError(message);
    }

    @Override
    public void showPasswordInputError(String message) {
        editPassword.setError(message);
    }

    @Override
    public void showConfirmPasswordInputError(String message) {
        editConfirmPassword.setError(message);
    }
    @Override
    public void backToLoginScreen() {
        finish();
    }
}
