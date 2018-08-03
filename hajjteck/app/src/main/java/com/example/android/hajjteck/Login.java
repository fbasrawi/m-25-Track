package com.example.android.hajjteck;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;

import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;

import java.io.File;

import app.ipsaous.com.espacemembretuto.myrequest.MyRequest;

public class Login extends AppCompatActivity {
    private Button btn_send;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    /*private ProgressBar pb;
    private TextInputLayout tl_email, tl_password;
    private RequestQueue queue;
    private MyRequest request;
    private Handler handler;
    private Session session;*/
    private static final int TAKE_PICTURE = 1;
    private Uri imageUri;
    Button photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        photo = (Button) findViewById(R.id.shoot);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();

            }
        });
/*
        Intent intent=getIntent();
        if (intent.hasExtra("REGISTER")){
            Toast.makeText(this, intent.getStringExtra("REGISTER"), Toast.LENGTH_SHORT).show();
        }

        btn_send = (Button) findViewById(R.id.btn_send);
        tl_email = (TextInputLayout) findViewById(R.id.tl_email);
        tl_password = (TextInputLayout) findViewById(R.id.tl_password);
        pb=(ProgressBar)findViewById(R.id.pb_loader);
        session=new Session(this);
        handler=new Handler();

        queue = VolleySingleton.getInstance(this).getRequestQueue();
        request = new MyRequest(this, queue);


        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = tl_email.getEditText().getText().toString().trim();
                final String password = tl_password.getEditText().getText().toString().trim();
//                password.setHintTextColor(android.graphics.Color.BLUE);

                pb.setVisibility(View.VISIBLE);
                if (email.length()>0 && password.length()>0) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            request.connection(email, password, new MyRequest.LoginCallback() {
                                @Override
                                public void onSuccess(String id,String nom, String email,String idcni) {
                                    pb.setVisibility(View.GONE);
                                  */ // session.insertUser(id,nom, email,idcni);
        btn_send = (Button) findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

/*
                                }

                                @Override
                                public void onError(String message) {
                                    pb.setVisibility(View.GONE);
                                    Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    },1000);

                    request.connection(email, password, new MyRequest.LoginCallback() {
                        @Override
                        public void onSuccess(String id,String nom, String email ,String idcni) {
                            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                            finish();

                        }

                        @Override
                        public void onError(String message) {
                            Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    Toast.makeText(Login.this, "Veuillez remplire les champs", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }*/
    }



    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
           Intent i = new Intent(this,Emergency.class);
           startActivity(i);
        }
    }



}
