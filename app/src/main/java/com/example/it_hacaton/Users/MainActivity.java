package com.example.it_hacaton.Users;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.it_hacaton.API.ApiClient;
import com.example.it_hacaton.API.ApiInterface;
import com.example.it_hacaton.Adapters.Adapter;
import com.example.it_hacaton.Items.Item;
import com.example.it_hacaton.Items.ItemForDBForAdmin;
import com.example.it_hacaton.R;
import com.example.it_hacaton.UserAdapter.AdapterUser;
import com.example.it_hacaton.model.Event;
import com.example.it_hacaton.model.GetListNameDB;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;
    private Button btnToDB;

    private AdapterUser adapter;
    private ArrayList<Item> arrayList = new ArrayList<>();
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getSupportActionBar().hide();
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        apiInterface = ApiClient.getAppClient().create(ApiInterface.class);
        Call<List<Event>> call = apiInterface.get_events();

        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                List<Event> object = response.body();
                for (Event event : object) {
                    arrayList.add(new Item(event.getTo_subject(), event.getDescription()));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {

            }
        });

        adapter = new AdapterUser(this, arrayList);
        rv.setAdapter(adapter);


        btnToDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ListOfDBActivity.class));
            }
        });

    }

    private void init(){
        rv = findViewById(R.id.rv);
        btnToDB = findViewById(R.id.recyclerOfBD);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Выход")
                .setMessage("Вы действительно хотите выйти из приложения?")
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("Нет", null).show();
    }

}