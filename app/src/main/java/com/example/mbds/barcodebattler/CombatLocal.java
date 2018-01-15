package com.example.mbds.barcodebattler;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class CombatLocal extends AppCompatActivity {

    TextView title;
    TextView pv1;
    TextView pv2;
    TextView nom1;
    TextView nom2;
    TextView attaqueLeft;
    TextView attaqueRigth;
    TextView potionLeft;
    TextView potionRigth;
    TextView fuirRigth;
    TextView fuirLeft;
    ImageView image1;
    ImageView image2;
    boolean tourCreature1;
    Creature creature1;
    Creature creature2;
    int potionCpt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat_local);
        // Initialize Composant
        title = (TextView)findViewById(R.id.title);
        pv1 = (TextView)findViewById(R.id.pv1);
        pv2 = (TextView)findViewById(R.id.pv2);
        nom1 = (TextView)findViewById(R.id.nom1);
        nom2 = (TextView)findViewById(R.id.nom2);
        image1 = (ImageView)findViewById(R.id.image1);
        image2 = (ImageView)findViewById(R.id.image2);
        attaqueLeft = (TextView)findViewById(R.id.attaqueLeft);
        potionLeft = (TextView)findViewById(R.id.potionLeft);
        fuirLeft = (TextView)findViewById(R.id.fuirLeft);
        attaqueRigth = (TextView)findViewById(R.id.attaqueRigth);
        potionRigth = (TextView)findViewById(R.id.potionRigth);
        fuirRigth = (TextView)findViewById(R.id.fuirRigth);

        // Initialize Data
        tourCreature1 = true;
        creature1 = new Creature("toto", 30 , 4, 12, BitmapFactory.decodeResource(this.getResources(),
                R.mipmap.archer_squelette));
        creature2 = new Creature("tata", 30 , 8, 8, BitmapFactory.decodeResource(this.getResources(),
                R.mipmap.archidiable));
        potionCpt = 30;

        // Custom Composant
        setMenu();
        title.setText("Combat Local");
        refreshComposant();
        nom1.setText(creature1.Nom);
        nom2.setText(creature2.Nom);
        image1.setImageBitmap(creature1.Image);
        image2.setImageBitmap(creature2.Image);
        attaqueLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attaquer(creature1, creature2);
            }
        });
        attaqueRigth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attaquer(creature2, creature1);
            }
        });
        fuirLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupFuir();
            }
        });
        fuirRigth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupFuir();
            }
        });
        potionLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utiliserPotion(creature1);
            }
        });
        potionRigth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utiliserPotion(creature2);
            }
        });
    }

    public void attaquer(Creature attaque, Creature defense){
        int jet = new Random().nextInt(6) +1;
        int domage = (attaque.Attaque + jet - defense.Defense);
        if(domage<=1){
            domage = 1; // pour faire au moins 1 de domage minimum en cas de trop grande defense de la part de l'adversaire
        }
        defense.PV = defense.PV - domage;
        if(defense.PV >0){
            refreshComposant();
            setTour();
        }else{
            refreshComposant();
            popupVictoire(attaque.Nom);
        }
    }

    public void utiliserPotion(Creature creature){
        if(potionCpt>=10){
            creature.PV = creature.PV + 10;
            potionCpt = potionCpt -10;
            if(potionCpt==0){
                potionLeft.setTextColor(Color.LTGRAY);
                potionRigth.setTextColor(Color.LTGRAY);
            }
            refreshComposant();
            setTour();
        }
        else if (potionCpt >0){
            creature.PV = creature.PV + potionCpt;
            potionCpt =0;
            potionLeft.setTextColor(Color.LTGRAY);
            potionRigth.setTextColor(Color.LTGRAY);
            refreshComposant();
            setTour();
        }
    }

    private void popupFuir(){
        AlertDialog.Builder builder = new AlertDialog.Builder(CombatLocal.this);

        builder.setMessage("Voulez vous abandonner le combat ? ")
                .setTitle("Fuir");
        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void popupVictoire(String nomCreature){
        AlertDialog.Builder builder = new AlertDialog.Builder(CombatLocal.this);

        builder.setMessage("Victoire de la créature : " + nomCreature)
                .setTitle("Victoire");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void setTour() {
        tourCreature1 = !tourCreature1;
        setMenu();
    }

    private void refreshComposant() {
        pv1.setText("PV restant : "+creature1.PV);
        pv2.setText("PV restant : "+creature2.PV);
    }

    private void setMenu(){
        if(tourCreature1){
            findViewById(R.id.menuRigth).setVisibility(View.INVISIBLE);
            findViewById(R.id.menuLeft).setVisibility(View.VISIBLE);
        }else {
            findViewById(R.id.menuLeft).setVisibility(View.INVISIBLE);
            findViewById(R.id.menuRigth).setVisibility(View.VISIBLE);
        }
    }
}
