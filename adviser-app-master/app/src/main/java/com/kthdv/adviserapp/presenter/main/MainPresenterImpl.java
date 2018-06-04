package com.kthdv.adviserapp.presenter.main;

import android.content.Context;

import com.kthdv.adviserapp.R;
import com.kthdv.adviserapp.common.utils.ToastUtils;
import com.kthdv.adviserapp.models.StudentForm;
import com.kthdv.adviserapp.view.main.MainView;

import java.util.List;

public class MainPresenterImpl implements MainPresenter {
    private Context context;
    private MainView mainView;
    private MainInteractor mainInteractor;

    public MainPresenterImpl(Context context, MainView mainView) {
        this.context = context;
        this.mainView = mainView;
        this.mainInteractor = new MainInteractorImpl(context);
    }

    @Override
    public void onViewDestroy() {
        mainInteractor.onViewDestroy();
    }

    @Override
    public void fetchReceivedStudentForms() {
        mainView.showRefreshingProgress();
        mainInteractor.getReceivedStudentForms(new OnGetReceivedStudentFormsCompleteListener() {
            @Override
            public void onGetReceivedStudentFormsSuccess(List<StudentForm> studentForms) {
                mainView.hideRefreshingProgress();
                mainView.refreshReceivedStudentForms(studentForms);
            }

            @Override
            public void onServerError(String message) {
                mainView.hideRefreshingProgress();
                ToastUtils.quickToast(context, R.string.error_message);
            }

            @Override
            public void onNetworkConnectionError() {
                mainView.hideRefreshingProgress();
                ToastUtils.quickToast(context, R.string.no_internet_connection);
            }
        });
    }
}
