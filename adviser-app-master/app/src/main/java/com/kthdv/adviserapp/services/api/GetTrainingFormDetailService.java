package com.kthdv.adviserapp.services.api;

import com.kthdv.adviserapp.models.TrainingPointForm;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetTrainingFormDetailService {
    @GET("/api/trainingPointForm/{id}")
    Observable<TrainingPointForm> getTrainingPointFormDetail(@Path("id") String studentID);
}
