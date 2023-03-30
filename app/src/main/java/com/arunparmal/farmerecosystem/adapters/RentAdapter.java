package com.arunparmal.farmerecosystem.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arunparmal.farmerecosystem.R;
import com.arunparmal.farmerecosystem.model.ChemicalModel;
import com.arunparmal.farmerecosystem.model.RentModel;
import com.arunparmal.farmerecosystem.model.SeedOrderModel;
import com.arunparmal.farmerecosystem.rent.RentOrderActivity;
import com.arunparmal.farmerecosystem.shop.Chemical_Details_Activity;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class RentAdapter extends RecyclerView.Adapter<RentAdapter.RentViewholder> {
    Context context;
    ArrayList<RentModel> rentlist;
    FirebaseFirestore firestore=FirebaseFirestore.getInstance();
    Intent intent;

    public RentAdapter(Context context, ArrayList<RentModel> rentlist) {
        this.context = context;
        this.rentlist = rentlist;
    }

    @NonNull
    @Override
    public RentViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.machine_details,parent,false);
        return new RentAdapter.RentViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RentViewholder holder, int position) {
        RentModel pos=rentlist.get(position);
        holder.mname.setText(pos.getName());
        holder.mprice.setText(pos.getRate());
        holder.mcompany.setText(pos.getCompany());
        holder.used.setText(pos.getPurpose());
        holder.details.setText(pos.getDetails());
        String url=pos.getImageUrl();
        if(url!=null){
            Glide.with(context.getApplicationContext()).load(url).into(holder.image);
        }
        else{
            Glide.with(context.getApplicationContext()).load(R.drawable.ic_launcher_background).into(holder.image);
        }
        firestore.collection("MachineLender").document(pos.getLenderID()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                holder.username.setText(documentSnapshot.getString("Name"));
                holder.mobile.setText(documentSnapshot.getString("Phone"));
                String address=documentSnapshot.getString("City")+", "+documentSnapshot.getString("Post")+", "+
                documentSnapshot.getString("District")+", "+documentSnapshot.getString("State")+", "+documentSnapshot.getString("Pincode");
                holder.address.setText(address);
            }
        });

        holder.placeorder.setOnClickListener(v-> {

            intent = new Intent(context, RentOrderActivity.class);
            intent.putExtra("name",pos.getName());
            intent.putExtra("Rate",pos.getRate());
            intent.putExtra("Comp",pos.getCompany());
            intent.putExtra("Use",pos.getPurpose());
            intent.putExtra("Details",pos.getDetails());
            intent.putExtra("Url",pos.getImageUrl());
            intent.putExtra("username",holder.username.getText().toString());
            intent.putExtra("mobile",holder.mobile.getText().toString());
            intent.putExtra("address",holder.address.getText().toString());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return rentlist.size();
    }

    public class RentViewholder extends RecyclerView.ViewHolder{
        TextView mname,mprice,mcompany,used,details,username,mobile,address;
        ImageView image;
        LinearLayout linearLayout;
        Button placeorder;
        public RentViewholder(@NonNull View itemView) {
            super(itemView);
            mname=itemView.findViewById(R.id.machinename);
            mprice=itemView.findViewById(R.id.machineprice);
            mcompany=itemView.findViewById(R.id.machinecomp);
            used=itemView.findViewById(R.id.machineused);
            details=itemView.findViewById(R.id.machinedetails);
            username=itemView.findViewById(R.id.machineusername);
            mobile=itemView.findViewById(R.id.machineusermobile);
            address=itemView.findViewById(R.id.machineuseraddress);
            image=itemView.findViewById(R.id.machineimg);
            placeorder=itemView.findViewById(R.id.mplaceorder);
        }
    }

}
