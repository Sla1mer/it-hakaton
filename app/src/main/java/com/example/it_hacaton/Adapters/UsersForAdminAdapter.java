package com.example.it_hacaton.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_hacaton.API.ApiClient;
import com.example.it_hacaton.API.ApiInterface;
import com.example.it_hacaton.Admin.MainForAdminActivity;
import com.example.it_hacaton.Items.ItemForDBForAdmin;
import com.example.it_hacaton.Items.ItemUsersForAdmin;
import com.example.it_hacaton.R;
import com.example.it_hacaton.model.DeletePerson;
import com.example.it_hacaton.model.Event;
import com.example.it_hacaton.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersForAdminAdapter extends RecyclerView.Adapter<UsersForAdminAdapter.ViewHolder>{

    private ArrayList<ItemUsersForAdmin> parseItems;
    private Context context;
    private ApiInterface apiInterface;
    private ApiInterface apiInterface2;
    private String database_name;

    public UsersForAdminAdapter(ArrayList<ItemUsersForAdmin> parseItems, Context context, String database_name) {
        this.parseItems = parseItems;
        this.context = context;
        this.database_name = database_name;
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
        holder.last_name.setText(parseItem.getLastName());
    }

    @Override
    public int getItemCount() {
        return parseItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, middleName, cancel, last_name;
        public ViewHolder(@NonNull View v) {
            super(v);
            v.setOnClickListener(this);
            last_name = v.findViewById(R.id.midleName2);
            name = v.findViewById(R.id.name);
            middleName = v.findViewById(R.id.midleName);
            cancel = v.findViewById(R.id.cancel);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            apiInterface2 = ApiClient.getAppClient().create(ApiInterface.class);
            Call<Event> call2 = apiInterface2.add_event(parseItems.get(position).getLastName() +
                    " " + parseItems.get(position).getName() + " " + parseItems.get(position).getMiddleName() +
                    " был удалён из " + database_name, parseItems.get(position).getLastName() +
                    " " + parseItems.get(position).getName() + " " + parseItems.get(position).getMiddleName());

            call2.enqueue(new Callback<Event>() {
                @Override
                public void onResponse(Call<Event> call, Response<Event> response) {
                    Toast.makeText(context, "Success", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<Event> call, Throwable t) {

                }
            });

            apiInterface = ApiClient.getAppClient().create(ApiInterface.class);
            Call<DeletePerson> call = apiInterface.delete_person_from_db(parseItems.get(position).getName(),
                    parseItems.get(position).getMiddleName(), parseItems.get(position).getLastName(), database_name);
            parseItems.remove(position);

            call.enqueue(new Callback<DeletePerson>() {
                @Override
                public void onResponse(Call<DeletePerson> call, Response<DeletePerson> response) {
                    Toast.makeText(context, "Success", Toast.LENGTH_LONG).show();
                    notifyDataSetChanged();
                }

                @Override//
                public void onFailure(Call<DeletePerson> call, Throwable t) {

                }
            });
        }
    }

    public void filterList(ArrayList<ItemUsersForAdmin> filterList){
        parseItems = filterList;
        notifyDataSetChanged();
    }
}
