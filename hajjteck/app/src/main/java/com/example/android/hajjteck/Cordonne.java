package com.example.android.hajjteck;

public class Cordonne {
    String cordE;
    String cordN;
    String name;

    //constructor
    public Cordonne(String cordE, String cordN, String name) {
        this.cordE = cordE;
        this.cordN = cordN;
        this.name = name;
    }

    //getters


    public String getCordE() {
        return this.cordE;
    }

    public String getCordN() {
        return this.cordN;
    }

    public String getName() {
        return this.name;
    }
}
