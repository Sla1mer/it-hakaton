package com.example.it_hacaton.Users;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.it_hacaton.API.ApiClient;
import com.example.it_hacaton.API.ApiInterface;
import com.example.it_hacaton.Items.ItemForDBForAdmin;
import com.example.it_hacaton.R;
import com.example.it_hacaton.UserAdapter.UsersAdapterInDB;
import com.example.it_hacaton.model.GetListNameDB;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListOfDBActivity extends AppCompatActivity {
    private EditText search;
    private RecyclerView rv;
    private UsersAdapterInDB adapter;
    private ArrayList<ItemForDBForAdmin> arrayList = new ArrayList<>();
    private ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_d_b);
        init();
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        getSupportActionBar().hide();
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

        apiInterface = ApiClient.getAppClient().create(ApiInterface.class);
        Call<List<GetListNameDB>> call = apiInterface.get_list_name_db();
        call.enqueue(new Callback<List<GetListNameDB>>() {
            @Override
            public void onResponse(Call<List<GetListNameDB>> call, Response<List<GetListNameDB>> response) {
                List<GetListNameDB> names = response.body();
                for (GetListNameDB getListNameDB : names) {
                    arrayList.add(new ItemForDBForAdmin(getListNameDB.getName_db()));
                    System.out.println(arrayList);
                }

                adapter = new UsersAdapterInDB(arrayList, getApplicationContext());
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<GetListNameDB>> call, Throwable t) {

            }
        });

    }

    private void init(){
        rv = findViewById(R.id.rv);
        search = findViewById(R.id.search);
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



}