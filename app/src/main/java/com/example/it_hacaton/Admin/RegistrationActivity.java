package com.example.it_hacaton.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.it_hacaton.API.ApiClient;
import com.example.it_hacaton.API.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.it_hacaton.R;
import com.example.it_hacaton.model.Event;
import com.example.it_hacaton.model.User;

public class RegistrationActivity extends AppCompatActivity {
    private RadioButton radEmply, radAdmin;
    private EditText email, password, name, middleName, surname;
    private Button registration;
    private ApiInterface apiInterface;
    private ApiInterface apiInterface2;
    private String status;
    private String fullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        init();
        onCLicks();
        getSupportActionBar().hide();

        Intent intent = getIntent();
        fullname = intent.getStringExtra("fullname");
        System.out.println(fullname);
    }

    private void onCLicks(){
        View.OnClickListener BTNs = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.radAdmin:
                        radAdmin.setEnabled(true);
                        status = radAdmin.getText().toString();
                        break;
                    case R.id.radEmploy:
                        radEmply.setEnabled(true);
                        status = radEmply.getText().toString();
                        break;
                    case R.id.registr:
                        System.out.println(password.getText().toString());
                        apiInterface = ApiClient.getAppClient().create(ApiInterface.class);
                        Call<User> call = apiInterface.reg(name.getText().toString(),
                                middleName.getText().toString(), surname.getText().toString(),
                                email.getText().toString(), password.getText().toString(),
                                status.toString());

                        call.enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), MainForAdminActivity.class));
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                        apiInterface2 = ApiClient.getAppClient().create(ApiInterface.class);
                        Call<Event> call2 = apiInterface.add_event(fullname + " " +
                                "добавил " + surname.getText().toString() + " " +
                                name.getText().toString() + " " + middleName.getText().toString() +
                                " в систему ", null);

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
                        break;
                }
            }
        };
        registration.setOnClickListener(BTNs);
        radEmply.setOnClickListener(BTNs);
        radAdmin.setOnClickListener(BTNs);
    }

    private void init(){
        radAdmin = findViewById(R.id.radAdmin);
        radEmply = findViewById(R.id.radEmploy);
        name = findViewById(R.id.firstName);
        middleName = findViewById(R.id.midleName);
        surname = findViewById(R.id.surname);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        registration = findViewById(R.id.registr);
    }



}