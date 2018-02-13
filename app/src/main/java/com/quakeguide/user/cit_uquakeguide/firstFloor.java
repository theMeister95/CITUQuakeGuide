package com.quakeguide.user.cit_uquakeguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class firstFloor extends AppCompatActivity implements View.OnClickListener{

    Button first, second, third, fourth, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_floor);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

        first = (Button)findViewById(R.id.fBtn);
        second = (Button)findViewById(R.id.sBtn);
        third = (Button)findViewById(R.id.tBtn);
        fourth = (Button)findViewById(R.id.frtBtn);
        back = (Button)findViewById(R.id.backBtn);

        first.setOnClickListener(this);
        second.setOnClickListener(this);
        third.setOnClickListener(this);
        fourth.setOnClickListener(this);
        back.setOnClickListener(this);

        first.setEnabled(false);
        second.setEnabled(true);
        third.setEnabled(true);
        fourth.setEnabled(true);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.fBtn:
                startActivity(new Intent("com.quakeguide.user.cit_uquakeguide.firstFloor"));
                break;
            case R.id.sBtn:
                startActivity(new Intent("com.quakeguide.user.cit_uquakeguide.secondFloor"));
                break;
            case R.id.tBtn:
                startActivity(new Intent("com.quakeguide.user.cit_uquakeguide.thirdFloor"));
                break;
            case R.id.frtBtn:
                startActivity(new Intent("com.quakeguide.user.cit_uquakeguide.fourthFloor"));
                break;
            default:
                break;
        }
        finish();
    }
}
