package com.example.it_hacaton.Users;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.it_hacaton.Adapters.AdapterForDBList_ForAdmin;
import com.example.it_hacaton.Items.ItemForDBForAdmin;
import com.example.it_hacaton.R;
import com.example.it_hacaton.UserAdapter.UsersAdapterInDB;

import java.util.ArrayList;
//
public class ListOfDBActivity extends AppCompatActivity {
    private RecyclerView rv;
    private UsersAdapterInDB adapter;
    private ArrayList<ItemForDBForAdmin> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_d_b);
        init();
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        arrayList.add(new ItemForDBForAdmin("База данных 1"));
        adapter = new UsersAdapterInDB(arrayList, this);
        rv.setAdapter(adapter);

    }

    private void init(){
        rv = findViewById(R.id.rv);
    }

}