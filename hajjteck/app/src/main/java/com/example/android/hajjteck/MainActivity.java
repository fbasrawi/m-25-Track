package com.example.android.hajjteck;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button b1, b2, b3,wajha,gaze;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById((R.id.button2));
        b3 = (Button) findViewById((R.id.button3));
         wajha =(Button) findViewById(R.id.wajha);
         gaze = (Button) findViewById(R.id.gaze);
         gaze.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Uri gmmIntentUri = Uri.parse("geo:21.4080,39.8893?q=gas stations");
                 Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                 mapIntent.setPackage("com.google.android.apps.maps");
                 startActivity(mapIntent);
             }
         });
                 wajha.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Uri gmmIntentUri = Uri.parse("google.navigation:q=Mina,Saudi Arabia");
                 Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                 mapIntent.setPackage("com.google.android.apps.maps");
                 startActivity(mapIntent);
             }
         });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent profile= new Intent(MainActivity.this, profile.class);

                startActivity(profile);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent  manasik = new Intent(MainActivity.this, manasik2.class);

                startActivity(manasik);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent information = new Intent(MainActivity.this, Information.class);

                startActivity(information);
            }
        });
    }
}