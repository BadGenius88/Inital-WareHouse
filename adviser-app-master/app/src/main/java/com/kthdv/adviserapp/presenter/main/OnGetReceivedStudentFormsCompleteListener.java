package com.kthdv.adviserapp.presenter.main;

import com.kthdv.adviserapp.models.StudentForm;
import com.kthdv.adviserapp.presenter.BaseResponseListener;

import java.util.List;

public interface OnGetReceivedStudentFormsCompleteListener extends BaseResponseListener {
    void onGetReceivedStudentFormsSuccess(List<StudentForm> studentForms);
}
