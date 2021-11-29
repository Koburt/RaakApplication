package com.example.raakapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class BreakfastFragment extends Fragment {

    ArrayList<MenuItem> breakfast;
    RecyclerView recyclerViewBreakfast;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_breakfast, container, false);

        recyclerViewBreakfast = root.findViewById(R.id.recyclerviewBreakfast);
        breakfast = ((HomeActivity) getActivity()).breakfast;


        recyclerViewBreakfast.setAdapter(null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewBreakfast.setLayoutManager(linearLayoutManager);
        breakfastAdaptor breakfastAdaptor = new breakfastAdaptor(breakfast);
        recyclerViewBreakfast.setAdapter(breakfastAdaptor);

        return root;

    }
}