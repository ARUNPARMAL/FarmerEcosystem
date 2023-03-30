package com.arunparmal.farmerecosystem.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arunparmal.farmerecosystem.R;
import com.arunparmal.farmerecosystem.utility.Constants;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.ktx.Firebase;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlaceSeedOrderActivity extends AppCompatActivity {

    Constants constants = new Constants();
    FirebaseFirestore firestore;
    ImageView productimage;
    Button buyitem,buynow;
    TextView productname,productprice,producttype,totalprice,productid,vendorid;
    EditText itemcount,address,pincode,paymentmode;
    FirebaseUser fuser= FirebaseAuth.getInstance().getCurrentUser();
    ProgressBar progressBar;

    String orderid= UUID.randomUUID().toString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_seed_order);

        firestore=FirebaseFirestore.getInstance();

        String activity_code;
        String product_id;

        productimage=findViewById(R.id.productimage);
        productname=findViewById(R.id.productname);
        productprice=findViewById(R.id.product_price);
        producttype=findViewById(R.id.productype);
        totalprice=findViewById(R.id.totalprice);
        itemcount=findViewById(R.id.product_itemcount);
        address=findViewById(R.id.address);
        pincode=findViewById(R.id.pincode);
        paymentmode=findViewById(R.id.paymentmode);
        buyitem=findViewById(R.id.buy_item);
        buynow=findViewById(R.id.buynow);
        productid=findViewById(R.id.productid);
        vendorid=findViewById(R.id.vendorid);
        progressBar=findViewById(R.id.progressbar);

        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            activity_code=bundle.get(constants.ACTIVITY_VERIFICATION_CODE).toString();
            product_id=bundle.get(constants.PRODUCT_ID).toString();

            if (activity_code.equals(constants.ACTIVITY_SEED_DETAIL)){
                getseeddatafromfirebase(product_id);
            }

            buyitem.setOnClickListener(v->{
                if (checkdata()){
                        buynow.setVisibility(View.VISIBLE);
                        buynow.animate();
                }
            });

            buynow.setOnClickListener(v->{
                progressBar.setVisibility(View.VISIBLE);
                String tproductid,titemcount,taddress,tpincode,tmodeodpayment,tamount,tvendorid;
                tproductid=productid.getText().toString();
                taddress=address.getText().toString();
                tvendorid=vendorid.getText().toString();
                tpincode=pincode.getText().toString();
                titemcount=itemcount.getText().toString();
                tamount=totalprice.getText().toString();
                Toast.makeText(this, tvendorid, Toast.LENGTH_SHORT).show();

                Map<String,Object> bookingdata = new HashMap<>();
                bookingdata.put("Order_id",orderid);
                bookingdata.put("Order_Product_id",tproductid);
                bookingdata.put("Order_Product_address",taddress);
                bookingdata.put("Order_Product_pincode",tpincode);
                bookingdata.put("Order_Product_count",titemcount);
                bookingdata.put("Order_Product_amount",tamount);
                bookingdata.put("Order_Product_Type","SEED");
                bookingdata.put("Order_Product_UserID",fuser.getUid().toString());

                firestore.collection("Vendors").document(tvendorid.toString()).collection("Orders").add(bookingdata).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                   if (task.isComplete()){
                       Toast.makeText(PlaceSeedOrderActivity.this, "Order Placed", Toast.LENGTH_SHORT).show();
                       progressBar.setVisibility(View.GONE);
                       startActivity(new Intent(PlaceSeedOrderActivity.this,MainActivity.class));
                       finish();
                   }
                    }
                });

                firestore.collection("Farmers").document(fuser.getUid()).collection("Orders").add(bookingdata).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Log.d("database","data added in farmer collection");
                    }
                });
            });

            System.out.println(" the data are "+activity_code+" and id is "+product_id);


        }
    }

    private Boolean checkdata() {

        String itemcoount=itemcount.getText().toString();
        if (itemcoount.isEmpty()){
            itemcount.setError("Value can't be empty");
        }else
            if (Integer.valueOf(itemcoount)< 0) {
                itemcount.setError(" enter a valid count");
                return false;
            } else if (address.getText().toString().trim().isEmpty()) {
                address.setError("Enter a valid address");
                return false;
            } else if (pincode.getText().toString().trim().length() != 6) {
                pincode.setError("enter a valid pincode");
            }
            return true;

    }
///////////////////////////////

    ////////////
    private void getseeddatafromfirebase(String product_id) {
        firestore.collection("Seeds").whereEqualTo("SeedID",product_id).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error!=null){
                    Toast.makeText(PlaceSeedOrderActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
                DocumentSnapshot documentSnapshot=value.getDocuments().get(0);
                Toast.makeText(PlaceSeedOrderActivity.this, documentSnapshot.getId(), Toast.LENGTH_SHORT).show();
                String pname=documentSnapshot.getString("SeedName")+" "+documentSnapshot.getString("Variety");
                productname.setText(pname);
                productprice.setText("Rs. "+documentSnapshot.getDouble("Price"));
                Glide.with(PlaceSeedOrderActivity.this).load(documentSnapshot.getString("ImageUrl")).into(productimage);
                productid.setText(documentSnapshot.getString("SeedID"));
                vendorid.setText(documentSnapshot.getString("VendorID"));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        buynow.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }
}