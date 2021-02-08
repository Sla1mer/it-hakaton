package com.example.it_hacaton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class RegistrationActivity extends AppCompatActivity {
private RadioButton radEmply, radAdmin;
private EditText email, password, name, middleName, surname;
private Button registration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        init();
        onCLicks();
    }
//start
    private void onCLicks(){
        View.OnClickListener BTNs = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.registr:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        break;
                    case R.id.radAdmin:
                        radEmply.setEnabled(false);
                        radAdmin.setEnabled(true);
                        break;
                    case R.id.radEmploy:
                        radAdmin.setEnabled(false);
                        radEmply.setEnabled(true);
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