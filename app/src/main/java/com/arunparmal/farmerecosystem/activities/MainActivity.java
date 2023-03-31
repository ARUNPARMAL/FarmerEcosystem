package com.arunparmal.farmerecosystem.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.arunparmal.farmerecosystem.R;
import com.arunparmal.farmerecosystem.home.HomeFragment;
import com.arunparmal.farmerecosystem.profile.ProfileFragment;
import com.arunparmal.farmerecosystem.rent.RentFragment;
import com.arunparmal.farmerecosystem.shop.ShopFragment;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {
    MeowBottomNavigation meowBottomNavigation;
    FrameLayout frameLayout;

    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //firebase instance
        firestore=FirebaseFirestore.getInstance();

        //meownavigationbar
        meowBottomNavigation=findViewById(R.id.meowbnv);
        frameLayout=findViewById(R.id.framelayout);

        setnavigationbar();


        getdata();


    }

    private void getdata() {
        Query query = FirebaseFirestore.getInstance()
                .collection("Seeds");
    }

    private void setnavigationbar(){
        meowBottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.home_ic));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.shop_ic));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.rent_ic));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.profile_ic));

        meowBottomNavigation.show(1,true);


        meowBottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch(model.getId()){
                    case 1:
                        replace(new HomeFragment());
                        break;

                    case 2:
                        replace(new ShopFragment());
                        break;

                    case 3:
                        replace(new RentFragment());
                        break;

                    case 4:
                        replace(new ProfileFragment());
                        break;

                }
                return null;
            }
        });
    }

    private void replace(Fragment fragment) {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framelayout,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framelayout,new HomeFragment());
        transaction.commit();
    }
}