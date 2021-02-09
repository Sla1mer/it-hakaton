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
import com.example.it_hacaton.model.AddDatabase;
import com.example.it_hacaton.model.Event;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDBForAdminActivity extends AppCompatActivity {

    private EditText name_db;
    private Button create;
    private ApiInterface apiInterface;
    private ApiInterface apiInterface2;
    private String fullname = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_d_b_for_admin);

        Intent intent = getIntent();
        fullname = intent.getStringExtra("fullname");

        name_db = findViewById(R.id.name_db);
        create = findViewById(R.id.create);
        getSupportActionBar().hide();
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiInterface = ApiClient.getAppClient().create(ApiInterface.class);
                Call<AddDatabase> call = apiInterface.add_db(name_db.getText().toString());

                call.enqueue(new Callback<AddDatabase>() {
                    @Override
                    public void onResponse(Call<AddDatabase> call, Response<AddDatabase> response) {
                        Toast.makeText(getApplicationContext(), "Succeess", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), MainForAdminActivity.class));
                    }

                    @Override
                    public void onFailure(Call<AddDatabase> call, Throwable t) {

                    }
                });

                apiInterface2 = ApiClient.getAppClient().create(ApiInterface.class);
                Call<Event> call2 = apiInterface.add_event(fullname + " " +
                        "добавил новую базу данных (" + name_db.getText().toString() + ")", null);

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