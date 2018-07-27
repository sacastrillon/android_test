package com.example.sergiocastrillon.goals;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;



public class alert extends BroadcastReceiver  {
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Toast.makeText(context,"Alarm",Toast.LENGTH_LONG).show();
        /*MediaPlayer mediaPlayer = MediaPlayer.create(context,
               Settings.System.DEFAULT_RINGTONE_URI);
        mediaPlayer.start();*/
        Intent i = new Intent(context, Medicine_Alert.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

    }
}
