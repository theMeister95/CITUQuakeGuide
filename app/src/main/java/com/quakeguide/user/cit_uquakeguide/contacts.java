package com.quakeguide.user.cit_uquakeguide;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Created by Obenita on 22/1/2018.
 */

public class contacts extends AppCompatActivity{

    EditText nameTxt1, numberTxt1,nameTxt2, numberTxt2,nameTxt3, numberTxt3;
    Button save, back;
    OpenHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

        nameTxt1 = (EditText) findViewById(R.id.name1);
        numberTxt1 = (EditText) findViewById(R.id.number1);
        nameTxt2 = (EditText) findViewById(R.id.name2);
        numberTxt2 = (EditText) findViewById(R.id.number2);
        nameTxt3 = (EditText) findViewById(R.id.name3);
        numberTxt3 = (EditText) findViewById(R.id.number3);
        save = (Button) findViewById(R.id.saveBtn);
        back = (Button) findViewById(R.id.backBtn);

        helper = new OpenHelper(this);

        checkData();
        displayAll(111);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = nameTxt1.getText().toString();
                String number1 = numberTxt1.getText().toString();
                String name2 = nameTxt2.getText().toString();
                String number2 = numberTxt2.getText().toString();
                String name3 = nameTxt3.getText().toString();
                String number3 = numberTxt3.getText().toString();
                int ctr = 0;

                if(CheckNumber(number1)){
                    updateData(name1, number1, 1);
                    ctr += 1;
                }
                if(CheckNumber(number2)){
                    updateData(name2, number2, 2);
                    ctr += 10;
                }
                if(CheckNumber(number3)){
                    updateData(name3, number3, 3);
                    ctr += 100;
                }
                if(ctr > 0) {
                    Toast.makeText(contacts.this, "UPDATED", Toast.LENGTH_LONG).show();
                    displayAll(ctr);
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void updateData(String name, String contact, int id){
        //boolean insert =
                helper.updateData(id, name, contact);

        /*if(insert){
            Toast.makeText(contacts.this, "UPDATED", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(contacts.this, "NOT SUCCESS", Toast.LENGTH_LONG).show();
        }*/
    }

    public void displayAll(int control){
        Cursor data = helper.getData();
        String text, text1, text2;
        int ctr =0;

        while(data.moveToNext()){
            text = data.getString(0);
            text1 = data.getString(1);
            text2 = data.getString(2);
            ctr++;
            if(ctr == 1 && (control == 111 || control == 11 || control==1 || control == 101)) {
                nameTxt1.setText(text1);
                numberTxt1.setText(text2);
            }else if(ctr == 2  && (control == 111 || control == 11 || control==10 || control == 110)){
                nameTxt2.setText(text1);
                numberTxt2.setText(text2);
            }else if(ctr == 3  && (control == 111 || control == 110 || control==101 || control == 100)){
                nameTxt3.setText(text1);
                numberTxt3.setText(text2);
            }
            //Toast.makeText(MainActivity.this, text+text1+text2,Toast.LENGTH_LONG).show();
        }

    }

    public void checkData(){
        Cursor data = helper.getData();

        if(data.getCount() == 0){
            helper.addData("","",1);
            helper.addData("","",2);
            helper.addData("","",3);
        }

        if(data.getCount() == 1){
            helper.addData("","",2);
            helper.addData("","",3);
        }

        if(data.getCount() == 2){
            helper.addData("","",3);
        }
    }



    private boolean CheckNumber(String number){
        boolean res = true, check = true;
        char[] ascii = number.toCharArray();

        for (char ch : ascii) {
            if ((int) ch < 48 || (int) ch > 57) {
                res = false;
                break;
            }
        }
        if (number.length() == 0){
            check = true;
        }else if(number.length() == 9 && res == false){
            Toast.makeText(getApplicationContext(), "Numbers Only!", Toast.LENGTH_LONG).show();
            check = false;
        }else if(number.length() > 9 && res == false){
            Toast.makeText(getApplicationContext(), "Characters exceeded and numbers only!", Toast.LENGTH_LONG).show();
            check = false;
        }else if(number.length() > 9 && res == true) {
            Toast.makeText(getApplicationContext(), "Characters exceeded!", Toast.LENGTH_LONG).show();
            check = false;
        }else if(number.length() < 9 && res == false){
            Toast.makeText(getApplicationContext(), "Lacking number and numbers only!", Toast.LENGTH_LONG).show();
            check = false;
        }else if(number.length() < 9 && res == true) {
            Toast.makeText(getApplicationContext(), "Lacking number!", Toast.LENGTH_LONG).show();
            check = false;
        }
        return check;
    }

}
