package com.example.it_hacaton.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it_hacaton.Items.Item;
import com.example.it_hacaton.R;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private ArrayList<Item> arrayList = new ArrayList<>();
    private Context context;

    public Adapter(Context context, ArrayList<Item> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item parseItem = arrayList.get(position);
        if (arrayList.get(position).getName() == null)
        {
            holder.name.setText("Объявление!");
            holder.name.setTextColor(Color.parseColor("#e34234"));
        }else {
            holder.name.setText(parseItem.getName());
        }
        holder.description.setText(parseItem.getDescription());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name, description;
        public ViewHolder(@NonNull View v) {
            super(v);
            name = v.findViewById(R.id.name);
            description = v.findViewById(R.id.description);
        }
    }
}
