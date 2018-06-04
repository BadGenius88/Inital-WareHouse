package com.kthdv.adviserapp.services.api;

import com.kthdv.adviserapp.models.StudentForm;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface GetReceivedStudentFormsService {
    @GET("/api/adviser/trainingPointForms")
    Observable<List<StudentForm>> getReceivedStudentForm();
}
