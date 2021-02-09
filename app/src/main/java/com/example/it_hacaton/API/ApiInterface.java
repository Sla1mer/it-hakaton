package com.example.it_hacaton.API;

import com.example.it_hacaton.model.AddDatabase;
import com.example.it_hacaton.model.Event;
import com.example.it_hacaton.model.AddPersonFromDBPersonal;
import com.example.it_hacaton.model.DeletePerson;
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

    @GET("add_db.php")
    Call<AddDatabase> add_db(
            @Query("database_name") String database_name,
            @Query("view") String view

    );

    @GET("get_list_name_db.php")
    Call<List<GetListNameDB>> get_list_name_db(
            @Query("view") String view
    );

    @GET("get_list_db_personal.php")
    Call<List<GetPersonFromDBPersonal>> get_list_db_personal(
            @Query("name_db") String list_db
    );

    @GET("delete_db_person.php")
    Call<DeletePerson> delete_person_from_db(
            @Query("name") String name,
            @Query("middle_name") String middle_name,
            @Query("last_name") String last_name,
            @Query("database_name") String database_name
    );

    @GET("add_user_from_db_personal.php")
    Call<AddPersonFromDBPersonal> add_user_from_db_personal(
            @Query("name") String name,
            @Query("middle_name") String middle_name,
            @Query("last_name") String last_name,
            @Query("database_name") String database_name
    );

    @GET("add_event.php")
    Call<Event> add_event(
            @Query("description") String description,
            @Query("to_subject") String to_subject
    );

    @GET("get_list_events.php")
    Call<List<Event>> get_events();
}
