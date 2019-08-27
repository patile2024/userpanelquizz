package com.squadtech.userpanelquizapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import com.squadtech.userpanelquizapp.Models.Questions;
import com.squadtech.userpanelquizapp.R;

import java.util.ArrayList;

public class PagerAdapterClass extends PagerAdapter {


    private Context context;

    public ArrayList<Questions> questionsArrayList;


    public PagerAdapterClass(Context context, ArrayList<Questions> questionsArrayList) {
        this.context = context;
        this.questionsArrayList = questionsArrayList;
        System.out.println("My array is "+ questionsArrayList);
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewpaggerlayout, null);


        TextView questxt = view.findViewById(R.id.questiontxt);

        TextView option1 = view.findViewById(R.id.option1);
        TextView option2 = view.findViewById(R.id.option2);
        TextView option3 = view.findViewById(R.id.option3);
        TextView option4 = view.findViewById(R.id.option4);


        System.err.println("my data"+questionsArrayList.get(position).getQuestion());



        questxt.setText(questionsArrayList.get(position).getQuestion());
        option1.setText(questionsArrayList.get(position).getOpt1());
        option2.setText(questionsArrayList.get(position).getOpt2());
        option3.setText(questionsArrayList.get(position).getOpt3());
        option4.setText(questionsArrayList.get(position).getOpt4());

        container.addView(view);

        return view;
    }
    /*
    This callback is responsible for destroying a page. Since we are using view only as the
    object key we just directly remove the view from parent container
    */
    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        container.removeView((View) view);
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