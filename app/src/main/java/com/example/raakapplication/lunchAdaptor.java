package com.example.raakapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class lunchAdaptor extends RecyclerView.Adapter<lunchAdaptor.ViewHolder> {

    ArrayList<MenuItem> lunch;

    public lunchAdaptor(ArrayList<MenuItem> lunch) {
        this.lunch = lunch;
    }

    @NonNull
    @Override
    public lunchAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolder viewHolder = new ViewHolder(root);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull lunchAdaptor.ViewHolder holder, int position) {
        ViewHolder myViewHolder = holder;
        myViewHolder.Item.setText(lunch.get(position).Description);
        myViewHolder.Price.setText("R"+lunch.get(position).Price);
    }

    @Override
    public int getItemCount() {
        return lunch.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Item, Price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Item = itemView.findViewById(R.id.textViewTitle);
            Price = itemView.findViewById(R.id.textViewAddress);

        }
    }
}
