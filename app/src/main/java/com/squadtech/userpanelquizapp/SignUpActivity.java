package com.squadtech.userpanelquizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    private EditText eName , eEmail , ePass , ePhone ;

    private TextView txtAlreadyAcnt;
    private ImageButton confirmBtn;
    private CheckBox checkBox;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        eName = (EditText)findViewById(R.id.RegName);
        eEmail = (EditText)findViewById(R.id.RegEmail);
        ePass = (EditText)findViewById(R.id.RegPass);
        ePhone = (EditText)findViewById(R.id.RegPhone);

        mProgress = new ProgressDialog(this);

        txtAlreadyAcnt = (TextView)findViewById(R.id.txtAlreadyReg);
        txtAlreadyAcnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this , LoginActivity.class));
            }
        });


        //Button and Check box
        confirmBtn = (ImageButton) findViewById(R.id.RegBtn);
        checkBox = (CheckBox) findViewById(R.id.Regchkbox);

        mAuth = FirebaseAuth.getInstance();

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sName = eName.getText().toString();
                String sEmail = eEmail.getText().toString();
                String sPass = ePass.getText().toString();
                String sPhone = ePhone.getText().toString();

                if (TextUtils.isEmpty(sName)&&TextUtils.isEmpty(sEmail)&&TextUtils.isEmpty(sPass)&&TextUtils.isEmpty(sPhone)){

                    Exception exception = null;
                    Toast.makeText(SignUpActivity.this, "error "+ exception.getMessage() , Toast.LENGTH_SHORT).show();
                }
                else {
                    RegisterAccount(sName , sEmail, sPass , sPhone);
                }
            }
        });

    }

    private void RegisterAccount(final String sName, final String sEmail, final String sPass, final String sPhone) {

        mProgress.setTitle("Please Wait");
        mProgress.setMessage("Registering Account");
        mProgress.show();
        mAuth.createUserWithEmailAndPassword(sEmail , sPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

//                    String SaveCurrentDate;
//
//                    Calendar calendar = Calendar.getInstance();
//                    SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
//                    SaveCurrentDate = currentDate.format(calendar.getTime());
                    final String date = DateFormat.getDateInstance().format(new Date());


                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getUid());
                    final HashMap<String , Object> userMap = new HashMap<>();
                    userMap.put("user_name" , sName);
                    userMap.put("user_email" , sEmail);
                    userMap.put("user_phone" , sPhone);
                    userMap.put("user_pass" , sPass);
                    userMap.put("user_uid", FirebaseAuth.getInstance().getUid());
                    userMap.put("user_dp" , "default");
                    userMap.put("registered_date",date );
                    userMap.put("total_points" ,"100");

                    mProgress.dismiss();
                    mDatabase.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Intent mainIntent = new Intent(SignUpActivity.this, MainActivity.class);
                                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(mainIntent);
                                finish();
                            }
                        }
                    });

                }

            }
        });

    }

}
