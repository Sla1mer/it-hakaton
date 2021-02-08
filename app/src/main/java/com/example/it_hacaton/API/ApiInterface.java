package com.example.it_hacaton.API;

import com.example.it_hacaton.model.AddDatabase;
import com.example.it_hacaton.model.GetListNameDB;
import com.example.it_hacaton.model.GetPersonFromDBPersonal;
import com.example.it_hacaton.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    @FormUrlEncoded
    @POST("add_db.php")
    Call<AddDatabase> add_db(
            @Field("database_name") String database_name
    );

    @GET("get_list_name_db.php")
    Call<List<GetListNameDB>> get_list_name_db();

    @GET("get_list_db_personal.php")
    Call<List<GetPersonFromDBPersonal>> get_list_db_personal(
            @Query("name_db") String list_db
    );
}
