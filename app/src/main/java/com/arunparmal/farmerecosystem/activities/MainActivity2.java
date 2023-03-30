package com.arunparmal.farmerecosystem.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.arunparmal.farmerecosystem.R;
import com.arunparmal.farmerecosystem.adapters.Seedsadapter;
import com.arunparmal.farmerecosystem.utility.Constants;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity2 extends AppCompatActivity {

    FirebaseFirestore firestore;
    Seedsadapter seedsadapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);




    }

}