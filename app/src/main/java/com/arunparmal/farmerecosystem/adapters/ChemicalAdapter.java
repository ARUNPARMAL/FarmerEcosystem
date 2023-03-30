package com.arunparmal.farmerecosystem.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arunparmal.farmerecosystem.R;
import com.arunparmal.farmerecosystem.model.ChemicalModel;
import com.arunparmal.farmerecosystem.shop.Chemical_Detail_Activity;
import com.arunparmal.farmerecosystem.utility.Constants;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ChemicalAdapter extends RecyclerView.Adapter<ChemicalAdapter.ChemicalViewHolder> {
    Context context;
    ArrayList<ChemicalModel> chemicallist;
    Constants constants=new Constants();
    Intent intent;

    public ChemicalAdapter(Context context, ArrayList<ChemicalModel> chemicallist) {
        this.context = context;
        this.chemicallist = chemicallist;
    }

    @NonNull
    @Override
    public ChemicalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chemical_layout,parent,false);
        return new ChemicalAdapter.ChemicalViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChemicalViewHolder holder, int position) {
        holder.name.setText(chemicallist.get(position).getName());
        holder.category.setText(chemicallist.get(position).getCategory());
        Double mrp=chemicallist.get(position).getPrice();
        String MRP=String.format("%.2f",mrp);
        holder.price.setText(MRP);
        String url=chemicallist.get(position).getImageUrl();
        if(url!=null){
            Glide.with(context.getApplicationContext()).load(url).into(holder.image);
        }
        else{
            Glide.with(context.getApplicationContext()).load(R.drawable.ic_launcher_background).into(holder.image);
        }
        holder.linearLayout.setOnClickListener(v-> {

            intent = new Intent(context, Chemical_Detail_Activity.class);
            intent.putExtra(constants.CHEMICAL_NAME, chemicallist.get(position).getName());
            intent.putExtra(constants.CHEMICAL_BRAND_NAME, chemicallist.get(position).getBrand());
            intent.putExtra(constants.CHEMICAL_ID, chemicallist.get(position).getID());
            intent.putExtra(constants.CHEMICAL_DESC, chemicallist.get(position).getDesc());
            intent.putExtra(constants.CHEMICAL_USE, chemicallist.get(position).getUse());
            intent.putExtra(constants.CHEMICAL_ITEM_WEIGHT, chemicallist.get(position).getItemWeight());
            intent.putExtra(constants.CHEMICAL_NET_QUANTITY, chemicallist.get(position).getNetQuantity());
            intent.putExtra(constants.CHEMICAL_STOCK_STATUS, chemicallist.get(position).getStock());
            intent.putExtra(constants.CHEMICAL_PRICE, chemicallist.get(position).getPrice());
            intent.putExtra(constants.CHEMICAL_CATEGORY, chemicallist.get(position).getCategory());
            intent.putExtra(constants.CHEMICAL_VENDOR_ID, chemicallist.get(position).getVendorID());
            intent.putExtra(constants.CHEMICAL_IMAGEURL, chemicallist.get(position).getImageUrl());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return chemicallist.size();
    }

    public class ChemicalViewHolder extends RecyclerView.ViewHolder{
        TextView name,category,price;
        ImageView image;
        LinearLayout linearLayout;
        public ChemicalViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.chemicalnamerv);
            category=itemView.findViewById(R.id.chemicalcategoryrv);
            price=itemView.findViewById(R.id.chemicalpricerv);
            image=itemView.findViewById(R.id.chemicalimgrv);
            linearLayout=itemView.findViewById(R.id.chemicallinearlayouttouch);
        }
    }
}
