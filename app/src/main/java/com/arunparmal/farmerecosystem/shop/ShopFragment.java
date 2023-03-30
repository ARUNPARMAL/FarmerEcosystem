package com.arunparmal.farmerecosystem.shop;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.arunparmal.farmerecosystem.R;
import com.arunparmal.farmerecosystem.adapters.ChemicalAdapter;
import com.arunparmal.farmerecosystem.adapters.Seedsadapter;
import com.arunparmal.farmerecosystem.model.ChemicalModel;
import com.arunparmal.farmerecosystem.model.SeedModel;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ShopFragment extends Fragment {

    FirebaseFirestore firestore;
    ChemicalAdapter chemicaladapter;
    RecyclerView recyclerView;
    ArrayList<ChemicalModel> chemicalList=new ArrayList<ChemicalModel>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firestore=FirebaseFirestore.getInstance();

        firestore.collection("Chemicals").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error!=null){
                    Log.d("Firebase Error",error.getMessage());
                }
                for (DocumentChange documentChange:value.getDocumentChanges()) {
                    if (documentChange.getType() == DocumentChange.Type.ADDED) {
                        System.out.println("function is running");
                        chemicalList.add(documentChange.getDocument().toObject(ChemicalModel.class));

//                    Database database=documentChange.getDocument().toObject(Database.class);
                    }
                }
                chemicaladapter.notifyDataSetChanged();

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_shop, container, false);

        chemicaladapter=new ChemicalAdapter(getActivity(),chemicalList);
        recyclerView=view.findViewById(R.id.recyclerviewshop);
        recyclerView.setAdapter(chemicaladapter);
        return view;
    }
}