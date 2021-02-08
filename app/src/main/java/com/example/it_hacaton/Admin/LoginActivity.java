package com.example.it_hacaton.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.it_hacaton.R;

public class LoginActivity extends AppCompatActivity {
private EditText email, password;
private TextView regText, forgetText;
private Button enter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        init();
        onCLicks();
    }

    private void onCLicks(){
        View.OnClickListener BTNs = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.enter:
                        startActivity(new Intent(getApplicationContext(), MainForAdminActivity.class));
                        break;
                    case R.id.regText:
                        startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
                        break;
                }
            }
        };
        regText.setOnClickListener(BTNs);
        enter.setOnClickListener(BTNs);
    }

    private void init(){
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        regText = findViewById(R.id.regText);
        forgetText = findViewById(R.id.forgetText);
        enter = findViewById(R.id.enter);
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