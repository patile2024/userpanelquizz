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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    private ImageButton LoginBtn;
    private EditText eEmail, ePass;
    private TextView txtNoAcount , signInGoogle;
    GoogleSignInClient googleSignInClient;
    private FirebaseAuth mAuth;
    static final int GOOGLE_SIGN_IN = 123;
    ProgressDialog mProgress;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mProgress = new ProgressDialog(this);
        LoginBtn = (ImageButton)findViewById(R.id.LoginBtn);
        eEmail = (EditText)findViewById(R.id.LoginEmail);
        ePass = (EditText)findViewById(R.id.LoginPass);
        txtNoAcount = (TextView) findViewById(R.id.txtNoAccount);
        signInGoogle = (TextView)findViewById(R.id.loginWithGoogle);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);





        signInGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignInGoogle();
            }
        });

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
    public void SignInGoogle() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN);
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d("TAG", "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {

                        Log.d("TAG", "signInWithCredential:success");

                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {

                        Log.w("TAG", "signInWithCredential:failure", task.getException());

                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        updateUI(null);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GOOGLE_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Log.w("TAG", "Google sign in failed", e);
            }
        }
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();
            String photo = String.valueOf(user.getPhotoUrl());
            String phone = user.getPhoneNumber();
            String SaveCurrentDate;

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
            SaveCurrentDate = currentDate.format(calendar.getTime());

            mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getUid());
            final HashMap<String , Object> userMap = new HashMap<>();
            userMap.put("user_name" , name);
            userMap.put("user_email" , email);
            userMap.put("user_phone" , phone);
            userMap.put("user_pass" , "Encrypted");
            userMap.put("user_uid", FirebaseAuth.getInstance().getUid());
            userMap.put("user_dp" , photo);
            userMap.put("registered_date",SaveCurrentDate );
            userMap.put("total_points" ,"100");

            mProgress.dismiss();
            mDatabase.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(mainIntent);
                        finish();
                    }
                }
            });


        } else {

        }
    }

    }

