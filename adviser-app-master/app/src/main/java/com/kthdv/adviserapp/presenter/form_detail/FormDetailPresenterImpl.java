package com.kthdv.adviserapp.presenter.form_detail;

import android.content.Context;

import com.kthdv.adviserapp.R;
import com.kthdv.adviserapp.common.utils.ToastUtils;
import com.kthdv.adviserapp.models.TrainingPointForm;
import com.kthdv.adviserapp.presenter.OnResponseCompleteListener;
import com.kthdv.adviserapp.view.form_detail.FormDetailView;

public class FormDetailPresenterImpl implements FormDetailPresenter {
    private Context context;
    private FormDetailView formDetailView;
    private FormDetailInteractor formDetailInteractor;
    private String studentID;

    public FormDetailPresenterImpl(Context context, FormDetailView formDetailView) {
        this.context = context;
        this.formDetailView = formDetailView;
        this.formDetailInteractor = new FormDetailInteractorImpl(context);
    }

    @Override
    public void onViewDestroy() {
        formDetailInteractor.onViewDestroy();
    }

    @Override
    public void fetchFormDetail(String id) {
        formDetailView.showLoadingProgress();
        formDetailInteractor.getFormDetail(id, new OnGetFormDetailCompleteListener() {
            @Override
            public void onGetFormDetailSuccess(TrainingPointForm trainingPointForm) {
                formDetailView.hideLoadingProgress();
                studentID = trainingPointForm.getStudentID();
                formDetailView.showStudentForm(trainingPointForm);
            }

            @Override
            public void onServerError(String message) {
                formDetailView.hideLoadingProgress();
                ToastUtils.quickToast(context, R.string.error_message);
            }

            @Override
            public void onNetworkConnectionError() {
                formDetailView.hideLoadingProgress();
                ToastUtils.quickToast(context, R.string.no_internet_connection);
            }
        });
    }

    @Override
    public void sendFeedback(boolean isAccept) {
        formDetailView.showLoadingProgress();
        formDetailInteractor.sendFeedback(studentID, isAccept, new OnResponseCompleteListener() {
            @Override
            public void onSuccess() {
                formDetailView.hideLoadingProgress();
                formDetailView.hideButtons();
            }

            @Override
            public void onServerError(String message) {
                formDetailView.hideLoadingProgress();
                formDetailView.hideButtons();
            }

            @Override
            public void onNetworkConnectionError() {
                formDetailView.hideLoadingProgress();
                ToastUtils.quickToast(context, R.string.no_internet_connection);
            }
        });
    }
}
