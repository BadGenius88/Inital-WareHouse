package com.kthdv.adviserapp.services.api;

import com.kthdv.adviserapp.models.AdviserDto;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterService {
    @POST("/api/auth/register")
    Observable<Response<Void>> register(@Body AdviserDto adviserDto);
}
