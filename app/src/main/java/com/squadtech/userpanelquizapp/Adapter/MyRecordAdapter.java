package com.squadtech.userpanelquizapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squadtech.userpanelquizapp.Models.QuizPoints;
import com.squadtech.userpanelquizapp.R;

import java.util.List;

public class MyRecordAdapter extends RecyclerView.Adapter<MyRecordAdapter.ViewHolder>  {


    private Context mContext;
    private List<QuizPoints> myRecord;


    public MyRecordAdapter() {
    }

    public MyRecordAdapter(Context mContext, List<QuizPoints> myRecord) {
        this.mContext = mContext;
        this.myRecord = myRecord;

        System.out.println("Constructor view " + myRecord);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.recordview, parent, false);

        ViewHolder holder = new ViewHolder(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuizPoints model = myRecord.get(position);
        holder.cat.setText(model.getCategory());
        holder.total.setText(model.getTotal_marks());
        holder.achived.setText(model.getAchived_marks());
        holder.date.setText(model.getSubmited_date());


        System.out.println("Bind view " + myRecord.get(position));



    }

    @Override
    public int getItemCount() {
        return myRecord.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        public TextView cat,total, achived ,date;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cat = itemView.findViewById(R.id.categoryview);
            total = itemView.findViewById(R.id.totalpointView);
            achived = itemView.findViewById(R.id.achivedView);
            date = itemView.findViewById(R.id.dateView);

        }
    }

}
