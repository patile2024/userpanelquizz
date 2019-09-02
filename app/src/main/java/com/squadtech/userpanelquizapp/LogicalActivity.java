package com.squadtech.userpanelquizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squadtech.userpanelquizapp.Adapter.PagerAdapterClass;
import com.squadtech.userpanelquizapp.Interface.FirebaseLoader;
import com.squadtech.userpanelquizapp.Models.Questions;
import com.squadtech.userpanelquizapp.Transformer.DepthPageTransformer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class LogicalActivity extends AppCompatActivity implements FirebaseLoader {

    DatabaseReference databaseReference;

    FirebaseLoader firebaseLoader;
    public List<Questions> questionsArrayList = new ArrayList<>();
    ViewPager viewPager;
    PagerAdapterClass adapter;
    int pagenextnumber = 0;
    String get10pts,get30pts,get50pts,get100pts;
    Button nextBtn ;
    Chronometer totalTime ;
    int q10, q30,q50,q100;
    TextView timer;
    TextView quizPoints;
    DatabaseReference pointRef;
    private Button submitBtn;
    String local;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logical);

        databaseReference = FirebaseDatabase.getInstance().getReference("Questions").child("Categories").child("Logical");

        viewPager = (ViewPager) findViewById(R.id.viewpaggerid);
        nextBtn = (Button)findViewById(R.id.nextBtn);
        firebaseLoader = this;
        timer = (TextView)findViewById(R.id.timer);
        submitBtn = (Button)findViewById(R.id.subBtn);

        quizPoints = (TextView)findViewById(R.id.quizPoints);

        pointRef = FirebaseDatabase.getInstance().getReference("QuizPoints").child(FirebaseAuth.getInstance().getUid()).push();

        try {
            get10pts = getIntent().getStringExtra("val" );
            q10 = Integer.parseInt(get10pts);



            switch (q10){

                case 10 : {
                    new CountDownTimer(420000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            timer.setText("" + millisUntilFinished / 1000);

                            SharedPreferences preferences = getSharedPreferences("counter", MODE_PRIVATE);
                            local = preferences.getString("counter", "zero");
                            quizPoints.setText(local);
                        }

                        public void onFinish() {
                            timer.setText("Times Up!");
                            nextBtn.setEnabled(false);

                        }
                    }.start();

                    submitBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            HashMap<String , Object> pointsMap = new HashMap<>();
                            System.out.println("val of local var "+ quizPoints.getText().toString() );
                            String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                            pointsMap.put("achived_marks" , quizPoints.getText().toString());
                            pointsMap.put("submited_date", currentDate);
                            pointsMap.put("total_marks", "10");
                            pointsMap.put("category" ,"Logical");
                            pointRef.setValue(pointsMap);
                        }
                    });
                    break;
                }
                case 30 :{
                    new CountDownTimer(900000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            timer.setText("" + millisUntilFinished / 1000);
                            SharedPreferences preferences = getSharedPreferences("counter", MODE_PRIVATE);

                            String local = preferences.getString("counter", "zero");
                            quizPoints.setText(local);
                        }

                        public void onFinish() {
                            timer.setText("Times Up!");
                            nextBtn.setEnabled(false);

                        }
                    }.start();
                    submitBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            HashMap<String , Object> pointsMap = new HashMap<>();
                            System.out.println("val of local var "+ quizPoints.getText().toString() );
                            String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                            pointsMap.put("achived_marks" , quizPoints.getText().toString());
                            pointsMap.put("submited_date", currentDate);
                            pointsMap.put("total_marks", "30");
                            pointsMap.put("category" ,"Logical");
                            pointRef.setValue(pointsMap);
                        }
                    });
                    break;
                }
                case 50 : {
                    new CountDownTimer(1500000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            timer.setText("" + millisUntilFinished / 1000);
                            SharedPreferences preferences = getSharedPreferences("counter", MODE_PRIVATE);

                            String local = preferences.getString("counter", "zero");
                            quizPoints.setText(local);
                        }

                        public void onFinish() {
                            timer.setText("Times Up!");
                            nextBtn.setEnabled(false);                        }
                    }.start();

                    submitBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            HashMap<String , Object> pointsMap = new HashMap<>();
                            System.out.println("val of local var "+ quizPoints.getText().toString() );
                            String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                            pointsMap.put("achived_marks" , quizPoints.getText().toString());
                            pointsMap.put("submited_date", currentDate);
                            pointsMap.put("total_marks", "50");
                            pointsMap.put("category" ,"Logical");
                            pointRef.setValue(pointsMap);
                        }
                    });
                    break;
                }
                case 100: {
                    new CountDownTimer(5400000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            timer.setText("seconds remaining: " + millisUntilFinished / 1000);
                            SharedPreferences preferences = getSharedPreferences("counter", MODE_PRIVATE);

                            String local = preferences.getString("counter", "zero");
                            quizPoints.setText(local);
                        }

                        public void onFinish() {
                            timer.setText("done!");
                            nextBtn.setEnabled(false);
                        }
                    }.start();

                    submitBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            HashMap<String , Object> pointsMap = new HashMap<>();
                            System.out.println("val of local var "+ quizPoints.getText().toString() );
                            String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                            pointsMap.put("achived_marks" , quizPoints.getText().toString());
                            pointsMap.put("submited_date", currentDate);
                            pointsMap.put("total_marks", "100");
                            pointsMap.put("category" ,"Logical");
                            pointRef.setValue(pointsMap);
                        }
                    });
                    break;
                }



            }
        }catch (Exception e){

        }
        loadData();
        viewPager.setPageTransformer(true, new DepthPageTransformer());
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

        try {

            switch (q10){
                case 10 : {
                    databaseReference.limitToFirst(10).addListenerForSingleValueEvent(new ValueEventListener() {
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

                    break;
                }
                case 30 : {
                    databaseReference.limitToFirst(30).addListenerForSingleValueEvent(new ValueEventListener() {
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
                    break;
                }

                case 50 :{
                    databaseReference.limitToFirst(50).addListenerForSingleValueEvent(new ValueEventListener() {
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
                    break;

                }
                case 100: {
                    databaseReference.limitToFirst(100).addListenerForSingleValueEvent(new ValueEventListener() {
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
                        break;
                }

                default: {
                    Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }

        }catch (Exception e){
            String getmessage ;

            Toast.makeText(this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onFirebaseLoadSuccess(List<Questions> questionsArrayList) {
        adapter = new PagerAdapterClass(this, (ArrayList<Questions>) questionsArrayList);
        viewPager.setAdapter(adapter);
    }

    public void next(View view) {

        viewPager.setCurrentItem(pagenextnumber + 1);

    }
    @Override
    public void onFirebaseLoadFailure(String message) {

        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }
}
