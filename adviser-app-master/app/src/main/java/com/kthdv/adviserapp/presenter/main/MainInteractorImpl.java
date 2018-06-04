package com.kthdv.adviserapp.presenter.main;

import android.content.Context;

import com.kthdv.adviserapp.models.StudentForm;
import com.kthdv.adviserapp.services.ApiClient;
import com.kthdv.adviserapp.services.ResponseObserver;
import com.kthdv.adviserapp.services.api.GetReceivedStudentFormsService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainInteractorImpl implements MainInteractor {
    private Context context;
    private CompositeDisposable compositeDisposable;

    public MainInteractorImpl(Context context) {
        this.context = context;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onViewDestroy() {
        compositeDisposable.clear();
    }

    @Override
    public void getReceivedStudentForms(OnGetReceivedStudentFormsCompleteListener listener) {
        Disposable disposable = ApiClient.getClient(context)
                .create(GetReceivedStudentFormsService.class)
                .getReceivedStudentForm()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribeWith(new ResponseObserver<List<StudentForm>>() {
                    @Override
                    public void onSuccess(List<StudentForm> response) {
                        listener.onGetReceivedStudentFormsSuccess(response);
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
