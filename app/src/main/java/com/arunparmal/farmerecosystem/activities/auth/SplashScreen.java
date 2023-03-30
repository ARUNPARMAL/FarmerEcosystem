package com.arunparmal.farmerecosystem.activities.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.arunparmal.farmerecosystem.R;
import com.arunparmal.farmerecosystem.activities.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {
    private static final int SPLASH=3300;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mAuth=FirebaseAuth.getInstance();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser currentuser=mAuth.getCurrentUser();
                if(currentuser!=null){
                    Intent intent=new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent=new Intent(SplashScreen.this, MainActivity2.class);
                    startActivity(intent);
                    finish();
                }

                //    Intent iHome = new Intent(SplashScreen.this,MainActivity.class);
                //     startActivity(iHome);
            }
        },SPLASH);
    }
}