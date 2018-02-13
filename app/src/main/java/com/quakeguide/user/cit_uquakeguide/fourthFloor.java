package com.quakeguide.user.cit_uquakeguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class fourthFloor extends AppCompatActivity implements View.OnClickListener {

    Button first, second, third, fourth, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_floor);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

        first = (Button)findViewById(R.id.firstBtn);
        second = (Button)findViewById(R.id.secondBtn);
        third = (Button)findViewById(R.id.thirdBtn);
        fourth = (Button)findViewById(R.id.fourthBtn);
        back = (Button)findViewById(R.id.backBtn);

        first.setOnClickListener(this);
        second.setOnClickListener(this);
        third.setOnClickListener(this);
        fourth.setOnClickListener(this);
        back.setOnClickListener(this);

        first.setEnabled(true);
        second.setEnabled(true);
        third.setEnabled(true);
        fourth.setEnabled(false);
    }
    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.firstBtn:
                startActivity(new Intent("com.quakeguide.user.cit_uquakeguide.firstFloor"));
                break;
            case R.id.secondBtn:
                startActivity(new Intent("com.quakeguide.user.cit_uquakeguide.secondFloor"));
                break;
            case R.id.thirdBtn:
                startActivity(new Intent("com.quakeguide.user.cit_uquakeguide.thirdFloor"));
                break;
            case R.id.fourthBtn:
                startActivity(new Intent("com.quakeguide.user.cit_uquakeguide.fourthFloor"));
                break;
            default:
                break;
        }
        finish();
    }
}
