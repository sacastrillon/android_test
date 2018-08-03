package com.example.sergiocastrillon.goals;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnMedicine_Alert;
    Button btnMobility_Check;
    Button btnLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMedicine_Alert=(Button) findViewById(R.id.btnAlert);
        btnMobility_Check=(Button) findViewById(R.id.btnMobilityCheck);
        btnLocation=(Button) findViewById(R.id.btnLocation);

        btnMedicine_Alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SetTime.class);
                startActivity(intent);

            }
        });

        btnMobility_Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MobilityCheck.class);
                startActivity(intent);
            }
        });

        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, GeofenceActivity.class);
                startActivity(intent);
            }
        });
    }
}
