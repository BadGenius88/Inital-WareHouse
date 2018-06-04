package com.kthdv.adviserapp.services.api;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SendFeedbackService {
    @GET("/api/adviser/trainingPointForm/{studentID}/state/{state}")
    Observable<Response<Void>> sendFeedback(@Path("studentID") String studentID,
                                      @Path("state") String state,
                                      @Query("adviserID") String adviserID);
}
