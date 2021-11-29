package com.example.raakapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class eventsAdaptor extends RecyclerView.Adapter<eventsAdaptor.ViewHolder>{

    public ArrayList<Event> events;
    private static OnItemClickListener onItemClickListener;

    public eventsAdaptor(ArrayList<Event> events) {
        this.events = events;
    }

    @NonNull
    @Override
    public eventsAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        eventsAdaptor.ViewHolder viewHolder = new eventsAdaptor.ViewHolder(root);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull eventsAdaptor.ViewHolder holder, int position) {
        eventsAdaptor.ViewHolder myViewHolder = holder;
        String date = events.get(position).date;
        myViewHolder.Item.setText(events.get(position).date);
        myViewHolder.Price.setText(events.get(position).description);
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
        TextView Item, Price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Item = itemView.findViewById(R.id.textViewTitle);
            Price = itemView.findViewById(R.id.textViewAddress);
        }
    }
}
