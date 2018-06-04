package com.kthdv.adviserapp.presenter.register;

import android.content.Context;

import com.kthdv.adviserapp.models.AdviserDto;
import com.kthdv.adviserapp.services.ApiClient;
import com.kthdv.adviserapp.services.ResponseObserver;
import com.kthdv.adviserapp.services.api.RegisterService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by TranThanhTung on 24/01/2018.
 */

public class RegisterInteractorImpl implements RegisterInteractor {
    private Context context;
    private CompositeDisposable compositeDisposable;

    public RegisterInteractorImpl(Context context) {
        this.context = context;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onViewDestroy() {
        this.compositeDisposable.clear();
    }

    @Override
    public void register(AdviserDto adviserDto,
                         OnRegisterCompleteListener listener) {
        Disposable disposable = ApiClient.getClient(context).create(RegisterService.class)
                .register(adviserDto)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResponseObserver<Response<Void>>() {
                    @Override
                    public void onSuccess(Response<Void> response) {
                        switch (response.code()) {
                            case 200:{
                                listener.onRegisterSuccess();
                            }
                            break;

                            case 409:{
                                listener.onUsernameExist();
                            }
                            break;

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
}
