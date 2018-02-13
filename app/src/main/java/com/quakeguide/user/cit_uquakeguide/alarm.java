package com.quakeguide.user.cit_uquakeguide;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

/**
 * Created by Obenita on 22/1/2018.
 */

public class alarm extends AppCompatActivity implements View.OnClickListener{

    Button sos, alarm, help, back;
    MediaPlayer helpMp, sosMp, alarmMp;

    private SeekBar volumeSeekbar = null;
    private AudioManager audioManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        sos = (Button)findViewById(R.id.sosBtn);
        alarm = (Button)findViewById(R.id.alarmBtn);
        help = (Button)findViewById(R.id.helpBtn);
        back = (Button)findViewById(R.id.backBtn);

        helpMp=  MediaPlayer.create(this, R.raw.help);
        sosMp=  MediaPlayer.create(this, R.raw.sos);
        alarmMp=  MediaPlayer.create(this, R.raw.alarm);

        helpMp.setLooping(true);
        sosMp.setLooping(true);
        alarmMp.setLooping(true);

        help.setOnClickListener(this);
        sos.setOnClickListener(this);
        alarm.setOnClickListener(this);
        back.setOnClickListener(this);

        initControls();

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.sosBtn:
                if(helpMp.isPlaying()) {
                    helpMp.pause();
                }
                if(alarmMp.isPlaying()){
                    alarmMp.pause();
                }
                sosMp.start();
                break;
            case R.id.alarmBtn:
                if(helpMp.isPlaying()) {
                    helpMp.pause();
                }
                if(sosMp.isPlaying()){
                    sosMp.pause();
                }
                alarmMp.start();
                break;
            case R.id.helpBtn:
                if(sosMp.isPlaying()) {
                    sosMp.pause();
                }
                if(alarmMp.isPlaying()){
                    alarmMp.pause();
                }
                helpMp.start();
                break;
            case R.id.backBtn:
                helpMp.release();
                sosMp.release();
                alarmMp.release();
                finish();
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        helpMp.release();
        sosMp.release();
        alarmMp.release();
        finish();
    }

    private void initControls()
    {
        try
        {
            volumeSeekbar = (SeekBar)findViewById(R.id.seekBar1);
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            volumeSeekbar.setMax(audioManager
                    .getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            volumeSeekbar.setProgress(audioManager
                    .getStreamVolume(AudioManager.STREAM_MUSIC));


            volumeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
            {
                @Override
                public void onStopTrackingTouch(SeekBar arg0)
                {
                }

                @Override
                public void onStartTrackingTouch(SeekBar arg0)
                {
                }

                @Override
                public void onProgressChanged(SeekBar arg0, int progress, boolean arg2)
                {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                            progress, 0);
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
