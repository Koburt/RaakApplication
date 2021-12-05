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

public class eventsAdaptor extends RecyclerView.Adapter<eventsAdaptor.ViewHolder>{

    public ArrayList<Event> events;
    public Context context;
    private static OnItemClickListener onItemClickListener;

    public eventsAdaptor(ArrayList<Event> events, Context context) {
        this.events = events;
        this.context = context;
    }

    @NonNull
    @Override
    public eventsAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);
        eventsAdaptor.ViewHolder viewHolder = new eventsAdaptor.ViewHolder(root);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull eventsAdaptor.ViewHolder holder, int position) {
        eventsAdaptor.ViewHolder myViewHolder = holder;
        String date = events.get(position).date;
        myViewHolder.Title.setText(events.get(position).eventName);
        myViewHolder.Date.setText(events.get(position).date);
        myViewHolder.Description.setText(events.get(position).description);
        Glide.with(context).load(events.get(position).imagePath).apply(new RequestOptions().override(300,300)).into(myViewHolder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClicked(v,date);
            }
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public interface OnItemClickListener {
        void onItemClicked(View view, String date);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Title, Date, Description;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            Title = itemView.findViewById(R.id.textViewTitle);
            Date = itemView.findViewById(R.id.textViewAddress);
            Description = itemView.findViewById(R.id.textViewDescription);
        }
    }
}
