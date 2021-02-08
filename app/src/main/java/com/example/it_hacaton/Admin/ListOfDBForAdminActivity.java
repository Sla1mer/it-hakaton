package com.example.it_hacaton.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.it_hacaton.API.ApiClient;
import com.example.it_hacaton.API.ApiInterface;
import com.example.it_hacaton.Adapters.AdapterForDBList_ForAdmin;
import com.example.it_hacaton.Items.ItemForDBForAdmin;
import com.example.it_hacaton.R;
import com.example.it_hacaton.model.GetListNameDB;
import com.example.it_hacaton.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListOfDBForAdminActivity extends AppCompatActivity {
    private RecyclerView rv;
    private ImageView addImage;
    private AdapterForDBList_ForAdmin adapter;
    private ArrayList<ItemForDBForAdmin> arrayList = new ArrayList<>();
    private ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_d_b_for_admin);

        init();

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddDBForAdminActivity.class));
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
                    System.out.println(arrayList);
                }
                adapter = new AdapterForDBList_ForAdmin(arrayList, getApplicationContext());
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<GetListNameDB>> call, Throwable t) {

            }
        });

    }

    private void init(){
        rv = findViewById(R.id.rv);
        addImage = findViewById(R.id.addImage);
    }

}