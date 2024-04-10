package com.squadtech.userpanelquizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
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
import com.squadtech.userpanelquizapp.R;
import com.squadtech.userpanelquizapp.Transformer.DepthPageTransformer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import cn.iwgang.countdownview.CountdownView;

public class EteaActivity extends AppCompatActivity implements FirebaseLoader {
    DatabaseReference databaseReference;
    private CountdownView mCvCountdownView;

    FirebaseLoader firebaseLoader;
    public List<Questions> questionsArrayList = new ArrayList<>();
    ViewPager viewPager;
    PagerAdapterClass adapter;
    int pagenextnumber = 0;
    String get10pts,get30pts,get50pts,get100pts;

    DatabaseReference pointRef;

    int q10, q30, q50, q100;
    TextView timer;
    private Button nextBtn;
    TextView quizPoints;
    private Button submitBtn;
    String local;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etea);

        nextBtn = (Button)findViewById(R.id.nextBtn);
        mCvCountdownView = (CountdownView)findViewById(R.id.timer);

        databaseReference = FirebaseDatabase.getInstance().getReference("Questions").child("Categories").child("ETEA");

        viewPager = (ViewPager) findViewById(R.id.viewpaggerid);

        firebaseLoader = this;
        timer = (TextView)findViewById(R.id.timer);
        submitBtn = (Button)findViewById(R.id.subBtn);


        pointRef = FirebaseDatabase.getInstance().getReference("QuizPoints").child(FirebaseAuth.getInstance().getUid()).push();
        try {
            get10pts = getIntent().getStringExtra("val" );
            q10 = Integer.parseInt(get10pts);



            switch (q10){

                case 10 : {


                    local = "";
                    mCvCountdownView.start(420000);


                    mCvCountdownView.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
                        @Override
                        public void onEnd(CountdownView cv) {
                            nextBtn.setEnabled(false);
                            timer.setText("Times Up!");

                        }
                    });

                    submitBtn.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {

                            SharedPreferences preferences = getSharedPreferences("counter", MODE_PRIVATE);
                            local = preferences.getString("counter", "zero");


                            final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(EteaActivity.this);
                            final View mView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.customedialogbox, null);


                            Button confirmBtn = (Button)mView.findViewById(R.id.confirmBtn_ID);
                            TextView myMarks = (TextView)mView.findViewById(R.id.mypointstxt);



                            myMarks.setText("You have got "+local+" Marks");
                            // AlertDialog optionDialog = new AlertDialog.Builder(CreateOfferActivity.this).create();
                            builder.setView(mView);
                            final AlertDialog dialog = builder.create();

                            confirmBtn.setOnClickListener(new View.OnClickListener() {


                                @Override
                                public void onClick(View view) {


                                    SharedPreferences preferences = getSharedPreferences("counter", MODE_PRIVATE);
                                    local = preferences.getString("counter", "zero");

                                    HashMap<String , Object> pointsMap = new HashMap<>();
                                    System.out.println("val of local var "+ quizPoints.getText().toString() );
                                    String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                                    pointsMap.put("achived_marks" , local);
                                    pointsMap.put("submited_date", currentDate);
                                    pointsMap.put("total_marks", "10");
                                    pointsMap.put("category" ,"Logical");
                                    pointRef.setValue(pointsMap);
                                    dialog.dismiss();



                                }
                            });

                            dialog.show();
                            dialog.setCanceledOnTouchOutside(false);



                        }





                    });
                    break;
                }
                case 30 :{


                    local = "";

                    mCvCountdownView.start(900000);


//

                    mCvCountdownView.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
                        @Override
                        public void onEnd(CountdownView cv) {
                            nextBtn.setEnabled(false);
                            timer.setText("Times Up!");

                        }
                    });

                    submitBtn.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {

                            SharedPreferences preferences = getSharedPreferences("counter", MODE_PRIVATE);
                            local = preferences.getString("counter", "zero");


                            final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(EteaActivity.this);
                            final View mView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.customedialogbox, null);


                            Button confirmBtn = (Button)mView.findViewById(R.id.confirmBtn_ID);
                            TextView myMarks = (TextView)mView.findViewById(R.id.mypointstxt);



                            myMarks.setText("You have got "+local+" Marks");
                            // AlertDialog optionDialog = new AlertDialog.Builder(CreateOfferActivity.this).create();
                            builder.setView(mView);
                            final AlertDialog dialog = builder.create();

                            confirmBtn.setOnClickListener(new View.OnClickListener() {


                                @Override
                                public void onClick(View view) {


                                    SharedPreferences preferences = getSharedPreferences("counter", MODE_PRIVATE);
                                    local = preferences.getString("counter", "zero");

                                    HashMap<String , Object> pointsMap = new HashMap<>();
                                    System.out.println("val of local var "+ quizPoints.getText().toString() );
                                    String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                                    pointsMap.put("achived_marks" , local);
                                    pointsMap.put("submited_date", currentDate);
                                    pointsMap.put("total_marks", "10");
                                    pointsMap.put("category" ,"Logical");
                                    pointRef.setValue(pointsMap);
                                    dialog.dismiss();

                                }
                            });

                            dialog.show();
                            dialog.setCanceledOnTouchOutside(false);



                        }





                    });
                    break;
                }
                case 50 : {

                    mCvCountdownView.start(1500000);



                    mCvCountdownView.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
                        @Override
                        public void onEnd(CountdownView cv) {
                            nextBtn.setEnabled(false);
                            timer.setText("Times Up!");

                        }
                    });


                    submitBtn.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {

                            SharedPreferences preferences = getSharedPreferences("counter", MODE_PRIVATE);
                            local = preferences.getString("counter", "zero");


                            final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(EteaActivity.this);
                            final View mView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.customedialogbox, null);


                            Button confirmBtn = (Button)mView.findViewById(R.id.confirmBtn_ID);
                            TextView myMarks = (TextView)mView.findViewById(R.id.mypointstxt);



                            myMarks.setText("You have got "+local+" Marks");
                            // AlertDialog optionDialog = new AlertDialog.Builder(CreateOfferActivity.this).create();
                            builder.setView(mView);
                            final AlertDialog dialog = builder.create();

                            confirmBtn.setOnClickListener(new View.OnClickListener() {


                                @Override
                                public void onClick(View view) {


                                    SharedPreferences preferences = getSharedPreferences("counter", MODE_PRIVATE);
                                    local = preferences.getString("counter", "zero");

                                    HashMap<String , Object> pointsMap = new HashMap<>();
                                    System.out.println("val of local var "+ quizPoints.getText().toString() );
                                    String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                                    pointsMap.put("achived_marks" , local);
                                    pointsMap.put("submited_date", currentDate);
                                    pointsMap.put("total_marks", "10");
                                    pointsMap.put("category" ,"Logical");
                                    pointRef.setValue(pointsMap);
                                    dialog.dismiss();

                                }
                            });

                            dialog.show();
                            dialog.setCanceledOnTouchOutside(false);



                        }





                    });
                    break;
                }
                case 100: {

                    local = "";

                    mCvCountdownView.start(5400000);


//                        }
//

                    mCvCountdownView.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
                        @Override
                        public void onEnd(CountdownView cv) {
                            nextBtn.setEnabled(false);
                            timer.setText("Times Up!");

                        }
                    });
                    submitBtn.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {

                            SharedPreferences preferences = getSharedPreferences("counter", MODE_PRIVATE);
                            local = preferences.getString("counter", "zero");


                            final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(EteaActivity.this);
                            final View mView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.customedialogbox, null);


                            Button confirmBtn = (Button)mView.findViewById(R.id.confirmBtn_ID);
                            TextView myMarks = (TextView)mView.findViewById(R.id.mypointstxt);



                            myMarks.setText("You have got "+local+" Marks");
                            // AlertDialog optionDialog = new AlertDialog.Builder(CreateOfferActivity.this).create();
                            builder.setView(mView);
                            final AlertDialog dialog = builder.create();

                            confirmBtn.setOnClickListener(new View.OnClickListener() {


                                @Override
                                public void onClick(View view) {


                                    SharedPreferences preferences = getSharedPreferences("counter", MODE_PRIVATE);
                                    local = preferences.getString("counter", "zero");

                                    HashMap<String , Object> pointsMap = new HashMap<>();
                                    System.out.println("val of local var "+ quizPoints.getText().toString() );
                                    String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                                    pointsMap.put("achived_marks" , local);
                                    pointsMap.put("submited_date", currentDate);
                                    pointsMap.put("total_marks", "10");
                                    pointsMap.put("category" ,"Logical");
                                    pointRef.setValue(pointsMap);
                                    dialog.dismiss();

                                }
                            });

                            dialog.show();
                            dialog.setCanceledOnTouchOutside(false);

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
