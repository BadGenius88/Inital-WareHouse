package com.kthdv.adviserapp.services;

import android.content.Context;

import com.kthdv.adviserapp.common.utils.Utils;
import com.kthdv.adviserapp.exception.NoInternetConnectionException;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ConnectivityInterceptor implements Interceptor {
    private Context context;

    public ConnectivityInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if(!Utils.checkNetwork(context)) {
            throw new NoInternetConnectionException();
        }
        Request.Builder builder = chain.request().newBuilder();
        return chain.proceed(builder.build());
    }
}
