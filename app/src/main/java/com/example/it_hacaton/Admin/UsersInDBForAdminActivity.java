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
import com.example.it_hacaton.Adapters.UsersForAdminAdapter;
import com.example.it_hacaton.Items.ItemForDBForAdmin;
import com.example.it_hacaton.Items.ItemUsersForAdmin;
import com.example.it_hacaton.R;
import com.example.it_hacaton.model.GetListNameDB;
import com.example.it_hacaton.model.GetPersonFromDBPersonal;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersInDBForAdminActivity extends AppCompatActivity {
    private RecyclerView rv;
    private EditText search;
    private ApiInterface apiInterface;
    private ApiInterface apiInterface2;
 private ImageView addImage;
    private UsersForAdminAdapter adapter;
    private ArrayList<ItemUsersForAdmin> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_in_d_b_for_admin);
        Intent intent = getIntent();

        String name_db = intent.getStringExtra("name_db");

        search = findViewById(R.id.search);
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
        Call<List<GetPersonFromDBPersonal>> call = apiInterface.get_list_db_personal(name_db);

        call.enqueue(new Callback<List<GetPersonFromDBPersonal>>() {
            @Override
            public void onResponse(Call<List<GetPersonFromDBPersonal>> call, Response<List<GetPersonFromDBPersonal>> response) {
                List<GetPersonFromDBPersonal> names = response.body();
                for (GetPersonFromDBPersonal getPerson : names) {
                    arrayList.add(new ItemUsersForAdmin(getPerson.getName(), getPerson.getMiddle_name(), getPerson.getLast_name()));
                }
                adapter = new UsersForAdminAdapter(arrayList, getApplicationContext());
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<GetPersonFromDBPersonal>> call, Throwable t) {

            }
        });

        rv = findViewById(R.id.rv);
        addImage = findViewById(R.id.addImage);
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override//
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddUserToForAdminActivity.class).putExtra("name_db", name_db));
            }
        });

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));//

    }

    private void filter(String text){
        ArrayList<ItemUsersForAdmin> array = new ArrayList<>();
        for(ItemUsersForAdmin item : arrayList){
            if(item.getName().toLowerCase().contains(text.toLowerCase()) || item.getMiddleName().toLowerCase().contains(text.toLowerCase()) || item.getLastName().toLowerCase().contains(text.toLowerCase())){
                array.add(item);
            }
        }
        adapter.filterList(array);
    }

}