package com.arunparmal.farmerecosystem.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.arunparmal.farmerecosystem.R;

public class orderpage extends AppCompatActivity {

    Button shopping,rent,harvesting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderpage);

        shopping=findViewById(R.id.shoppinghistory);
        rent=findViewById(R.id.rentmachinehistory);
        harvesting=findViewById(R.id.harvetinghistory);
    }
}