package com.arunparmal.farmerecosystem.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.arunparmal.farmerecosystem.R;
import com.arunparmal.farmerecosystem.home.SeedorderHistory;

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
    }
}