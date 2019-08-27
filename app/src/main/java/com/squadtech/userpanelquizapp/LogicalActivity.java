package com.squadtech.userpanelquizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squadtech.userpanelquizapp.Adapter.PagerAdapterClass;
import com.squadtech.userpanelquizapp.Models.Questions;

import java.util.ArrayList;

public class LogicalActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    public ArrayList<Questions> questionsArrayList = new ArrayList<>();
    ViewPager viewPager;
    PagerAdapterClass adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logical);

        databaseReference = FirebaseDatabase.getInstance().getReference("Questions").child("Categories").child("Logical");

        viewPager = (ViewPager) findViewById(R.id.viewpaggerid);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                questionsArrayList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    System.out.println("Snapshots "+ ds.getValue());

                    Questions questions = dataSnapshot.getValue(Questions.class);

                    questionsArrayList.add(questions);
                }
                adapter = new PagerAdapterClass(getApplicationContext(), questionsArrayList);
                viewPager.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
