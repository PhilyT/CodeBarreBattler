package com.example.mbds.barcodebattler;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class ChoixReseau extends AppCompatActivity {

    TextView title;
    TextView nom;
    TextView pv;
    TextView attaque;
    TextView defense;
    TextView retour;
    Spinner choix;
    ListView equipements;
    Spinner joueur;
    Button lancer;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_reseau);

        // Init widget
        title =  (TextView) findViewById(R.id.title);
        nom =  (TextView) findViewById(R.id.nom);
        pv =  (TextView) findViewById(R.id.pv);
        attaque =  (TextView) findViewById(R.id.attaque);
        defense =  (TextView) findViewById(R.id.defense);
        retour =  (TextView) findViewById(R.id.retour);
        choix = (Spinner) findViewById(R.id.choix);
        joueur = (Spinner) findViewById(R.id.joueur);
        equipements = (ListView) findViewById(R.id.equipements);
        lancer = (Button) findViewById(R.id.lancer );
        image = (ImageView) findViewById(R.id.image);

        // Init data
        Creature[] items = new Creature[]{// Data for test
                new Creature("toto", 30, 4, 12, BitmapFactory.decodeResource(this.getResources(),
                        R.mipmap.archer_squelette)),
                new Creature("titi", 30, 8, 8, BitmapFactory.decodeResource(this.getResources(),
                        R.mipmap.archidiable)),
                new Creature("tata", 10, 20, 15, BitmapFactory.decodeResource(this.getResources(),
                        R.mipmap.archidiablotin))
        };
        String[] joueurs= new String[]{
                "marco",
                "polo",
                "fabio"
        };
        ArrayAdapter<Creature> adapter = new ArrayAdapter<Creature>(this, android.R.layout.simple_spinner_item, items);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, joueurs);

        // Set Widget
        choix.setAdapter(adapter);
        choix.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Creature creatureSelected = (Creature)parentView.getItemAtPosition(position);
                nom.setText(creatureSelected.Nom);
                pv.setText("PV : "+creatureSelected.PV);
                attaque.setText("Attaque : "+creatureSelected.Attaque);
                defense.setText("Defense : "+creatureSelected.Defense);
                image.setImageBitmap(creatureSelected.Image);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });
        joueur.setAdapter(adapter2);
        title.setText("Combat Reseau - Choix");
    }
}
