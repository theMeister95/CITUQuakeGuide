package com.quakeguide.user.cit_uquakeguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button game, alarm, contacts, pathway, sms, guide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = (Button)findViewById(R.id.gameBtn);
        alarm = (Button)findViewById(R.id.alarmBtn);
        sms = (Button)findViewById(R.id.smsBtn);
        contacts = (Button)findViewById(R.id.contactBtn);
        pathway = (Button)findViewById(R.id.pathwayBtn);
        guide = (Button)findViewById(R.id.guidelinesBtn);

        alarm.setOnClickListener(this);
        contacts.setOnClickListener(this);
        sms.setOnClickListener(this);
        guide.setOnClickListener(this);
        pathway.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.alarmBtn:
                startActivity(new Intent("com.quakeguide.user.cit_uquakeguide.alarm"));
                break;
            case R.id.contactBtn:
                startActivity(new Intent("com.quakeguide.user.cit_uquakeguide.contacts"));
                break;
            case R.id.smsBtn:
                startActivity(new Intent("com.quakeguide.user.cit_uquakeguide.sms"));
                break;
            case R.id.pathwayBtn:
                startActivity(new Intent("com.quakeguide.user.cit_uquakeguide.firstFloor"));
                break;
            case R.id.guidelinesBtn:
                startActivity(new Intent("com.quakeguide.user.cit_uquakeguide.guidelines"));
                break;
        }
    }
}
