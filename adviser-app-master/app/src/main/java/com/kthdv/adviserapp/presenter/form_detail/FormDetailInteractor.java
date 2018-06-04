package com.kthdv.adviserapp.presenter.form_detail;

import com.kthdv.adviserapp.presenter.BaseInteractor;
import com.kthdv.adviserapp.presenter.OnResponseCompleteListener;

public interface FormDetailInteractor extends BaseInteractor {
    void getFormDetail(String id, OnGetFormDetailCompleteListener listener);
    void sendFeedback(String studentID, boolean isAccept, OnResponseCompleteListener listener);
}
