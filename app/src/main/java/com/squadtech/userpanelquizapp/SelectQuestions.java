package com.squadtech.userpanelquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SelectQuestions extends AppCompatActivity {

    private TextView pst10 , pst30 , pst50 , pst100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_questions);

        pst10 = (TextView)findViewById(R.id.pts10);
        pst30 = (TextView)findViewById(R.id.pts30);
        pst50 = (TextView)findViewById(R.id.pts50);
        pst100 = (TextView)findViewById(R.id.pts100);



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

            if (logical != null && logical.equals("Logical")){


                        pst10.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent logIntent = new Intent(SelectQuestions.this , LogicalActivity.class);
                                logIntent.putExtra("10" , "10");
                                startActivity(logIntent);

                            }
                        });


                    pst30.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent logIntent = new Intent(SelectQuestions.this , LogicalActivity.class);
                            logIntent.putExtra("30" , "30");
                            startActivity(logIntent);

                        }
                    });


                    pst50.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent logIntent = new Intent(SelectQuestions.this , LogicalActivity.class);
                            logIntent.putExtra("50" , "50");
                            startActivity(logIntent);

                        }
                    });


                    pst100.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent logIntent = new Intent(SelectQuestions.this , LogicalActivity.class);
                            logIntent.putExtra("100" , "100");
                            startActivity(logIntent);

                        }
                    });

            }

             if ( analytical != null && analytical.equalsIgnoreCase("Analytical") ) {
                pst10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(SelectQuestions.this, "Analytical", Toast.LENGTH_SHORT).show();
                        Intent logIntent = new Intent(SelectQuestions.this , AnalyticalActivity.class);
                        logIntent.putExtra("10" , "10");
                        startActivity(logIntent);

                    }
                });


                pst30.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent logIntent = new Intent(SelectQuestions.this , AnalyticalActivity.class);
                        logIntent.putExtra("30" , "30");
                        startActivity(logIntent);

                    }
                });


                pst50.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent logIntent = new Intent(SelectQuestions.this , AnalyticalActivity.class);
                        logIntent.putExtra("50" , "50");
                        startActivity(logIntent);

                    }
                });


                pst100.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent logIntent = new Intent(SelectQuestions.this , AnalyticalActivity.class);
                        logIntent.putExtra("100" , "100");
                        startActivity(logIntent);

                    }
                });


            }
             if (general != null && general.equalsIgnoreCase("General")){
                pst10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent logIntent = new Intent(SelectQuestions.this , GeneralActivity.class);
                        logIntent.putExtra("10" , "10");
                        startActivity(logIntent);

                    }
                });


                pst30.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent logIntent = new Intent(SelectQuestions.this , GeneralActivity.class);
                        logIntent.putExtra("30" , "30");
                        startActivity(logIntent);

                    }
                });


                pst50.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent logIntent = new Intent(SelectQuestions.this , GeneralActivity.class);
                        logIntent.putExtra("50" , "50");
                        startActivity(logIntent);

                    }
                });


                pst100.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent logIntent = new Intent(SelectQuestions.this , GeneralActivity.class);
                        logIntent.putExtra("100" , "100");
                        startActivity(logIntent);

                    }
                });

            }
             if ( etea != null && etea.equalsIgnoreCase("ETEA") ){
                pst10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent logIntent = new Intent(SelectQuestions.this , EteaActivity.class);
                        logIntent.putExtra("10" , "10");
                        startActivity(logIntent);

                    }
                });


                pst30.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent logIntent = new Intent(SelectQuestions.this , EteaActivity.class);
                        logIntent.putExtra("30" , "30");
                        startActivity(logIntent);

                    }
                });


                pst50.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent logIntent = new Intent(SelectQuestions.this , EteaActivity.class);
                        logIntent.putExtra("50" , "50");
                        startActivity(logIntent);

                    }
                });


                pst100.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent logIntent = new Intent(SelectQuestions.this , EteaActivity.class);
                        logIntent.putExtra("100" , "100");
                        startActivity(logIntent);

                    }
                });
            }



        //






    }
}
