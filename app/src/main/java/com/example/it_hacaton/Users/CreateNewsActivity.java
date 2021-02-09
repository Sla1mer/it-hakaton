package com.example.it_hacaton.Users;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.it_hacaton.API.ApiClient;
import com.example.it_hacaton.API.ApiInterface;
import com.example.it_hacaton.Admin.MainForAdminActivity;
import com.example.it_hacaton.R;
import com.example.it_hacaton.model.Event;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateNewsActivity extends AppCompatActivity {
    private Button createBtn;
    private EditText name, middle_name, last_name, description;
    private boolean isReached = false;
    private ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_news);
        init();
        getSupportActionBar().hide();
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiInterface = ApiClient.getAppClient().create(ApiInterface.class);

                if (name.getText().toString().equals("") && middle_name.getText().toString().equals("") &&
                        last_name.getText().toString().equals("")) {

                    Call<Event> call = apiInterface.add_event(description.getText().toString(), null);

                    call.enqueue(new Callback<Event>() {
                        @Override
                        public void onResponse(Call<Event> call, Response<Event> response) {
                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }

                        @Override
                        public void onFailure(Call<Event> call, Throwable t) {

                        }
                    });

                } else {
                    Call<Event> call = apiInterface.add_event(description.getText().toString(),
                            last_name.getText().toString() + " " + name.getText().toString() + " " +
                                    middle_name.getText().toString());

                    call.enqueue(new Callback<Event>() {
                        @Override
                        public void onResponse(Call<Event> call, Response<Event> response) {
                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }

                        @Override
                        public void onFailure(Call<Event> call, Throwable t) {

                        }
                    });
                }
            }
        });

    }

    private void init(){
        name = findViewById(R.id.name);
        middle_name = findViewById(R.id.middleName);
        last_name = findViewById(R.id.surname);
        description = findViewById(R.id.description);
        createBtn = findViewById(R.id.create);
    }

}