package com.example.it_hacaton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;
    private Adapter adapter;
    private ArrayList<Item> arrayList = new ArrayList<>();
    private Button rvOfBD;
    private ImageView addImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        arrayList.add(new Item("Тема", "Максимович", "Дай доступ в БД1"));

        adapter = new Adapter(this, arrayList);
        rv.setAdapter(adapter);
    }

    private void init(){
        rv = findViewById(R.id.rv);
        rvOfBD = findViewById(R.id.recyclerOfBD);
        addImage = findViewById(R.id.addImage);
    }

}