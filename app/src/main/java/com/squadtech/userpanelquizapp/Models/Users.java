package com.squadtech.userpanelquizapp.Models;

public class Users {

    String registered_date, total_points ,user_dp,user_email, user_name,user_pass,user_phone ,user_uid;

    public Users() {
    }

    public Users(String registered_date, String total_points, String user_dp, String user_email, String user_name, String user_pass, String user_phone, String user_uid) {
        this.registered_date = registered_date;
        this.total_points = total_points;
        this.user_dp = user_dp;
        this.user_email = user_email;
        this.user_name = user_name;
        this.user_pass = user_pass;
        this.user_phone = user_phone;
        this.user_uid = user_uid;
    }

    public String getRegistered_date() {
        return registered_date;
    }

    public void setRegistered_date(String registered_date) {
        this.registered_date = registered_date;
    }

    public String getTotal_points() {
        return total_points;
    }

    public void setTotal_points(String total_points) {
        this.total_points = total_points;
    }

    public String getUser_dp() {
        return user_dp;
    }

    public void setUser_dp(String user_dp) {
        this.user_dp = user_dp;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_uid() {
        return user_uid;
    }

    public void setUser_uid(String user_uid) {
        this.user_uid = user_uid;
    }
}
