package com.example.mbds.barcodebattler;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

/**
 * Created by Tom on 10/01/2018.
 */

public class Creature implements Parcelable {
    public int Id;
    public String Nom;
    public int PV;
    public int Attaque;
    public int Defense;
    public Bitmap Image;
    public Equipement[] Equipements;

    public Creature(){

    }

    public Creature(String nom, int pv, int defense, int attaque, Bitmap image, Equipement[]equipements){
        Nom = nom;
        PV = pv;
        Attaque = attaque;
        Defense = defense;
        Image = image;
        Equipements = equipements;
    }
    public Creature(String nom, int pv, int defense, int attaque){
        Nom = nom;
        PV = pv;
        Attaque = attaque;
        Defense = defense;

    }

    public int getPV(){
        int total = PV;
        for (Equipement equipement : Equipements){
            if (equipement.Attribut.equals(Attribut.Vie)){
                total = total + equipement.Point;
            }
        }
        return total;
    }

    public int getAttaque(){
        int total = Attaque;
        for (Equipement equipement : Equipements){
            if (equipement.Attribut.equals(Attribut.Attaque)){
                total = total + equipement.Point;
            }
        }
        return total;
    }

    public int getDefense(){
        int total = Defense;
        for (Equipement equipement : Equipements){
            if (equipement.Attribut.equals(Attribut.Defense)){
                total = total + equipement.Point;
            }
        }
        return total;
    }

    protected Creature(Parcel in) {
        Nom = in.readString();
        int[] data = new int[3];
        in.readIntArray(data);
        PV = data[0];
        Attaque = data[1];
        Defense = data[2];
        Image = in.readParcelable(Bitmap.class.getClassLoader());
        Parcelable[] dataEquipements = in.readParcelableArray(Equipement.class.getClassLoader());
        Equipements = Arrays.copyOf(dataEquipements, dataEquipements.length, Equipement[].class);
        Id = in.readInt();
    }

    public static final Creator<Creature> CREATOR = new Creator<Creature>() {
        @Override
        public Creature createFromParcel(Parcel in) {
            return new Creature(in);
        }

        @Override
        public Creature[] newArray(int size) {
            return new Creature[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Nom);
        dest.writeIntArray(new int[] {PV, Attaque, Defense});
        dest.writeParcelable(this.Image,0);
        dest.writeParcelableArray(Equipements, 1);
        dest.writeInt(Id);
    }

    @Override
    public String toString(){
        return Nom;
    }
    public void setImage(Bitmap image){
        this.Image = image;

    }
}

