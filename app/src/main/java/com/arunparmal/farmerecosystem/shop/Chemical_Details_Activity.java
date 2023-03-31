package com.arunparmal.farmerecosystem.shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.arunparmal.farmerecosystem.R;
import com.arunparmal.farmerecosystem.activities.PlaceSeedOrderActivity;
import com.arunparmal.farmerecosystem.utility.Constants;
import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;

public class Chemical_Details_Activity extends AppCompatActivity {

    TextView productname,price,stockstatus,cname,cvariety,netweight,netquantity
            ,brandname,use,description;
    ImageView chemicalimage;
    Button buyitemchemical;
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chemical_details);


        Constants constants= new Constants();
        productname=findViewById(R.id.c_name_text);
        price=findViewById(R.id.c_price);
        stockstatus=findViewById(R.id.c_stockstatus);
        cname=findViewById(R.id.cname);
        cvariety=findViewById(R.id.c_variety);
        netweight=findViewById(R.id.citem_weight);
        netquantity=findViewById(R.id.cquantity);
        brandname=findViewById(R.id.cbrand_name);
        use=findViewById(R.id.charverstingtime);
        description=findViewById(R.id.cdescription);
        chemicalimage=findViewById(R.id.c_imageView);
        buyitemchemical=findViewById(R.id.buy_item_chemical);

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){

            //getting extras

            String tid = String.valueOf(bundle.get(constants.CHEMICAL_ID));
            String tname=bundle.get(constants.CHEMICAL_NAME).toString();
            String tcategory=bundle.get(constants.CHEMICAL_CATEGORY).toString();
            String timageUrl=bundle.get(constants.CHEMICAL_IMAGEURL).toString();
            String tprice=bundle.get(constants.CHEMICAL_PRICE).toString();
            String tvendorid=bundle.get(constants.CHEMICAL_VENDOR_ID).toString();
            String tnet_weight=String.valueOf(bundle.get(constants.CHEMICAL_ITEM_WEIGHT));
            String tnet_quantity=String.valueOf(bundle.get(constants.CHEMICAL_NET_QUANTITY));
            String tbrand_name=bundle.get(constants.CHEMICAL_BRAND_NAME).toString();
            String tuse=bundle.get(constants.CHEMICAL_USE).toString();
            String tdescription=bundle.get(constants.CHEMICAL_DESC).toString();
            String tstockstatus=bundle.get(constants.CHEMICAL_STOCK_STATUS).toString();

            buyitemchemical.setOnClickListener(v -> {

                    Intent intent=new Intent(this, PlaceSeedOrderActivity.class);
                    intent.putExtra(constants.PRODUCT_ID,tid);
                    intent.putExtra(constants.ACTIVITY_VERIFICATION_CODE,constants.ACTIVITY_CHEMICAL_DETAIL);
                    startActivity(intent);
            });

            // setting values
            if(timageUrl!=null){
                Glide.with(this).load(timageUrl).into(chemicalimage);
            }
            else{
                Glide.with(this).load(R.drawable.ic_launcher_background).into(chemicalimage);
            }
            productname.setText(tname+" "+tcategory);
            price.setText(tprice);
            stockstatus.setText(tstockstatus);
            if (tstockstatus.equals("In Stock")) {
                stockstatus.setTextColor(this.getResources().getColor(R.color.green));
            } else {
                stockstatus.setTextColor(this.getResources().getColor(R.color.red));
            }
            cname.setText(tname);
            cvariety.setText(tcategory);
            netweight.setText(tnet_weight);
            netquantity.setText(tnet_quantity);
            brandname.setText(tbrand_name);
            use.setText(tuse);
            description.setText(tdescription);


        }
    }
}