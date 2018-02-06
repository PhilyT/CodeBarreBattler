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

import java.util.ArrayList;
import java.util.Arrays;

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
    Creature creatureSelected;

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
        Equipement[] equipements1 = new Equipement[]{
                new Equipement("baton", BitmapFactory.decodeResource(this.getResources(), R.mipmap.baton), 2, "Attaque"),
                new Equipement("bouclier", BitmapFactory.decodeResource(this.getResources(), R.mipmap.bouclier), 6, "Defense")
        };
        Equipement[] equipements2 = new Equipement[]{
                new Equipement("epee", BitmapFactory.decodeResource(this.getResources(), R.mipmap.epee), 6, "Attaque"),
                new Equipement("bouclier", BitmapFactory.decodeResource(this.getResources(), R.mipmap.bouclier), 6, "Defense")
        };
        Equipement[] equipements3 = new Equipement[]{
                new Equipement("casque", BitmapFactory.decodeResource(this.getResources(), R.mipmap.casque), 10, "Vie")
        };
        Creature[] items = new Creature[]{// Data for test
                new Creature("toto", 30, 4, 12, BitmapFactory.decodeResource(this.getResources(),
                        R.mipmap.archer_squelette),equipements1),
                new Creature("titi", 30, 8, 8, BitmapFactory.decodeResource(this.getResources(),
                        R.mipmap.archidiable),equipements2),
                new Creature("tata", 10, 17, 15, BitmapFactory.decodeResource(this.getResources(),
                        R.mipmap.archidiablotin), equipements3)
        };
        creatureSelected = items[0];
        String[] joueurs= new String[]{
                "marco",
                "polo",
                "fabio"
        };
        ArrayAdapter<Creature> adapter = new ArrayAdapter<Creature>(this, android.R.layout.simple_spinner_item, items);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, joueurs);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown_item);
        DataEquipement adapterEquipements1 = new DataEquipement(ChoixReseau.this, new ArrayList<Equipement>(Arrays.asList(creatureSelected.Equipements)));

        // Set Widget
        equipements.setAdapter(adapterEquipements1);
        choix.setAdapter(adapter);
        choix.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                creatureSelected = (Creature)parentView.getItemAtPosition(position);
                nom.setText(creatureSelected.Nom);
                pv.setText("PV : "+creatureSelected.getPV());
                attaque.setText("Attaque : "+creatureSelected.getAttaque());
                defense.setText("Defense : "+creatureSelected.getDefense());
                image.setImageBitmap(creatureSelected.Image);
                equipements.setAdapter(new DataEquipement(ChoixReseau.this, new ArrayList<Equipement>(Arrays.asList(creatureSelected.Equipements))));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        joueur.setAdapter(adapter2);
        title.setText("Combat Reseau - Choix");
    }
}
