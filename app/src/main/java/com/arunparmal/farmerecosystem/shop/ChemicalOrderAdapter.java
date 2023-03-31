package com.arunparmal.farmerecosystem.shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arunparmal.farmerecosystem.R;
import com.arunparmal.farmerecosystem.adapters.SeedOrderAdapter;
import com.arunparmal.farmerecosystem.model.SeedOrderModel;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ChemicalOrderAdapter extends RecyclerView.Adapter<ChemicalOrderAdapter.ChemicalOrderViewholder>{


    Context context;
    ArrayList<SeedOrderModel> orderlist;
    FirebaseFirestore firestore=FirebaseFirestore.getInstance();
    FirebaseAuth mauth=FirebaseAuth.getInstance();

    public ChemicalOrderAdapter(Context context, ArrayList<SeedOrderModel> orderlist) {
        this.context = context;
        this.orderlist = orderlist;
    }

    @NonNull
    @Override
    public ChemicalOrderViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.seed_order_item_layout,parent,false);
        return new ChemicalOrderViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChemicalOrderViewholder holder, int position) {
        SeedOrderModel pos=orderlist.get(position);

        holder.prodid.setText(pos.getOrder_Product_id());
        holder.price.setText(pos.getOrder_Product_amount());
        holder.qty.setText(pos.getOrder_Product_count());
        String address=pos.getOrder_Product_address()+", "+pos.getOrder_Product_pincode();
        holder.address.setText(address);
        holder.orderid.setText(pos.getOrder_id());
        holder.paymentmode.setText(pos.getOrder_Payment_Mode());
        holder.status.setText(pos.getOrder_Status());

//        holder.deliveredbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Map<String, Object> status = new HashMap<>();
//                SeedOrderModel model=new SeedOrderModel();
//                status.put("Order_Status", "Delivered");
//                firestore.collection("Vendors").document(mauth.getCurrentUser().getUid())
//                        .collection("Orders").document(pos.getDocumentId()).set(status, SetOptions.merge());
//                holder.status.setText("Delivered");
//
////                firestore.collection("Farmers").document(pos.getOrder_Product_UserID())
////                        .collection("Orders").whereEqualTo("Order_id",pos.getOrder_id()).get()
////                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
////                            @Override
////                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
////                            }
////                        });
//
//            }
//        });

        firestore.collection("Chemicals").whereEqualTo("ID",pos.getOrder_Product_id())
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(!queryDocumentSnapshots.isEmpty()){
                            List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot d:list){
                                holder.seedname.setText(d.getString("Name"));
                                String url=d.getString("ImageUrl");
                                if(url!=null){
                                    Glide.with(context).load(url).into(holder.img);
                                }
                                else{
                                    Glide.with(context).load(R.drawable.ic_launcher_background).into(holder.img);
                                }
                            }
                        }
                    }
                });

        firestore.collection("Chemicals").document(pos.getOrder_Product_UserID()).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        holder.username.setText(documentSnapshot.getString("Name"));
                        holder.mobile.setText(documentSnapshot.getString("Phone"));
                    }
                });
    }

    @Override
    public int getItemCount() {
        return orderlist.size();
    }

    public class ChemicalOrderViewholder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView seedname,prodid,price,qty,address,orderid,paymentmode,username,mobile,status;
        Button deliveredbtn;
        public ChemicalOrderViewholder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.sorderimg);
            seedname=itemView.findViewById(R.id.sorderseedname);
            prodid=itemView.findViewById(R.id.sorderprodid);
            price=itemView.findViewById(R.id.sorderprice);
            qty=itemView.findViewById(R.id.sorderqty);
            address=itemView.findViewById(R.id.sorderuseraddress);
            orderid=itemView.findViewById(R.id.sorderid);
            paymentmode=itemView.findViewById(R.id.sorderpaymentmode);
            username=itemView.findViewById(R.id.sorderusername);
            mobile=itemView.findViewById(R.id.sorderusermobile);
            orderid=itemView.findViewById(R.id.sorderid);
            status=itemView.findViewById(R.id.sorderstatus);
//            deliveredbtn=itemView.findViewById(R.id.sorderdeliveredbtn);


        }
    }
}
