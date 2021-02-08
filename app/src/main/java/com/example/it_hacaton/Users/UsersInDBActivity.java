package com.example.it_hacaton.Users;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.it_hacaton.Adapters.UsersForAdminAdapter;
import com.example.it_hacaton.Items.ItemUsersForAdmin;
import com.example.it_hacaton.R;

import java.util.ArrayList;

public class UsersInDBActivity extends AppCompatActivity {
    private RecyclerView rv;

    private UsersForAdminAdapter adapter;
    private ArrayList<ItemUsersForAdmin> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_in_d_b);
        rv = findViewById(R.id.rv);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        arrayList.add(new ItemUsersForAdmin("Пупа", "Пупович", "dsadas"));

        adapter = new UsersForAdminAdapter(arrayList, this);
        rv.setAdapter(adapter);

    }
}