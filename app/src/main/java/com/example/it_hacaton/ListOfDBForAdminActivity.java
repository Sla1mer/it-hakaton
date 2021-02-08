package com.example.it_hacaton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.it_hacaton.Adapters.AdapterForDBList_ForAdmin;
import com.example.it_hacaton.Items.ItemForDBForAdmin;

import java.util.ArrayList;

public class ListOfDBForAdminActivity extends AppCompatActivity {
    private RecyclerView rv;
    private AdapterForDBList_ForAdmin adapter;
    private ArrayList<ItemForDBForAdmin> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_d_b_for_admin);

        init();
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        arrayList.add(new ItemForDBForAdmin("База данных 1"));
        adapter = new AdapterForDBList_ForAdmin(arrayList, this);
        rv.setAdapter(adapter);

    }

    private void init(){
        rv = findViewById(R.id.rv);
    }

}