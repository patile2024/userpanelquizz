package com.squadtech.userpanelquizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class SelectQuestions extends AppCompatActivity {

    private TextView pst10 , pst30 , pst50 , pst100;

    DatabaseReference myPoints ;
    FirebaseAuth mAuth;
    int pointsInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_questions);

        pst10 = (TextView) findViewById(R.id.pts10);
        pst30 = (TextView) findViewById(R.id.pts30);
        pst50 = (TextView) findViewById(R.id.pts50);
        pst100 = (TextView) findViewById(R.id.pts100);


        mAuth = FirebaseAuth.getInstance();

        myPoints = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getUid());


        Intent intent = getIntent();
        String logical = intent.getStringExtra("logical");
        String analytical = intent.getStringExtra("analytical");
        String general = intent.getStringExtra("general");
        String etea = intent.getStringExtra("etea");

        //values
        System.out.println("Value of Logical is " + logical);
        System.out.println("Value of analytical is " + analytical);
        System.out.println("Value of etea is " + etea);
        System.out.println("Value of general is " + general);


        final String point10 = pst10.getText().toString();
        final String point30 = pst30.getText().toString();
        final String point50 = pst50.getText().toString();
        final String point100 = pst100.getText().toString();

        if (logical != null && logical.equals("Logical")) {
            myPoints.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String totalPoint = dataSnapshot.child("total_points").getValue().toString();
                    pointsInt = Integer.parseInt(totalPoint);
                    System.out.println("My Total points are " + pointsInt);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


            {
                pst10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (pointsInt >= 10 && pointsInt != 0) {
                            Intent logIntent = new Intent(SelectQuestions.this, LogicalActivity.class);
                            logIntent.putExtra("10", "10");
                            startActivity(logIntent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Your Points are below the Selected Category", Toast.LENGTH_SHORT).show();

                        }

                    }
                });


                    pst30.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (pointsInt >= 30 && pointsInt != 0) {
                                Intent logIntent = new Intent(SelectQuestions.this, LogicalActivity.class);
                                logIntent.putExtra("30", "30");
                                startActivity(logIntent);
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Your Points are below the Selected Category", Toast.LENGTH_SHORT).show();

                            }

                        }
                    });


                    pst50.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (pointsInt >= 50 && pointsInt != 0) {
                            Intent logIntent = new Intent(SelectQuestions.this, LogicalActivity.class);
                            logIntent.putExtra("50", "50");
                            startActivity(logIntent);

                        } else {
                                Toast.makeText(getApplicationContext(), "Your Points are below the Selected Category", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
                    pst100.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (pointsInt >= 100 && pointsInt != 0) {

                                Intent logIntent = new Intent(SelectQuestions.this, LogicalActivity.class);
                                logIntent.putExtra("100", "100");
                                startActivity(logIntent);
                            } else {
                                Toast.makeText(getApplicationContext(), "Your Points are below the Selected Category", Toast.LENGTH_SHORT).show();


                            }
                        }
                    });

            }

            if (analytical != null && analytical.equalsIgnoreCase("Analytical")) {
                pst10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(SelectQuestions.this, "Analytical", Toast.LENGTH_SHORT).show();
                        Intent logIntent = new Intent(SelectQuestions.this, AnalyticalActivity.class);
                        logIntent.putExtra("10", "10");
                        startActivity(logIntent);

                    }
                });


                pst30.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent logIntent = new Intent(SelectQuestions.this, AnalyticalActivity.class);
                        logIntent.putExtra("30", "30");
                        startActivity(logIntent);

                    }
                });


                pst50.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent logIntent = new Intent(SelectQuestions.this, AnalyticalActivity.class);
                        logIntent.putExtra("50", "50");
                        startActivity(logIntent);

                    }
                });


                pst100.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent logIntent = new Intent(SelectQuestions.this, AnalyticalActivity.class);
                        logIntent.putExtra("100", "100");
                        startActivity(logIntent);

                    }
                });


            }
            if (general != null && general.equalsIgnoreCase("General")) {
                pst10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent logIntent = new Intent(SelectQuestions.this, GeneralActivity.class);
                        logIntent.putExtra("10", "10");
                        startActivity(logIntent);

                    }
                });


                pst30.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent logIntent = new Intent(SelectQuestions.this, GeneralActivity.class);
                        logIntent.putExtra("30", "30");
                        startActivity(logIntent);

                    }
                });


                pst50.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent logIntent = new Intent(SelectQuestions.this, GeneralActivity.class);
                        logIntent.putExtra("50", "50");
                        startActivity(logIntent);

                    }
                });


                pst100.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent logIntent = new Intent(SelectQuestions.this, GeneralActivity.class);
                        logIntent.putExtra("100", "100");
                        startActivity(logIntent);

                    }
                });

            }
            if (etea != null && etea.equalsIgnoreCase("ETEA")) {
                pst10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent logIntent = new Intent(SelectQuestions.this, EteaActivity.class);
                        logIntent.putExtra("10", "10");
                        startActivity(logIntent);

                    }
                });


                pst30.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent logIntent = new Intent(SelectQuestions.this, EteaActivity.class);
                        logIntent.putExtra("30", "30");
                        startActivity(logIntent);

                    }
                });


                pst50.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent logIntent = new Intent(SelectQuestions.this, EteaActivity.class);
                        logIntent.putExtra("50", "50");
                        startActivity(logIntent);

                    }
                });


                pst100.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent logIntent = new Intent(SelectQuestions.this, EteaActivity.class);
                        logIntent.putExtra("100", "100");
                        startActivity(logIntent);

                    }
                });
            }


            //


        }
    }

