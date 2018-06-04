package com.kthdv.adviserapp.services.api;

import com.kthdv.adviserapp.models.UserInfo;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginService {
    @POST("/api/auth/login")
    Observable<Response<UserInfo>> login(@Query("username") String userName,
                                         @Query("password") String password);
}
