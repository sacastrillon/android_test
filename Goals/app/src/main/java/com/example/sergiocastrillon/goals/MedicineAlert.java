package com.example.sergiocastrillon.goals;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MedicineAlert extends AppCompatActivity {

    TextView txtDay;
    TextView txtHour;
    TextView txtDrugName;
    Button btnAccept;
    Button btnDecline;
    Calendar calendar = Calendar.getInstance();
    MediaPlayer mMediaPlayer;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_alert);

        context=this;

        txtDay=(TextView) findViewById(R.id.txtDay);
        txtHour=(TextView) findViewById(R.id.txthour);
        txtDrugName=(TextView) findViewById(R.id.txtDrugName);
        btnAccept=(Button) findViewById(R.id.btnAccept);
        btnDecline=(Button) findViewById(R.id.btnDismiss);

        txtDay.setText(day());
        txtHour.setText(hour());
        txtDrugName.setText("Dolex");
        sound();
        final Vibrator vibrator = (Vibrator)context.getSystemService(context.VIBRATOR_SERVICE);
        vibrator.vibrate(100000);

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMediaPlayer.stop();
                vibrator.cancel();
                finish();
            }
        });
    }

    @Override
    public void onBackPressed(){
        Toast.makeText(context,"You Are Not Allowed to Exit the App", Toast.LENGTH_SHORT).show();
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
}
