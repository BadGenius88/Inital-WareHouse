package com.kthdv.adviserapp.presenter.form_detail;

import com.kthdv.adviserapp.models.TrainingPointForm;
import com.kthdv.adviserapp.presenter.BaseResponseListener;

public interface OnGetFormDetailCompleteListener extends BaseResponseListener {
    void onGetFormDetailSuccess(TrainingPointForm trainingPointForm);
}
