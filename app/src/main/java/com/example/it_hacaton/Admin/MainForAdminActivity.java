package com.example.it_hacaton.Admin;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.it_hacaton.API.ApiClient;
import com.example.it_hacaton.API.ApiInterface;
import com.example.it_hacaton.Adapters.Adapter;
import com.example.it_hacaton.Items.Item;
import com.example.it_hacaton.R;
import com.example.it_hacaton.Services.UserAdminService;
import com.example.it_hacaton.model.Event;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainForAdminActivity extends AppCompatActivity {
    private Button regText;
    private RecyclerView rv;
    private Adapter adapter;
    private ArrayList<Item> arrayList = new ArrayList<>();
    private Button rvOfBD;
    private ImageView addImage;
    private ApiInterface apiInterface;
    private String fullname = null;
    public static final String CHANNEL_ID = "hakaton";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_for_admin);
        init();
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        getSupportActionBar().hide();

        Intent intent = getIntent();
        fullname = intent.getStringExtra("fullname");
        System.out.println(fullname);

        apiInterface = ApiClient.getAppClient().create(ApiInterface.class);
        Call<List<Event>> call = apiInterface.get_events();


        regText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegistrationActivity.class).putExtra("fullname", fullname));
                System.out.println(fullname + " dasdasdfasfas");
            }
        });

        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                List<Event> object = response.body();
                for (Event event : object) {
                    arrayList.add(new Item(event.getTo_subject(), event.getDescription()));
                    adapter.notifyDataSetChanged();
                    rv.smoothScrollToPosition(adapter.getItemCount());
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {

            }
        });

        adapter = new Adapter(this, arrayList);
        rv.setAdapter(adapter);

        onCLicks();
        startService();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startService() {
        Intent serviceIntent = new Intent(getApplicationContext(), UserAdminService.class);
        startForegroundService(serviceIntent);
    }

    private void onCLicks(){
        View.OnClickListener BTNs = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.addImage){
                    startActivity(new Intent(getApplicationContext(), CreateNewsForAdminActivity.class));
                }else if(v.getId() == R.id.recyclerOfBD){
                    startActivity(new Intent(getApplicationContext(), ListOfDBForAdminActivity.class).putExtra("fullname", fullname));
                }
            }
        };
        addImage.setOnClickListener(BTNs);
        rvOfBD.setOnClickListener(BTNs);
    }

    private void init(){
        rv = findViewById(R.id.rv);
        rvOfBD = findViewById(R.id.recyclerOfBD);
        addImage = findViewById(R.id.addImage);
        regText = findViewById(R.id.addNewUser);
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