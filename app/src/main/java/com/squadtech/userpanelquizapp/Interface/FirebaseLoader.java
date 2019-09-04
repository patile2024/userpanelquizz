package com.squadtech.userpanelquizapp.Interface;

import com.squadtech.userpanelquizapp.Models.Questions;

import java.util.List;

public interface FirebaseLoader {
    void onFirebaseLoadSuccess(List<Questions>questionsArrayList);

    void onFirebaseLoadFailure(String message);
}
