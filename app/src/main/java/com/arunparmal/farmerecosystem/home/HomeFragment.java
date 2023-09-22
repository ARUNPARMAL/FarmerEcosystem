package com.arunparmal.farmerecosystem.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arunparmal.farmerecosystem.R;
import com.arunparmal.farmerecosystem.activities.MainActivity;
import com.arunparmal.farmerecosystem.activities.OrderpageActivity;
import com.arunparmal.farmerecosystem.activities.SeedDetailActivity;
import com.arunparmal.farmerecosystem.adapters.ChemicalAdapter;
import com.arunparmal.farmerecosystem.adapters.Seedsadapter;
import com.arunparmal.farmerecosystem.model.ChemicalModel;
import com.arunparmal.farmerecosystem.model.SeedModel;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    FirebaseFirestore firestore;
    Seedsadapter seedsadapter;
    RecyclerView recyclerView,recyclerViewChemicals;
    ImageView orders;
    ImageSlider imageSlider;
    ArrayList<SeedModel> seedlist=new ArrayList<SeedModel>();
    final ArrayList<SlideModel> remoteimages = new ArrayList<>();
    ArrayList<String> imageUrl=new ArrayList<>();
    ChemicalAdapter chemicaladapter;
    ArrayList<ChemicalModel> chemicalList=new ArrayList<ChemicalModel>();

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
        chemicaladapter=new ChemicalAdapter(getActivity(),chemicalList);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment
        recyclerView=(RecyclerView) view.findViewById(R.id.recyclerviewhome);
        recyclerViewChemicals=view.findViewById(R.id.recyclerviewshop);
        orders=view.findViewById(R.id.orders);
        imageSlider = view.findViewById(R.id.image_slider);

        orders.setOnClickListener(v -> {
            getView().getContext().startActivity(new Intent(getActivity(), OrderpageActivity.class));
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(seedsadapter);
        getBannerImages();

        recyclerViewChemicals.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerViewChemicals.setAdapter(chemicaladapter);
        return view;
    }

    private void getBannerImages() {
        firestore.collection("BannerImages").document("vvf36xZ3iKSYzAtJiHsr").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot=task.getResult();
                    if (documentSnapshot.exists()){
                        imageUrl = (ArrayList<String>) documentSnapshot.get("Images");
                        showimages(imageUrl);
                    }
                    else{
                        Toast.makeText(getContext(), "No such Document of product", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), "Error "+task.getException(), Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    private void showimages(ArrayList<String> imageUrl) {
        if(imageUrl!=null){
            remoteimages.clear();
            for(int i=0;i<imageUrl.size();i++) {
                String visiblestring = null;
                visiblestring = imageUrl.get(i);
                remoteimages.add(new SlideModel(visiblestring, ScaleTypes.CENTER_INSIDE));

            }
            imageSlider.setImageList(remoteimages,ScaleTypes.FIT);
            imageSlider.startSliding(3000);
        }
        else{
            Toast.makeText(getContext(), "error in uploading img", Toast.LENGTH_SHORT).show();
        }
    }


}