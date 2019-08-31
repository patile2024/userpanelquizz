package com.squadtech.userpanelquizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
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
import com.squadtech.userpanelquizapp.R;
import com.squadtech.userpanelquizapp.Transformer.DepthPageTransformer;

import java.util.ArrayList;
import java.util.List;

public class EteaActivity extends AppCompatActivity implements FirebaseLoader {
    DatabaseReference databaseReference;

    FirebaseLoader firebaseLoader;
    public List<Questions> questionsArrayList = new ArrayList<>();
    ViewPager viewPager;
    PagerAdapterClass adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etea);


        databaseReference = FirebaseDatabase.getInstance().getReference("Questions").child("Categories").child("ETEA");

        viewPager = (ViewPager) findViewById(R.id.viewpaggerid);

        firebaseLoader = this;

        loadData();
        viewPager.setPageTransformer(true, new DepthPageTransformer());

        viewPager.setOnTouchListener(new View.OnTouchListener()


        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
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

        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }

}
