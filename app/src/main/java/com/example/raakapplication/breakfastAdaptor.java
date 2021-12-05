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

public class breakfastAdaptor extends RecyclerView.Adapter<breakfastAdaptor.ViewHolder>{

    ArrayList<MenuItem> breakfast;
    public Context context;

    public breakfastAdaptor(ArrayList<MenuItem> breakfast,Context context) {
        this.breakfast = breakfast;
        this.context = context;
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
        myViewHolder.Item.setText(breakfast.get(position).Name);
        myViewHolder.Description.setText(breakfast.get(position).Description);
        myViewHolder.Price.setText("R"+breakfast.get(position).Price);
        Glide.with(context).load(breakfast.get(position).ImagePath).apply(new RequestOptions().override(300,300)).into(myViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return breakfast.size();
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
