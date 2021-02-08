package com.example.it_hacaton.UserAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_hacaton.Adapters.AdapterForDBList_ForAdmin;
import com.example.it_hacaton.Admin.UsersInDBForAdminActivity;
import com.example.it_hacaton.Items.ItemForDBForAdmin;
import com.example.it_hacaton.R;
import com.example.it_hacaton.Users.UsersInDBActivity;

import java.util.ArrayList;

public class UsersAdapterInDB extends RecyclerView.Adapter<UsersAdapterInDB.ViewHolder> {

    private ArrayList<ItemForDBForAdmin> arrayList;
    private Context context;

    public UsersAdapterInDB(ArrayList<ItemForDBForAdmin> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public UsersAdapterInDB.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_for_db_for_admin, parent, false);
        return new UsersAdapterInDB.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapterInDB.ViewHolder holder, int position) {
        ItemForDBForAdmin parseItem = arrayList.get(position);
        holder.DB.setText(parseItem.getDB());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView DB;
        public ViewHolder(@NonNull View v) {//
            super(v);
            DB = v.findViewById(R.id.DB);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            //ItemForDBForAdmin parseItem = arrayList.get(position); //- не удалять
            context.startActivity(new Intent(context, UsersInDBActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }
}