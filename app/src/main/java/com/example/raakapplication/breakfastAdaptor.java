package com.example.raakapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class breakfastAdaptor extends RecyclerView.Adapter<breakfastAdaptor.ViewHolder>{

    ArrayList<MenuItem> breakfast;

    public breakfastAdaptor(ArrayList<MenuItem> breakfast) {
        this.breakfast = breakfast;
    }

    @NonNull
    @Override
    public breakfastAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        breakfastAdaptor.ViewHolder viewHolder = new breakfastAdaptor.ViewHolder(root);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull breakfastAdaptor.ViewHolder holder, int position) {
        breakfastAdaptor.ViewHolder myViewHolder = holder;
        myViewHolder.Item.setText(breakfast.get(position).Description);
        myViewHolder.Price.setText("R"+breakfast.get(position).Price);
    }

    @Override
    public int getItemCount() {
        return breakfast.size();
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
