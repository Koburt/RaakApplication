package com.example.raakapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class DinnerFragment extends Fragment {

    ArrayList<MenuItem> dinner;
    RecyclerView recyclerViewDinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_dinner, container, false);

        recyclerViewDinner = root.findViewById(R.id.recyclerviewDinner);
        dinner = ((HomeActivity) getActivity()).dinner;


        recyclerViewDinner.setAdapter(null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewDinner.setLayoutManager(linearLayoutManager);
        dinnerAdaptor dinnerAdaptor = new dinnerAdaptor(dinner);
        recyclerViewDinner.setAdapter(dinnerAdaptor);

        return root;


    }
}