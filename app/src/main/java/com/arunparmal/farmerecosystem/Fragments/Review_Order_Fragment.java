package com.arunparmal.farmerecosystem.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arunparmal.farmerecosystem.R;

public class Review_Order_Fragment extends Fragment {


    public Review_Order_Fragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_review__order_, container, false);
        // Inflate the layout for this fragment
        return view;
    }
}