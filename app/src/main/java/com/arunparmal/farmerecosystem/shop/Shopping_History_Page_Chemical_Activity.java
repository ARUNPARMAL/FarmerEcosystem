package com.arunparmal.farmerecosystem.shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.arunparmal.farmerecosystem.R;
import com.arunparmal.farmerecosystem.adapters.SeedOrderAdapter;
import com.arunparmal.farmerecosystem.home.SeedorderHistory;
import com.arunparmal.farmerecosystem.model.SeedOrderModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Shopping_History_Page_Chemical_Activity extends AppCompatActivity {


    FirebaseFirestore firestore=FirebaseFirestore.getInstance();
    RecyclerView recyclerView,recyclerView2;
    ArrayList<SeedOrderModel> orderlist=new ArrayList<>();
    ArrayList<SeedOrderModel> orderlistpast=new ArrayList<>();
    ChemicalOrderAdapter adapter,adapter1;
    FirebaseAuth mauth;
    String farmerid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_history_page);

        recyclerView=findViewById(R.id.recyclervieworderseeds);
        recyclerView2=findViewById(R.id.recyclervieworderseedspast);
        mauth=FirebaseAuth.getInstance();
        farmerid=mauth.getCurrentUser().getUid();

        adapter=new ChemicalOrderAdapter(this,orderlist);
        adapter1=new ChemicalOrderAdapter(this,orderlistpast);

        recyclerView.setAdapter(adapter);
        recyclerView2.setAdapter(adapter1);


        firestore.collection("Farmers").document(farmerid).collection("C_Orders").whereEqualTo("Order_Status","Delivered").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(!queryDocumentSnapshots.isEmpty()){

                            List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot d:list){
                                SeedOrderModel c=d.toObject(SeedOrderModel.class);
                                String documentId=d.getId();
                                c.setDocumentId(documentId);
                                orderlistpast.add(c);

                            }
                            adapter1.notifyDataSetChanged();
                        }
                        else{
                            Toast.makeText(Shopping_History_Page_Chemical_Activity.this, "No data found", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        getOrders();
    }
    private void getOrders() {
        firestore.collection("Farmers").document(farmerid).collection("C_Orders").whereEqualTo("Order_Status","Order Placed").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(!queryDocumentSnapshots.isEmpty()){

                            List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot d:list){
                                SeedOrderModel c=d.toObject(SeedOrderModel.class);
                                String documentId=d.getId();
                                c.setDocumentId(documentId);
                                orderlist.add(c);


                            }
                            adapter.notifyDataSetChanged();
                        }
                        else{
                            Toast.makeText(Shopping_History_Page_Chemical_Activity.this, "No data found", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}