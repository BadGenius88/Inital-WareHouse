package com.kthdv.adviserapp.presenter.form_detail;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.kthdv.adviserapp.common.Constants;
import com.kthdv.adviserapp.models.TrainingPointForm;
import com.kthdv.adviserapp.presenter.OnResponseCompleteListener;
import com.kthdv.adviserapp.services.ApiClient;
import com.kthdv.adviserapp.services.ResponseObserver;
import com.kthdv.adviserapp.services.api.GetTrainingFormDetailService;
import com.kthdv.adviserapp.services.api.SendFeedbackService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class FormDetailInteractorImpl implements FormDetailInteractor {
    private Context context;
    private CompositeDisposable compositeDisposable;

    public FormDetailInteractorImpl(Context context) {
        this.context = context;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onViewDestroy() {
        compositeDisposable.clear();
    }

    @Override
    public void getFormDetail(String id, OnGetFormDetailCompleteListener listener) {
        Disposable disposable = ApiClient.getClient(context)
                .create(GetTrainingFormDetailService.class)
                .getTrainingPointFormDetail(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribeWith(new ResponseObserver<TrainingPointForm>() {
                    @Override
                    public void onSuccess(TrainingPointForm response) {
                        listener.onGetFormDetailSuccess(response);
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
    public void sendFeedback(String studentID, boolean isAccept, OnResponseCompleteListener listener) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.USER_PREF, Context.MODE_PRIVATE);
        String userID = sharedPreferences.getString(Constants.USER_ID, null);
        Disposable disposable = ApiClient.getClient(context)
                .create(SendFeedbackService.class)
                .sendFeedback(studentID, isAccept? "accepted" : "rejected", userID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResponseObserver<Response<Void>>() {
                    @Override
                    public void onSuccess(Response<Void> response) {
                        listener.onSuccess();
                    }

                    @Override
                    public void onServerError(String message) {
                        Log.i("ABC", "onServerError: "+message);
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
