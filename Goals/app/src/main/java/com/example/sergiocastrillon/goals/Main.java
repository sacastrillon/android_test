package com.example.sergiocastrillon.goals;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends AppCompatActivity{
    Button btnMedicine_Alert;
    Button btnMobility_Check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMedicine_Alert=(Button) findViewById(R.id.btnAlert);
        btnMobility_Check=(Button) findViewById(R.id.btnMobilityCheck);

        btnMedicine_Alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main.this,Set_Time.class);
                startActivity(intent);

            }
        });

        btnMobility_Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main.this,Mobility_Chek.class);
                startActivity(intent);
            }
        });




    }



}
