package com.example.it_hacaton.Users;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;

import com.example.it_hacaton.API.ApiClient;
import com.example.it_hacaton.API.ApiInterface;
import com.example.it_hacaton.Adapters.AdapterForDBList_ForAdmin;
import com.example.it_hacaton.Items.ItemForDBForAdmin;
import com.example.it_hacaton.R;
import com.example.it_hacaton.UserAdapter.AdapterUser;
import com.example.it_hacaton.UserAdapter.UsersAdapterInDB;
import com.example.it_hacaton.model.GetListNameDB;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//
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
    }

}