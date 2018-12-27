package com.kstransfter.webservice;

import com.google.gson.JsonObject;
import com.kstransfter.interfaces.ApiClient;
import com.kstransfter.interfaces.ApiInterface;
import com.kstransfter.models.AutocompleteAddrees;
import com.kstransfter.models.app.BookedCarModel;
import com.kstransfter.models.app.CarListtModel;
import com.kstransfter.models.app.DriverListModel;
import com.kstransfter.models.app.Login;
import com.kstransfter.models.app.MoviesResponse;
import com.kstransfter.models.app.SignUpModel;
import com.kstransfter.models.app.UploadImage;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

public class WsFactory {

    public static Call getTopRattedMovie(String key) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MoviesResponse> moviesResponseCall = apiService.getTopRatedMovies(key);
        return moviesResponseCall;
    }

    public static Call loginWsCall(Map<String, String> map) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Login> loginCall = apiService.login(map);
        return loginCall;
    }


    public static Call uplaodImage(MultipartBody.Part image, Map<String, RequestBody> map) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UploadImage> loginCall = apiService.postImage(image, map);
        return loginCall;
    }


    public static Call rawJSONRequest(JsonObject jsonObject) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<JsonObject> loginCall = apiService.postRawJSON(jsonObject);
        return loginCall;

    }

    public static Call getAutoCompleteAddress(String key, String address) {
        ApiInterface apiService = ApiClient.getClientForGoogleApi().create(ApiInterface.class);
        Call<AutocompleteAddrees> getAutoCompleteAddress = apiService.getAutocompleteAddress(key, address);
        return getAutoCompleteAddress;
    }

    public static Call signUp(Map<String, String> map) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<SignUpModel> signUpModelCall = apiService.signUp(map);
        return signUpModelCall;
    }

    public static Call carList(Map<String, String> map) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<CarListtModel> signUpModelCall = apiService.getCarList(map);
        return signUpModelCall;
    }

    public static Call driverList(Map<String, String> map) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<DriverListModel> signUpModelCall = apiService.getDriverList(map);
        return signUpModelCall;
    }

    public static Call carBooked(Map<String, String> map) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<BookedCarModel> signUpModelCall = apiService.getBookedCar(map);
        return signUpModelCall;
    }

}
