package com.iplds.minimintji.iplds.manager.http;

import com.iplds.minimintji.iplds.dao.CarPositions.AvailableParkingCollection;
import com.iplds.minimintji.iplds.dao.CarPositions.CarPositionCollection;
import com.iplds.minimintji.iplds.dao.Token;
import com.iplds.minimintji.iplds.dao.User;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @FormUrlEncoded
    @POST("users/login/")
    Call<Token> login(@Field("username") String username,
                      @Field("password") String password);

    @GET("users/getUserInfo/{token}/")
    Call<User> getUserInfo(@Path("token") String token);

    @FormUrlEncoded
    @POST("users/register/")
    Call<User> register(@Field("name") String name,
                        @Field("surname") String surname,
                        @Field("tel") String tel,
                        @Field("email") String email,
                        @Field("username") String username,
                        @Field("password") String password);

    @DELETE("users/logout/{token}/")
    Call<User> logout(@Path("token") String token);

    @FormUrlEncoded
    @POST("users/car/{token}/")
    Call<CarPositionCollection> sendXYPosition(@Path("token") String token,
                                               @Field("x") double x_position,
                                               @Field("y") double y_position,
                                               @Field("floor_id") int floorId,
                                               @Field("fcm_token") String fcmToken,
                                               @Field("timestamp_stop_engine") long time);

    @GET("users/showparking/")
    Call<AvailableParkingCollection> getAvailableParking();
}


