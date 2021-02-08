package com.example.it_hacaton.UserAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_hacaton.Items.ItemForDBForAdmin;
import com.example.it_hacaton.R;
import com.example.it_hacaton.Users.UsersInDBActivity;

import java.util.ArrayList;

public class UsersAdapterInUsers  extends RecyclerView.Adapter<UsersAdapterInUsers.ViewHolder> {

    private ArrayList<ItemForDBForAdmin> arrayList;
    private Context context;

    public UsersAdapterInUsers(ArrayList<ItemForDBForAdmin> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public UsersAdapterInUsers.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_for_db_for_admin, parent, false);
        return new UsersAdapterInUsers.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapterInUsers.ViewHolder holder, int position) {
        ItemForDBForAdmin parseItem = arrayList.get(position);
        holder.DB.setText(parseItem.getDB());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView DB;
        public ViewHolder(@NonNull View v) {//
            super(v);
            DB = v.findViewById(R.id.DB);
        }

    }

    public void filterList(ArrayList<ItemForDBForAdmin> filterList){
        arrayList = filterList;
        notifyDataSetChanged();
    }

}