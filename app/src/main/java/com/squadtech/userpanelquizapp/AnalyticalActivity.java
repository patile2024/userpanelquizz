package com.squadtech.userpanelquizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squadtech.userpanelquizapp.Adapter.PagerAdapterClass;
import com.squadtech.userpanelquizapp.Interface.FirebaseLoader;
import com.squadtech.userpanelquizapp.Models.Questions;
import com.squadtech.userpanelquizapp.Transformer.DepthPageTransformer;

import java.util.ArrayList;
import java.util.List;

public class AnalyticalActivity extends AppCompatActivity implements FirebaseLoader {


    DatabaseReference databaseReference;

    FirebaseLoader firebaseLoader;
    public List<Questions> questionsArrayList = new ArrayList<>();
    ViewPager viewPager;
    PagerAdapterClass adapter;

    private static final String STATE_LIST = "State Adapter Data";

    int pagenextnumber = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytical);
        databaseReference = FirebaseDatabase.getInstance().getReference("Questions").child("Categories").child("Analytical");

        viewPager = (ViewPager) findViewById(R.id.viewpaggerid);


        firebaseLoader = this;

        loadData();


        String get10pts = getIntent().getStringExtra("10" );
        String get30pts = getIntent().getStringExtra("30" );
        String get50pts = getIntent().getStringExtra("50" );
        String get100pts = getIntent().getStringExtra("100" );
        viewPager.setPageTransformer(true, new DepthPageTransformer());


        viewPager.setOnTouchListener(new View.OnTouchListener()


        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }

        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                pagenextnumber = position;

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void loadData() {

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    questionsArrayList.add(ds.getValue(Questions.class));
                    firebaseLoader.onFirebaseLoadSuccess(questionsArrayList);
                    System.out.println("Snapshots " + ds.getValue());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                firebaseLoader.onFirebaseLoadFailure(databaseError.getMessage());
            }
        });
    }

    @Override
    public void onFirebaseLoadSuccess(List<Questions> questionsArrayList) {
        adapter = new PagerAdapterClass(this, (ArrayList<Questions>) questionsArrayList);
        viewPager.setAdapter(adapter);
    }


    @Override
    public void onFirebaseLoadFailure(String message) {

        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }

    public void next(View view) {

        viewPager.setCurrentItem(pagenextnumber + 1);

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
