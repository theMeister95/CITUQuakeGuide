package com.quakeguide.user.cit_uquakeguide;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class guidelines extends AppCompatActivity{

    ViewPager viewPager;
    Button before, during, after;

    ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
    AfterViewPager afterViewPager = new AfterViewPager(this);
    DuringViewPager duringViewPager = new DuringViewPager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidelines);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        before = (Button)findViewById(R.id.beforeBtn);
        after = (Button)findViewById(R.id.afterBtn);
        during = (Button)findViewById(R.id.duringBtn);

        viewPager.setAdapter(viewPagerAdapter);

        before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setAdapter(viewPagerAdapter);
            }
        });

        after.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setAdapter(afterViewPager);
            }
        });

        during.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setAdapter(duringViewPager);
            }
        });


    }
}
