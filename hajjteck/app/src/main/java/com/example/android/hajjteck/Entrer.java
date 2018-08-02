package com.example.android.hajjteck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Entrer extends AppCompatActivity {
   private TextView textView;
   private ImageView imageView;
    //TEST
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrer);

        textView =(TextView)findViewById(R.id.text_Splash);
        imageView =(ImageView)findViewById(R.id.logoSplash);
        final Intent i = new Intent(this,Login.class);
        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        textView.startAnimation(myanim);
        imageView.startAnimation(myanim);
        Thread timer = new Thread(){
            public void  run(){
                try {
                    sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };

        timer.start();
    }
}
