package com.kthdv.adviserapp.view.form_detail;

import com.kthdv.adviserapp.models.TrainingPointForm;

public interface FormDetailView {
    void showLoadingProgress();
    void hideLoadingProgress();
    void showStudentForm(TrainingPointForm trainingPointForm);
    void hideButtons();
}
