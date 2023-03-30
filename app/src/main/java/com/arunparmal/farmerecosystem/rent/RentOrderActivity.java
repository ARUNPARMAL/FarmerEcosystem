package com.arunparmal.farmerecosystem.rent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.arunparmal.farmerecosystem.R;
import com.bumptech.glide.Glide;

public class RentOrderActivity extends AppCompatActivity {
    TextView mname,mprice,mcompany,mused,mdetails,musername,mmobile,maddress;
    ImageView mimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_order);

        mname=findViewById(R.id.mname);
        mprice=findViewById(R.id.mprice);
        mcompany=findViewById(R.id.mcomp);
        mused=findViewById(R.id.mused);
        mdetails=findViewById(R.id.mdetails);
        musername=findViewById(R.id.musername);
        mmobile=findViewById(R.id.mmobile);
        maddress=findViewById(R.id.maddress);
        mimage=findViewById(R.id.mimg);

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null) {

            //getting extras

            String name = String.valueOf(bundle.get("name"));
            String price = String.valueOf(bundle.get("Rate"));
            String comp = String.valueOf(bundle.get("Comp"));
            String used = String.valueOf(bundle.get("Use"));
            String details = String.valueOf(bundle.get("Details"));
            String username = String.valueOf(bundle.get("username"));
            String mobile = String.valueOf(bundle.get("mobile"));
            String address = String.valueOf(bundle.get("address"));
            String image = String.valueOf(bundle.get("Url"));

            mname.setText(name);
            mprice.setText(price);
            mcompany.setText(comp);
            mused.setText(used);
            mdetails.setText(details);
            musername.setText(username);
            mmobile.setText(mobile);
            maddress.setText(address);
            if(image!=null){
                Glide.with(this).load(image).into(mimage);
            }
            else{
                Glide.with(this).load(R.drawable.ic_launcher_background).into(mimage);
            }
        }
    }
}