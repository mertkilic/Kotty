package com.mert.gettykotlin.data.rest;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mert Kilic on 24.10.2017.
 */

public class RestApiClient {

    @Inject
    RestService service;

    interface ServiceCallback<T> {
        void onSuccess(T result);

        void onServerError(Response<T> response);

        void onFailure(Throwable t);
    }

    class CallbackAdapter<T> implements Callback<T> {

        ServiceCallback<T> serviceCallback;

        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            if (response.isSuccessful())
                serviceCallback.onSuccess(response.body());
            else
                serviceCallback.onServerError(response);
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            serviceCallback.onFailure(t);
        }
    }
}
