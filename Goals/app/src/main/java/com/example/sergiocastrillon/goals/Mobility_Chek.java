package com.example.sergiocastrillon.goals;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Mobility_Chek extends AppCompatActivity implements SensorEventListener {
    TextView txtDay;
    TextView txtHour;
    Calendar calendar = Calendar.getInstance();
    MediaPlayer mMediaPlayer;
    Context context;
    private SensorManager sensorManager;
    Sensor accelerometer;
    Vibrator vibrator;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobility__chek);

        context=this;

        txtDay=(TextView) findViewById(R.id.txtDay);
        txtHour=(TextView) findViewById(R.id.txthour);
        txtDay.setText(day());
        txtHour.setText(hour());

        sensorManager =(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);


        sound();
        vibrator = (Vibrator)context.getSystemService(context.VIBRATOR_SERVICE);
        vibrator.vibrate(100000);
    }


    @Override
    public void onBackPressed(){
        Toast.makeText(context,"You Are Not Allowed to Exit the App", Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_HOME) {
            Toast.makeText(context,"You Are Not Allowed to Exit the App", Toast.LENGTH_SHORT).show();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private String hour (){
        String texthour;
        String hour =String.valueOf(calendar.get(Calendar.HOUR));
        int minute=calendar.get(Calendar.MINUTE);
        String min;

        if (minute<10)
        {
            min="0"+String.valueOf(minute);

        }
        else
        {
            min=String.valueOf(minute);
        }
        texthour=hour+":"+min;
        return texthour;
    }

    private String day (){
        String day;
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek==1){
            day="Sunday";
        }
        else if (dayOfWeek==2)
        {
            day="Monday";
        }
        else if (dayOfWeek==3)
        {
            day="Tuesday";
        }
        else if (dayOfWeek==4)
        {
            day="Wednesday";
        }
        else if (dayOfWeek==5)
        {
            day="Thursday";
        }
        else if (dayOfWeek==6)
        {
            day="Friday";
        }
        else
        {
            day="Saturday";
        }
        return day;
    }

    private void sound()
    {
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        mMediaPlayer = MediaPlayer.create(this, soundUri);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                float speed = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 10000;

                if (speed > SHAKE_THRESHOLD) {

                    Toast.makeText(this, "Mobility Check Accepted", Toast.LENGTH_SHORT).show();
                    mMediaPlayer.stop();
                    vibrator.cancel();
                    finish();

                }

                last_x = x;
                last_y = y;
                last_z = z;


            }
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
