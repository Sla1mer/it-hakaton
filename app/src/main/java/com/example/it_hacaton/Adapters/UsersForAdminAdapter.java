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

public class UsersForAdminAdapter extends RecyclerView.Adapter<UsersForAdminAdapter.ViewHolder>{

    private ArrayList<ItemUsersForAdmin> parseItems;
    private Context context;
    private ApiInterface apiInterface;

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
    }

    @Override
    public int getItemCount() {
        return parseItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, middleName, cancel;
        public ViewHolder(@NonNull View v) {
            super(v);
            v.setOnClickListener(this);
            name = v.findViewById(R.id.name);
            middleName = v.findViewById(R.id.midleName);
            cancel = v.findViewById(R.id.cancel);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            apiInterface = ApiClient.getAppClient().create(ApiInterface.class);
            Call<DeletePerson> call = apiInterface.delete_person_from_db(parseItems.get(position).getName(),
                    parseItems.get(position).getMiddleName(), parseItems.get(position).getLastName());

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
