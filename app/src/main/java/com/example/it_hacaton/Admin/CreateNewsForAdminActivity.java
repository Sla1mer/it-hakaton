package com.example.it_hacaton.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.it_hacaton.Admin.MainForAdminActivity;
import com.example.it_hacaton.R;

public class CreateNewsForAdminActivity extends AppCompatActivity {
private Button createBtn;
private EditText nameTo, description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_news_for_admin);
        init();

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainForAdminActivity.class));
            }
        });

    }

    private void init(){
        nameTo = findViewById(R.id.nameTo);
        description = findViewById(R.id.description);
        createBtn = findViewById(R.id.create);
    }

}