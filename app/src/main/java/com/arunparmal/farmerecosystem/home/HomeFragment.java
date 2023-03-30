package com.arunparmal.farmerecosystem.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.arunparmal.farmerecosystem.R;
import com.arunparmal.farmerecosystem.activities.MainActivity;
import com.arunparmal.farmerecosystem.activities.SeedDetailActivity;
import com.arunparmal.farmerecosystem.adapters.Seedsadapter;
import com.arunparmal.farmerecosystem.model.SeedModel;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    FirebaseFirestore firestore;
    Seedsadapter seedsadapter;
    RecyclerView recyclerView;
    ArrayList<SeedModel> seedlist=new ArrayList<SeedModel>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firestore=FirebaseFirestore.getInstance();

        firestore.collection("Seeds").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error!=null){
                    Log.d("Firebase Error",error.getMessage());
                }
                for (DocumentChange documentChange:value.getDocumentChanges()) {
                    if (documentChange.getType() == DocumentChange.Type.ADDED) {
                        System.out.println("function is running");
                        seedlist.add(documentChange.getDocument().toObject(SeedModel.class));

//                    Database database=documentChange.getDocument().toObject(Database.class);
                    }
                }
                seedsadapter.notifyDataSetChanged();

            }
        });

        seedsadapter=new Seedsadapter(seedlist,getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment
        recyclerView=(RecyclerView) view.findViewById(R.id.recyclerviewhome);

                recyclerView.setAdapter(seedsadapter);
        return view;
    }


}