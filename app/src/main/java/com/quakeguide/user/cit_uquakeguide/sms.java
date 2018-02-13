package com.quakeguide.user.cit_uquakeguide;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Obenita on 26/1/2018.
 */

public class sms extends AppCompatActivity implements View.OnClickListener{

    final int SEND_SMS_PERMISSION_REQUEST_CODE = 111;
    Button trap, safe, back;
    OpenHelper helper;

    String trapText = "I'm trapped at Cebu Institute of Technology-University in Science and Technology (ST) Building!";
    String safeText = "I'm safe at Cebu Institute of Technology-University!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

        trap = (Button) findViewById(R.id.trap);
        safe = (Button) findViewById(R.id.safe);
        back = (Button) findViewById(R.id.backBtn);

        trap.setOnClickListener(this);
        safe.setOnClickListener(this);
        back.setOnClickListener(this);

        helper = new OpenHelper(this);

        trap.setEnabled(false);
        safe.setEnabled(false);

        if (checkPermission(Manifest.permission.SEND_SMS)) {
            trap.setEnabled(true);
            safe.setEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},
                    SEND_SMS_PERMISSION_REQUEST_CODE);
        }

    }

    private boolean checkPermission(String permission) {
        int checkPermission = ContextCompat.checkSelfPermission(this, permission);
        return (checkPermission == PackageManager.PERMISSION_GRANTED);
    }

    private void sendSms(String message, String number) {
        if (!TextUtils.isEmpty(message) && !TextUtils.isEmpty(number)) {

            if (checkPermission(Manifest.permission.SEND_SMS)) {

                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage("09"+number, null, message, null, null);
            } else {
                Toast.makeText(sms.this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case SEND_SMS_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    trap.setEnabled(true);
                    safe.setEnabled(true);
                }
                return;
            }
        }
    }

    @Override
    public void onClick(View v) {
        Cursor data = helper.getData();
        int ctr = 0;

        switch (v.getId()) {
            case R.id.trap:
                while (data.moveToNext()) {
                    ctr++;
                    if(ctr <= 3){
                        sendSms(trapText, data.getString(2));
                    }
                }
                finish();
                
                break;
            case R.id.safe:
                while (data.moveToNext()) {
                    ctr++;
                    if(ctr <= 3){
                        sendSms(safeText, data.getString(2));
                    }
                }
                finish();
                break;
            case R.id.backBtn:
                finish();
                break;
            default:
                break;
        }
    }
}
