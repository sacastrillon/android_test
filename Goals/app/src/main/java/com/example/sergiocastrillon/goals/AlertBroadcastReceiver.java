package com.example.sergiocastrillon.goals;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlertBroadcastReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent)
    {
        Toast.makeText(context,"Alarm",Toast.LENGTH_SHORT).show();

        Intent i = new Intent(context, MedicineAlert.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

    }
}
