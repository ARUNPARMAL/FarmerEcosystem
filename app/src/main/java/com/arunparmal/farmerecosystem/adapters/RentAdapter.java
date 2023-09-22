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
import androidx.appcompat.app.AlertDialog;
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
        holder.used.setText(pos.getPurpose());
        String url=pos.getImageUrl();
        if(url!=null){
            Glide.with(context.getApplicationContext()).load(url).into(holder.image);
        }
        else{
            Glide.with(context.getApplicationContext()).load(R.drawable.ic_launcher_background).into(holder.image);
        }

        holder.rentnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                // set the custom layout

                View customLayout=LayoutInflater.from(context).inflate(R.layout.layout_rent_dialog,null);
                builder.setView(customLayout);
                AlertDialog dialog = builder.create();
                dialog.show();
                TextView cancel=customLayout.findViewById(R.id.cancelbtn);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        return rentlist.size();
    }

    public class RentViewholder extends RecyclerView.ViewHolder{
        TextView mname,mprice,used;
        ImageView image;
        LinearLayout linearLayout;
        Button rentnow;
        public RentViewholder(@NonNull View itemView) {
            super(itemView);
            mname=itemView.findViewById(R.id.machinename);
            mprice=itemView.findViewById(R.id.machineprice);
            used=itemView.findViewById(R.id.machineused);
            image=itemView.findViewById(R.id.machineimg);
            rentnow=itemView.findViewById(R.id.mrentnow);
        }
    }

}
