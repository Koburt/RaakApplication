package com.example.raakapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class dinnerAdaptor extends RecyclerView.Adapter<dinnerAdaptor.ViewHolder> {

    ArrayList<MenuItem> dinner;

    public dinnerAdaptor(ArrayList<MenuItem> dinner) {
        this.dinner = dinner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        dinnerAdaptor.ViewHolder viewHolder = new dinnerAdaptor.ViewHolder(root);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull dinnerAdaptor.ViewHolder holder, int position) {
        dinnerAdaptor.ViewHolder myViewHolder = holder;
        myViewHolder.Item.setText(dinner.get(position).Description);
        myViewHolder.Price.setText("R"+dinner.get(position).Price);
    }

    @Override
    public int getItemCount() {
        return dinner.size();
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
