package com.example.it_hacaton.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_hacaton.Items.ItemUsersForAdmin;
import com.example.it_hacaton.R;

import java.util.ArrayList;

public class UsersForAdminAdapter extends RecyclerView.Adapter<UsersForAdminAdapter.ViewHolder>{

    private ArrayList<ItemUsersForAdmin> parseItems;
    private Context context;

    public UsersForAdminAdapter(ArrayList<ItemUsersForAdmin> parseItems, Context context) {
        this.parseItems = parseItems;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_users_for_admin, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemUsersForAdmin parseItem = parseItems.get(position);
        holder.name.setText(parseItem.getName());
        holder.middleName.setText(parseItem.getMiddleName());
        holder.surname.setText(parseItem.getLastName());
    }

    @Override
    public int getItemCount() {
        return parseItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, middleName, cancel, surname;
        public ViewHolder(@NonNull View v) {
            super(v);
            name = v.findViewById(R.id.name);
            middleName = v.findViewById(R.id.midleName);
            surname = v.findViewById(R.id.midleName2);
            cancel = v.findViewById(R.id.cancel);
        }
    }

    public void filterList(ArrayList<ItemUsersForAdmin> filterList){
        parseItems = filterList;
        notifyDataSetChanged();
    }

}
