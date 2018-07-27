package com.example.sergiocastrillon.goals;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Set_Time extends AppCompatActivity {

    Button btnSetTime;
    TimePicker tpTime;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set__time);

        btnSetTime=(Button)findViewById(R.id.btnSetTime);
        tpTime=(TimePicker)findViewById(R.id.tpTime);

        btnSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                calendar.set(
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_WEEK),
                        tpTime.getHour(),
                        tpTime.getMinute(),
                        0
                );
                setAlarm(calendar.getTimeInMillis());
            }
        });
    }


    private void setAlarm(long timeInMillis) {

        AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent=new Intent(this,alert.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,0,intent,0);
        alarmManager.setRepeating(AlarmManager.RTC,timeInMillis,
                AlarmManager.INTERVAL_DAY,pendingIntent);
        Toast.makeText(this,"Alarm sets",Toast.LENGTH_LONG).show();

    }
}
