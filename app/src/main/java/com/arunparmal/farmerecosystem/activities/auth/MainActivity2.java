package com.arunparmal.farmerecosystem.activities.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.arunparmal.farmerecosystem.R;
import com.arunparmal.farmerecosystem.adapters.Seedsadapter;
import com.arunparmal.farmerecosystem.utility.Constants;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.TimeUnit;

public class MainActivity2 extends AppCompatActivity {

    Button get_otp;
    EditText mobielnumber;
    ProgressBar progressBar;
    ImageView loginGoogle;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        get_otp=findViewById(R.id.btn_get_otp);
        mobielnumber=findViewById(R.id.input_mobileno);
        progressBar=findViewById(R.id.progressbarsending);

        loginGoogle=findViewById(R.id.googlelogobtn);
        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc= GoogleSignIn.getClient(this,gso);

        loginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googlesignin();

            }
        });

        get_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mobielnumber.getText().toString().trim().isEmpty()){
                    if(mobielnumber.getText().toString().trim().length()==10){

                        progressBar.setVisibility(View.VISIBLE);
                        get_otp.setVisibility(View.INVISIBLE);

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91" + mobielnumber.getText().toString(),
                                120,
                                TimeUnit.SECONDS,
                                MainActivity2.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                        progressBar.setVisibility(View.GONE);
                                        get_otp.setVisibility(View.VISIBLE);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        progressBar.setVisibility(View.GONE);
                                        get_otp.setVisibility(View.VISIBLE);
                                        Toast.makeText(MainActivity2.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        Log.e("TAG", "onVerificationFailed: "+e.getMessage() );
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                        progressBar.setVisibility(View.GONE);
                                        get_otp.setVisibility(View.VISIBLE);

                                        Intent i=new Intent(MainActivity2.this, VerifyOtpActivity.class);
                                        i.putExtra("mobilenumberintent",mobielnumber.getText().toString());
                                        i.putExtra("backendotp",backendotp);
                                        startActivity(i);
                                    }
                                }
                        );


                    }
                    else{
                        Toast.makeText(MainActivity2.this, "Mobile Number is Incorrect...", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(MainActivity2.this, "Please Enter Mobile Number...", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    private void googlesignin() {
//        Intent i=new Intent(MainActivity2.this,M.class);
//        startActivity(i);
//        Intent signInIntent=gsc.getSignInIntent();
//        startActivityForResult(signInIntent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1000){
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);
                startActivity(new Intent(MainActivity2.this,FarmerDetailsActivity.class));
                finish();
            } catch (ApiException e) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }

        }
    }

}