package com.kthdv.adviserapp.presenter.login;

import android.content.Context;

import com.kthdv.adviserapp.models.UserInfo;
import com.kthdv.adviserapp.services.ApiClient;
import com.kthdv.adviserapp.services.ResponseObserver;
import com.kthdv.adviserapp.services.api.LoginService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by TranThanhTung on 23/01/2018.
 */

public class LoginInteractorImpl implements LoginInteractor {
    private Context context;
    private CompositeDisposable compositeDisposable;

    public LoginInteractorImpl(Context context) {
        this.context = context;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void login(String username, String password, OnLoginCompleteListener listener) {
        Disposable disposable = ApiClient.getClient(context)
                .create(LoginService.class)
                .login(username,password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResponseObserver<Response<UserInfo>>() {
                    @Override
                    public void onSuccess(Response<UserInfo> response) {
                        switch (response.code()){
                            case 200:{
                                listener.onLoginSuccess(response.body());
                            }
                            break;

                            case 403:{
                                listener.onWrongEmailOrPassword();
                            }
                            break;

                            case 404:{
                                listener.onAccountNotRegistered();
                            }

                            default:{
                                listener.onServerError(response.message());
                                break;
                            }
                        }
                    }

                    @Override
                    public void onServerError(String message) {
                        listener.onServerError(message);
                    }

                    @Override
                    public void onNetworkConnectionError() {
                        listener.onNetworkConnectionError();
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    public void onViewDestroy() {
        compositeDisposable.clear();
    }
}
