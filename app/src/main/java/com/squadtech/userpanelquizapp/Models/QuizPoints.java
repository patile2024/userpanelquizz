package com.squadtech.userpanelquizapp.Models;

public class QuizPoints {

    String out_of_ten,out_of_fifty,out_of_thirty,out_of_hundred, submited_date , category ,total_marks,achived_marks ;

    public QuizPoints() {
    }

    public QuizPoints(String out_of_ten, String out_of_fifty, String out_of_thirty, String out_of_hundred, String submited_date, String category, String total_marks, String achived_marks) {
        this.out_of_ten = out_of_ten;
        this.out_of_fifty = out_of_fifty;
        this.out_of_thirty = out_of_thirty;
        this.out_of_hundred = out_of_hundred;
        this.submited_date = submited_date;
        this.category = category;
        this.total_marks = total_marks;
        this.achived_marks = achived_marks;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTotal_marks() {
        return total_marks;
    }

    public void setTotal_marks(String total_marks) {
        this.total_marks = total_marks;
    }

    public String getAchived_marks() {
        return achived_marks;
    }

    public void setAchived_marks(String achived_marks) {
        this.achived_marks = achived_marks;
    }

    public String getOut_of_ten() {
        return out_of_ten;
    }

    public void setOut_of_ten(String out_of_ten) {
        this.out_of_ten = out_of_ten;
    }

    public String getOut_of_fifty() {
        return out_of_fifty;
    }

    public void setOut_of_fifty(String out_of_fifty) {
        this.out_of_fifty = out_of_fifty;
    }

    public String getOut_of_thirty() {
        return out_of_thirty;
    }

    public void setOut_of_thirty(String out_of_thirty) {
        this.out_of_thirty = out_of_thirty;
    }

    public String getOut_of_hundred() {
        return out_of_hundred;
    }

    public void setOut_of_hundred(String out_of_hundred) {
        this.out_of_hundred = out_of_hundred;
    }

    public String getSubmited_date() {
        return submited_date;
    }

    public void setSubmited_date(String submited_date) {
        this.submited_date = submited_date;
    }
}
