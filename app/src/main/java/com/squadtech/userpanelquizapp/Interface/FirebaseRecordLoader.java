package com.squadtech.userpanelquizapp.Interface;

import com.squadtech.userpanelquizapp.Models.Questions;
import com.squadtech.userpanelquizapp.Models.QuizPoints;

import java.util.List;

public interface FirebaseRecordLoader {

    void onFirebaseLoadSuccess(List<QuizPoints> arrayList);

    void onFirebaseLoadFailure(String message);
}

