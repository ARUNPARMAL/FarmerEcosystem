package com.arunparmal.farmerecosystem.rent;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arunparmal.farmerecosystem.R;
import com.arunparmal.farmerecosystem.adapters.ChemicalAdapter;
import com.arunparmal.farmerecosystem.adapters.RentAdapter;
import com.arunparmal.farmerecosystem.model.ChemicalModel;
import com.arunparmal.farmerecosystem.model.RentModel;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class RentFragment extends Fragment {

    FirebaseFirestore firestore;
    RentAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<RentModel> list=new ArrayList<RentModel>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firestore=FirebaseFirestore.getInstance();

        firestore.collection("Machines").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error!=null){
                    Log.d("Firebase Error",error.getMessage());
                }
                for (DocumentChange documentChange:value.getDocumentChanges()) {
                    if (documentChange.getType() == DocumentChange.Type.ADDED) {
                        System.out.println("function is running");
                        list.add(documentChange.getDocument().toObject(RentModel.class));

//                    Database database=documentChange.getDocument().toObject(Database.class);
                    }
                }
                adapter.notifyDataSetChanged();

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_rent, container, false);

        adapter=new RentAdapter(getActivity(),list);
        recyclerView=view.findViewById(R.id.recyclerrent);
        recyclerView.setAdapter(adapter);
        return view;
    }
}