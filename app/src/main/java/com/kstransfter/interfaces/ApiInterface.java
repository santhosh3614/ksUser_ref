package com.kstransfter.interfaces;


import com.google.gson.JsonObject;
import com.kstransfter.models.AutocompleteAddrees;
import com.kstransfter.models.Result;
import com.kstransfter.models.app.Login;
import com.kstransfter.models.app.MoviesResponse;
import com.kstransfter.models.app.UploadImage;
import com.kstransfter.models.app.User;
import java.util.Map;
import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {

    @GET("maps/api/directions/json")
    Single<Result> getDirections(@Query("mode") String mode,
                                 @Query("transit_routing_preference") String routingPreference,
                                 @Query("origin") String origin,
                                 @Query("destination") String destination,
                                 @Query("key") String apiKey);


    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    @FormUrlEncoded
    @POST("user/edit")
    Call<User> updateUser(@Field("first_name") String first, @Field("last_name") String last);


    @FormUrlEncoded
    @POST("LoginNew.php/")
    Call<Login> Login(@FieldMap Map<String, String> fields);


    @Headers("Content-type: application/json")
    @POST("GetJSONResponse.php/")
    Call<JsonObject> postRawJSON(@Body JsonObject locationPost);

    @Multipart
    @POST("user/photo")
    Call<User> updateUser(@Part("photo") RequestBody photo, @Part("description") RequestBody description);

    @Multipart
    @POST("uploadNew.php")
    Call<UploadImage> postImage(@Part MultipartBody.Part image, @PartMap Map<String, RequestBody> map);

    //The
   /* @Multipart
    @POST("uploadNew.php")
    Call<ResponseBody> postImage(@Part MultipartBody.Part image, @PartMap Map<String, RequestBody> map);*/

    @GET("maps/api/place/autocomplete/json")
    Call<AutocompleteAddrees> getAutocompleteAddress
            (@Query("key") String apiKey,
             @Query("input") String address);


}
