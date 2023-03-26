package com.arunparmal.farmerecosystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.arunparmal.farmerecosystem.home.HomeFragment;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {
    MeowBottomNavigation meowBottomNavigation;
    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        meowBottomNavigation=findViewById(R.id.meowbnv);
        frameLayout=findViewById(R.id.framelayout);

        meowBottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.home_ic));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.shop_ic));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.rent_ic));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.profile_ic));

        meowBottomNavigation.show(1,true);
        replace(new HomeFragment());

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
}