package com.example.it_hacaton.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.it_hacaton.R;

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

    private void onCLicks(){
        View.OnClickListener BTNs = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.registr:
                        startActivity(new Intent(getApplicationContext(), MainForAdminActivity.class));
                        break;
                    case R.id.radAdmin:
                        radAdmin.setEnabled(true);
                        break;
                    case R.id.radEmploy:
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

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Выход")
                .setMessage("Вы действительно хотите выйти из приложения?")
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("Нет", null).show();
    }

}