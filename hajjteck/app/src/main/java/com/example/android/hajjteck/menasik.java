package com.example.android.hajjteck;

import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static android.widget.Toast.LENGTH_LONG;

public class menasik extends AppCompatActivity  {


    private LocationManager lm;
    BufferedInputStream is;
    String line = null;
    String result = null;
    String[] cordN = {};
    String[] cordE = {};
    String[] name = {};

    ArrayList<Cordonne> arrayList = new ArrayList<Cordonne>();
    CostumListeView Clst;
    ListView lst;

    ListView lv;
    ArrayAdapter<String> adapter;
    String adresse = "http://192.168.8.103/loc.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menasik);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("مكان تواجدك        ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        lst = (ListView) findViewById(R.id.listeview);
        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        getData();

        for (int i = 0; i < name.length; i++) {
            Cordonne model = new Cordonne(cordE[i], cordN[i], name[i]);
            //bind all strings in an array
            arrayList.add(model);
        }

        //pass results to listViewAdapter class
        Clst = new CostumListeView(this, arrayList);

        //bind the adapter to the listview
        lst.setAdapter(Clst);



    }

    private void getData() {
        try{

            URL url=new URL(adresse);

            HttpURLConnection con=(HttpURLConnection)url.openConnection();

            con.setRequestMethod("GET");
            is=new BufferedInputStream(con.getInputStream());


        }
        catch (Exception ex)
        {

            Toast.makeText(this,"verifier votre connexion puis réessayer", LENGTH_LONG).show();

        }
        //content
        try{

            BufferedReader br=new BufferedReader(new InputStreamReader(is) );
            StringBuilder sb=new StringBuilder();
            while ((line=br.readLine())!=null){
                sb.append(line+"\n");

            }
            is.close();
            result=sb.toString();

        }
        catch (Exception ex)
        {
            ex.getStackTrace();

        }
        try{
            JSONArray ja=new JSONArray(result);
            JSONObject jo=null;

            cordN=new String[ja.length()];
            cordE=new String[ja.length()];
            name=new String[ja.length()];


            for(int i=0;i<=ja.length();i++) {
                jo = ja.getJSONObject(i);

                cordE[i] = jo.getString("cordE");
                cordN[i] = jo.getString("cordN");
                name[i] = jo.getString ("name");
            }

        }
        catch (Exception ex)
        {
            ex.getStackTrace();
        }


    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();

        return true ;
    }

}
