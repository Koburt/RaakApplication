package com.example.raakapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class lunchAdaptor extends RecyclerView.Adapter<lunchAdaptor.ViewHolder> {

    ArrayList<MenuItem> lunch;
    public Context context;

    public lunchAdaptor(ArrayList<MenuItem> lunch,Context context) {
        this.lunch = lunch;
        this.context = context;
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
        myViewHolder.Item.setText(lunch.get(position).Name);
        myViewHolder.Description.setText(lunch.get(position).Description);
        myViewHolder.Price.setText("R"+lunch.get(position).Price);
        Glide.with(context).load(lunch.get(position).ImagePath).apply(new RequestOptions().override(300,300)).into(myViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return lunch.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Item, Price, Description;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image2);
            Description = itemView.findViewById(R.id.textViewDescription);
            Item = itemView.findViewById(R.id.textViewTitle);
            Price = itemView.findViewById(R.id.textViewAddress);

        }
    }
}
