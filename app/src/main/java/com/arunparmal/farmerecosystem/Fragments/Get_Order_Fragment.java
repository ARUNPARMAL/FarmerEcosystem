package com.arunparmal.farmerecosystem.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arunparmal.farmerecosystem.R;


public class Get_Order_Fragment extends Fragment {

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Get_Order_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_get__order_, container, false);


        return view;
    }
}