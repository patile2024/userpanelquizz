package com.squadtech.userpanelquizapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.squadtech.userpanelquizapp.Models.Questions;
import com.squadtech.userpanelquizapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class PagerAdapterClass extends PagerAdapter {


    private Context context;
    LayoutInflater mLayoutInflate;

    public List<Questions> questionsArrayList;
    public ArrayList<String> answersList = new ArrayList<String>();
    private boolean enabled;
    int localtime;
    String selected, correct;
    int selectedInt, correctInt;
    int counter = 0;
    SharedPreferences myPoints;
    SharedPreferences.Editor editor;
    ArrayList<Integer> timeArray = new ArrayList<Integer>();


    public PagerAdapterClass() {
    }

    public PagerAdapterClass(Context context, ArrayList<Questions> questionsArrayList) {
        this.context = context;

        mLayoutInflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.questionsArrayList = questionsArrayList;

        System.out.println("My array is " + questionsArrayList);
    }


    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = mLayoutInflate.inflate(R.layout.viewpaggerlayout, container, false);

        TextView questxt = (TextView) view.findViewById(R.id.questiontxt);
        final TextView option1 = (TextView) view.findViewById(R.id.option1);
        final TextView option2 = (TextView) view.findViewById(R.id.option2);
        final TextView option3 = (TextView) view.findViewById(R.id.option3);
        final TextView option4 = (TextView) view.findViewById(R.id.option4);
        final TextView answer = (TextView) view.findViewById(R.id.answer);


        System.err.println("my data" + questionsArrayList.get(position).getQuestion());
        questxt.setText(questionsArrayList.get(position).getQuestion());
        option1.setText(questionsArrayList.get(position).getOpt1());
        option2.setText(questionsArrayList.get(position).getOpt2());
        option3.setText(questionsArrayList.get(position).getOpt3());
        option4.setText(questionsArrayList.get(position).getOpt4());
        answer.setVisibility(View.GONE);
//        timer.setText(questionsArrayList.get(position).getTime());

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (answersList.size() > position && !answersList.get(position).equals("")) {
                    answersList.remove(position);
                }
                selected = option1.getText().toString();
                correct = answer.getText().toString();


                if (selected.equals(correct))
                    counter++;
                System.out.println("my points " + (++counter));


                SharedPreferences.Editor editor1 = context.getSharedPreferences("counter", MODE_PRIVATE).edit();


                editor1.putString("counter", String.valueOf(counter));

                editor1.apply();


                SharedPreferences preferences = context.getSharedPreferences("counter", MODE_PRIVATE);

                System.out.println("poing "+preferences.getString("counter", "zero"));


                answer.setVisibility(View.VISIBLE);
                answer.setText(questionsArrayList.get(position).getAnswer());
//                        answersList.add(position, option1.getText().toString());
                option1.setBackgroundColor(Color.GREEN);
                option2.setBackgroundResource(R.drawable.textinputs);
                option3.setBackgroundResource(R.drawable.textinputs);
                option4.setBackgroundResource(R.drawable.textinputs);

                //click disable
                option1.setClickable(false);
                option2.setClickable(false);
                option3.setClickable(false);
                option4.setClickable(false);
                System.out.println("Array list data " + answersList);
                Toast.makeText(context, "Cliked " + position, Toast.LENGTH_SHORT).show();


            }


        });
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (answersList.size() > position && !answersList.get(position).equals("")) {
                    answersList.remove(position);
                }
                selected = option1.getText().toString();
                correct = answer.getText().toString();


                if (selected.equals(correct))
                    counter++;


                SharedPreferences.Editor editor1 = context.getSharedPreferences("counter", MODE_PRIVATE).edit();
                editor1.putString("counter", String.valueOf(counter));

                editor1.apply();


                //  answersList.add(position, option2.getText().toString());
                answer.setText(questionsArrayList.get(position).getAnswer());
                answer.setVisibility(View.VISIBLE);
                option2.setBackgroundColor(Color.GREEN);
                option1.setBackgroundResource(R.drawable.textinputs);
                option3.setBackgroundResource(R.drawable.textinputs);
                option4.setBackgroundResource(R.drawable.textinputs);
                System.out.println("Array list data " + answersList);
                option1.setClickable(false);
                option2.setClickable(false);
                option3.setClickable(false);
                option4.setClickable(false);
                Toast.makeText(context, "Cliked " + position, Toast.LENGTH_SHORT).show();
            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (answersList.size() > position && !answersList.get(position).equals("")) {
                    answersList.remove(position);
                }

                selected = option1.getText().toString();
                correct = answer.getText().toString();


                if (selected.equals(correct))
                    counter++;

                SharedPreferences.Editor editor1 = context.getSharedPreferences("counter", MODE_PRIVATE).edit();
                editor1.putString("counter", String.valueOf(counter));

                editor1.apply();

                // answersList.add(position, option3.getText().toString());
                option3.setBackgroundColor(Color.GREEN);
                option2.setBackgroundResource(R.drawable.textinputs);
                option1.setBackgroundResource(R.drawable.textinputs);
                option4.setBackgroundResource(R.drawable.textinputs);
                answer.setText(questionsArrayList.get(position).getAnswer());
                answer.setVisibility(View.VISIBLE);
                option1.setClickable(false);
                option2.setClickable(false);
                option3.setClickable(false);
                option4.setClickable(false);
                System.out.println("Array list data " + answersList);

                Toast.makeText(context, "Cliked " + position, Toast.LENGTH_SHORT).show();
            }
        });
        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (answersList.size() > position && !answersList.get(position).equals("")) {
                    answersList.remove(position);
                }

                selected = option1.getText().toString();
                correct = answer.getText().toString();


                if (selected.equals(correct))
                    counter++;

                SharedPreferences.Editor editor1 = context.getSharedPreferences("counter", MODE_PRIVATE).edit();
                editor1.putString("counter", String.valueOf(counter));

                editor1.apply();

                //answersList.add(position, option4.getText().toString());
                option4.setBackgroundColor(Color.GREEN);
                option2.setBackgroundResource(R.drawable.textinputs);
                option3.setBackgroundResource(R.drawable.textinputs);
                option1.setBackgroundResource(R.drawable.textinputs);
                answer.setText(questionsArrayList.get(position).getAnswer());
                answer.setVisibility(View.VISIBLE);
                option1.setClickable(false);
                option2.setClickable(false);
                option3.setClickable(false);
                option4.setClickable(false);
                System.out.println("Array list data " + answersList);
                Toast.makeText(context, "Cliked " + position, Toast.LENGTH_SHORT).show();
            }
        });
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {

        ((ViewPager) container).removeView((View) view);

    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    /*
    Returns the count of the total pages
    */
    @Override
    public int getCount() {
        return questionsArrayList.size();
    }


}