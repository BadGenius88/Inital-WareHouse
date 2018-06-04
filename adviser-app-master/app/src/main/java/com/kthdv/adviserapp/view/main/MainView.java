package com.kthdv.adviserapp.view.main;

import com.kthdv.adviserapp.models.StudentForm;

import java.util.List;

public interface MainView {
    void showRefreshingProgress();
    void hideRefreshingProgress();
    void refreshReceivedStudentForms(List<StudentForm> studentForms);
}
