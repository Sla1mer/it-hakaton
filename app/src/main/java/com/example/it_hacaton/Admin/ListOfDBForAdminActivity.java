package com.example.it_hacaton.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.it_hacaton.API.ApiClient;
import com.example.it_hacaton.API.ApiInterface;
import com.example.it_hacaton.Adapters.AdapterForDBList_ForAdmin;
import com.example.it_hacaton.Items.ItemForDBForAdmin;
import com.example.it_hacaton.R;
import com.example.it_hacaton.model.GetListNameDB;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListOfDBForAdminActivity extends AppCompatActivity {
    private EditText search;
    private RecyclerView rv;
    private ImageView addImage;
    private AdapterForDBList_ForAdmin adapter;
    private ArrayList<ItemForDBForAdmin> arrayList = new ArrayList<>();
    private ApiInterface apiInterface;
    private String fullname = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_d_b_for_admin);

        Intent intent = getIntent();
        fullname = intent.getStringExtra("fullname");
        System.out.println(fullname + " rtyuio");

        init();
        getSupportActionBar().hide();
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddDBForAdminActivity.class).putExtra("fullname", fullname));
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        apiInterface = ApiClient.getAppClient().create(ApiInterface.class);
        Call<List<GetListNameDB>> call = apiInterface.get_list_name_db();

        call.enqueue(new Callback<List<GetListNameDB>>() {
            @Override
            public void onResponse(Call<List<GetListNameDB>> call, Response<List<GetListNameDB>> response) {
                List<GetListNameDB> names = response.body();
                for (GetListNameDB getListNameDB : names) {
                    arrayList.add(new ItemForDBForAdmin(getListNameDB.getName_db()));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<GetListNameDB>> call, Throwable t) {

            }
        });

        adapter = new AdapterForDBList_ForAdmin(arrayList, getApplicationContext());
        rv.setAdapter(adapter);

    }

    private void filter(String text){
        ArrayList<ItemForDBForAdmin> array = new ArrayList<>();
        for(ItemForDBForAdmin item : arrayList){
            if(item.getDB().toLowerCase().contains(text.toLowerCase())){
                array.add(item);
            }
        }
        adapter.filterList(array);
    }

    private void init(){
        rv = findViewById(R.id.rv);
        addImage = findViewById(R.id.addImage);
        search = findViewById(R.id.search);
    }
}