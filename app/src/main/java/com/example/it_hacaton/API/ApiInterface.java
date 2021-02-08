package com.example.it_hacaton.API;

import com.example.it_hacaton.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("add_user.php")
    Call<User> reg(
            @Field("name") String name,
            @Field("middle_name") String middle_name,
            @Field("last_name") String last_name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("status") String status
    );

    @GET("get_user_personal.php")
    Call<List<User>> auth(
            @Query("email") String email,
            @Query("password") String password
    );
}
