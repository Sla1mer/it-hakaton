package com.example.it_hacaton.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.it_hacaton.Adapters.Adapter;
import com.example.it_hacaton.Items.Item;
import com.example.it_hacaton.R;

import java.util.ArrayList;

public class MainForAdminActivity extends AppCompatActivity {
    private RecyclerView rv;
    private Adapter adapter;
    private ArrayList<Item> arrayList = new ArrayList<>();
    private Button rvOfBD;
    private ImageView addImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_for_admin);
        init();
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        arrayList.add(new Item("Тема", "Максимович", "Дай доступ в БД1"));

        adapter = new Adapter(this, arrayList);
        rv.setAdapter(adapter);

        onCLicks();
    }

    private void onCLicks(){
        View.OnClickListener BTNs = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.addImage){
                    startActivity(new Intent(getApplicationContext(), CreateNewsForAdminActivity.class));
                }else if(v.getId() == R.id.recyclerOfBD){
                    startActivity(new Intent(getApplicationContext(), ListOfDBForAdminActivity.class));
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