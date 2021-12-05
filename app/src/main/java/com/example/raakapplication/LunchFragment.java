package com.example.raakapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class LunchFragment extends Fragment {

    ArrayList<MenuItem> lunch;
    RecyclerView recyclerViewLunch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_lunch, container, false);

        recyclerViewLunch = root.findViewById(R.id.recyclerviewLunch);
        lunch = ((HomeActivity) getActivity()).lunch;


        recyclerViewLunch.setAdapter(null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewLunch.setLayoutManager(linearLayoutManager);
        lunchAdaptor lunchAdaptor = new lunchAdaptor(lunch,getContext());
        recyclerViewLunch.setAdapter(lunchAdaptor);

        return root;
    }
}