package com.squadtech.userpanelquizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squadtech.userpanelquizapp.Adapter.MyRecordAdapter;
import com.squadtech.userpanelquizapp.Adapter.PagerAdapterClass;
import com.squadtech.userpanelquizapp.Interface.FirebaseLoader;
import com.squadtech.userpanelquizapp.Interface.FirebaseRecordLoader;
import com.squadtech.userpanelquizapp.Models.Questions;
import com.squadtech.userpanelquizapp.Models.QuizPoints;

import java.util.ArrayList;
import java.util.List;

public class MyRecords extends AppCompatActivity implements FirebaseRecordLoader {
    private RecyclerView myRecordView;
    private ArrayList<QuizPoints> arrayList ;
    private MyRecordAdapter adapter;
    DatabaseReference dbReference;

    FirebaseRecordLoader firebaseRecordLoader;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_records);

        dbReference = FirebaseDatabase.getInstance().getReference("QuizPoints").child(FirebaseAuth.getInstance().getUid());

        myRecordView = findViewById(R.id.myRecView);
        myRecordView.hasFixedSize();
        myRecordView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        arrayList = new ArrayList<>();

        firebaseRecordLoader = this;
        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    QuizPoints model = ds.getValue(QuizPoints.class);
                    arrayList.add(model);

                    System.out.println("snapshots child "+ ds.getValue());
                }
                adapter = new MyRecordAdapter(getApplicationContext(), arrayList);
                myRecordView.setAdapter(adapter);

                myRecordView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void onFirebaseLoadSuccess(List<QuizPoints> arrayList) {
        adapter = new MyRecordAdapter(getApplicationContext(), arrayList);
        myRecordView.setAdapter(adapter);

    }

    @Override
    public void onFirebaseLoadFailure(String message) {

    }
}
