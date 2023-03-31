package com.arunparmal.farmerecosystem.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.arunparmal.farmerecosystem.R;
import com.arunparmal.farmerecosystem.home.SeedorderHistory;
import com.arunparmal.farmerecosystem.shop.Shopping_History_Page_Chemical_Activity;

public class OrderpageActivity extends AppCompatActivity {

    Button chemicals,rent,harvesting,seed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderpage);

        chemicals =findViewById(R.id.shoppinghistory);
        rent=findViewById(R.id.rentmachinehistory);
        harvesting=findViewById(R.id.harvetinghistory);
        seed=findViewById(R.id.shoppingseedhistory);

        seed.setOnClickListener(v->{
            startActivity(new Intent(this, SeedorderHistory.class));
        });



        chemicals.setOnClickListener(v->{
            startActivity(new Intent(this, Shopping_History_Page_Chemical_Activity.class));
        });
//        seed.setOnClickListener(v->{
//            startActivity(new Intent(this, SeedorderHistory.class));
//        });
//        seed.setOnClickListener(v->{
//            startActivity(new Intent(this, SeedorderHistory.class));
//        });

    }
}