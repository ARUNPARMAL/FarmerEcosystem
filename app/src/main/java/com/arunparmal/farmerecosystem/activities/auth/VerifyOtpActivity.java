package com.arunparmal.farmerecosystem.activities.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arunparmal.farmerecosystem.R;
import com.arunparmal.farmerecosystem.activities.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyOtpActivity extends AppCompatActivity {
    EditText otpBox;
    TextView textView,resendotp;
    Button verifyotp;
    String getotpbackend;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        otpBox = findViewById(R.id.otpBox);

        resendotp=findViewById(R.id.resendotp);

        verifyotp=findViewById(R.id.btn_verify_otp);
        progressBar=findViewById(R.id.progressbar);

        textView=findViewById(R.id.textmobilenumbershown);
        textView.setText(String.format("+91-%s",getIntent().getStringExtra("mobilenumberintent")));

        getotpbackend=getIntent().getStringExtra("backendotp");

        verifyotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(otpBox.getText().toString().length()==6){
                    String entercodeotp=otpBox.getText().toString();

                    if(getotpbackend!=null){
                        progressBar.setVisibility(View.VISIBLE);
                        verifyotp.setVisibility(View.INVISIBLE);

                        PhoneAuthCredential phoneAuthCredential= PhoneAuthProvider.getCredential(getotpbackend,entercodeotp);
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        progressBar.setVisibility(View.GONE);
                                        verifyotp.setVisibility(View.VISIBLE);
                                        if(task.isSuccessful()){
                                            Toast.makeText(VerifyOtpActivity.this, "OTP verified successfully", Toast.LENGTH_SHORT).show();
                                            Intent i=new Intent(VerifyOtpActivity.this, MainActivity.class);
                                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(i);
                                        }else{
                                            Toast.makeText(VerifyOtpActivity.this, "Please check the OTP", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                    }else{
                        Toast.makeText(VerifyOtpActivity.this, "OTP Not Fetched", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(VerifyOtpActivity.this, "OTP Incomplete", Toast.LENGTH_SHORT).show();
                }
            }
        });


        resendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = getIntent().getStringExtra("mobilenumberintent");
                if (s.contains("+91")){
                    s = s.replace("+91-", "");
                }
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + s,
                        60,
                        TimeUnit.SECONDS,
                        VerifyOtpActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(VerifyOtpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newbackendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                getotpbackend=newbackendotp;
                                Toast.makeText(VerifyOtpActivity.this, "OTP sent successfully!!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        });

    }
}