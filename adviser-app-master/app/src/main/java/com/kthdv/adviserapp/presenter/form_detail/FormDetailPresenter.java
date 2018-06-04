package com.kthdv.adviserapp.presenter.form_detail;

import com.kthdv.adviserapp.presenter.BasePresenter;

public interface FormDetailPresenter extends BasePresenter {
    void fetchFormDetail(String id);
    void sendFeedback(boolean isAccept);
}
