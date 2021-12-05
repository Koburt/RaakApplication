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

public class dinnerAdaptor extends RecyclerView.Adapter<dinnerAdaptor.ViewHolder> {

    ArrayList<MenuItem> dinner;
    public Context context;

    public dinnerAdaptor(ArrayList<MenuItem> dinner,Context context) {
        this.dinner = dinner;
        this.context = context;
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
        myViewHolder.Item.setText(dinner.get(position).Name);
        myViewHolder.Description.setText(dinner.get(position).Description);
        myViewHolder.Price.setText("R"+dinner.get(position).Price);
        Glide.with(context).load(dinner.get(position).ImagePath).apply(new RequestOptions().override(300,300)).into(myViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return dinner.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Item, Price, Description;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image2);
            Description = itemView.findViewById(R.id.textViewDescription);
            Item = itemView.findViewById(R.id.textViewTitle);
            Price = itemView.findViewById(R.id.textViewAddress);;

        }
    }
}
