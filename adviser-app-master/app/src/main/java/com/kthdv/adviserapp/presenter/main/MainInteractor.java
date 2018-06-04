package com.kthdv.adviserapp.presenter.main;

import com.kthdv.adviserapp.presenter.BaseInteractor;

public interface MainInteractor extends BaseInteractor {
    void getReceivedStudentForms(OnGetReceivedStudentFormsCompleteListener listener);
}
