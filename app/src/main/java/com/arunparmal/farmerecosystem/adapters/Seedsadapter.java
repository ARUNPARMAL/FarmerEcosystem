package com.arunparmal.farmerecosystem.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.arunparmal.farmerecosystem.R;
import com.arunparmal.farmerecosystem.activities.MainActivity;
import com.arunparmal.farmerecosystem.activities.SeedDetailActivity;
import com.arunparmal.farmerecosystem.model.SeedModel;
import com.arunparmal.farmerecosystem.utility.Constants;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Seedsadapter extends RecyclerView.Adapter<Seedsadapter.SeedViewHolder> {

    ArrayList<SeedModel> datalist;
    Context context;
    Constants constants=new Constants();
    Intent intent;

    public Seedsadapter(ArrayList<SeedModel> datalist, Context context) {
        this.datalist = datalist;
        this.context = context;

    }

    @NonNull
    @Override
    public SeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.seed_item_layout,parent,false);
        return new SeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeedViewHolder holder, int position) {
        Glide.with(context).load(datalist.get(position).getImageUrl()).into(holder.imageView);
        holder.sname.setText(datalist.get(position).getSeedName());
        holder.sprice.setText(String.valueOf(datalist.get(position).getPrice()));
        holder.itemView.setOnClickListener(v->{
//            AppCompatActivity appCompatActivity = (AppCompatActivity)v.getContext();

            intent= new Intent(context, SeedDetailActivity.class);
            intent.putExtra(constants.SEED_NAME,datalist.get(position).getSeedName());
            intent.putExtra(constants.BRAND_NAME,datalist.get(position).getBrandName());
            intent.putExtra(constants.SEED_ID,datalist.get(position).getSeedID());
            intent.putExtra(constants.SEED_DESCRIPTION,datalist.get(position).getSeedDescription());
            intent.putExtra(constants.TIME_PERIOD,datalist.get(position).getTimeperiod());
            intent.putExtra(constants.ITEM_WEIGHT,datalist.get(position).getItemWeight());
            intent.putExtra(constants.NET_QUANTITY,datalist.get(position).getNetQuantity());
            intent.putExtra(constants.STOCK_STATUS,datalist.get(position).getStockStatus());
            intent.putExtra(constants.PRICE,datalist.get(position).getPrice());
            intent.putExtra(constants.SEED_VARIETY,datalist.get(position).getVariety());
            intent.putExtra(constants.VENDOR_ID,datalist.get(position).getVendorID());
            intent.putExtra(constants.SEED_iMAGE_URL,datalist.get(position).getImageUrl());
            context.startActivity(intent);



        });

    }


    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class SeedViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView  sname, sprice;
        ConstraintLayout constraintLayoutitem;
        public SeedViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            sname=itemView.findViewById(R.id.name);
            sprice=itemView.findViewById(R.id.price);
            constraintLayoutitem=itemView.findViewById(R.id.clayoutitem);
        }
    }
}
