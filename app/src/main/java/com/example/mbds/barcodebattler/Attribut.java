package com.example.mbds.barcodebattler;

/**
 * Created by Tom on 05/02/2018.
 */

public enum Attribut {
    Defense("Defense"),
    Attaque("Attaque"),
    Vie("Vie");

    private String key;

    Attribut(String key){
        this.key = key;
    }

    public String getKey(){
        return key;
    }

    public String toString(){
        return key;
    }
}
