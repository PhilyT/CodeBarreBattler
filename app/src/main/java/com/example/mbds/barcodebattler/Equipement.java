package com.example.mbds.barcodebattler;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by deptinfo on 13/01/2018.
 */

public class Equipement implements Parcelable {
    public int Id;
    public String Nom;
    public Bitmap Image;
    public int Point;
    public Attribut Attribut;
    public int CreatureID ;

    public Equipement(String nom, Bitmap image, int point, String attribut){
        Nom = nom;
        Image = image;
        Point = point;
        Attribut = Attribut.valueOf(attribut);
    }
    public Equipement(String nom, int point, String attribut){
        Nom = nom;

        Point = point;
        Attribut = Attribut.valueOf(attribut);
    }
    public Equipement(){

    }
public Equipement(String nom, Bitmap image , int point , int creatureID ){
    Nom=nom;
    Image=image;
    Point = point ;
    CreatureID=creatureID;
}
    public Equipement(String nom, int point ){
        Nom=nom;
        Point = point ;

    }
    protected Equipement(Parcel in) {
        int[] data = new int[2];
        in.readIntArray(data);
        Id = data[0];
        Point = data[1];
        String[] valeurs = new String[2];
        in.readStringArray(valeurs);
        Nom = valeurs[0];
        Attribut = Attribut.valueOf(valeurs[1]);
        Image = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<Equipement> CREATOR = new Creator<Equipement>() {
        @Override
        public Equipement createFromParcel(Parcel in) {
            return new Equipement(in);
        }

        @Override
        public Equipement[] newArray(int size) {
            return new Equipement[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        String[] valeurs = new String[]{Nom, Attribut.getKey()};
        parcel.writeIntArray(new int[]{Id, Point});
        parcel.writeStringArray(valeurs);
        parcel.writeParcelable(Image, i);
    }
    public void setImage(Bitmap image){
        this.Image = image;

    }
}
