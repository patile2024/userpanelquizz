package com.squadtech.userpanelquizapp.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.squadtech.userpanelquizapp.MainActivity;
import com.squadtech.userpanelquizapp.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    public void ReferToProfile(View view)
    {
        startActivity(new Intent(getApplicationContext(),profileSetting.class));
    }

    public void ReferToCreditRecord(View view)
    {

    }

    public void backtoDashboard(View view)
    {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
