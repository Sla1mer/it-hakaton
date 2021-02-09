package com.example.it_hacaton.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.it_hacaton.API.ApiClient;
import com.example.it_hacaton.API.ApiInterface;
import com.example.it_hacaton.R;
import com.example.it_hacaton.model.AddPersonFromDBPersonal;
import com.example.it_hacaton.model.Event;
import com.example.it_hacaton.model.GetPersonFromDBPersonal;
import com.example.it_hacaton.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddUserToForAdminActivity extends AppCompatActivity {

    private EditText last_name, name, middle_name;
    private Button invite;
    private ApiInterface apiInterface;
    private ApiInterface apiInterface2;
    private String fullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_to_for_admin);

        Intent intent = getIntent();
        getSupportActionBar().hide();
        String name_db = intent.getStringExtra("name_db");
        fullname = intent.getStringExtra("fullname");

        last_name = findViewById(R.id.surname);
        name = findViewById(R.id.name);
        middle_name = findViewById(R.id.middleName);
        invite = findViewById(R.id.invite);

        System.out.println(name_db);

        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiInterface = ApiClient.getAppClient().create(ApiInterface.class);
                Call<AddPersonFromDBPersonal> call = apiInterface.add_user_from_db_personal(name.getText().toString(),
                        middle_name.getText().toString(), last_name.getText().toString(), name_db);

                call.enqueue(new Callback<AddPersonFromDBPersonal>() {
                    @Override
                    public void onResponse(Call<AddPersonFromDBPersonal> call, Response<AddPersonFromDBPersonal> response) {
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), ListOfDBForAdminActivity.class));
                    }

                    @Override
                    public void onFailure(Call<AddPersonFromDBPersonal> call, Throwable t) {

                    }
                });

                apiInterface2 = ApiClient.getAppClient().create(ApiInterface.class);
                Call<Event> call2 = apiInterface.add_event(fullname + " " +
                        "добавил " + last_name.getText().toString() +
                        " " + name.getText().toString() + " " + middle_name.getText().toString() +
                        " в базу данных " + name_db, last_name.getText().toString() +
                        " " + name.getText().toString() + " " + middle_name.getText().toString());

                call2.enqueue(new Callback<Event>() {
                    @Override
                    public void onResponse(Call<Event> call, Response<Event> response) {
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), MainForAdminActivity.class));
                    }

                    @Override
                    public void onFailure(Call<Event> call, Throwable t) {

                    }
                });
            }

        });
    }
}