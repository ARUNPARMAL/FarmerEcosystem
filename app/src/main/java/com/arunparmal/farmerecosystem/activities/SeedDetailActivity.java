package com.arunparmal.farmerecosystem.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.arunparmal.farmerecosystem.R;
import com.arunparmal.farmerecosystem.utility.Constants;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.units.qual.C;

public class SeedDetailActivity extends AppCompatActivity {

    TextView productname,price,sellername,stockstatus,seedname,seedvariety,netweight,netquantity
            ,brandname,harvestingtime,description;
    Button buyproduct;
    ImageView seedimage,userimage;
    String tseedid;
    String tseedname;
    String tseedvariety;
    String timageUrl;
    String tseedprice;
    String tvendorid;
    String tnet_weight;
    String tnet_quantity;
    String tbrand_name;
    String tharvesting_Time;
    String tdescription;
    String tstockstatus;

    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seed_detail);


        // constant object
        Constants constants= new Constants();

        // views
        productname=findViewById(R.id.product_name_text);
        price=findViewById(R.id.price);
        sellername=findViewById(R.id.seller_name);
        stockstatus=findViewById(R.id.stockstatus);
        seedname=findViewById(R.id.seedname);
        seedvariety=findViewById(R.id.seed_variety);
        netweight=findViewById(R.id.item_weight);
        netquantity=findViewById(R.id.quantity);
        brandname=findViewById(R.id.brand_name);
        harvestingtime=findViewById(R.id.harverstingtime);
        description=findViewById(R.id.description);
        seedimage=findViewById(R.id.imageView);
        userimage=findViewById(R.id.user_image);

        buyproduct=findViewById(R.id.buy_button);

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){

       //getting extras

         tseedid          =String.valueOf(bundle.get(constants.SEED_ID));
         tseedname        =bundle.get(constants.SEED_NAME).toString();
         tseedvariety     =bundle.get(constants.SEED_VARIETY).toString();
         timageUrl        =bundle.get(constants.SEED_iMAGE_URL).toString();
         tseedprice       =bundle.get(constants.PRICE).toString();
         tvendorid        =bundle.get(constants.VENDOR_ID).toString();
         tnet_weight      =String.valueOf(bundle.get(constants.ITEM_WEIGHT));
         tnet_quantity    =String.valueOf(bundle.get(constants.NET_QUANTITY));
         tbrand_name      =bundle.get(constants.BRAND_NAME).toString();
         tharvesting_Time =bundle.get(constants.TIME_PERIOD).toString();
         tdescription     =bundle.get(constants.SEED_DESCRIPTION).toString();
         tstockstatus     =bundle.get(constants.STOCK_STATUS).toString();



            //firebase
            firestore=FirebaseFirestore.getInstance();
            firestore.collection("Vendors").document(tvendorid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isComplete()){
                        sellername.setText(String.valueOf(task.getResult().get("Name")));
                        Glide.with(SeedDetailActivity.this).load(task.getResult().getString("ImageUrl")).into(userimage);
                    }
                }
            });


        // setting values

            Glide.with(this).load(timageUrl).into(seedimage);
            productname.setText(tseedname+" "+tseedvariety);
            price.setText("Rs. "+tseedprice);
            stockstatus.setText(tstockstatus);
            if (tstockstatus.equals("In Stock")) {
                stockstatus.setTextColor(this.getResources().getColor(R.color.green));
            } else {
                stockstatus.setTextColor(this.getResources().getColor(R.color.red));
            }
            seedname.setText(tseedname);
            seedvariety.setText(tseedvariety);
            netweight.setText(tnet_weight+" g");
            netquantity.setText(tnet_quantity+" pieces");
            brandname.setText(tbrand_name);
            harvestingtime.setText(tharvesting_Time);
            description.setText(tdescription);


        }

        buyproduct.setOnClickListener(v->{

            Intent intent=new Intent(this,PlaceSeedOrderActivity.class);
            intent.putExtra(constants.PRODUCT_ID,tseedid);
            intent.putExtra(constants.ACTIVITY_VERIFICATION_CODE,constants.ACTIVITY_SEED_DETAIL);
            startActivity(intent);
        });


    }
}