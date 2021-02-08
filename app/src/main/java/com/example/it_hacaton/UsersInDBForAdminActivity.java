package com.example.it_hacaton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.it_hacaton.Adapters.UsersForAdminAdapter;
import com.example.it_hacaton.Items.ItemUsersForAdmin;

import java.util.ArrayList;

public class UsersInDBForAdminActivity extends AppCompatActivity {
    private RecyclerView rv;

    private UsersForAdminAdapter adapter;
    private ArrayList<ItemUsersForAdmin> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_in_d_b_for_admin);

        rv = findViewById(R.id.rv);
        findViewById(R.id.addImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddUserToForAdminActivity.class));
            }
        });

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        arrayList.add(new ItemUsersForAdmin("Пупа", "Пупович"));

        adapter = new UsersForAdminAdapter(arrayList, this);
        rv.setAdapter(adapter);

    }
}