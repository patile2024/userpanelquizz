package com.squadtech.userpanelquizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private ImageButton LoginBtn;
    private EditText eEmail, ePass;
    private TextView txtNoAcount;
    private FirebaseAuth mAuth;

    ProgressDialog mProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mProgress = new ProgressDialog(this);
        LoginBtn = (ImageButton)findViewById(R.id.LoginBtn);
        eEmail = (EditText)findViewById(R.id.LoginEmail);
        ePass = (EditText)findViewById(R.id.LoginPass);
        txtNoAcount = (TextView) findViewById(R.id.txtNoAccount);

        //Firebase
        mAuth = FirebaseAuth.getInstance();

        txtNoAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this , SignUpActivity.class));

            }
        });




        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String sEmail= eEmail.getText().toString();
                String sPass= ePass.getText().toString();

                if (!TextUtils.isEmpty(sEmail) && !TextUtils.isEmpty(sPass)){
                    LoginAccount(sEmail , sPass);
                }
                else if (TextUtils.isEmpty(sEmail) || TextUtils.isEmpty(sPass)){

                    ePass.setError("Check the Field");
                    eEmail.setError("Check the Field");
                }
                else {
                    Exception exception = null;
                    Log.e("error" , exception.getMessage());
                }

            }
        });
    }

    private void LoginAccount(String sEmail, String sPass) {

        mProgress.setTitle("Checking User");
        mProgress.setMessage("Please wait");
        mProgress.show();
        mAuth.signInWithEmailAndPassword(sEmail , sPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    mProgress.dismiss();
                    startActivity(new Intent(LoginActivity.this , MainActivity.class));
                    finish();
                }
                else {



                    String task_result = task.getException().getMessage().toString();

                    Toast.makeText(LoginActivity.this, "Error : " + task_result, Toast.LENGTH_LONG).show();

                }
            }
        });
    }
    }

