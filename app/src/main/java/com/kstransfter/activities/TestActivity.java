package com.kstransfter.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.Toast;

import com.kstransfter.R;
import com.kstransfter.models.app.MoviesResponse;
import com.kstransfter.utils.StaticUtils;
import com.kstransfter.webservice.WsFactory;
import com.kstransfter.webservice.WsResponse;
import com.kstransfter.webservice.WsUtils;

import retrofit2.Call;

public class TestActivity extends BaseActivity implements WsResponse {

    private Button btnClick;
    private final static String API_KEY = "cf00cdce49f11a0bc3ebc18151ebed29";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        try {
            initial();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initial() {
        btnClick = findViewById(R.id.btnClick);
        btnClick.setOnClickListener(v -> {
            Call loginWsCall = WsFactory.getTopRattedMovie(API_KEY);
            WsUtils.getReponse(loginWsCall, StaticUtils.REQUEST_FOR_MOVIE_RESPONSE, this);
        });
    }


    @Override
    public void successResponse(Object response, int code) {
        switch (code) {
            case StaticUtils.REQUEST_FOR_MOVIE_RESPONSE:
                MoviesResponse moviesResponse = (MoviesResponse) response;
                Toast.makeText(TestActivity.this, "Response:" + moviesResponse, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void failureRespons(Throwable error, int code) {

    }

}
