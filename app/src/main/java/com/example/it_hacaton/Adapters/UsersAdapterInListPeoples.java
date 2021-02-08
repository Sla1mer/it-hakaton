package com.example.it_hacaton.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_hacaton.API.ApiClient;
import com.example.it_hacaton.API.ApiInterface;
import com.example.it_hacaton.Items.ItemForDBForAdmin;
import com.example.it_hacaton.Items.ItemUsersForAdmin;
import com.example.it_hacaton.R;
import com.example.it_hacaton.model.DeletePerson;
import com.example.it_hacaton.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersAdapterInListPeoples extends RecyclerView.Adapter<UsersAdapterInListPeoples.ViewHolder>{

    private ArrayList<ItemUsersForAdmin> parseItems;
    private Context context;
    private ApiInterface apiInterface;
    private String database_name;

    public UsersAdapterInListPeoples(ArrayList<ItemUsersForAdmin> parseItems, Context context, String database_name) {
        this.parseItems = parseItems;
        this.context = context;
        this.database_name = database_name;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_for_users_in, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemUsersForAdmin parseItem = parseItems.get(position);
        holder.name.setText(parseItem.getName());
        holder.middleName.setText(parseItem.getMiddleName());
        holder.last_name.setText(parseItem.getLastName());
    }

    @Override
    public int getItemCount() {
        return parseItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, middleName, last_name;

        public ViewHolder(@NonNull View v) {
            super(v);
            last_name = v.findViewById(R.id.midleName2);
            name = v.findViewById(R.id.name);
            middleName = v.findViewById(R.id.midleName);
        }

    }

    public void filterList(ArrayList<ItemUsersForAdmin> filterList) {
        parseItems = filterList;
        notifyDataSetChanged();
    }
}
