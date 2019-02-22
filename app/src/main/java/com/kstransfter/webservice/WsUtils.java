package com.kstransfter.webservice;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class WsUtils {
    public static final String API_Key = "AIzaSyCmcJ6hsgOceI-icnBoJ8W7L4zZaTJPefQ";

    public abstract void init();

    public static void getReponse(Call call, final int code, final WsResponse jsonResponse) {
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                jsonResponse.successResponse(response.body(), code);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                jsonResponse.failureRespons(t, code);
                call.cancel();
            }
        });
    }

}
