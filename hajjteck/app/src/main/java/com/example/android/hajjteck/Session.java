package com.example.android.hajjteck;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private final static String PREF="apps_prefs";
    private final static int PRIVATE=0;
    private final static String IS_LOGGED="is logged";
    private final static String EMAIL="email";
    private final static String NOMPAT="nom";
    private final static String IDCNI="idcni";
    private final static String ID="id";
    private Context context;



    public Session(Context context) {
        this.context = context;
        prefs = context.getSharedPreferences(PREF,PRIVATE);
        editor = prefs.edit();
    }
    public boolean islogged(){
        return prefs.getBoolean(IS_LOGGED, false);
    }

    public String getIdcni() {
        return prefs.getString(IDCNI,null);
    }

    public String getEmail() {
        return prefs.getString(EMAIL,null);
    }
    public String getId() {
        return prefs.getString(ID,null);
    }
 public String getNom(){

    return  prefs.getString(NOMPAT,null);
}
    public void insertUser(String id,String nom, String email,String idcni) {
        editor.putBoolean(IS_LOGGED,true);
        editor.putString(ID,id);
        editor.putString(NOMPAT,nom);
        editor.putString(IDCNI,idcni);
        editor.putString(EMAIL,email);
        editor.commit();
    }
    public void logout(){
        editor.clear().commit();
    }
}
