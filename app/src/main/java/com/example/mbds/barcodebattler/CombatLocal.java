package com.example.mbds.barcodebattler;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

        // Custom Composant
        setMenu();
        title.setText("Combat Local");
        pv1.setText("PV restant : "+creature1.PV);
        pv2.setText("PV restant : "+creature2.PV);
        nom1.setText(creature1.Nom);
        nom2.setText(creature2.Nom);
        image1.setImageBitmap(creature1.Image);
        image2.setImageBitmap(creature2.Image);
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