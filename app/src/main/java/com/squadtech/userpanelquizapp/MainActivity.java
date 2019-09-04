package com.squadtech.userpanelquizapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squadtech.userpanelquizapp.Adapter.MyRecordAdapter;
import com.squadtech.userpanelquizapp.Profile.ProfileActivity;
import com.squareup.picasso.Picasso;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button logiBtn, eteaBtn, genBtn, anaBtn;
    CircleImageView headProfile;
    TextView headName;
    FirebaseAuth mAuth;
    TextView headEmail;
    DatabaseReference mRef;
    GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        mAuth = FirebaseAuth.getInstance();
        logiBtn = (Button) findViewById(R.id.logicalBtn);
        eteaBtn = (Button) findViewById(R.id.EteaBtn);
        genBtn = (Button) findViewById(R.id.GeneralBtn);
        anaBtn = (Button) findViewById(R.id.analyticalBtn);
        // Check if user is signed in (non-null) and update UI accordingly.

        //Firebase

        mAuth = FirebaseAuth.getInstance();


        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser== null){
            startActivity(new Intent(MainActivity.this , LoginActivity.class));
            finish();
        }
            mRef = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getUid());


            //Headers

            headProfile = headerView.findViewById(R.id.headProfileImg);
            headName = headerView.findViewById(R.id.HeaderUserName);
            headEmail = headerView.findViewById(R.id.headerEmail);


            //Value Listener

            mRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String image_url = dataSnapshot.child("user_dp").getValue().toString();
                    String nameS = dataSnapshot.child("user_name").getValue().toString();
                    String emailS = dataSnapshot.child("user_email").getValue().toString();
                    System.out.println("my email " + dataSnapshot.child("user_email").getValue().toString());
                    headEmail.setText(emailS);
                    headName.setText(nameS);
                    if (image_url != null && !image_url.equals("") && !image_url.equals("default")) {
                        Picasso.get().load(image_url).placeholder(R.drawable.ic_profile).into(headProfile);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


            logiBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, SelectQuestions.class);
                    intent.putExtra("logical", "Logical");
                    startActivity(intent);
                }
            });

            eteaBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, SelectQuestions.class);
                    intent.putExtra("etea", "ETEA");
                    startActivity(intent);
                }
            });

            genBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, SelectQuestions.class);
                    intent.putExtra("general", "General");
                    startActivity(intent);
                }
            });

            anaBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, SelectQuestions.class);
                    intent.putExtra("analytical", "Analytical");
                    startActivity(intent);
                }
            });
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        NavigationView navigationView = findViewById(R.id.nav_view);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();
            navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
if (currentUser== null){
startActivity(new Intent(MainActivity.this , LoginActivity.class));
finish();
}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Profile)
        {
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }
        else if (id == R.id.nav_quiz_contest)
        {
            startActivity(new Intent(MainActivity.this , MyRecords.class));
        }
        else if (id == R.id.nav_Timer_quiz)
        {

        }
        else if (id == R.id.nav_completedTask)
        {

        }
        else if (id == R.id.nav_logout)
        {
        startActivity(new Intent(MainActivity.this , LoginActivity.class));
        finish();
        FirebaseAuth.getInstance().signOut();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
