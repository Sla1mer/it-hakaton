package com.example.it_hacaton.Adapters;

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
import com.example.it_hacaton.Admin.UsersInDBForAdminActivity;

import java.util.ArrayList;

public class AdapterForDBList_ForAdmin extends RecyclerView.Adapter<AdapterForDBList_ForAdmin.ViewHolder> {

    private ArrayList<ItemForDBForAdmin> arrayList;
    private Context context;
    private String fullname;

    public AdapterForDBList_ForAdmin(ArrayList<ItemForDBForAdmin> arrayList, Context context, String fullname) {
        this.arrayList = arrayList;
        this.context = context;
        this.fullname = fullname;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_for_db_for_admin, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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
            context.startActivity(new Intent(context, UsersInDBForAdminActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    .putExtra("name_db", arrayList.get(position).getDB())
                    .putExtra("fullname", fullname));
        }
    }

    public void filterList(ArrayList<ItemForDBForAdmin> filterList){
        arrayList = filterList;
        notifyDataSetChanged();
    }

}
